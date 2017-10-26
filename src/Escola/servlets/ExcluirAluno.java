package Escola.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Escola.dados.AlunoDAO;

import org.json.JSONException;

@WebServlet("/excluirAluno")
public class ExcluirAluno extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			AlunoDAO alDAO = new AlunoDAO();
			alDAO.excluirAluno(id);
			HttpServletResponse resposta = (HttpServletResponse) response;
			resposta.sendRedirect("Alunos.jsp");
			
		}catch(Exception ex) {
			HttpServletResponse resposta = (HttpServletResponse) response;
			resposta.sendRedirect("Falha.jsp?msg=" + ex.getMessage());
		}
	}
}
