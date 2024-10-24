<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10">
            <div class="card shadow-lg border-0 rounded-lg">
                <div class="card-body p-4">
                    <h1 class="h3 mb-3 fw-bold text-center">회원 정보 수정</h1>
                    <form method="post" action="/mypage/updateAction.do">

                        <div class="form-floating mb-3">
                            <input type="text" name="user_id" class="form-control" id="user_id"
                                   placeholder="회원 아이디는 변경할 수 없습니다." readonly required>
                            <label for="user_id">회원 아이디 (변경불가)</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" name="user_name" class="form-control" id="user_name" placeholder="회원 이름"
                                   required>
                            <label for="user_name">이름</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="password" name="user_password" class="form-control" id="user_password"
                                   placeholder="새로운 비밀번호">
                            <label for="user_password">새로운 비밀번호</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="생년월일"
                                   required>
                            <label for="user_birth">생년월일 (예: 19990101)</label>
                        </div>

                        <div class="form-floating mb-3">
                            <select class="form-select" id="user_auth" name="user_auth" required>
                                <option value="ROLE_USER">사용자</option>
                                <option value="ROLE_ADMIN">관리자</option>
                            </select>
                            <label for="user_auth">권한</label>
                        </div>

                        <div class="d-grid">
                            <button class="btn btn-primary btn-lg mt-4" type="submit">정보 수정</button>
                        </div>

                        <p class="mt-5 mb-0 text-center text-muted">© 2022-2024</p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
