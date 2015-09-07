package huehue.br.modelo;

import huehue.br.MultilayerPerceptron;
import huehue.br.dados.DadosFelizes;
import huehue.br.util.VeiaUtil;

import java.time.Duration;
import java.time.LocalDateTime;

import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.factory.MLTrainFactory;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.util.simple.EncogUtility;

/**
 * Classe representando um jogador com Intelig�ncia Artificial utilizando uma
 * Rede Neural Artificial.
 * 
 * @author Luiz Felipe Nazari
 */
public class JogadorRNA extends JogadorAutomato {
	
	private BasicNetwork rede;
	
	// Conjuntos de dados conhecidos pela rede e utilizados no treinamento.
	private DadosFelizes dados;
	
	public JogadorRNA(Caractere caractere) {
		super(caractere);
		dados = new DadosFelizes();
		dados.carregarDoArquivo();
		rede = MultilayerPerceptron.getNetwotk();
	}
	
	@Override
	public int novaJogada(double[] entradas) {
		entradas = validaEntradasDaRede(entradas);
		MLData saida = rede.compute(converteArrayEmMlData(entradas));
		int posicaoEscolhida = VeiaUtil.RNA.traduzSaida(saida);
		
		// Escolheu uma posi��o j� ocupada.
		if (entradas[posicaoEscolhida] != Caractere.VAZIO.getValor()) {
			System.out
			        .println("A Rede computou uma posi��o inv�lida. Escolhendo novo movimento...");
			posicaoEscolhida = escolhePosicao(entradas);
		}
		
		return posicaoEscolhida;
	}
	
	@Override
	public void notificarResultado(Partida partida) {
		partida.getJogadasVencedor().forEach(
		    j -> dados.adicionarDadoES(j.getConfiguracao(), validaResultadoDaRede(j
		            .getPosicaoEscolhida())));
		aprenderJogadas();
	}
	
	private void aprenderJogadas() {
		MLTrainFactory trainFactory = new MLTrainFactory();
		MLTrain train = trainFactory.create(rede, dados.getMLDataSet(),
		    MultilayerPerceptron.treinamento, "");
		
		// FIXME Tempor�rio
		dados.salvarEmArquivo();
		// ----------------
		
		LocalDateTime inicio = LocalDateTime.now();
		System.out.println("Iniciando treinamento.");
		EncogUtility.trainToError(train, MultilayerPerceptron.MARGEM_DE_ERRO);
		System.out.println("Treinamento finalizado. Tempo total: " + Duration.between(inicio,
		    LocalDateTime.now()));
		
		// FIXME Tempor�rio
		MultilayerPerceptron.salvaNetwork(rede);
		// ----------------
	}
	
	/**
	 * Busca a configura��o das posi��es do tabuleiro atual e constroi os dados
	 * a serem utilizados pelo Framework {@link Encog}.
	 * 
	 * @param entradas
	 *            array de entradas representando o estado do tabuleiro.
	 * @return os dados do tabuleiro atual.
	 */
	private MLData converteArrayEmMlData(double[] entradas) {
		return new BasicMLData(entradas);
	}
	
	/**
	 * Valida os valores das entradas a serem computadas pela Rede. Caso o {@link JogadorRNA} em
	 * quest�o possuir o {@link Caractere#O}, os valores das entradas precisar�o ser ajustados,
	 * pois a rede � treinada para reconhecer o valor do {@link Caractere#X} como posi��o da Rede e
	 * o {@link Caractere#O} como o advers�rio.
	 * 
	 * @param entradas
	 * @return
	 */
	private double[] validaEntradasDaRede(double[] entradas) {
		if (getCaractere().equals(Caractere.O))
			for (int i = 0; i < entradas.length; i++)
				entradas[i] *= -1;
		
		return entradas;
	}
	
	private double validaResultadoDaRede(double resultado) {
		return (resultado + 1) / 10;
	}
}
