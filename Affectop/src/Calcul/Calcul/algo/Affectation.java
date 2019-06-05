package Calcul.Calcul.algo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.Servlet;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import Calcul.Calcul.base.BaseReader;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;

public class Affectation {

	public static ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
	public static ArrayList<ArrayList<Etudiant>> affectationTab = new ArrayList<ArrayList<Etudiant>>();
	public static int compteurBricolage = 0;

	public static String statAffect = ""; 
	public static String resultatAffectation = "";
	public static String resultatScolarite = "";
	ArrayList<Student> eleves;


	private int numAffectation;
	private int nombreUE; // Nombre d'UE proposï¿½ pour une journï¿½e d'option
	private int nombreEtudiant; // Nombre d'ï¿½tudiant qui participe a une journï¿½e d'option
	private ArrayList<Etudiant> listeAffectation; // Liste des ï¿½tudiants qu'on doit affecter
	private ArrayList<UE> optionData; // Tableau de toute les UE d'un jour d'option
	private ArrayList<Integer> effectif; // Rï¿½cupï¿½re les effectifs des UE pour les changer a chaque affectation
	private ArrayList<ArrayList<Etudiant>> optionList;
	public  ArrayList<Affectation> allAffectation;


	public Affectation(int numAffectation, ArrayList<Affectation> aff, ArrayList<Student> eleves ) {
		this.numAffectation = numAffectation; // Le fichier qui contient toutes les informations pour affecter
		this.allAffectation = aff;
		optionData = new ArrayList<UE>();
		effectif = new ArrayList<Integer>();
		optionList = new ArrayList<ArrayList<Etudiant>>();
		this.eleves = eleves;
		
		

		// Tableau de tableau avec la liste des ï¿½tudiants triï¿½ par crï¿½dit pour chaque
		// jour d'option
	}
	
	public Affectation() {
	}
	
	void init() {
		System.out.println("aaaaaa1");
		BaseReader basereader = new BaseReader();
		System.out.println("aaaaaa2");
		ArrayList<Option> allUE = basereader.getOptionsPerDays(numAffectation);
		System.out.println("aaaaaa3");
        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        System.out.println("aaaaaa4");
        for (Student s : eleves) {
        	ArrayList<Integer> choix = basereader.getChoiceForOneStudentOneGroup(s.getNumEtudiant(), numAffectation);
        	ArrayList<Float> floatChoix = new ArrayList<Float>();
        	for(int i=0; i<choix.size(); i++) {
        		floatChoix.add((float) choix.get(i));
        	}
        	Etudiant e = new Etudiant(s.getNom(), s.getPrenom(), Integer.toString(s.getNumEtudiant()), floatChoix);
			etudiants.add(e);
			//System.out.println("etu|"+e.getIdEtudiant()+"|"+e.creditOption.toString());
		}
        System.out.println("aaaaaa5");
        listeAffectation = new ArrayList<Etudiant>(etudiants);
        
        System.out.println("aaaaaa6");
        ArrayList<UE> UEs = new ArrayList<UE>();
        for (Option u : allUE) {
        	UEs.add(new UE(u.getCodeModule(), 
					u.getNom(), 
					u.getSize()));
		}
        System.out.println("aaaaaa7");
        optionData = UEs;
        
        nombreEtudiant = listeAffectation.size();
        nombreUE = optionData.size();
        System.out.println("FIN INIT ______________________________________________________");
	}
	
	
	public void reset() {
		statAffect = "";
		resultatAffectation = "";
		resultatScolarite = "";
		
		compteurBricolage = 0;
		listeEtudiant.clear();
		affectationTab.clear();
	}
	
	public void noChoice() {
        for (Etudiant etu : listeAffectation) {
            if(etu.creditOption.size() == 0) {
                for(int i=0; i<nombreUE; i++) {
                    etu.creditOption.add((float) 0);
                }    
            }
        }
    }


