package Calcul.Calcul.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Promotion {
	private int annee;
	private int niveau;
	private String filliere;
	private List<Student> eleves;
	
	public Promotion(int annee, int niveau, String filliere) {
		this.annee = annee;
		this.niveau = niveau;
		this.filliere = filliere;
		this.eleves = new ArrayList<>();;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	public String getFilliere() {
		return filliere;
	}
	public void setFilliere(String filliere) {
		this.filliere = filliere;
	}
	public List<Student> getEleves() {
		return eleves;
	}
	public void setEleves(List<Student> eleves) {
		this.eleves = eleves;
	}
	public void addEleve(Student e) {
		if(eleves==null||eleves.isEmpty()||!eleves.contains(e)) {
			eleves.add(e);
		}
	}
	public String toString() {
		String s=annee+" " +" "+ filliere+" "+niveau;
		for(Student e :eleves) {
			s+=e.toString();
		}
		return s;
	}
}
