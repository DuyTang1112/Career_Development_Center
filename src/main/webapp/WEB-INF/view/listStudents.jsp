<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<jsp:include page="header.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<body>
<table>
      <thead>
        <tr>
           <td>Student Id</td>
           <td>Student Name</td>
           <td>Status</td>
        </tr>
      </thead>
      <c:forEach items="${students}" var="student">
      <tr>
         <td>${student.getUserid()}</td>
         <td>${student.getFirstname()}</td>
         <td>${student.getMajor()}</td>
      </tr>
      </c:forEach>
</table>
</body>
</html>