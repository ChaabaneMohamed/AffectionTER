package Calcul.Calcul.token;

import Calcul.Calcul.exceptions.TokenLengthException;

public class main {
	public static void main(String[] args) {
		
		try {
			for(String s:Token.generateTokenList(20, 15)){
				System.out.println(s);
			}
		} catch (TokenLengthException e) {
			e.printStackTrace();
		}
		
	}
}
