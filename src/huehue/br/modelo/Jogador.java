package huehue.br.modelo;

import lombok.Getter;

/**
 * Classe abstrata que define as a��es de um jogador vis�veis �s telas.
 * 
 * @author Luiz Felipe Nazari
 */
public abstract class Jogador {
	
	@Getter
	private Integer pontuacao = 0;
	
	private Caractere caractere;
	
	public Jogador(Caractere caractere) {
		this.caractere = caractere;
	}
	
	/**
	 * Retorna o caractere que ser� utilizado para demarcar as
	 * posi��es escolhidas pelo jogador durante as partidas.
	 * 
	 * @return o caractere utilizado pelo jogador.
	 */
	public Caractere getCaractere() {
		return this.caractere;
	}
	
	/**
	 * Adiciona um ponto ao contador do jogador.
	 */
	public void pontuar() {
		pontuacao++;
	}
	
	/**
	 * Realiza uma nova jogada.
	 * 
	 * @param entradas
	 *            configura��o atual do tabuleiro.
	 * @return posi��o escolhida pelo jogador.
	 */
	abstract public int novaJogada(double[] entradas);
	
}
