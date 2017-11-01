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
import Escola.dados.TurmaDAO;

import org.json.JSONException;

@WebServlet("/excluirTurma")
public class ExcluirTurma extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			TurmaDAO dao = new TurmaDAO();
			dao.excluirTurma(id);
			HttpServletResponse resposta = (HttpServletResponse) response;
			resposta.sendRedirect("Turmas.jsp");
			
		}catch(Exception ex) {
			HttpServletResponse resposta = (HttpServletResponse) response;
			resposta.sendRedirect("Falha.jsp?msg=" + ex.getMessage());
		}
	}
}
