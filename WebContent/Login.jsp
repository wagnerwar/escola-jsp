<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.TurmaDAO,Escola.dados.Turma,java.util.ArrayList,java.util.List" %>
<!DOCTYPE html>
<html>

<jsp:include page="header.jsp"></jsp:include>

<body>

<jsp:include page="menu.jsp"></jsp:include>

<div class="container bemvindo">
	<h1>LOGIN E SENHA</h1>
</div>
<br>
<form name="frmLogin" method="POST" action="loginUsuario" id="frmLogin">
<div class="row">
	<div class="col-md-3">
		<div class="input-group">
			<strong>Login:</strong>
			<input type="text" name="login" class="form-control" placeholder="Login" id="login" >
		</div>
	</div>
	
	<div class="col-md-3">
		<div class="input-group">
			<strong>Senha:</strong>
			<input type="password" name="senha" class="form-control"  id="senha" >
		</div>
	</div>
		
</div>

<div class="row">
	<div class="col-md-12">
		<button type="submit" class="btn btn-default" id="logar"> <span class="glyphicon glyphicon-ok"></span> Logar-se</button>
	</div>
</div>

</form>
<jsp:include page="rodape.jsp"></jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	$("#logar").click(function(event){
		event.preventDefault();
		//alert("Houve clique");
		var campos = $("#frmLogin").serialize();
		console.log(campos);
		
		$.ajax({
			url: "loginUsuario",
			data: campos,
			type: "POST",
			dataType: "json",
			success: function(data){
				if(data.status == "OK"){
					exibirMsg("SUCESSO!", data.msg);
					location.href = "index.jsp";
				}else{
					exibirMsg("ERRO!", data.msg);	
				}				
			},
			error: function(xhr,status,error){
				exibirMsg("ERRO!", data.msg);
			}
		});
	});	
});

</script>
</body>
</html>