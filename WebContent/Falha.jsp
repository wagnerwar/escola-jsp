<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>ERRO GENERAL!!!</h1>
	<br>
	<div class="row">
		${param.msg}
	</div>
</div>


<jsp:include page="rodape.jsp"></jsp:include>

</body>
</html>