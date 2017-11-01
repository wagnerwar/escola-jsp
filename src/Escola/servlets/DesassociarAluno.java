package Escola.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import Escola.dados.Aluno;
import Escola.dados.AlunoDAO;
import Escola.dados.Turma;
import Escola.dados.TurmaDAO;

import org.json.JSONObject;
import org.json.JSONException;

@WebServlet("/desassociarAluno")
public class DesassociarAluno extends HttpServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException{
		HttpServletResponse resposta = (HttpServletResponse) response;
		try {
			int id_turma = Integer.parseInt(request.getParameter("id_turma"));
			int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
			TurmaDAO daoTurma = new TurmaDAO();
			AlunoDAO daoAluno = new AlunoDAO();
			Aluno aluno = daoAluno.getAluno(id_aluno);
			Turma turma = daoTurma.getTurma(id_turma);
			daoTurma.desassociarAluno(turma, aluno);
			resposta.sendRedirect("Turmas.jsp");
		}catch(Exception ex) {
			resposta.sendRedirect("Falha.jsp?msg=" + ex.getMessage());
		}
	}
}
