<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.TurmaDAO,Escola.dados.Turma" %>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Edição de turma</h1>
</div>
<br>
<%
TurmaDAO dao = new TurmaDAO();
Turma turma = dao.getTurma(Integer.parseInt(request.getParameter("id")));
if(turma != null){
%>
<div class="container">
	<form id="cadastro" method="post" action="editarTurma">
		<input type="hidden" name="id" id="id" value="<%=turma.id %>" >
		<div class="row">
			<div class="col-md-4">
				<div class="input-group">
					<strong>Nome:</strong>
					<input type="text" name="nome" class="form-control" placeholder="Nome" id="nome" disabled="disabled" value="<%=turma.nome %>" >
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<strong>Descrição:</strong>
					<input type="text" name="descricao" class="form-control" id="descricao" value="<%=turma.descricao %>" >
				</div>
			</div>
			
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<button type="submit" class="btn btn-default"> <span class="glyphicon glyphicon-pencil"></span> Atualizar</button>
			</div>
		</div>
	</form>
</div>
<% }else{ %>
<div class="container">
	<h1>Nenhuma turma encontrada</h1>
</div>
<% } %>
<jsp:include page="rodape.jsp"></jsp:include>

<script type="text/javascript">
/*Implementando validação do formulário usando o plugin jquery-validation*/
 
$(document).ready(function(){
	
	$("#cadastro").submit(function(event){
		event.preventDefault();
		if($(this).valid()){
			var dados = $(this).serialize();
			
			$.ajax({
				url: "editarTurma",
				data: dados,
				dataType: "json",
				success: function(data){
					exibirMsg("SUCESSO!!", data.msg);
				},
				error: function(xhr,status,error){
					exibirMsg("ERRO!!","Erro na chamada");
					console.log(xhr);
				}
			});
			event.preventDefault();
		}
	});
	
	$("#cadastro").validate({		
		rules: {
			nome: {
				required: true,
				minlength: 3
			}		
		},
		
		messages: {
			nome: {
				required: "Nome deve ser preenchido",
				minlength: "Nome deve ter no mínimo 3 caracteres"
			}
		}
	});	 
 });
</script>


</body>
</html>