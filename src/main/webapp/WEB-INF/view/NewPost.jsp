<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Make a post</title>
    <style>
        #inputHeader{
            font-size: 24px;
            width:95%;
        }
        #inputText{
            width:95%;
            height: 300px;
        }
    </style>
</head>
<body class="container">
	<jsp:include page="Navbar.jsp" />
	<h1>Make a post</h1>
	<br>
	<!-- Title -->
	<!-- Text body -->
	<!-- form POST -->
    <form method="POST" action="makePost" enctype="multipart/form-data">
        <input type="text" name="header" id="inputHeader" placeholder="Title"/>
        <textarea name="contents" id="inputText"> </textarea>
        <br>
        Upload images: <input type="file" name="file" accept="image/*">
        <br>
        <input type="submit" value="Submit Post for Approval" class="btn-primary" />
    </form>
	 
</body>
</html>