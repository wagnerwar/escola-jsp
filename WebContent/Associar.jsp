<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.TurmaDAO,Escola.dados.Turma,java.util.ArrayList,java.util.List"%>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Associação de turmas com aluno</h1>
</div>

<div class="row">
	<div class="col-md-4">
	&nbsp;
	</div>
	<div class="col-md-4">
		<div class="input-group">
			
			<input type="text" class="form-control" name="nm_turma" id="nm_turma" placeholder="Digite o nome da turma" list="turma_lista">
			
			<%
			TurmaDAO dao = new TurmaDAO();
			List<Turma> turmas = dao.getTurmas();
			if(turmas.size() > 0){
			%>
				<datalist id="turma_lista">
				<%
				
				for(Turma turma: turmas){
				%>
					<option><%=turma.nome %></option> 
				<% } %>
				</datalist>
			<% } %>
			<div class="input-group-btn">
				<button name="pesquisar" id="searchTurma" class="btn btn-default"> <i class="glyphicon glyphicon-search"></i> Pesquisar </button>
			</div>
		</div>
	</div>
	<div class="col-md-4">
	&nbsp;
	</div>
	
</div>

<jsp:include page="rodape.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchTurma").click(function(){
		
		
	});	
});

</script>

</body>
</html>