package Escola.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/AssociarAluno")
public class AssociarAluno extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		JSONObject js = new JSONObject();	
		response.setContentType("application/json");
		try {
			int id_turma = Integer.parseInt(request.getParameter("id_turma"));

			if(request.getParameterValues("alunos") == null) {
				throw new Exception("Deve ser selecionado pelo menos um aluno");
			}

			String[] lista = request.getParameterValues("alunos");
			List<Aluno> alunos = new ArrayList<Aluno>();
			AlunoDAO daoAluno = new AlunoDAO();
			TurmaDAO daoTurma = new TurmaDAO();
			
			for(String al: lista) {
				int id_aluno = Integer.parseInt(al);
				Aluno aluno = daoAluno.getAluno(id_aluno);
				alunos.add(aluno);
			}
			Turma turma = daoTurma.getTurma(id_turma);
			boolean retorno = daoTurma.associarAlunos(turma, alunos);
			if(retorno == true) {
				js.put("msg", "Alunos associados com sucesso");
				js.put("status", "OK");
				String saida = js.toString();
				out.println(saida);
			}else {
				throw new Exception("Erro ao associar alunos");
			}
			
			//throw new Exception("Erro ao associar alunos");
		}catch(Exception ex) {
			js.put("msg", "Erro encontrado" + ex.getMessage());
			js.put("status", "NOK");
			String saida = js.toString();
			out.println(saida);
		}
	}
}
