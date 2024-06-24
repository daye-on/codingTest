<!DOCTYPE html>
<html xmlns:th="https://thymeleaf.org">
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<link href="assets/dist/css/bootstrap.min.css" rel="stylesheet"> <!-- Bootstrap core CSS -->
<link href="assets/dist/css/signin.css" rel="stylesheet"> <!-- Custom styles for this template -->

<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <style>
    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      -ms-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }
  </style>
</head>

<body class="text-center">
  <form class="form-signin" action="/login" method="post">
    <div class="m-3 font-weight-normal">
      <span>2024-1</span>
      <h2>SCH 코딩 역량 테스트</h2>
    </div>

    <div class="mt-5">
      <input type="text" id="inputEmail" name="userId" class="form-control" placeholder="학번" required autofocus>
      <input type="password" id="inputPassword" name="userPassword" class="form-control" placeholder="Password" required>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <!--<a href="/dev" class="btn btn-outline-secondary mt-1">dev</a>-->
    </div>
  </form>

  <script th:inline="javascript">
    $(document).ready(function () {
      let errorMsg = [[${error}]];
      if(errorMsg!=null) {
        alert(errorMsg);
        location.href="/";
      }

      let adminMsg = [[${admin}]];
      if(adminMsg!=null) location.href="/score";
    });
  </script>
</body>
</html>