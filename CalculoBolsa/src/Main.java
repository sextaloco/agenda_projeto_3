import java.math.BigDecimal;

import javax.swing.JOptionPane;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double valorCompra = new Double(JOptionPane.showInputDialog("Valor de compra da a��o"));
		Integer quantidadeCompra = new Integer(JOptionPane.showInputDialog("Quantos lotes?"));
		String resposta = "";
		
		for(int i=10; i>-10; i--){
			resposta += valorCompra+(i*0.01);
			resposta += " = ";
			resposta += new BigDecimal(quantidadeCompra*i-10).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
			if(i==0){
				resposta += " <- VALOR DE COMPRA";
			}
			resposta += "\n";
		}
		
		JOptionPane.showMessageDialog(null, resposta);
	}

}