	public void setupEffectif() {
		for(int i=0; i<optionData.size(); i++) {
			effectif.add(optionData.get(i).getEffectif());
		}
	}

	public void getChoiceforEveryone() {
		for (Etudiant etu : listeAffectation) {
			int meilleurIndex = -1;
			for(int i=0; i<nombreUE; i++) {
				etu.choice.add(-1);
			}
			for(int i=0; i<nombreUE; i++) {
				float meilleurChoix = -1;
				System.out.println("CO = " + etu.creditOption.size() + "------ nombre UE = " + nombreUE);
				for(int j=0; j<nombreUE;j++){

					if(meilleurChoix<etu.creditOption.get(j)){
						if(!isInArrayList(etu.choice, j)){
							meilleurChoix=etu.creditOption.get(j);
							meilleurIndex=j;
						}
					}
				}
				etu.choice.set(i, meilleurIndex);
			}
		}
	}

	public boolean isInArrayList(ArrayList<Integer> tab, int varCheck){
		for(int i=0; i<tab.size(); i++){
			if(tab.get(i)==varCheck){
				return true;
			}
		}
		return false;
	}

	/* Fonction pour regarder si un ï¿½lï¿½ment appartient au tableau */
	public boolean isInArray(int[] tab, int varCheck){
		for(int i=0; i<tab.length; i++){
			if(tab[i]==varCheck){
				return true;
			}
		}
		return false;
	}

	public void fillOptionList() {

		ArrayList<Etudiant> cpy = new ArrayList<Etudiant>(listeAffectation);

		for (int i = 0; i < nombreUE; i++) {
			optionList.add(cpy);
			cpy = new ArrayList<Etudiant>(cpy);

		}
	}

	public void triOptionList() {
		Etudiant tmp;
		for(int indiceUE=0; indiceUE<optionList.size(); indiceUE++){   // On tri chaque liste du tableau
			for(int i=0; i<optionList.get(indiceUE).size(); i++){
				for(int j=0; j<optionList.get(indiceUE).size(); j++){
					if(optionList.get(indiceUE).get(i).creditOption.get(indiceUE) > optionList.get(indiceUE).get(j).creditOption.get(indiceUE)){  // On compare les crï¿½dits pour trier par ordre dï¿½croissant
						tmp = optionList.get(indiceUE).get(i);         // On swap les deux ï¿½lï¿½ments du tableaux
						optionList.get(indiceUE).set(i, optionList.get(indiceUE).get(j));
						optionList.get(indiceUE).set(j, tmp);
					}
				}
			}
		}

	}

	// Si plusieus ï¿½tudiants ont le meme nombre de crï¿½dit pour matiï¿½re on les echange alï¿½atoirement pour pas avoir ordre AlphaB
	public void randomizeOptionList(){
		//System.out.println("in randomize optlist gen");
		for(int i=0; i<optionList.size(); i++){    // On doit mettre une touch d'alï¿½atoire pour chaque liste
			float currentCredit = -1;
			int nbRandom = 0;   // nombre d'ï¿½tudiant avec le meme nombre de crï¿½dit qu'on va devoir randomiser
			for(int j=0; j<optionList.get(i).size()-1; j++){   // On parcourt notre liste d'ï¿½tudiant
				if (currentCredit != optionList.get(i).get(j).creditOption.get(i)){ // Si on a pas dï¿½jï¿½ traitï¿½ le cas alors on le traite
					currentCredit = optionList.get(i).get(j).creditOption.get(i);
					int k=j+1;
					if(k<optionList.get(i).size()){    // Si on est pas a la fin de la liste
						while (this.optionList.get(i).get(k).creditOption.get(i) == currentCredit){ // On la parcours pour savoir combien d'autre ï¿½tudiant ont le meme nombre de crï¿½dit pour l'UE
							nbRandom ++;    // On incrï¿½mente notre nombre tant qu'on a le meme nombre de crï¿½dit
							k ++;
							if(k == this.optionList.get(i).size()){ // Quand on a passï¿½ le dernier ï¿½tudiant on arete
								break;
							}
						}
					}
					if(nbRandom > 0){   // Si on a plusieurs ï¿½tu avec le meme nombre 
						int[] randomizedRange = randomGeneration(j, (j+nbRandom));
						for(int change=0; change<randomizedRange.length; change++){ // Et on swap tout les ï¿½tu entre eux pour avoir un ordre !=
							Etudiant tmp = optionList.get(i).get(j+change);
							optionList.get(i).set(j+change, optionList.get(i).get(randomizedRange[change])) ;
							optionList.get(i).set(randomizedRange[change], tmp);
						}
					}   
				}
				nbRandom = 0; // On reset notre nombre d'ï¿½tu avec mï¿½me nombre de crï¿½dit

			}
		}
		//System.out.println("out randomize optlist gen");
	}

