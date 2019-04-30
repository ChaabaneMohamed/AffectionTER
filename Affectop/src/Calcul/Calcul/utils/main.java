package Calcul.Calcul.utils;

import Calcul.Calcul.exceptions.UnexpectedFileException;

public class main {

	public static void main(String[] args) {
		System.out.println("Debut du test de TxtToMail");
		try {
			TxtToMail ttm = new TxtToMail("C:\\Travail\\GL\\test.txt");
			
		} catch (UnexpectedFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
