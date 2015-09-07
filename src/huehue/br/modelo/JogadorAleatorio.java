package huehue.br.modelo;

/**
 * Representa um jogador que utiliza um algor�timo para determinar posi��es aleat�rias.
 * 
 * @author Luiz Felipe Nazari
 */
public class JogadorAleatorio extends JogadorAutomato {
	
	public JogadorAleatorio(Caractere caractere) {
		super(caractere);
	}
	
	@Override
	public int novaJogada(double[] entradas) {
		return escolhePosicao(entradas);
	}
	
}
