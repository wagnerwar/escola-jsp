<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.TurmaDAO,java.util.ArrayList,java.util.List,Escola.dados.Turma"%>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Listagem de turmas</h1>
		<div class="row" >
		<div class="col-md-2">&nbsp;</div>
		<div class="col-md-8">
			<table class="table table-striped table-bordered table-responsive">
			<tr>
				<th>Nome</th>
				<th>Descrição</th>
				<th>Ações</th>
			</tr>
			<%
			TurmaDAO dao = new TurmaDAO();
			List<Turma> turmas = dao.getTurmas();
			for(Turma turma: turmas){
			%>
			<tr>
				<td><%=turma.nome %></td>
				<td><%=turma.descricao %></td>
				<td>
					<a href="EditarTurma.jsp?id=<%=turma.id %>">
						<button type="button" class="btn btn-success"><i class="glyphicon glyphicon-pencil"></i></button>
					</a>&nbsp;
					<a href="excluirTurma?id=<%=turma.id %>">
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