	/* Tous le processus d'affectation */
	public void affectEveryone(){
		//System.out.println("affect in");
		int remaining = nombreEtudiant;
		
		System.out.println("nombre etu = " + nombreEtudiant);

		while(remaining > 0){
			System.out.println("re="+remaining);
			Etudiant bestEtu = null;
			int besti = -1;
			float bestCredit = -1;
			for(int i=0; i<this.optionList.size(); i++){
				//System.out.println("effectif = " + effectif.size());
				//console.log(this.optionList[i][0]);
				if(effectif.get(i) > 0){
					if(bestCredit < optionList.get(i).get(0).creditOption.get(i)){
						bestEtu = optionList.get(i).get(0);
						besti = i;
						bestCredit = optionList.get(i).get(0).creditOption.get(i);
					}
				} 
			}

			for(int i=0; i<this.listeAffectation.size(); i++){
				//console.log(bestEtu + "VS " + this.listeAffectation[i]);
				System.out.println(i);
				if(listeAffectation.get(i).idEtudiant == bestEtu.idEtudiant){
					listeAffectation.get(i).affectation = besti;
				}
			}

			this.removeWhenAssigned(bestEtu);
			effectif.set(besti, effectif.get(besti)-1);

			remaining --;

		}
		//System.out.println("affect out");
	}

	/* On enlï¿½ve un ï¿½tudiant de toutes les listes a quand on lui affecte une option */
	public void removeWhenAssigned(Etudiant etudiantCourant){
		for(int i=0; i<this.nombreUE; i++){
			for(int j=0; j<optionList.get(i).size(); j++) {
				if(optionList.get(i).get(j).idEtudiant == etudiantCourant.idEtudiant) {
					optionList.get(i).remove(j);
				}
			}  
			//this.optionList[i] = arrayRemove(this.optionList[i], etudiantCourant);
		}
	}

	// on rempli le tableau tab de nombre appartenant ï¿½ [min;max] sans doublon dans un ordre alï¿½atoire
	public int[] randomGeneration(int min, int max){
		
		int tab[] = new int[max-min+1];
		int taille = max - min +1;
		for(int i=0; i<taille; i++) {
			tab[i] = -1;
		}
		Random randomGenerator = new Random();
		int rand = randomGenerator.nextInt((max - min) + 1) + min;
		for(int i=0; i<taille; i++){
			while(isInArray(tab, rand)){
				rand = randomGenerator.nextInt((max - min) + 1) + min;
			}
			if(!isInArray(tab, rand)){
				//System.out.println("taille = " + tab.length + " i = " + i);
				tab[i] = rand;
			}

		}
		return tab;
	}

