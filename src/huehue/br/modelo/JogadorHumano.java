package huehue.br.modelo;

import huehue.br.exception.VeiaException;

/**
 * Classe representando um jogador humano, requer intera��o via entrada de dados
 * pelo jogador.
 * 
 * @author Luiz Felipe Nazari
 */
public final class JogadorHumano extends Jogador {
	
	public JogadorHumano(Caractere caractere) {
		super(caractere);
	}
	
	@Override
	public final int novaJogada(double[] entradas) {
		throw new VeiaException(
		        "M�todo n�o suportado! A entrada da posi��o ecolhida depende da a��o do usu�rio.");
	}
	
}
