<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>Cadastro de aluno novo</h1>
</div>
<br>
<div class="container">
	<form id="cadastro" method="post" action="cadastrarAluno">
		<div class="row">
			<div class="col-md-4">
				<div class="input-group">
					<strong>Nome:</strong>
					<input type="text" name="nome" class="form-control" placeholder="Nome" id="nome" >
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<strong>Data de nascimento:</strong>
					<input type="text" name="dt_nascimento" class="form-control data" id="dt_nascimento" >
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-group">
					<strong>Gênero:</strong>
					<select name="genero" id="genero" class="form-control">
						<option value="">Selecione</option>
						<option value="M">Masculino</option>
						<option value="F">Feminino</option>
					</select>
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
				url: "cadastrarAluno",
				data: dados,
				dataType: "json",
				success: function(data){
					alert("Chamada feita com sucesso");
					console.log(data);
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