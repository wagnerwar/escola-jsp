package Escola.Exception;

public class TurmaCadastradaException extends Exception {
	public TurmaCadastradaException() {
		super("Turma já cadastrada");
	}
}
