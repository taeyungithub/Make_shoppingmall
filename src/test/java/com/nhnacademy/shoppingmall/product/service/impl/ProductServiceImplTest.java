package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class ProductServiceImplTest {

    private ProductRepository productRepository ;
    private ProductService productService;
    private Product testProduct;


    @BeforeEach
    void setUp() {
        productRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
        testProduct = new Product(1, 1, "Test Product", "test_image.jpg", 10000L, "Test Description", 10);
    }

    @AfterEach
    void tearDown() {
        Mockito.verify(productRepository, Mockito.atLeast(0)).deleteByProductId(testProduct.getProductId());
    }

    @Test
    @DisplayName("Get Product by ID - Success")
    void getProduct_success() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(testProduct));
        Product product = productService.getProduct(testProduct.getProductId());
        assertEquals(testProduct, product);
    }

    @Test
    @DisplayName("Get Product by ID - Not Found")
    void getProduct_notFound() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(testProduct.getProductId()));
    }

    @Test
    @DisplayName("Save Product - Success")
    void saveProduct_success() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        Mockito.when(productRepository.save(any())).thenReturn(1);
        productService.saveProduct(testProduct);
        Mockito.verify(productRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Save Product - Already Exists")
    void saveProduct_alreadyExists() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(testProduct));
        assertThrows(ProductAlreadyExistsException.class, () -> productService.saveProduct(testProduct));
    }

    @Test
    @DisplayName("Update Product - Success")
    void updateProduct_success() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(testProduct));
        Mockito.when(productRepository.update(any())).thenReturn(1);
        productService.updateProduct(testProduct);
        Mockito.verify(productRepository, Mockito.times(1)).update(any());
    }

    @Test
    @DisplayName("Update Product - Not Found")
    void updateProduct_notFound() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(testProduct));
    }

    @Test
    @DisplayName("Delete Product - Success")
    void deleteProduct_success() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(testProduct));
        Mockito.when(productRepository.deleteByProductId(anyInt())).thenReturn(1);
        productService.deleteProduct(testProduct.getProductId());
        Mockito.verify(productRepository, Mockito.times(1)).deleteByProductId(anyInt());
    }

    @Test
    @DisplayName("Delete Product - Not Found")
    void deleteProduct_notFound() {
        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(testProduct.getProductId()));
    }

    @Test
    @DisplayName("Search Products by Name")
    void searchProductsByName() {
        Mockito.when(productRepository.findByName(anyString())).thenReturn(List.of(testProduct));
        List<Product> products = productService.searchProductsByName("Test");
        assertFalse(products.isEmpty());
    }

    @Test
    @DisplayName("Get Product Page List")
    void getProductPageList() {
        Page<Product> productPage = new Page<>(List.of(testProduct), 1);
        Mockito.when(productRepository.findAll(anyInt(), anyInt())).thenReturn(productPage);
        Page<Product> page = productService.getProductPageList(1, 1);
        assertEquals(1, page.getContent().size());
    }
}
