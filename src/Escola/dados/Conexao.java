package Escola.dados;

import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
	
	private java.sql.Connection conn = null;
	
	public java.sql.Connection getConexao(){
		return this.conn;
	}
	
	public void fechaConexao() {	
		try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void abreConexao() {
		try {
			this.conn = Conexao.getConnection();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static java.sql.Connection getConnection() {
        java.sql.Connection conn = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/aluno?user=usuario&password=amesma";
        	conn = DriverManager.getConnection(url);
        }catch(Exception ex) {
                ex.printStackTrace();
        }
        return conn;
	}
}
