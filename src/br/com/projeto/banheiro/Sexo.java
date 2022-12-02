package br.com.projeto.banheiro;

public enum Sexo {
	HOMEM("Homem"),
	MULHER("Mulher"),
	NENHUM("Nenhum");
	
	
	private String label;
	
	private Sexo(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
