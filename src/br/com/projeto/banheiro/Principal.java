package br.com.projeto.banheiro;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Principal {
	
	private final static Random random = new Random();
	private static int capacidadeBanheiro = 0;
	private static int qtdPessoas = 0;
	
	public static void main(String[] args) {
		
		ArrayList<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		
		System.out.println("Bem vindo ao programa Banheiro Unissex");
		
		inserirValores();
		
		Banheiro banheiro = new Banheiro(capacidadeBanheiro);
		
		int qtdHomens = 0;
		int qtdMulheres = 0;
		
		for(int i = 0 ; i < qtdPessoas ; i++) {
			if(proximaPessoaSeraHomem()) {
				Pessoa pessoa = new Pessoa("Homem(" + qtdHomens + ")", Sexo.HOMEM, banheiro);
				listaPessoas.add(pessoa);
				qtdHomens++;
			}else {
				Pessoa pessoa = new Pessoa("Mulher(" + qtdMulheres + ")", Sexo.MULHER, banheiro);
				listaPessoas.add(pessoa);
				qtdMulheres++;
			}
		}
		
		for(Pessoa p : listaPessoas) {
			p.start();
		}
		
		for(Pessoa p : listaPessoas) {
			try {
				p.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Todo mundo já usou o banheiro");
		
	}

	private static void inserirValores() {
		Scanner leitura = new Scanner(System.in);
		
		System.out.println("Informe a capacidade de pessoas do banheiro:");
		
		try {
		
			capacidadeBanheiro = leitura.nextInt();
		
		}catch(Exception ex) {
			System.out.println("Valor inválido, valor atribuido foi o padrão (3 pessoas)");
			capacidadeBanheiro = 3;
		}
		
		if(capacidadeBanheiro <= 0) {
			System.out.println("Valor inválido, valor atribuido foi o padrão (3 pessoas)");
			capacidadeBanheiro = 3;
		}
		
		System.out.println("Informe a quantidade de pessoas que vao usar o banheiro:");
		
		try {
			
			qtdPessoas = leitura.nextInt();
		
		}catch(Exception ex) {
			System.out.println("Valor inválido, valor atribuido foi o padrão (100 pessoas)");
			qtdPessoas = 100;
		}
		
		if(qtdPessoas <= 0) {
			System.out.println("Valor inválido, valor atribuido foi o padrão (100 pessoas)");
			qtdPessoas = 100;
		}
		
		leitura.close();
	}
	
	private static boolean proximaPessoaSeraHomem() {
	  if (random.nextBoolean()) {
	    return true;
	  } else {
	    return false;
	  }
	}
}
