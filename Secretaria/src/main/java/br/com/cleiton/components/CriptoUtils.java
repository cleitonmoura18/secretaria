package br.com.cleiton.components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CriptoUtils {
	private static final String hexDigits = "0123456789abcdef";
	/**
	* Realiza um digest em um array de bytes através do algoritmo especificado
	* @param input - O array de bytes a ser criptografado
	* @param algoritmo - O algoritmo a ser utilizado
	* @return byte[] - O resultado da criptografia
	* @throws NoSuchAlgorithmException - Caso o algoritmo fornecido não seja
	* válido
	*/
	
	private static byte[] digest(byte[] input, String algoritmo)
		throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algoritmo);
		md.reset();
		return md.digest(input);
	}
  
	/**
	 * Converte o array de bytes em uma representação hexadecimal.
	 * @param input - O array de bytes a ser convertido.
	 * @return Uma String com a representação hexa do array
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer buf = new StringBuffer();
    
		for (int i = 0; i < b.length; i++) {
			int j = ((int) b[i]) & 0xFF; 
			buf.append(hexDigits.charAt(j / 16)); 
			buf.append(hexDigits.charAt(j % 16)); 
		}
	    
		return buf.toString();
	}
  
	public static String criptografa(String senha) throws NoSuchAlgorithmException {
		return CriptoUtils.byteArrayToHexString(CriptoUtils.digest(senha.getBytes(), "md5"));
	}
}
