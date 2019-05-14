package Calcul.Calcul.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Option {
	//la taille de l'option
	public int size;
	//la liste des étudiants
	public LinkedList<Student> accepted;
	//le nom de l'option
	public String nom;	

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int id;
	
	public int year;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String description;
	
	private String mail_prof;

	public String getMail_prof() {
		return mail_prof;
	}

	public void setMail_prof(String mail_prof) {
		this.mail_prof = mail_prof;
	}

	public Option(int size, String intitule, String mail, int id){
		accepted = new LinkedList<Student>();
		this.size = size;
		this.nom = intitule;
		this.id = id;
		this.mail_prof = mail;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return vrai si l'option est pleine
	 */
	public boolean isFull() {
		return size <= accepted.size(); 
	}
	
	public String toString() {
		return "Nom de l'option : " + nom+"| Mail : "+ mail_prof;
	}
	
	public Option nameToOption(ArrayList<Option> options, String name) {
		for (Option option : options) {
			if(option.nom.equals(name))
				return option;
		}
		return null;
	}
}