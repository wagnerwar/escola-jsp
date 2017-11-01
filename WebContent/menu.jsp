<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.TurmaDAO,Escola.dados.Turma,java.util.ArrayList,java.util.List, Escola.Cookie.CookieManager" %>

<nav class="navbar navbar-inverse navbar-fixed-top">
<%
// Verificando se usuário está logado
Cookie[] cookies = request.getCookies();
CookieManager manager = new CookieManager();
if(cookies != null){
	if(manager.checaUsuarioAutenticado(cookies) == false){
		response.sendRedirect("Login.jsp");
	}else{	
%>
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Escola Online</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">Home</a></li>
            
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="">Alunos <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="CadastroAluno.jsp">Cadastro</a></li>
                  <li><a href="Alunos.jsp">Listagem</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" style="">Turmas <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="CadastroTurma.jsp">Cadastro</a></li>
                  <li><a href="Turmas.jsp">Listagem</a></li>
                </ul>
            </li>
            <li><a href="Associar.jsp">Associar</a></li>
            <li><a href="deslogin">Sair</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
      <% } } %>
</nav>