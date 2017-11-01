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

@WebServlet("/editarTurma")
public class EditarTurma extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException{
		 PrintWriter out = response.getWriter();
		 JSONObject js = new JSONObject();	
		 response.setContentType("application/json");
		 try {
				Turma turma = new Turma();
				turma.descricao = request.getParameter("descricao");
				turma.id = Integer.parseInt(request.getParameter("id").trim());
				TurmaDAO dao = new TurmaDAO();
				boolean retorno = dao.atualizarTurma(turma);
				if(retorno == true) {
					js.put("msg", "Turma atualizada com sucesso");
					String saida = js.toString();
					out.println(saida);
				}else {
					throw new Exception("Erro ao atualizar turma");
				}
		 }catch(Exception ex) {
				js.put("msg", ex.getMessage());
				String saida = js.toString();
				out.println(saida);
		 }
	}
}
