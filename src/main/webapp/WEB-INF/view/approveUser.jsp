<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Approve user</title>
</head>

<body class="container">
	<jsp:include page="Navbar.jsp" />
	<!--id, username, approval, deny-->
	<h1>Pending users:</h1>
	<table class="table" id="pending">
	    <tr>
	        <th>ID </th>
	        <th>USERNAME </th>
	        <th>IS APPROVED?</th>
	        
	    </tr>
	    <c:forEach items="${loginList}" var="login">
		      <tr id="${login.getUserid()}">
		         <td>${login.getUserid()}</td>
		         <td>${login.getUsername()}</td>
		         <td>${login.getApproved()}</td>
		         <c:if test="${login.getApproved()}">
		         	<td><button onclick="deny(${login.getUserid()})" class="btn-danger">Delete</button></td>
		         </c:if>
		         <c:if test="${not login.getApproved()}">
		         	<td><button onclick="approve(${login.getUserid()})" class="btn-success">Approve</button></td>
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
                url: "approveAjax",
                method: "GET",
                data: { 
                    "id" : id
                },
            }).done(function( html ) {
                $( "#msg" ).html( html );
                
                entity.fadeOut(400, function(){
    	            $(this).remove();
    	        });
            });
	        
	        //TODO: add functionality to alert the server
	    }
	
	    function deny(id){
	        var entity =  $("#"+id);
	        $.ajax({
                url: "denyAjax",
                method: "GET",
                data: { 
                    "id" : id
                },
            }).done(function( html ) {
                $( "#msg" ).html( html );
                
                entity.fadeOut(400, function(){
    	            $(this).remove();
    	        });
            });
	        //TODO: add functionality to alert the server
	    }
	</script>
</html>