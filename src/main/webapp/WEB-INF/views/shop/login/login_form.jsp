<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-6 col-sm-8">
            <div class="card shadow-lg border-0 rounded-lg">
                <div class="card-body p-4">
                    <h1 class="h3 mb-3 fw-bold text-center">로그인</h1>
                    <form method="post" action="/loginAction.do">
                        <div class="form-floating mb-3">
                            <input type="text" name="user_id" class="form-control" id="user_id" placeholder="회원 아이디"
                                   required>
                            <label for="user_id">회원 아이디</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" name="user_password" class="form-control" id="user_password"
                                   placeholder="비밀번호" required>
                            <label for="user_password">비밀번호</label>
                        </div>
                        <div class="d-grid">
                            <button class="btn btn-primary btn-lg" type="submit">Sign in</button>
                        </div>

                    </form>
                </div>
                <div class="card-footer text-center">
                    <p class="mt-3 mb-0 text-muted">© 2022-2024</p>
                </div>
            </div>
        </div>
    </div>
</div>
