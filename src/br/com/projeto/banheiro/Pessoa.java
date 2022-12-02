package br.com.projeto.banheiro;

import java.util.Random;

public class Pessoa extends Thread{

	private String nome;
	private Sexo sexo;
	private int tempoUsoBanheiro;
	private Banheiro banheiro;
	
	public Pessoa() {
		Random rng = new Random();
		this.tempoUsoBanheiro = (rng.nextInt() % 6 + 10)*1000;
	}
	
	public Pessoa(String nome, Sexo sexo, Banheiro banheiro) {
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.banheiro = banheiro;
		Random rng = new Random();
		this.tempoUsoBanheiro = (rng.nextInt() % 6 + 10)*1000;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public int getTempoUsoBanheiro() {
		return tempoUsoBanheiro;
	}

	public void setTempoUsoBanheiro(int tempoUsoBanheiro) {
		this.tempoUsoBanheiro = tempoUsoBanheiro;
	}

	public Banheiro getBanheiro() {
		return banheiro;
	}

	public void setBanheiro(Banheiro banheiro) {
		this.banheiro = banheiro;
	}
	
	@Override
	public void run() {
		
		banheiro.tentarUsarBanheiro(this);
		
	}
}

