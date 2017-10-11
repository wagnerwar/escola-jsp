package Escola.dados;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Conexao {
	public static java.sql.Connection getConnection() {
        java.sql.Connection conn = null;

        try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost/aluno?user=root&password=amesma";
                conn = DriverManager.getConnection(url);

        }catch(Exception ex) {
                System.out.println(ex.getMessage());
        }
        return conn;
	}
}
