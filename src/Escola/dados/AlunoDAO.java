package Escola.dados;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
	
	public boolean atualizarAluno(Aluno aluno) {
		boolean retorno = false;
		try {
			this.abreConexao();
			if(this.validaAluno(aluno)) {
				java.sql.Date dt_formatado = new java.sql.Date(aluno.dt_nascimento.getTime());
				PreparedStatement stmt = this.getConexao().prepareStatement("UPDATE aluno SET nome = ?, dt_nascimento = ?, genero = ? WHERE id = ?");
				stmt.setString(1, aluno.nome);
				stmt.setDate(2, dt_formatado);
				stmt.setString(3, aluno.genero);
				stmt.setInt(4, aluno.id);
				stmt.execute();			
				retorno = true;
			}else {
				throw new Exception("Aluno não validado");
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
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
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				aluno = new Aluno();
				aluno.id = Integer.parseInt(rs.getString(1).toString());
				aluno.nome = rs.getString(2).toString();
				aluno.dt_nascimento = formato_saida.parse(rs.getString(3).toString());
				aluno.dt_nascimento_formatado = formato_saida.format(aluno.dt_nascimento);
				aluno.genero = rs.getString(4).toString();	
				break;
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
	
	public int getIdadeAluno(Aluno aluno) {
		int idade = 0;
		try {
			Date dt_nascimento = aluno.dt_nascimento;
			Date agora = new Date();
			//Duration duration = Duration.between(agora, dt_nascimento);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return idade;
	}
	
	public List<Aluno> getAlunosNaoAssociados(Turma turma){
		List<Aluno> alunos = new ArrayList<Aluno>();
		try {
			this.abreConexao();
			java.sql.PreparedStatement stmt = this.getConexao().prepareStatement("SELECT  id, nome, date_format('%d/%m/%Y', dt_nascimento) as dt_nascimento, genero from aluno WHERE id not in (  SELECT a.id as id FROM aluno a INNER JOIN turma_aluno b ON b.id_aluno = a.id INNER JOIN turma c ON b.id_turma = c.id WHERE c.id = ?  )  ");
			stmt.setInt(1, turma.id);
			ResultSet rs = stmt.executeQuery();
			SimpleDateFormat formato_saida = new SimpleDateFormat("dd/MM/yyyy");
			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.id = rs.getInt(1);
				aluno.nome = rs.getString(2);
				aluno.dt_nascimento = formato_saida.parse(rs.getString(3).toString());
				aluno.dt_nascimento_formatado = formato_saida.format(aluno.dt_nascimento);				
				aluno.genero = rs.getString(4);
				alunos.add(aluno);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			this.fechaConexao();
		}
		return alunos;
	}
	
}
