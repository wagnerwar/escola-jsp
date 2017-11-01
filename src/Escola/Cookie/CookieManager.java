package Escola.Cookie;

import javax.servlet.http.Cookie;

public class CookieManager {
	private final String login = "admin";
	private final String senha = "admin";
	public final String chave = "usuario";
	
	public Cookie autenticaUsuario(String login_input, String senha_input) {
		Cookie cookie = null;
		try {
			if(login_input.equals(this.login) && senha_input.equals(this.senha)) {
				cookie = new Cookie(this.chave, login_input);
				cookie.setMaxAge(30*60);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return cookie;
	}
	
	public boolean checaUsuarioAutenticado(Cookie[] cookies) {
		boolean retorno = false;
		try {
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(this.chave)){
					retorno = true;
					break;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return retorno;
	}
}