	public void completerListeEtudiant(){
		ArrayList<Etudiant> tableau = new ArrayList<Etudiant>(listeAffectation);


		if(listeEtudiant.size() == 0){   // La liste est vide donc on vient de faire la premiï¿½re affectation
			for(int i=0; i<tableau.size(); i++){
				listeEtudiant.add(tableau.get(i));     // On ajoute les ï¿½tudiants de "tableau" ï¿½ notre listeEtudiants
				listeEtudiant.get(i).affectationS = new ArrayList<Integer>();
				listeEtudiant.get(i).affectationS.add(null);
				listeEtudiant.get(i).affectationS.set(compteurBricolage, listeEtudiant.get(i).affectation);
				listeEtudiant.get(i).nombreAffectation ++;
			}
		}   
		else{
            System.out.println("la liste est pas vide");
            for(int i=0; i<tableau.size(); i++){
                int checkEtudiant = 0;
                for(int j=0; j<listeEtudiant.size(); j++){
                	if(tableau.get(i).idEtudiant.equals(listeEtudiant.get(j).idEtudiant) ){   // On regarde si l'ï¿½tudiant est dï¿½jï¿½ dans la liste
                        if(listeEtudiant.get(j).nombreAffectation > 0){
                            System.out.println("test nombre affectation");
                            checkEtudiant = 1;
                            listeEtudiant.get(i).affectationS.add(null);
                            listeEtudiant.get(j).affectationS.set(compteurBricolage, tableau.get(i).affectation);
                            listeEtudiant.get(j).nombreAffectation ++;
                        }
                    }

                }
                if(checkEtudiant == 0){ // L'ï¿½tudiant n'est pas dans la liste donc on l'add
                    listeEtudiant.add(tableau.get(i));
                    listeEtudiant.get(listeEtudiant.size()-1).affectationS = new ArrayList<Integer>();
                    int fill = 0;
                    while(fill<compteurBricolage) {
                        listeEtudiant.get(listeEtudiant.size()-1).affectationS.add(-1);
                        listeEtudiant.get(listeEtudiant.size()-1).affectationS.set(fill, null);
                        fill++;
                    }
                    listeEtudiant.get(listeEtudiant.size()-1).affectationS.add(-1);
                    listeEtudiant.get(listeEtudiant.size()-1).affectationS.set(compteurBricolage, listeEtudiant.get(listeEtudiant.size()-1).affectation);
                }
            }
        }

		compteurBricolage ++;

		// On complï¿½te avec des null pour pas avoir des missing item
		for(int i=0; i<listeEtudiant.size(); i++){
			if(listeEtudiant.get(i).affectationS.size() != compteurBricolage){
				listeEtudiant.get(i).affectationS.add(null);
			}
		}


		//        // On supprime les informations inutiles de la liste
		//        for(var i=0; i<listeEtudiant.length; i++){
		////            delete listeEtudiant[i].creditOption;
		////            delete listeEtudiant[i].choice;
		////            delete listeEtudiant[i].affectation;
		//        }
		//        // On complï¿½te avec des null pour pas avoir des missing item
		//        for(var i=0; i<listeEtudiant.length; i++){
		//            if(listeEtudiant[i].affectationS.length != compteurBricolage){
		//                listeEtudiant[i].affectationS.push(null);
		//            }
		//        }
		//        // On remplace "empty item" par des null
		//        for(var i=0; i<listeEtudiant.length; i++){
		//            for(var j=0; j<listeEtudiant[i].affectationS.length; j++){
		//                if(listeEtudiant[i].affectationS[j] == undefined){
		//                    listeEtudiant[i].affectationS[j] = null;
		//                }
		//            }
		//        }  
		//        

	}

	public void afficherOptionList() {
		int i = 0;
		for (ArrayList<Etudiant> innerList : optionList) {
			System.out.println(i + " : ");
			for (Etudiant etu : innerList) {
				System.out.println(etu.nom);
			}
			i++;
		}
	}
	
	public void afficherAffectation() {
		for(int i=0; i<listeAffectation.size(); i++) {
			System.out.println(listeAffectation.get(i).getIdEtudiant() + " Affectation " + listeAffectation.get(i).affectation);			
		}
	}

