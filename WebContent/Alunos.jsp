<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.AlunoDAO,Escola.dados.Aluno,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Listagem de alunos</h1>
	<div class="row" >
		<div class="col-md-2">&nbsp;</div>
		<div class="col-md-8">
			<table class="table table-striped table-bordered table-responsive">
			<tr>
				<th>Nome</th>
				<th>Data de nascimento</th>
				<th>Gênero</th>
				<th>Ações</th>
			</tr>
			<%
			AlunoDAO dao = new AlunoDAO();
			List<Aluno> alunos = dao.getAlunos();
			for(Aluno aluno: alunos){
			%>
			<tr>
				<td><%=aluno.nome %></td>
				<td><%=aluno.dt_nascimento_formatado %></td>
				<% if(aluno.genero == "M"){ %>
					<td>Masculino</td>
				<%}else{ %>
					<td>Feminino</td>
				<%} %>
				<td>
					<a href="#>">
						<button type="button" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i></button>
					</a>
						&nbsp;
					<a href="excluirAluno?id=<%=aluno.id %>">
					<button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></button>
					</a>
				</td>
			</tr>
			<% } %>
		</table>
		</div>
		<div class="col-md-2">&nbsp;</div>
	</div>
</div>


<jsp:include page="rodape.jsp"></jsp:include>

</body>
</html>