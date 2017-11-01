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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Escola.dados.Aluno;
import Escola.dados.AlunoDAO;
import Escola.dados.Turma;
import Escola.dados.TurmaDAO;
import Escola.Cookie.CookieManager;
import org.json.JSONObject;
import org.json.JSONException;


@WebServlet("/loginUsuario")
public class LoginUsuario extends HttpServlet {
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject js = new JSONObject();	
		try {
			String login_input = request.getParameter("login");
			String senha_input = request.getParameter("senha");
			
			// Realizando autenticação
			CookieManager cookieManager = new CookieManager();
			Cookie cookie = cookieManager.autenticaUsuario(login_input, senha_input);
			if(cookie != null) {
				response.addCookie(cookie);
				js.put("msg", "Usuário autenticado com sucesso");
				js.put("status", "OK");
				String saida = js.toString();
				out.println(saida);
			}else {
				throw new Exception("Login inválido");
			}
		}catch(Exception ex) {
			js.put("msg", "Erro encontrado" + ex.getMessage());
			js.put("status", "NOK");
			String saida = js.toString();
			out.println(saida);
		}
	}
}
