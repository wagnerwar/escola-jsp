<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Escola.dados.AlunoDAO, Escola.dados.Aluno, java.util.ArrayList, java.util.List, Escola.dados.TurmaDAO, Escola.dados.Turma"%>

<div class="container">
<div class="row">
   <%
   TurmaDAO daoTurma = new TurmaDAO();
   AlunoDAO daoAluno = new AlunoDAO();
   String nome_turma = request.getParameter("nome_turma");
   Turma turma_pesquisa = new Turma();
   turma_pesquisa.nome = nome_turma;
   List<Turma> turmas = daoTurma.consultarTurmaPorNome(turma_pesquisa);
   if(turmas.size() > 0){
	   List<Aluno> alunos = daoAluno.getAlunosNaoAssociados(turmas.get(0));
	   if(alunos.size()> 0){
		   int x = 0;
		   %>
		   <input type="hidden" name="id_turma" value="<%=turmas.get(0).id %>" >
		   <% for(Aluno al: alunos){ %>
		    <div class="col-sm-6 col-md-4 col-lg-3 mt-4">
                <div class="card">
                    <div class="card-block">
                        <h4 class="card-title"><%=al.nome %></h4>
                        <div class="card-text">
                            Idade: <%=al.getIdade() %><br>
                            Gênero: <% if(al.genero.equals("M")){ %> Masculino <% }else{ %>Feminino<% } %> 
                        </div>
                    </div>
                    <div class="card-footer">
                        <span><i class=""> 	
                        	<section>  
							<h3>Selecione</h3>					  	
							<div class ="checkboxOne">		
							<input type ="checkbox" value ="<%=al.id %>" id="alunos[<%=x %>]"  name="alunos"/>
							<label for ="alunos[<%=x %>]"></label>  	
							</div>
							</section>
                        	
    					</i></span>
                    </div>
                </div>
            </div>
		   <% x++;} %>
		   <%
	   }
	   
   }else{
	   
   }
   %>
   </div>
   </div>
    