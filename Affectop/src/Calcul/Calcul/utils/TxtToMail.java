package Calcul.Calcul.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.exceptions.UnexpectedFileException;
import Calcul.Calcul.mail.MailManager;

public class TxtToMail {

	private MailManager mail;

	private File txtFile;

	public TxtToMail(String path) throws UnexpectedFileException {
		String[] s = path.split("/");// For Linux
		if (System.getProperty("os.name").equals("Windows"))
			s = path.split("\\\\");// For Windows

		String fileName = s[s.length - 1];
		s = fileName.split("\\.");
	

		s = fileName.split("[.]");

		if (!s[1].equals("txt")) {
			throw new UnexpectedFileException();
		}

		txtFile = new File(path);
		String str = "";
		try (Scanner sc = new Scanner(txtFile)) {

			while (sc.hasNext()) {
				
				str += sc.nextLine()+"\n";
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> m=readMail("to", "token", str);
		
		
		for(Object key:m.keySet()) { System.out.println("Clef de la map "+key+" Valeur "+m.get(key)); }


	}

	public Map<String,String> readMail(String to, String token, String txt) {
		HashMap<String,String> table=new HashMap<>();
		BaseReader br = new BaseReader();

		Map<String, ArrayList<String>> map = tagChecker(txt);
		ArrayList<String> list = map.get("Valid");
		for (String balise : list) {
			System.out.println("la balise " + balise + " est valide");

			txt = txt.replace(balise, br.tagRequest(balise, token));
		}
		
		String subject="Subject:";
		String content="Content:";
		subject=txt.substring(txt.indexOf(subject)+subject.length(), txt.indexOf(content));
		content=txt.substring(txt.indexOf(content)+content.length());
		table.put("subject",subject );
		table.put("content", content);
		
		
		return table;
	}

	private static String listeTag = "NOM PRENOM DATE LISTE_AFFECTATION";

	/**
	 * Return all tag that don't belong to the listeTag, case sensitive
	 * 
	 * @param txt the string that need to be checked
	 * @return an ArrayList of String containing all tag that are not valid
	 * @author Valentin JABRE
	 * @version 1.2
	 */
	public static Map<String, ArrayList<String>> tagChecker(String txt) {

		HashMap<String, ArrayList<String>> res = new HashMap<>();

		Pattern patern = Pattern.compile("<[" + listeTag + "]+>");
		Matcher matcher = patern.matcher(txt);

		ArrayList<String> result = new ArrayList<>();

		// Si un tag est valide on le stocke dans une arraylist
		while (matcher.find()) {
			System.out.println("Valid " + matcher.group(0));
			result.add(matcher.group(0));

		}
		// Ajoute dans le dictionnaire à Valid l'ensemble des tags valides
		res.put("Valid", result);

		// Si un tag est valide on le retire de la chaine de travail
		String s = txt.replaceAll("<[" + listeTag + "]+>", "");

		// Si il existe un tag non reconnu on l'ajoute à la liste des invalides
		patern = Pattern.compile("<[a-zA-Z0-9]*>");
		matcher = patern.matcher(s);
		result = new ArrayList<>();
		System.out.println("Detection balises invalides");
		while (matcher.find()) {
			System.out.println("invalide " + matcher.group(0));
			result.add(matcher.group(0));

		}

		res.put("Invalid", result);
		return res;
	}
}
