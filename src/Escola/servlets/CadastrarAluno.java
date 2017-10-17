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
import Escola.dados.Aluno;
import Escola.dados.AlunoDAO;

@WebServlet("/cadastrarAluno")
public class CadastrarAluno extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		try {
			

			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			
			String nome = request.getParameter("nome");
			Date dt_nascimento = formato.parse(request.getParameter("dt_nascimento"));
			String genero = request.getParameter("genero");
			
			Aluno aluno = new Aluno();
			aluno.nome = nome;
			aluno.dt_nascimento = dt_nascimento;
			aluno.genero = genero;
			
			AlunoDAO alDAO = new AlunoDAO();
			boolean retorno = false;
			retorno = alDAO.createAluno(aluno);
		
			if(retorno == true) {
				out.println("<html>");
	            out.println("<body>");
	            out.println("Contato " + nome + " adicionado com sucesso");
	            out.println("</body>");
	            out.println("</html>");
			}else {
				out.println("<html>");
	            out.println("<body>");
	            out.println(" Erro na inclusão!! ");
	            out.println("</body>");
	            out.println("</html>");
			}

		}catch(Exception ex) {
			out.println("<html>");
            out.println("<body>");
            out.println(" Erro encontrado " + ex.getMessage());
            out.println("</body>");
            out.println("</html>");
		}
	}
}