	public String creerSortie() {
		resultatAffectation+="\n\n";
		for(int i=0; i<listeEtudiant.size(); i++) {
			resultatAffectation += "• " + listeEtudiant.get(i).nom;
			resultatAffectation += " " + listeEtudiant.get(i).prenom;
			resultatAffectation += " " + listeEtudiant.get(i).idEtudiant + " :\n";
			for(int j=0; j<listeEtudiant.get(i).affectationS.size(); j++) {
				if(listeEtudiant.get(i).affectationS.get(j) != null) {
					int indice = listeEtudiant.get(i).affectationS.get(j);
					//System.out.println("allAffectation = " + allAffectation.size());
					UE ue = allAffectation.get(j).optionData.get(indice);
					resultatAffectation += "        " + ue.getCodeModule() + " " + ue.getName() + "\n";
				}
			}

		}
		
		/*
		try {
			PrintWriter out = new PrintWriter("output" + File.separator + "resultatEtudiant.txt");
			out.print(resultatAffectation);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Echec de crï¿½ation sortie ï¿½tudiant");			
			e.printStackTrace();
		}
		*/
		//System.out.println(resultatAffectation);
		return resultatAffectation;
	}

	public String creerListeScolarite(){
		for(int i=0; i<affectationTab.size(); i++){
			resultatScolarite += "\n\n\t\t Option nï¿½" + i + ",";
			for(int j=0; j<allAffectation.get(i).optionData.size(); j++){
				UE current = allAffectation.get(i).optionData.get(j);
				resultatScolarite += "\n\t  * " + current.getName() + "\t" + current.getCodeModule() +",\n";
				for(int k=0; k<allAffectation.get(i).listeAffectation.size(); k++){
					if(allAffectation.get(i).listeAffectation.get(k).affectation == j){
						//console.log(allAffectation[i].listeAffectation[k].affectation);
						Etudiant currentEtu = allAffectation.get(i).listeAffectation.get(k);
						resultatScolarite += currentEtu.nom + " " + currentEtu.prenom + " " + currentEtu.idEtudiant + " ,\n";
					}
				}
			}
		}
		
//		try {
//			PrintWriter out = new PrintWriter("output" + File.separator + "resultatScolarite.txt");
//			out.print(resultatScolarite);
//			out.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("Echec de crï¿½ation sortie scolaritï¿½");			
//			e.printStackTrace();
//		}
		
		return resultatScolarite;
	}


	public void preventDoubleAffectationInitial(){
		//System.out.println("prevent in");
		for(int i=0; i<listeAffectation.size(); i++){
			float creditRedistribuer = 0;
			for(int find=0; find<listeEtudiant.size(); find++){
				if(listeAffectation.get(i).idEtudiant == listeEtudiant.get(find).idEtudiant){ // On cherche l'ï¿½tudiant dans la liste gï¿½nï¿½rale
					Etudiant current = listeAffectation.get(i);  // l'ï¿½tudiant courant sur l'affectation actuelle
					Etudiant same = listeEtudiant.get(find);     // le mï¿½me ï¿½tudiant sur une affectation antï¿½rieure
					for(int j=0; j<this.nombreUE; j++){
						if(current.creditOption.get(j) > 0){   // Pour chaque matiï¿½re ou il y a des crï¿½dits
							String codeUE = this.optionData.get(j).getCodeModule();
							for(int check=0; check<same.affectationS.size(); check++){  // On regarde dans toutes les affectations dï¿½ja faite
								Integer estAffecte = same.affectationS.get(check);
								if(estAffecte != null){
									if(codeUE == allAffectation.get(check).optionData.get(estAffecte).getCodeModule()){   // On a des crï¿½dits dans une matiï¿½re ou on est dï¿½jï¿½ affectï¿½
										creditRedistribuer = current.creditOption.get(j);
										int indiceOption = 0;
										while (creditRedistribuer > 0){     // On redistribue les credits
											if(current.choice.get(indiceOption) != j){
												int tmp = current.choice.get(indiceOption);
												if(current.creditOption.get(tmp) < ((this.nombreUE*2)*70)/100){ // infï¿½rieur ï¿½ 70% des credits totaux
													current.creditOption.set(tmp, current.creditOption.get(tmp) +1);
													creditRedistribuer --;
												}
												else indiceOption ++;
											}
											else indiceOption ++;
										}
										current.creditOption.set(j, (float) 0);
									}
								}
							}
						}
					}
				}
			}
		}
		//System.out.println("prevent out");
	}
	
