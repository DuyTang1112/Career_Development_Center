<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Upload your resume</title>
    
</head>
<body class="container">
	<jsp:include page="Navbar.jsp" />
	<h1>Upload a new resume</h1>
	<br>
	<!-- Title -->
	<!-- Text body -->
	<!-- form POST -->
	    <form method="POST" action="uploadRes" enctype="multipart/form-data">
	        Upload your resume:<input type="file" name="file" accept=".doc,.docx,.pdf">
	        <br>
	        <input type="submit" value="Upload new resume" class="btn-primary" />
	    </form>
	    
	    <div class="text-info">${msg}</div>
</body>
</html>