package Escola.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import Escola.dados.Turma;
import Escola.dados.TurmaDAO;
import Escola.Exception.TurmaCadastradaException;

@WebServlet("/cadastrarTurma")
public class CadastrarTurma extends HttpServlet {
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		try {
			Turma turma = new Turma();
			turma.nome = request.getParameter("nome");
			turma.descricao = request.getParameter("descricao");
			TurmaDAO dao = new TurmaDAO();
			
			List<Turma> turmas = dao.consultarTurmaPorNome(turma);
			if(turmas.size() > 0) {
				throw new TurmaCadastradaException();
			}
			
			boolean retorno = dao.createTurma(turma);
			if(retorno == true) {
				out.println("<html>");
		        out.println("<body>");
		        out.println(" Turma cadastrada com sucesso");
		        out.println("</body>");
		        out.println("</html>");
			}else {
				throw new Exception("Erro ao cadastratar turma");
			}
		}catch(TurmaCadastradaException ex) {
			out.println("<html>");
	        out.println("<body>");
	        out.println(ex.getMessage());
	        out.println("</body>");
	        out.println("</html>");
		}catch(Exception ex) {
		   out.println("<html>");
           out.println("<body>");
           out.println(" Erro encontrado " + ex.getMessage());
           out.println("</body>");
           out.println("</html>");
		}
	}
}