	public void preventDoubleAffectation(){ // proportionellement
		//System.out.println("prevent in");
		for(int i=0; i<listeAffectation.size(); i++){
			float creditRedistribuer = 0;
			for(int find=0; find<listeEtudiant.size(); find++){
				if(listeAffectation.get(i).idEtudiant == listeEtudiant.get(find).idEtudiant){ // On cherche l'ï¿½tudiant dans la liste gï¿½nï¿½rale
					Etudiant current = listeAffectation.get(i);  // l'ï¿½tudiant courant sur l'affectation actuelle
					Etudiant same = listeEtudiant.get(find);     // le mï¿½me ï¿½tudiant sur une affectation antï¿½rieure
					for(int j=0; j<this.nombreUE; j++){
						if(current.creditOption.get(j) > 0){   // Pour chaque matiï¿½re ou il y a des crï¿½dits
							String codeUE = this.optionData.get(j).getCodeModule();
							for(int check=0; check<same.affectationS.size(); check++){  // On regarde dans toutes les affectations dï¿½ja faite
								Integer estAffecte = same.affectationS.get(check);
								if(estAffecte != null){
									if(codeUE == allAffectation.get(check).optionData.get(estAffecte).getCodeModule()){   // On a des crï¿½dits dans une matiï¿½re ou on est dï¿½jï¿½ affectï¿½
										creditRedistribuer = current.creditOption.get(j);
										float creditUtilise;
										if(nombreUE>5) {
											creditUtilise = ((nombreUE * (nombreUE - 1)) / 2) - creditRedistribuer;
										}
										else creditUtilise = 10 - creditRedistribuer;
										//while (creditRedistribuer > 0){     // On redistribue les credits
										
										for(int indiceOption = 0; indiceOption < nombreUE; indiceOption ++) {
											if(current.choice.get(indiceOption) != j){
												int tmp = current.choice.get(indiceOption);
												float pourcentage = (current.creditOption.get(tmp)) / creditUtilise;
												current.creditOption.set(tmp, current.creditOption.get(tmp) + (creditRedistribuer * pourcentage));

											}
										}
										current.creditOption.set(j, (float) 0);
									}
								}
							}
						}
					}
				}
			}
		}
		//System.out.println("prevent out");
	}


	public void affecter() {
		//readDataFromFile();
		
		System.out.println("init");
		init();
		System.out.println("noChoice");
		noChoice();
		System.out.println("printListeEtudiant");
		printListeEtudiant();
		System.out.println("setupEffectif");
		setupEffectif();
		System.out.println("getChoiceforEveryone");
		getChoiceforEveryone();
		System.out.println("preventDoubleAffectation");
		preventDoubleAffectation();
		getChoiceforEveryone();
		fillOptionList();
		afficherOptionList();
		triOptionList();
		randomizeOptionList();
		affectEveryone();
		afficherAffectation();
		buildSatisfaction(); 


		affectationTab.add(listeAffectation);
		completerListeEtudiant();



	}
	
