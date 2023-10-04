<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSP엥서 properties이 메세지를 사용할 수 있도록 하는 API -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/headCSS.jsp"></c:import>
</head>
<body id="page-top">
	    <!-- Page Wrapper -->
	    <div id="wrapper">
	    	<!-- sidebar -->
	    	<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>
	    	
	    	<div id="content-wrapper" class="d-flex flex-column">
	    		<div id="content">
	    		
	    		<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
	    		
		    		<div class="container-fluid">
		    				<!-- page 상세 내용 -->
		    				<h1>Welcome : <spring:message code="hello"></spring:message> </h1>
		    				<h1><spring:message code="hi"></spring:message></h1>
		    				<!-- key값 없을때는 코드자체가 출력 -->
		    				<sec:authorize access="isAuthenticated()">
		    				<sec:authentication property="name" var="name"></sec:authentication>
		    				<sec:authentication property="principal" var="vo"></sec:authentication>
		    				<!-- principal.username, vo.username -->
		    				<!-- 게시판할때 작성자  -->
		    				<h1><spring:message code="login.welcome" arguments="${name}"></spring:message></h1>
		    				</sec:authorize>
		    		</div>   		
	    		
	    		</div>	    		
	    		<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
	    	</div>
	    </div>  
	<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>