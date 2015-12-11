package br.edu.unoesc.rede.modelo;

import lombok.Getter;

import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;

import br.edu.unoesc.util.JdvUtils;

@Getter
public class MultilayerPerceptron extends JdvRedeAbstrata {

	public MultilayerPerceptron() {
		this(null);
	}

	public MultilayerPerceptron(String nome) {
		super(9, 1, nome);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <pre>
	 * Exemplos:
	 * 
	 * 0.0001	-> 0.0	-> 0
	 * 0.0981	-> 0.1	-> 1
	 * 0.196	-> 0.2	-> 2
	 * 0.3328	-> 0.3	-> 3
	 * 0.4007	-> 0.4	-> 4
	 * 0.7865	-> 0.8	-> 8
	 * 0.9999	-> 1.0	-> 8
	 * </pre>
	 */
	@Override
	public int traduzirSaida(MLData saida) {
		double d = JdvUtils.RNA.valorAproximado(saida.getData(0));

		if (d >= 0.9)
			return 8;
		if (d <= 0)
			return 0;
		return ( int ) (d * 10);
	}

	@Override
	public MLData convertePosicaoTabuleiroEmSaida(int posicao) {
		return new BasicMLData(new double[] {
			( double ) posicao / 10
		});
	}
}
