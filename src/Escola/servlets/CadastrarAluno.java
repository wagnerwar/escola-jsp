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
import org.json.JSONObject;
import org.json.JSONException;

@WebServlet("/cadastrarAluno")
public class CadastrarAluno extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject js = new JSONObject();	
		response.setContentType("application/json");
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			String nome = request.getParameter("nome");
			Date dt_nascimento = formato.parse(request.getParameter("dt_nascimento"));
			Date dt_limite_nascimento = formato.parse("01/01/1950");
			String genero = request.getParameter("genero");
			if(dt_nascimento.compareTo(dt_limite_nascimento) <= 0) {
				throw new Exception("Data mínima é 01/01/1950.");
			}
			
			Aluno aluno = new Aluno();
			aluno.nome = nome;
			aluno.dt_nascimento = dt_nascimento;
			aluno.genero = genero;
			AlunoDAO alDAO = new AlunoDAO();
			boolean retorno = false;
			retorno = alDAO.createAluno(aluno);
		
			if(retorno == true) {
				js.put("msg", "Aluno cadastrado com sucesso");
				String saida = js.toString();
				out.println(saida);
			}
			
		}catch(Exception ex) {
			js.put("msg", "Erro encontrado" + ex.getMessage());
			String saida = js.toString();
			out.println(saida);
		}
	}
}
