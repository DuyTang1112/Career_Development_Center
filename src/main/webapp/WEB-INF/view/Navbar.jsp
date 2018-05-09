<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/j_spring_security_logout" var="logoutUrl" />

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            
            <li class="active"><a href="/Spring411/home">Home</a></li>
			
			<sec:authorize access="isAnonymous()">
				<li><a href="/Spring411/auth/login">Login</a></li>
			</sec:authorize>
			
			<!-- For admin privileges -->
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="/Spring411/admin/approveUser">Approve Users</a></li>
				<li><a href="/Spring411/admin/approvePost">Approve Post</a></li>
			</sec:authorize>
			
			<!-- For student privileges -->
			<sec:authorize access="hasRole('ROLE_STUDENT')">
				<li><a href="/Spring411/student/uploadRes">Upload resume</a></li>
				
			</sec:authorize>
			
			<!-- For officer privilege -->
			<sec:authorize access="hasAnyRole('ROLE_OFFICER','ROLE_ADMIN')">
				<li><a href="/Spring411/recruit/search">Search</a></li>
			</sec:authorize>
			
			<!-- Common services and logout link  -->
			<sec:authorize access="isAuthenticated()">
				<li><a href="/Spring411/edit">Edit profile</a></li>
				<li><a href="/Spring411/chat">Chat</a></li>
				<li><a href="/Spring411/makePost">Make a Post</a></li>
				<li>
					<script>
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>
					<a href="javascript:formSubmit()">Logout</a>
				</li>
				
			</sec:authorize>
			
        </ul>
    </div>
</nav>
<form action="${logoutUrl}" method="post" id="logoutForm" >
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>