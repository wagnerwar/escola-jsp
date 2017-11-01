<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.AlunoDAO, Escola.dados.Aluno, java.util.ArrayList, java.util.List, Escola.dados.TurmaDAO, Escola.dados.Turma"%>

<div class="container">
	<div class="row">
	<div class="col-md-2">&nbsp;</div>
	<div class="col-md-8"><h2>Lista de alunos associados</h2></div>
	<div class="col-md-2">&nbsp;</div>
	</div>
	<div class="row">
		<div class="col-md-2">&nbsp;</div>
		<div class="col-md-8">
		<%
		TurmaDAO daoTurma = new TurmaDAO();
		AlunoDAO daoAluno = new AlunoDAO();
		int id_turma = Integer.parseInt(request.getParameter("id_turma"));
		Turma turma = daoTurma.getTurma(id_turma);
		List<Aluno> alunos = daoTurma.consultarAlunosAssociados(turma);
		
		if(alunos.size() > 0){   
		%>
			<table class="table table-striped table-bordered table-responsive">
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Data de nascimento</th>
					<th>Gênero</th>
					<th>Ações</th>
				</tr>
				
				<%for(Aluno aluno: alunos){ %>
					<tr>
					<td><%=aluno.id %></td>
					<td><%=aluno.nome %></td>
					<td><%=aluno.dt_nascimento_formatado %></td>
					<td><%if(aluno.genero == "M"){ %>Masculino<%}else{ %>Feminino<% } %></td>
					<td>
						<a href="desassociarAluno?id_turma=<%=turma.id %>&id_aluno=<%=aluno.id%>">
						<button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></button>
					</a>
					</td>
				</tr>
				<% } %>
				
			</table>
			<%}else{ %>
			<h1>Nenhum aluno associado</h1>
			<% } %>
		</div>
		<div class="col-md-2">&nbsp;</div>		
    </div>
</div>
    