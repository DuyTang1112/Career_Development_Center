<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"></jsp:include>
    <title>Login Page</title>
    <style>
        input[type="submit"]{
            width: 100%;
            height: 100%;
        }
        .block{
            background-color:lightskyblue;
            border-left: 6px solid #ccc;
            border-radius: 5x;
            padding: 10px;
            display:inline-block;
        }
    </style>
</head>
<body class="container">
	<jsp:include page="Navbar.jsp"></jsp:include>
	<br>	
    <div class="block">
        <form method="POST" action="<c:url value='/j_spring_security_check' />">
            <table>
                <tr><th>Login</th></tr>
                <tr>
                    <td>Username: </td>
                    <td>
                        <input name="username"/>
                    </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td>
                        <input type="password" name="password" />
                    </td>
                </tr>
            </table>
            <br>
            <input type="submit" name="login" value="Login">
        </form>
        <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<br>
		<div class="bg-info">${msg}</div>
        <br>
        <br>
        <form method="POST" action="register">
            <table>
                <tr><th>Register</th></tr>
                <tr>
                    <td>Username: </td>
                    <td>
                        <input type="text" name="username">
                    </td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>First name: </td>
                    <td>
                        <input type="text" name="firstname">
                    </td>
                </tr>
                <tr>
                    <td>Last name: </td>
                    <td>
                        <input type="text" name="lastname">
                    </td>
                </tr>
                <tr>
                    <td>Major: </td>
                    <td>
                        <input type="text" name="major">
                    </td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td>
                        <input type="text" name="address">
                    </td>
                </tr>
                <tr>
                    <td>Phone: </td>
                    <td>
                        <input type="tel" name="phone">
                    </td>
                </tr>
                <tr>
                    <td>Email: </td>
                    <td>
                        <input type="email" name="email">
                    </td>
                </tr>
                <tr>
                	<td>Are you:</td>
                	<td>
                	<select name=roleid>
                	  <option value="4">Student </option>
					  <option value="2">Falculty member</option>
					  <option value="3">Recruiting officer </option>
					</select>
					</td>
                </tr>
                <tr>
                	<td>Which department?</td>
                	<td>
                	<select name=deptid>
                	  <option value="1">CPEG </option>
                	  <option value="2">ENG</option>
					  <option value="3">MATH</option>
					  <option value="4">CPSC</option>
					</select>
					</td>
                </tr>
            </table>
            <br>
            <input type="submit" name="register" value="Register">
        </form>
    </div>
    
</body>
</html>