	public void printListeEtudiant(){
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for(int i=0; i<listeAffectation.size(); i++){
            System.out.println("\n" + listeAffectation.get(i).getNom() + " " + listeAffectation.get(i).idEtudiant + " : ");
            for(int j=0; j<listeAffectation.get(i).creditOption.size(); j++) {
                System.out.print(listeAffectation.get(i).creditOption.get(j) + " ");
            }
        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
	
	
	public void createCSV(ArrayList<Affectation> allAff, String path) {
		try {
			FileWriter fw = new FileWriter(path);
			for(int i=0; i<listeEtudiant.size(); i++) {
				fw.write(listeEtudiant.get(i).nom + ";" + listeEtudiant.get(i).prenom + ";" + listeEtudiant.get(i).idEtudiant);
				for(int j=0; j<listeEtudiant.get(i).affectationS.size(); j++) {
					if(listeEtudiant.get(i).affectationS.get(j) == null) {
						fw.write(";" + "null");
					}
					else {
						int cur = listeEtudiant.get(i).affectationS.get(j);
						fw.write(";" + "(" + allAff.get(j).optionData.get(cur).getName() + " / " +  allAff.get(j).optionData.get(cur).getCodeModule() + ")");
					}
				}
				fw.write("\r");
				
			}
			fw.close();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void buildSatisfaction(){ 
        statAffect += "\nOption numéro " + numAffectation;
        float tabStat [] = new float[this.nombreUE]; 
        for(int i=0; i<this.nombreUE; i++){ 
            tabStat[i] = 0; 
        } 
        for(int i=0; i<this.nombreEtudiant; i++){ 
            for(int j=0; j<this.nombreUE; j++){ 
                if(this.listeAffectation.get(i).choice.get(j) == this.listeAffectation.get(i).affectation){ 
                    tabStat[j]++; 
                } 
            } 
        } 
         
        for(int i=0; i<this.nombreUE; i++){ 
            statAffect += ("\nCHOIX n" + i + " : " + tabStat[i] + " sur " + this.nombreEtudiant +  
            " etudiants = " + (tabStat[i]/this.nombreEtudiant)*100 + "% "); 
        } 
    }  
     
     
    public static String getSatisfaction() { 
    	return statAffect; 
    } 
     
    
    
	public static void createPDF(String etu, FileOutputStream path) {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20 );		
        try {
        	PdfWriter.getInstance(document, path);
			//PdfWriter.getInstance(document, new FileOutputStream(path + "output" + File.separator + "listeEtudiant.pdf"));

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLACK);
		Chunk chunk = new Chunk("\n\n                                 Résultat des affectations des étudiants :", font);	
		Font font2 = FontFactory.getFont(FontFactory.defaultEncoding, 14, BaseColor.BLACK);
		Chunk chunk2 = new Chunk(etu, font2);
		
		try {
			document.add(new Paragraph(chunk));
			document.add(new Paragraph(chunk2));

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
	}
	
	public static void createPDFSco(String sco, FileOutputStream path) {
		
		/***
		 * Liste scolarité
		 */
		
        Document document2 = new Document(PageSize.A4, 20, 20, 20, 20 );		
        try {
			PdfWriter.getInstance(document2, path);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("ooooooooooooook");
		 
		document2.open();
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLACK);
		Font font2 = FontFactory.getFont(FontFactory.defaultEncoding, 14, BaseColor.BLACK);
		
		Chunk chunkSco = new Chunk("\n\n                                 Résultat des affectations par UE :", font);	
		Chunk chunkSco2 = new Chunk(sco, font2);	

		
		try {
			document2.add(new Paragraph(chunkSco));
			document2.add(new Paragraph(chunkSco2));

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document2.close();
		
		System.out.println("okok");
	}
	
//	
//	public static void main(String[] args) {
//
//		createPDF("a", "b", "");
//	}
//	
//	public void algo(int size) {
//		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//		for (int i = 1; i <= size; i++) {
//			Affectation a = new Affectation(i);
//			a.affecter();
//			allAffectation.add(a);
//			System.out.println("ADD");
//		}
//		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//		allAffectation.get(1).creerSortie();
//		allAffectation.get(1).creerListeScolarite();
//		System.out.println("fichier de sortie crï¿½ï¿½e");
//		//createPDF();
//	}
}
