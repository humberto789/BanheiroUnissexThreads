package br.com.projeto.banheiro;

import java.util.concurrent.Semaphore;

public class Banheiro extends Thread{
	
	//Capacidade máxima do banheiro
	private int qtdMaxima;
	
	//Sexo que está sendo utizado no banheiro
	private Sexo sexoVez;
	
	/* 	
	 * Semaforo que simboliza quantos homens estão usando o banheiro naquele momento.
	 * Se tiver valores nesse semaforo o banheiro nao pode ser utilizado por mulheres.
	 */
	private Semaphore semUsandoBanheiro;
	
	private int qtdPessoasUsandoBanheiro;
	
	/* 	
	 * Semaforo que simboliza a ordem de chegada na fila
	 */
	private Semaphore semOrdemChegada;
	
	public Banheiro(int qtdMaxima) {
		this.qtdMaxima = qtdMaxima;
		this.sexoVez = Sexo.NENHUM;
		this.semUsandoBanheiro = new Semaphore(qtdMaxima, true);
		this.qtdPessoasUsandoBanheiro = 0;
		this.semOrdemChegada = new Semaphore(1, true);
	}
	
	public int getQtdMaxima() {
		return qtdMaxima;
	}
	public void setQtdMaxima(int qtdMaxima) {
		this.qtdMaxima = qtdMaxima;
	}
	
	public Sexo getSexoVez() {
		return sexoVez;
	}
	
	public void setSexoVez(Sexo sexoVez) {
		this.sexoVez = sexoVez;
	}
	
	public void tentarUsarBanheiro (Pessoa pessoa) {
		try {
			
			System.out.println(pessoa.getNome() + " acabou de chegar na fila");
			semOrdemChegada.acquire();
			
			System.out.println(pessoa.getNome() + " pediu pra usar o banheiro.");
			
			pedirPraUsarBanheiro(pessoa);
						
			semOrdemChegada.release();
			
			System.out.println(pessoa.getNome() + " começou a usar o banheiro(por " + (pessoa.getTempoUsoBanheiro()/1000) + " segundos).");
			
			usandoBanheiro(pessoa);
			
			System.out.println(pessoa.getNome() + " terminou de usar o banheiro");
			
			this.semUsandoBanheiro.release();
			
			sairBanheiro(pessoa);
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	private synchronized void pedirPraUsarBanheiro(Pessoa pessoa) throws InterruptedException {
		while(!this.sexoVez.equals(pessoa.getSexo()) && !this.sexoVez.equals(Sexo.NENHUM)) {
			this.wait();
		}
		
		this.semUsandoBanheiro.acquire();
		this.sexoVez = pessoa.getSexo();
		this.qtdPessoasUsandoBanheiro++;
	}
	
	private synchronized void sairBanheiro(Pessoa pessoa) {
		this.qtdPessoasUsandoBanheiro--;
		
		if(this.qtdPessoasUsandoBanheiro == 0) {
			this.sexoVez = Sexo.NENHUM;
			this.notifyAll();
		}
	}
	
	private void usandoBanheiro(Pessoa pessoa) throws InterruptedException {
		Thread.sleep((long) pessoa.getTempoUsoBanheiro());
	}
}
