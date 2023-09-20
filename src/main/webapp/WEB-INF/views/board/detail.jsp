<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

				<!-- 페이지 내용 -->
				<div class="container-fluid">
					<div class="row">
						<div>${boardVO.boardTitle}</div>
						<div>${boardVO.boardWriter}</div>
						<div>${boardVO.boardContents}</div>					
					</div>
					
					<div class="row">
						<c:forEach items="${boardVO.list}" var="f">
							<img alt="" src="../files/${board}/${f.fileName}">
						</c:forEach>
					
					</div>
		<%-- 			<h1>Detail Page</h1>
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>No</th>
											<th>Title</th>
											<th>Writer</th>
											<th>Date</th>
											<th>Hit</th>
										</tr>
									</thead>
									<tbody>										
											<tr>
												<td>${dto.boardNo}</td>
												<td>${dto.boardTitle}</td>
												<td>${dto.boardWriter}</td>
												<td>${dto.boardDate}</td>
												<td>${dto.boardHit}</td>
											</tr>
											<tr>
												<td colspan="5">${dto.boardContents}</td>
											</tr>									
									</tbody>
								</table>
							</div>
							 <a href="./update?boardNo=${dto.boardNo}" class="btn btn-primary btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-flag"></i>
                                        </span>
                                        <span class="text">수정</span>
                             </a>
                              <a href="./delete?boardNo=${dto.boardNo}" class="btn btn-primary btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-flag"></i>
                                        </span>
                                        <span class="text">삭제</span>
                             </a>
						</div>
									
							
						</div> --%>
					</div>
				</div>
			  <c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
			</div>
		</div>
		<c:import url="/WEB-INF/views/layout/footjs.jsp"></c:import>
</body>
</html>