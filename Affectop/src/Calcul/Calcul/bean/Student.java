package Calcul.Calcul.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Student {
	private String nom;
	private String prenom;

	private Promotion promotion;
	/** 
	 * le mail de l'�tudiant
	 */
	public String mail;
	
	private int numEtudiant;
	
	/**
	 * le constructeur de la classe student
	 * @param mail le mail de l'�tudiant
	 * @return un etudiant
	 */
	public Student(String prenom, String nom, String mail, int numEtudiant) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.numEtudiant = numEtudiant;
	}
	
	/*
	public Eleve(String nom, String prenom, String adresseMail, Promotion promotion, List<Option> optionValid�,
			String numEtudiant) {
		super(nom, prenom, adresseMail);
		this.promotion = promotion;
		if(optionValid�==null)
			optionValid�=new ArrayList<>();
		this.optionValid� = optionValid�;
		this.numEtudiant = numEtudiant;
	}*/
	public String toString() {
		return nom + " " +prenom + " "  + mail +" " + numEtudiant;
	}
	/**
	 * Donne la satisfaction de l'�tudiant d�finie par
	 * s = somme pour tous les jours de l'indice de l'option affect�e ce jour l� 
	 * @return la satisfaction
	 */
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setNomPrenom(String nom, String prenom) {
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public int getNumEtudiant() {
		return numEtudiant;
	}

	public void setNumEtudiant(int numEtudiant) {
		this.numEtudiant = numEtudiant;
	}
}
