<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
  <div class="p-2">
    <form method="post" action="/signupAction.do">

      <h1 class="h3 mb-3 fw-normal">회원가입</h1>

      <div class="form-floating mb-2">
        <input type="text" name="user_id" class="form-control" id="user_id" placeholder="User ID" required>
        <label for="user_id">ID 입력</label>
      </div>

      <div class="form-floating mb-2">
        <input type="text" name="user_name" class="form-control" id="user_name" placeholder="Full Name" required>
        <label for="user_name">이름 입력</label>
      </div>

      <div class="form-floating mb-2">
        <input type="password" name="user_password" class="form-control" id="user_password" placeholder="Password" required>
        <label for="user_password">비밀번호 입력</label>
      </div>

      <div class="form-floating mb-2">
        <input type="text" name="user_birth" class="form-control" id="user_birth" placeholder="YYYY-MM-DD" required>
        <label for="user_birth">생년월일 (예: 19990101)</label>
      </div>

      <div class="form-floating mb-2">
        <select class="form-select" id="user_auth" name="user_auth" required>
          <option value="ROLE_USER">사용자</option>
          <option value="ROLE_ADMIN">관리자</option>
        </select>
        <label for="user_auth">권한</label>
      </div>

      <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">회원가입</button>

      <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

    </form>
  </div>
</div>
