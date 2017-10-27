<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.AlunoDAO,Escola.dados.Aluno" %>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Edição de aluno</h1>
</div>

<%
	int id = Integer.parseInt(request.getParameter("id"));
	AlunoDAO dao = new AlunoDAO();
	Aluno aluno = dao.getAluno(id);
	if(aluno != null){
%>

<div class="container">
	<form id="cadastro" method="post" action="editarAluno">
		<div class="row">
			<div class="col-md-4">
			<input type="hidden" name="id" id="id" value="<%= aluno.id %> ">
				<div class="input-group">
					<strong>Nome:</strong>
					<input type="text" name="nome" class="form-control" placeholder="Nome" id="nome" value="<%=aluno.nome %>" >
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<strong>Data de nascimento:</strong>
					<input type="text" name="dt_nascimento" class="form-control data" id="dt_nascimento" value="<%=aluno.dt_nascimento_formatado %>" >
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<strong>Gênero:</strong>
					<select name="genero" id="genero" class="form-control">
						<option value="">Selecione</option>
						<option value="M" <% if(aluno.genero.equals("M")){ %>selected <% } %> >Masculino</option>
						<option value="F" <% if(aluno.genero.equals("F")){ %>selected <% } %>>Feminino</option>
					</select>
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
	<h1>
		Aluno não encontrado
	</h1>
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
				url: "editarAluno",
				data: dados,
				dataType: "json",
				success: function(data){
					alert(data.msg);
				},
				error: function(xhr,status,error){
					alert("Erro na chamada");
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
			},
			genero: {
				required: true,
			},
			dt_nascimento: {
				required: true,
			}		
		},
		
		messages: {
			nome: {
				required: "Nome deve ser preenchido",
				minlength: "Nome deve ter no mínimo 3 caracteres"
			},
			genero: {
				required: "Gênero deve ser selecionado",
			},
			dt_nascimento: {
				required: "Data de nascimento deve ser preenchido",
			}
		}
	});	 
 });
</script>

</body>
</html>