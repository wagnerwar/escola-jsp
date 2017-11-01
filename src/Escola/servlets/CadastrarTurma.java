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
import org.json.JSONObject;
import org.json.JSONException;

@WebServlet("/cadastrarTurma")
public class CadastrarTurma extends HttpServlet {
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 JSONObject js = new JSONObject();	
		 response.setContentType("application/json");
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
				js.put("msg", "Turma cadastrada com sucesso");
				String saida = js.toString();
				out.println(saida);
			}else {
				throw new Exception("Erro ao cadastratar turma");
			}
		}catch(TurmaCadastradaException ex) {
			js.put("msg", ex.getMessage());
			String saida = js.toString();
			out.println(saida);
		}catch(Exception ex) {
			js.put("msg", ex.getMessage());
			String saida = js.toString();
			out.println(saida);
		}
	}
}
