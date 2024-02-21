<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" href="css/style.css"> <!-- CSSのリンク -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQueryのリンク -->
    <script src="js/script.js"></script> <!-- JavaScriptのリンク -->
</head>
<body>
    <div class="login-page">
        <div class="form">
            <form class="register-form" action="signup" method="post">
                <input type="text" id="signupUsername" name="username" placeholder="name"/>
                <input type="password" id="signupPassword" name="password" placeholder="password"/>
                <input type="text" id="email" name="email" placeholder="email address"/>
                <button>SIGN UP</button>
                <p class="message">Already registered? <a href="#" class="toggle-form">Sign In</a></p>
            </form>
            
            <form class="login-form" action="login" method="post">
                <input type="text" id="username" name="username" placeholder="username"/>
                <input type="password" id="password" name="password" placeholder="password"/>
                <button>LOGIN</button>
                <p class="message">Not registered? <a href="#" class="toggle-form">Create an account</a></p>
            </form>
        </div>
    </div>
    
    <script>
        $(document).ready(function() {
            // 初期設定
            $('.register-form').hide();

            // フォーム切り替え
            $('.toggle-form').click(function(){
                $('.login-form, .register-form').toggle();
            });
        });
    </script>
</body>
</html>
