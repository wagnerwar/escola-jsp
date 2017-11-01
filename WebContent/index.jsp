<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.TurmaDAO,Escola.dados.Turma,java.util.ArrayList,java.util.List, Escola.Cookie.CookieManager" %>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>
<%
// Verificando se usuário está logado
Cookie[] cookies = request.getCookies();
CookieManager manager = new CookieManager();
if(cookies != null){
	if(manager.checaUsuarioAutenticado(cookies) == false){
		response.sendRedirect("Login.jsp");
	}
}else{
	response.sendRedirect("Login.jsp");
}
%>
<div class="container bemvindo">
	<h1>Seja bemvindo <%=manager.getLogin() %></h1>
</div>


<jsp:include page="rodape.jsp"></jsp:include>

</body>
</html>