<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="header.jsp"></jsp:include>
</head>
<body class="container">
	<jsp:include page="Navbar.jsp"></jsp:include>
	<h1>HTTP Status 403 - Access is denied</h1>


	<h2>You do not have permission to access this page!</h2>

	<jsp:include page="footer.jsp" />
</body>
</html>
