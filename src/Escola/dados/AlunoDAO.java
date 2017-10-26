package Escola.dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class AlunoDAO extends Conexao {
	
	public List<Aluno> getAlunos(){
		List<Aluno> lista = new ArrayList<Aluno>();
		try {
			this.abreConexao();
			java.sql.Statement stmt = this.getConexao().createStatement();			
            ResultSet rs = stmt.executeQuery("SELECT id, nome,DATE_FORMAT(dt_nascimento,'%d/%m/%Y') AS dt_formatado, genero  FROM aluno");
            SimpleDateFormat formato_saida = new SimpleDateFormat("dd/MM/yyyy");
            while(rs.next()) {
            	Aluno al = new Aluno();
            	al.id =Integer.parseInt(rs.getString(1).toString());
            	al.nome = rs.getString(2).toString();
            	al.dt_nascimento = formato_saida.parse(rs.getString(3).toString());
            	al.genero = rs.getString(4).toString();
            	al.dt_nascimento_formatado = formato_saida.format(al.dt_nascimento);
            	lista.add(al);
            }
		}catch(Exception ex) {
			ex.printStackTrace();
			
		}finally {
			this.fechaConexao();
		}
		return lista;
	}
	
	public boolean createAluno(Aluno aluno) {
		boolean retorno = false;
		try {
			this.abreConexao();
			if(this.validaAluno(aluno)) {
				java.sql.Date dt_formatado = new java.sql.Date(aluno.dt_nascimento.getTime());
				
				PreparedStatement stmt = this.getConexao().prepareStatement("INSERT INTO aluno(nome, dt_nascimento, genero) values(?, ?, ?)");
				stmt.setString(1, aluno.nome);
				stmt.setDate(2, dt_formatado);
				stmt.setString(3, aluno.genero);
				stmt.execute();
				
				retorno = true;
			}else {
				System.out.println("Aluno não validado");
			}
			
		}catch(Exception ex) {
			System.out.println("Erro na aplicação");
			System.out.println(ex.getMessage());
		}finally {
			this.fechaConexao();
		}
		return retorno;
	}
	
	public Aluno getAluno(int id) {
		Aluno aluno = null;
		try {
			this.abreConexao();
			SimpleDateFormat formato_saida = new SimpleDateFormat("dd/MM/yyyy");
			PreparedStatement stmt = this.getConexao().prepareStatement("SELECT id, nome, DATE_FORMAT(dt_nascimento,'%d/%m/%Y') AS dt_formatado, genero from aluno WHERE id = ?");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				aluno = new Aluno();
				aluno.id = Integer.parseInt(rs.getString(1).toString());
				aluno.nome = rs.getString(2).toString();
				aluno.dt_nascimento = formato_saida.parse(rs.getString(3).toString());
				aluno.dt_nascimento_formatado = formato_saida.format(rs.getString(3).toString());
				aluno.genero = rs.getString(4).toString();
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return aluno;
	}
	
	public boolean excluirAluno(int id) {
		boolean retorno = false;
		try {
			this.abreConexao();
			PreparedStatement stmt = this.getConexao().prepareStatement("delete from aluno WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return retorno;
	}
	
	public boolean validaAluno(Aluno aluno) {
		boolean bola = false;
		try {
			bola = true;
		}catch(Exception ex) {
			
		}
		
		return bola;
	}
}
