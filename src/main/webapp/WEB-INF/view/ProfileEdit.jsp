<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Profile</title>
    <style>
        input#inputAddress{
            width:70%;
        }
        input#inputPhone, input#inputEmail{
            width:300px;
        }
    </style>
</head>
<body class="container">
    <jsp:include page="Navbar.jsp" />
    <h1>Edit profile</h1>
    <!-- userid, firstname, lastname, major, address, phone, deptid, roleid, email, resume -->
    <form name="profileEditForm" action="edit" method="post">
	    <!-- Hidden field -->
	    <input type="hidden" name="userid" value="${info.getUserid()}"/>
	    <input type="hidden" name="resume" value="${info.getResume()}"/>
	    <input type="hidden" name="roleid" id="inputRole" value="${info.getRoleid()}" />
	    <sec:authorize access="hasRole('ROLE_OFFICER')">
			<input type="hidden" name="major" id="inputMajor" value="${info.getMajor()}" />
		</sec:authorize>
        <table class="table" id="user-edit">
            <col width="20%">
            <col width="80%">
            <!-- hide ID -->
            <tr>
                <th>ROLE</th>
                <td>${role}</td>
               
            </tr>
            <tr>
                <th>FIRSTNAME </th>
                <td><input name="firstname" id="inputFirstName" value="${info.getFirstname()}" /> </td>
            </tr>
            <tr>
                <th>LASTNAME</th>
                <td><input name="lastname" id="inputLastName" value="${info.getLastname()}"  /></td>
            </tr>
            <tr>
                <th>DEPARTMENT</th>
                <td>
                    <select name=deptid>
                        <option value="1">CPEG </option>
                        <option value="2">ENG</option>
                        <option value="3">MATH</option>
                        <option value="4">CPSC</option>
                    </select>
                    Current: ${deptname}
                </td>
            </tr>

			<sec:authorize access="hasRole('ROLE_STUDENT')">
            <tr>
                <th>MAJOR</th>
                <td><input name="major" id="inputMajor" value="${info.getMajor()}" /></td>
            </tr>
            </sec:authorize>
            
            <tr>
                <th>ADDRESS</th>
                <td><input name="address" id="inputAddress" value="${info.getAddress()}" /></td>
            </tr>
            <tr>
                <th>PHONE NUMBER</th>
                <td><input type="tel" name="phone" id="inputPhone" value="${info.getPhone()}"/></td>
            </tr>
            <tr>
                <th>EMAIL</th>
                <td><input type="email" name="email" id="inputEmail" value="${info.getEmail()}"/></td>
            </tr>
            
            <tr>
            	<th>DESCRIPTION</th>
            	<td>
            		<textarea rows="5" cols="50" name="description">${info.getDescription()}</textarea>
            	</td> 
            </tr>
            <sec:authorize access="hasRole('ROLE_STUDENT')">
			<tr>
                <th>RESUME</th>
                <td>
                	<a href="<c:url value="/resume/${info.getResume()}"/>">${info.getResume()}</a>
                </td>
            </tr>
				
			</sec:authorize>
        </table>
        <input type="submit" value="Submit" class="btn-primary"> 
    </form>
    
    <div class="text-info">${msg }</div>
    
</body>
<script>
</script>
</html>