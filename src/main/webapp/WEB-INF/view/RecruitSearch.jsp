<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<%@page session="true"%>
<html lang="en">
<head>
    <jsp:include page="header.jsp" />
    <title>Find Students</title>
    <style>
        
        #search-box{
            /* background-color:rgb(253, 237, 205); */
            padding:15px;
            border-radius:5px;
            border-spacing:50px;
        }

        .student-unit{
            border: rgb(211, 127, 250) 3px solid;
            border-radius: 5px;
            width:100%;
            padding:10px;
            margin: 10px 0;
            border-radius:10px;
            
        }
		#student-list{
			height:50%;
			overflow-y: scroll;
		}
        .student-unit table{
            display:table;
            border-collapse: collapse;
            border-spacing: 0;
            table-layout: fixed;
        }
        table.recruit-info{
            width:100%;
            display:table;
            /* font-size:12px; */
        }
        table.recruit-info thead, tbody{
            width:100%;
        }
    </style>
</head>
<body class="container">
    <jsp:include page="Navbar.jsp" />

    
    <h2>Search for students</h2>

    <div id="search-box">
    	<form id="queryForm" action="search" method="post">
	        <table class="table-condensed recruit-info" id="recruit-search">
	            <!-- userid, firstname, lastname, major, address, phone, deptid, roleid, email, resume -->
	             <tr>
	                 <th><input class="form-control" name="querry" placeholder="Search here..."> </th>
	                 <th>Department:
	                     <select class="dropdown" name="deptid" id="inputDept">
	                         <option value="0"></option>
	                         <option value="1">CPEG</option>
	                         <option value="2">ENG</option>
	                         <option value="3">MATH</option>
	                         <option value="4">CPSC</option>
	                     </select>
	                 </th>
	                 
	                 
	                 <!-- below button submits form -->
	                 <th><input class="btn" id="buttonSearch" type="submit"></input></th>
	             </tr>
	             <tr>
	                 
	                 <th><input class="form-control" name="major" id="inputMajor" placeholder="Major"></th>
	                 
	                 <th></th>
	                 <!-- below button clears parameters -->
	                 <th><input class="btn" type="reset" value="Reset" /></th>
	             </tr>
	        </table>
        </form>
    </div>

    <hr>
	<c:if test="${not empty results  }">
	    <div id="student-list" class="recruit-info">
	    	<c:forEach items="${results}" var="student" varStatus="i" >
		        <div class="student-unit card bg-info">
		            <table class="table-condensed recruit-info">
		                <tr>
		                    <th>Name: ${student.getFirstname()} ${student.getLastname()}</th>
		                    
		                    <th>Address: ${student.getAddress()}</th>
		                    <th>Tel: ${student.getPhone()}</th>
		                </tr>
		                <tr>
		                    <th>Major: ${student.getMajor()}</th>
		                    <th>Email: ${student.getEmail()}</th>
		                    <td><a href="<c:url value="/resume/${student.getResume()}"/>">${student.getResume()}</a></td>
		                </tr>
		            </table>
		        </div>
	        </c:forEach>
	    </div>
    </c:if>    
                      
</body>
<script>
   

    $(document).ready(function(){
        //initialize values
        //console.log("this was ready");
       
    })
</script>
</html>