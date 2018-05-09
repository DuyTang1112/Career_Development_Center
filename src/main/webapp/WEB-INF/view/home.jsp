<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<html>
<head>
	<jsp:include page="header.jsp"></jsp:include>
	<title>Home</title>
	<style>
        #newsfeedbox{
            margin: 12px;
            padding: 10px;
            background-color:gainsboro;
            height:900px;
			overflow-y: scroll;
        }
        .post{
            background-color:lemonchiffon;
            padding:15px;
        }
    </style>
    
</head>
<body class="container">
	<jsp:include page="Navbar.jsp"></jsp:include>	
	<h1 class="jumbotron">${message}</h1>
	<sec:authorize access="isAuthenticated()">
		
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h6>
				Welcome, ${pageContext.request.userPrincipal.name}!
			</h6>
		</c:if>
	</sec:authorize>
	
	<br>
	<sec:authorize access="isAuthenticated()">
		<h1>Newsfeed</h1>
		<div id="newsfeedbox">
			<c:forEach items="${listPosts}" var="post" varStatus="i" >
		    	<div class="post">
		        	<h3 style="color: red;">${post.getHeader()}</h3>
		        	<h6>by ${names[i.index]} at ${post.getTime().toString()} </h6>
		        	<i>Message:</i>
		        	<pre >${post.getContents()}</pre>
		        	<c:if test="${not empty post.getImages()}">
					    <img src="<c:url value="/resources/${post.getImages()}"/>" alt="${post.getImages()}"
					    width="500" />
					</c:if>
		        	
		    	</div>	
		    	<br>	      
			</c:forEach>
		    
		</div>
	</sec:authorize>
	<jsp:include page="footer.jsp" />
</body>
</html>
