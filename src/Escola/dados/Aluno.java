package Escola.dados;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Aluno {
	
	public int id;
	
	public String nome;
	
	public Date dt_nascimento;
	
	public String genero;
	
	public String dt_nascimento_formatado;
	
	public int getIdade() {
		int idade = 0;
		try {
			Date dt_nascimento = this.dt_nascimento;
			Date agora = new Date();
			
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(dt_nascimento);
			int year_birth = calendar.get(Calendar.YEAR);
			
			calendar.setTime(agora);
			int year_now = calendar.get(Calendar.YEAR);
			
			idade = (year_now - year_birth);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return idade;
	}

}
