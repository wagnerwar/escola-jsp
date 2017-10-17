package Escola.dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class AlunoDAO extends Conexao {
	
	public List<Aluno> getAlunos(){
		List<Aluno> lista = new ArrayList<Aluno>();
		try {
			this.abreConexao();
			java.sql.Statement stmt = this.getConexao().createStatement();
			
            ResultSet rs = stmt.executeQuery("SELECT * FROM aluno");
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            while(rs.next()) {
            	Aluno al = new Aluno();
            	al.id =Integer.parseInt(rs.getString(1).toString());
            	al.nome = rs.getString(2).toString();
            	al.dt_nascimento = formato.parse(rs.getString(3).toString());
            	al.genero = rs.getString(4).toString();
            	lista.add(al);
            }
            this.fechaConexao();
		}catch(Exception ex) {
			System.out.println("Erro na aplicação");
			System.out.println(ex.getMessage());
		}
		
		return lista;
	}
	
	public boolean createAluno(Aluno aluno) {
		boolean retorno = false;
		try {
			this.abreConexao();
			/*if(this.validaAluno(aluno)) {
				java.sql.Date dt_formatado = new java.sql.Date(aluno.dt_nascimento.getTime());
				
				PreparedStatement stmt = this.getConexao().prepareStatement("INSERT INTO aluno(nome, dt_nascimento, genero) values(?, ?, ?)");
				stmt.setString(1, aluno.nome);
				stmt.setString(2, dt_formatado.toString());
				stmt.setString(3, aluno.genero);
				stmt.execute();
				
				retorno = true;
			}else {
				System.out.println("Aluno não validado");
			}*/
			this.fechaConexao();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return retorno;
	}
	
	public boolean validaAluno(Aluno aluno) {
		boolean bola = false;
		try {
			bola = true;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return bola;
	}
}
