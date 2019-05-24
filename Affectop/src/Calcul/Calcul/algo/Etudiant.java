package Calcul.Calcul.algo;

import java.util.*;

public class Etudiant {

	String nom;
	String prenom;
	String idEtudiant;
	ArrayList<Integer> creditOption;
	ArrayList<Integer> choice;
	int affectation;
	int nombreAffectation;
	ArrayList<Integer> affectationS;

	Etudiant(String nom, String prenom, String idEtudiant, ArrayList<Integer> creditOption){
		this.nom = nom;
		this.prenom = prenom;
		this.idEtudiant = idEtudiant;
        this.creditOption = creditOption;
        this.choice = new ArrayList<Integer>();
        this.affectation = -1;
        this.nombreAffectation = 0;
        
        // On a aussi un "this.affectationS = []" déclaré plus tard pour géré les plusieurs jours d'affectations
    }
	
	
	Etudiant(){
	        this.creditOption = new ArrayList<Integer>();
	        this.choice = new ArrayList<Integer>();
	        this.affectation = -1;
	        this.nombreAffectation = 0;
	        
	        // On a aussi un "this.affectationS = []" déclaré plus tard pour géré les plusieurs jours d'affectations
	    }
	
	
	    
	    void setNom(String name){ this.nom = name; }    // Liste des getter / setter
	    String getNom(){ return this.nom; }
	    
	    void setPrenom(String name){ this.prenom = name; }
	    String getPrenom(){ return this.prenom; }
	    
	    void setIdEtudiant(String IdEtudiant){ this.idEtudiant = IdEtudiant; }
	    String getIdEtudiant(){ return this.idEtudiant; }
}
