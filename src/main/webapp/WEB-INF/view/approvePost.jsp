<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Approve posts</title>
</head>

<body class="container">
	<jsp:include page="Navbar.jsp" />
	
	<h1>Pending posts:</h1>
	<table class="table" id="pending">
	    <tr>
	        <th>POST ID </th>
	        <th>USERNAME </th>
	        <th>TITLE</th>
	        <th>CONTENT</th>
	        <th>Images</th>
	        <th>IS APPROVED?</th>
	        
	    </tr>
	    <c:forEach items="${postList}" var="post" varStatus="i">
		      <tr id="${post.getPostid()}">
		         <td>${post.getPostid()}</td>
		         <td>${names[i.index]}</td>
		         <td>${post.getHeader()}</td>
		         <td>${post.getContents()}</td>
		         <td>
			         <c:if test="${not empty post.getImages()}" >
						    <img src="<c:url value="/resources/${post.getImages()}"/>" alt="${post.getImages()}"
						    width="100"/>
					 </c:if>
					 <c:if test="${empty post.getImages()}" >
					 	No images
					 </c:if>
				</td>
				<td>${post.getApproved()}</td>
				<c:if test="${not post.getApproved()}">
					<td><button onclick="approve(${post.getPostid()})" class="btn-success">Approve</button></td>
				</c:if>
		         <c:if test="${post.getApproved()}">
		         	<td><button onclick="deny(${post.getPostid()})" class="btn-danger">Delete</button></td>
		         </c:if>
		         
		      </tr>
      	</c:forEach>
	    
	</table>
	
	<div id="msg" class="bg-success"></div>
	
	
	</body>
	<script>
	    //just for initial testing. To be removed.

	    function approve(id){
	        //tr
	        var entity =  $("#"+id);
	        //alert(entity.html() + "Approved, thus removed");
	        
	        $.ajax({
                url: "approvePostAjax",
                method: "GET",
                data: { 
                    "id" : id
                },
            }).done(function( html ) {
                $( "#msg" ).html( html );
                
                entity.fadeOut(200, function(){
    	            $(this).remove();
    	        });
            });
	        
	        //TODO: add functionality to alert the server
	    }
	
	    function deny(id){
	        var entity =  $("#"+id);
	        $.ajax({
                url: "denyPostAjax",
                method: "GET",
                data: { 
                    "id" : id
                },
            }).done(function( html ) {
                $( "#msg" ).html( html );
                
                entity.fadeOut(200, function(){
    	            $(this).remove();
    	        });
            });
	        //TODO: add functionality to alert the server
	    }
	</script>
</html>