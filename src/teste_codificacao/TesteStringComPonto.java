package teste_codificacao;

import java.math.BigDecimal;

public class TesteStringComPonto {
	public static void main(String[] args){
		
	String valor = "55.600,30";
	String real = valor.replace(".", "").replace(",", ".");
	System.out.println("Real " + real);
	
	BigDecimal moeda = new BigDecimal (valor);
	System.out.println("Moeda: " + moeda);
 }
}
