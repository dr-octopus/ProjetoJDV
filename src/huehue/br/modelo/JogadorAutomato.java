package huehue.br.modelo;

/**
 * Classe representando um jogador aut�mato, as a��es s�o determinadas por intelig�ncias
 * artificiais.
 * 
 * @author Luiz Felipe Nazari
 */
public abstract class JogadorAutomato extends Jogador {
	
	public JogadorAutomato(Caractere caractere) {
		super(caractere);
	}
	
	@Override
	public abstract int novaJogada(double[] entradas);
	
	public void notificarResultado(Partida partida) {
		// Nada a fazer.
	}
	
	/**
	 * Escolhe uma posi��o na configura��o do tabuleiro atual, apenas posi��es n�o ocupadas ser�o
	 * escolhidas.
	 * 
	 * @param cfgTabuleiro
	 *            configura��o do tabuleiro atual.
	 * @return posica��o aleat�ria escolhida.
	 */
	protected int escolhePosicao(double[] cfgTabuleiro) {
		int posicoesValidasLength = 0;
		int[] posicoesValidas = new int[cfgTabuleiro.length];
		
		for (int i = 0; i < cfgTabuleiro.length; i++)
			if (cfgTabuleiro[i] == Caractere.VAZIO.getValor())
				posicoesValidas[posicoesValidasLength++] = i;
		
		return posicoesValidas[( int ) (Math.random() * --posicoesValidasLength)];
	}
	
}
