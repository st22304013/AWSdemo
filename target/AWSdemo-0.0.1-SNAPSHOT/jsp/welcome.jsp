<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ようこそ</title>
    <style>
        /* お好みのスタイルを記述 */
        body {
            font-family: "Roboto", sans-serif;
            background: #76b852;
        }

        .welcome-message {
            text-align: center;
            margin-top: 100px;
            font-size: 36px;
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
        }
        
         .user-info {
	        text-align: center;
	        margin-top: 50px; /* Adjust as needed */
	    }
	
	    table {
	        margin: 0 auto; /* This centers the table horizontally */
	        border-collapse: collapse;
	    }
	
	    table, th, td {
	        border: 1px solid #fff; /* Modify this border style as needed */
	        padding: 8px; /* Adjust padding as desired */
	        color: #fff; /* Text color */
	    }
	
	    th {
	        background-color: #d187c2; /* Header background color */
	    }
    </style>
</head>
<body>
    <div class="welcome-message">
	    おかえりなさい、<span style='color: #d187c2;'>${loggedInUser.username}</span>さん
	</div>
    
    <div class="user-info">
        <h2>ユーザー情報</h2>
        <table border="1">
            <tr>
                <th>ユーザー名</th>
                <th>パスワード</th>
                <th>メールアドレス</th>
            </tr>
            <c:if test="${not empty loggedInUser}">
                <tr>
                    <td>${loggedInUser.username}</td>
                    <td>${loggedInUser.password}</td>
                    <td>${loggedInUser.email}</td>
                </tr>
            </c:if>
        </table>
    </div>
</body>
</html>
