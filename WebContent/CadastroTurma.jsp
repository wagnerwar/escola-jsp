<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Cadastro de turma</h1>	
</div>
<br>
<div class="container">
	<form id="cadastro" method="post" action="cadastrarTurma">
		<div class="row">
			<div class="col-md-4">
				<div class="input-group">
					<strong>Nome:</strong>
					<input type="text" name="nome" class="form-control" placeholder="Nome" id="nome" >
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<strong>Descrição:</strong>
					<input type="text" name="descricao" class="form-control" id="descricao" >
				</div>
			</div>
			
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<button type="submit" class="btn btn-default"> <span class="glyphicon glyphicon-plus"></span> Cadastrar</button>
			</div>
		</div>
	</form>
</div>

<jsp:include page="rodape.jsp"></jsp:include>

<script type="text/javascript">
/*Implementando validação do formulário usando o plugin jquery-validation*/
 
$(document).ready(function(){
	
	$("#cadastro").submit(function(event){
		event.preventDefault();
		if($(this).valid()){
			var dados = $(this).serialize();
			
			$.ajax({
				url: "cadastrarTurma",
				data: dados,
				dataType: "json",
				success: function(data){
					exibirMsg("SUCESSO!!", data.msg);
					$("#cadastro")[0].reset();
				},
				error: function(xhr,status,error){
					exibirMsg("ERRO!!", "Erro na chamada");
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