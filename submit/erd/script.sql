CREATE TABLE `users`
(
    `user_id`         VARCHAR(50)  NOT NULL COMMENT '아이디',
    `user_name`       VARCHAR(50)  NOT NULL COMMENT '이름',
    `user_password`   VARCHAR(200) NOT NULL COMMENT 'mysql password 사용',
    `user_birth`      VARCHAR(8)   NOT NULL COMMENT '생년월일 : 19840503',
    `user_auth`       VARCHAR(10)  NOT NULL COMMENT '권한: ROLE_ADMIN, ROLE_USER',
    `user_point`      INT          NOT NULL DEFAULT 1000000 COMMENT '기본 포인트: 1000000',
    `created_at`      DATETIME     NOT NULL COMMENT '가입 일자',
    `latest_login_at` DATETIME NULL DEFAULT NULL COMMENT '마지막 로그인 일자',
    CONSTRAINT `PK_USERS` PRIMARY KEY (`user_id`)
);

CREATE TABLE `categories`
(
    `category_id`   INT          NOT NULL AUTO_INCREMENT COMMENT '카테고리 ID',
    `category_name` VARCHAR(100) NOT NULL COMMENT '카테고리 이름',
    CONSTRAINT `PK_CATEGORIES` PRIMARY KEY (`category_id`)
);

CREATE TABLE `products`
(
    `product_id`    INT          NOT NULL AUTO_INCREMENT COMMENT '상품 아이디',
    `product_name`  VARCHAR(200) NOT NULL COMMENT '상품 이름',
    `product_image` VARCHAR(200) NOT NULL COMMENT '상품 이미지',
    `product_price` BIGINT       NOT NULL COMMENT '상품 가격',
    `description`   VARCHAR(200) NOT NULL COMMENT '상품 설명',
    `stock`         INT          NOT NULL COMMENT '재고',
    `category_id`   INT          NOT NULL COMMENT '카테고리 ID',
    CONSTRAINT `PK_PRODUCTS` PRIMARY KEY (`product_id`),
    CONSTRAINT `FK_PRODUCTS_CATEGORY` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
);

CREATE TABLE `addresses`
(
    `address_id` INT          NOT NULL AUTO_INCREMENT COMMENT '주소 ID',
    `address`    VARCHAR(200) NOT NULL COMMENT '주소',
    `user_id`    VARCHAR(50)  NOT NULL COMMENT '아이디',
    CONSTRAINT `PK_ADDRESSES` PRIMARY KEY (`address_id`),
    CONSTRAINT `FK_ADDRESSES_USER` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

CREATE TABLE `orders`
(
    `order_Id`    INT         NOT NULL AUTO_INCREMENT COMMENT '주문 ID',
    `quantity`    INT         NOT NULL COMMENT '수량',
    `total_price` BIGINT      NOT NULL COMMENT '총 가격',
    `order_date`  DATETIME NULL COMMENT '주문 날짜',
    `user_id`     VARCHAR(50) NOT NULL COMMENT '아이디',
    `product_id`  INT         NOT NULL COMMENT '상품 아이디',
    address_id    INT,

    CONSTRAINT `PK_ORDERS` PRIMARY KEY (`order_Id`),
    CONSTRAINT `FK_ORDERS_USER` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
    CONSTRAINT `FK_ORDERS_PRODUCT` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
    FOREIGN KEY (address_id) REFERENCES addresses(address_id) ON DELETE SET NULL
);
