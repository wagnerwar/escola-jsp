package Escola.dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import Escola.Exception.TurmaCadastradaException;;

public class TurmaDAO extends Conexao {
	
	public Turma getTurma(int id){
		Turma turma = null;
		try {
			this.abreConexao();
			PreparedStatement stmt = this.getConexao().prepareStatement("SELECT id, nome, descricao FROM turma WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				turma = new Turma();
				turma.id = rs.getInt(1);
				turma.nome = rs.getString(2);
				turma.descricao = rs.getString(3);
				break;
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return turma;
	}
	
	public List<Turma> consultarTurmaPorNome(Turma turma){
		List<Turma> turmas = new ArrayList<Turma>();
		try {
			this.abreConexao();
			java.sql.Statement stmt = this.getConexao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id,nome FROM turma where nome like '%" + turma.nome + "%'");
			while(rs.next()) {
				Turma tt = new Turma();
				tt.nome = rs.getString(2).toString();
				tt.id = rs.getInt(1);
				turmas.add(tt);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return turmas;
	}
	

	
	public List<Turma> getTurmas(){
		List<Turma> turmas = new ArrayList<Turma>();
		try {
			this.abreConexao();
			java.sql.Statement stmt = this.getConexao().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, nome, descricao FROM turma");
			
			while(rs.next()) {
				Turma tt = new Turma();
				tt.id = Integer.parseInt(rs.getString(1).toString());
				tt.nome = rs.getString(2).toString();
				tt.descricao = rs.getString(3).toString();
				turmas.add(tt);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return turmas;
	}
	
	public boolean excluirTurma(int id) {
		boolean retorno = false;
		try {
			this.abreConexao();
			PreparedStatement stmt = this.getConexao().prepareStatement("delete from turma WHERE id = ?");
			stmt.setInt(1, id);
			stmt.execute();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return retorno;
	}
	
	
	public boolean createTurma(Turma turma) throws TurmaCadastradaException, Exception {
		boolean retorno = false;
		try {		
			this.abreConexao();
			PreparedStatement stmt = this.getConexao().prepareStatement("INSERT INTO turma(nome, descricao) values( ?, ?)");
			stmt.setString(1, turma.nome);
			stmt.setString(2, turma.descricao);
			stmt.execute();
			retorno = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return retorno;
	}
	
	public boolean atualizarTurma(Turma turma) throws TurmaCadastradaException, Exception {
		boolean retorno = false;
		try {		
			this.abreConexao();
			PreparedStatement stmt = this.getConexao().prepareStatement("UPDATE turma SET descricao = ? WHERE id = ?");
			stmt.setString(1, turma.descricao);
			stmt.setInt(2, turma.id);
			stmt.execute();
			retorno = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return retorno;
	}
	
	public boolean associarAlunos(Turma turma, List<Aluno> alunos) {
		boolean retorno = false;
		try {
			this.abreConexao();
			PreparedStatement stmt = this.getConexao().prepareStatement("INSERT INTO turma_aluno(id_turma, id_aluno, dt_associacao) values(?,?,now())");
			for(Aluno aluno: alunos) {
				stmt.setInt(1, turma.id);
				stmt.setInt(2, aluno.id);
				stmt.execute();
			}
			retorno = true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return retorno;
	}
	
}
