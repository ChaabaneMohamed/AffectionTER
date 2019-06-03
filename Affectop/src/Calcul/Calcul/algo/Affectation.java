package Calcul.Calcul.algo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

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


	private int numAffectation;
	private int nombreUE; // Nombre d'UE propos� pour une journ�e d'option
	private int nombreEtudiant; // Nombre d'�tudiant qui participe a une journ�e d'option
	private ArrayList<Etudiant> listeAffectation; // Liste des �tudiants qu'on doit affecter
	private ArrayList<UE> optionData; // Tableau de toute les UE d'un jour d'option
	private ArrayList<Integer> effectif; // R�cup�re les effectifs des UE pour les changer a chaque affectation
	private ArrayList<ArrayList<Etudiant>> optionList;
	public  ArrayList<Affectation> allAffectation;


	public Affectation(int numAffectation, ArrayList<Affectation> aff ) {
		this.numAffectation = numAffectation; // Le fichier qui contient toutes les informations pour affecter
		this.allAffectation = aff;
		optionData = new ArrayList<UE>();
		effectif = new ArrayList<Integer>();
		optionList = new ArrayList<ArrayList<Etudiant>>();
		
		

		// Tableau de tableau avec la liste des �tudiants tri� par cr�dit pour chaque
		// jour d'option
	}
	
	public Affectation() {
	}
	
	void init() {
		BaseReader basereader = new BaseReader();
		ArrayList<Option> allUE = basereader.getOptionsPerDays(numAffectation);
        ArrayList<Student> eleves = basereader.getStudents(2017);
        
        ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();

        for (Student s : eleves) {
        	ArrayList<Integer> choix = basereader.getChoiceForOneStudentOneGroup(s.getNumEtudiant(), numAffectation);
        	Etudiant e = new Etudiant(s.getNom(), s.getPrenom(), Integer.toString(s.getNumEtudiant()), choix);
			etudiants.add(e);
			//System.out.println("etu|"+e.getIdEtudiant()+"|"+e.creditOption.toString());
		}
        listeAffectation = new ArrayList<Etudiant>(etudiants);
        
        ArrayList<UE> UEs = new ArrayList<UE>();
        for (Option u : allUE) {
        	UEs.add(new UE(u.getCodeModule(), 
					u.getNom(), 
					u.getSize()));
		}
        optionData = UEs;
        
        nombreEtudiant = listeAffectation.size();
        nombreUE = optionData.size();
        System.out.println("FIN INIT ______________________________________________________");
	}

	void readDataFromFile() {
		waitingForDataRead();
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
                    etu.creditOption.add(0);
                }    
            }
        }
    }

	void waitingForDataRead() {
		if (this.numAffectation == 1) { // Data a la con pour test
			this.nombreUE = 4;
			this.nombreEtudiant = 5;

			Etudiant Etu1 = new Etudiant();
			Etu1.setNom("Ahmed");
			Etu1.setIdEtudiant("1234");
			int[] data = { 4, 1, 3, 0 };
			for (int i = 0; i < data.length; i++) {
				Etu1.creditOption.add(data[i]);
			}

			Etudiant Etu2 = new Etudiant();
			Etu2.setNom("Brandon");
			Etu2.setIdEtudiant("100");
			int[] data2 = { 2, 3, 0, 3 };
			for (int i = 0; i < data2.length; i++) {
				Etu2.creditOption.add(data2[i]);
			}

			Etudiant Etu3 = new Etudiant();
			Etu3.setNom("Amine");
			Etu3.setIdEtudiant("0");
			int[] data3 = { 2, 2, 2, 2 };
			for (int i = 0; i < data3.length; i++) {
				Etu3.creditOption.add(data3[i]);
			}

			Etudiant Etu4 = new Etudiant();
			Etu4.setNom("Rodolphe");
			Etu4.setIdEtudiant("999");
			int[] data4 = { 1, 1, 4, 2 };
			for (int i = 0; i < data4.length; i++) {
				Etu4.creditOption.add(data4[i]);
			}

			Etudiant Etu5 = new Etudiant();
			Etu5.setNom("JJ");
			Etu5.setIdEtudiant("1997");
			int[] data5 = { 0, 2, 2, 4 };
			for (int i = 0; i < data5.length; i++) {
				Etu5.creditOption.add(data5[i]);
			}

			this.listeAffectation.add(Etu1);
			this.listeAffectation.add(Etu2);
			this.listeAffectation.add(Etu3);
			this.listeAffectation.add(Etu4);
			this.listeAffectation.add(Etu5);

			this.optionData.add(new UE("LNAMU2018", "Langage Natuel", 2));
			this.optionData.add(new UE("IAAMU2018", "Intelligence artificielle", 2));
			this.optionData.add(new UE("PIAAMU2018", "Projet informatique appliqu�", 2));
			this.optionData.add(new UE("GLAMU2018", "G�nie Logiciel", 2));


		}

		else if(this.numAffectation == 2){

			this.nombreUE = 5;
			this.nombreEtudiant = 6;

			Etudiant Etu1 = new Etudiant();
			Etu1.setNom("Ahmed");
			Etu1.setIdEtudiant("1234");
			int[] data = { 4, 1, 3, 0, 2};
			for (int i = 0; i < data.length; i++) {
				Etu1.creditOption.add(data[i]);
			}

			Etudiant Etu2 = new Etudiant();
			Etu2.setNom("Brandon");
			Etu2.setIdEtudiant("100");
			int[] data2 = { 2, 5, 0, 3, 0};
			for (int i = 0; i < data2.length; i++) {
				Etu2.creditOption.add(data2[i]);
			}

			Etudiant Etu3 = new Etudiant();
			Etu3.setNom("Amine");
			Etu3.setIdEtudiant("0");
			int[] data3 = { 2, 2, 2, 2, 2};
			for (int i = 0; i < data3.length; i++) {
				Etu3.creditOption.add(data3[i]);
			}

			Etudiant Etu4 = new Etudiant();
			Etu4.setNom("Rodolphe");
			Etu4.setIdEtudiant("999");
			int[] data4 = { 0, 1, 4, 2, 3};
			for (int i = 0; i < data4.length; i++) {
				Etu4.creditOption.add(data4[i]);
			}

			Etudiant Etu5 = new Etudiant();
			Etu5.setNom("JJ");
			Etu5.setIdEtudiant("1997");
			int[] data5 = { 0, 2, 2, 2, 4 };
			for (int i = 0; i < data5.length; i++) {
				Etu5.creditOption.add(data5[i]);
			}

			Etudiant Etu6 = new Etudiant();
			Etu6.setNom("agane");
			Etu6.setIdEtudiant("300");
			int[] data6 = { 0, 2, 4, 0, 4 };
			for (int i = 0; i < data5.length; i++) {
				Etu6.creditOption.add(data6[i]);
			}

			this.listeAffectation.add(Etu1);
			this.listeAffectation.add(Etu2);
			this.listeAffectation.add(Etu3);
			this.listeAffectation.add(Etu4);
			this.listeAffectation.add(Etu5);
			this.listeAffectation.add(Etu6);


			this.optionData.add(new UE("LNAMU2018", "Langage Natuel", 4));
			this.optionData.add(new UE("IAAMU2018", "Intelligence artificielle", 4));
			this.optionData.add(new UE("PIAAMU2018", "Projet informatique appliqu�",4));
			this.optionData.add(new UE("JEEAMU2019", "JEE", 4));
			this.optionData.add(new UE("TERAMU2019", "TER",4));

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
				int meilleurChoix = -1;
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

	/* Fonction pour regarder si un �l�ment appartient au tableau */
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
					if(optionList.get(indiceUE).get(i).creditOption.get(indiceUE) > optionList.get(indiceUE).get(j).creditOption.get(indiceUE)){  // On compare les cr�dits pour trier par ordre d�croissant
						tmp = optionList.get(indiceUE).get(i);         // On swap les deux �l�ments du tableaux
						optionList.get(indiceUE).set(i, optionList.get(indiceUE).get(j));
						optionList.get(indiceUE).set(j, tmp);
					}
				}
			}
		}

	}

	// Si plusieus �tudiants ont le meme nombre de cr�dit pour mati�re on les echange al�atoirement pour pas avoir ordre AlphaB
	public void randomizeOptionList(){
		//System.out.println("in randomize optlist gen");
		for(int i=0; i<optionList.size(); i++){    // On doit mettre une touch d'al�atoire pour chaque liste
			int currentCredit = -1;
			int nbRandom = 0;   // nombre d'�tudiant avec le meme nombre de cr�dit qu'on va devoir randomiser
			for(int j=0; j<optionList.get(i).size()-1; j++){   // On parcourt notre liste d'�tudiant
				if (currentCredit != optionList.get(i).get(j).creditOption.get(i)){ // Si on a pas d�j� trait� le cas alors on le traite
					currentCredit = optionList.get(i).get(j).creditOption.get(i);
					int k=j+1;
					if(k<optionList.get(i).size()){    // Si on est pas a la fin de la liste
						while (this.optionList.get(i).get(k).creditOption.get(i) == currentCredit){ // On la parcours pour savoir combien d'autre �tudiant ont le meme nombre de cr�dit pour l'UE
							nbRandom ++;    // On incr�mente notre nombre tant qu'on a le meme nombre de cr�dit
							k ++;
							if(k == this.optionList.get(i).size()){ // Quand on a pass� le dernier �tudiant on arete
								break;
							}
						}
					}
					if(nbRandom > 0){   // Si on a plusieurs �tu avec le meme nombre 
						int[] randomizedRange = randomGeneration(j, (j+nbRandom));
						for(int change=0; change<randomizedRange.length; change++){ // Et on swap tout les �tu entre eux pour avoir un ordre !=
							Etudiant tmp = optionList.get(i).get(j+change);
							optionList.get(i).set(j+change, optionList.get(i).get(randomizedRange[change])) ;
							optionList.get(i).set(randomizedRange[change], tmp);
						}
					}   
				}
				nbRandom = 0; // On reset notre nombre d'�tu avec m�me nombre de cr�dit

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
			Etudiant bestEtu = null;
			int besti = -1;
			int bestCredit = -1;
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

	/* On enl�ve un �tudiant de toutes les listes a quand on lui affecte une option */
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

	// on rempli le tableau tab de nombre appartenant � [min;max] sans doublon dans un ordre al�atoire
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


		if(listeEtudiant.size() == 0){   // La liste est vide donc on vient de faire la premi�re affectation
			for(int i=0; i<tableau.size(); i++){
				listeEtudiant.add(tableau.get(i));     // On ajoute les �tudiants de "tableau" � notre listeEtudiants
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
                	if(tableau.get(i).idEtudiant.equals(listeEtudiant.get(j).idEtudiant) ){   // On regarde si l'�tudiant est d�j� dans la liste
                        if(listeEtudiant.get(j).nombreAffectation > 0){
                            System.out.println("test nombre affectation");
                            checkEtudiant = 1;
                            listeEtudiant.get(i).affectationS.add(null);
                            listeEtudiant.get(j).affectationS.set(compteurBricolage, tableau.get(i).affectation);
                            listeEtudiant.get(j).nombreAffectation ++;
                        }
                    }

                }
                if(checkEtudiant == 0){ // L'�tudiant n'est pas dans la liste donc on l'add
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

		// On compl�te avec des null pour pas avoir des missing item
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
		//        // On compl�te avec des null pour pas avoir des missing item
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
		for(int i=0; i<listeEtudiant.size(); i++) {
			resultatAffectation += "- " + listeEtudiant.get(i).nom;
			resultatAffectation += " " + listeEtudiant.get(i).prenom;
			resultatAffectation += " " + listeEtudiant.get(i).idEtudiant + " :\n";
			for(int j=0; j<listeEtudiant.get(i).affectationS.size(); j++) {
				if(listeEtudiant.get(i).affectationS.get(j) != null) {
					int indice = listeEtudiant.get(i).affectationS.get(j);
					//System.out.println("allAffectation = " + allAffectation.size());
					UE ue = allAffectation.get(j).optionData.get(indice);
					resultatAffectation += "\t\t" + ue.getCodeModule() + " " + ue.getName() + "\n";
				}
			}

		}
		
		/*
		try {
			PrintWriter out = new PrintWriter("output" + File.separator + "resultatEtudiant.txt");
			out.print(resultatAffectation);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("Echec de cr�ation sortie �tudiant");			
			e.printStackTrace();
		}
		*/
		//System.out.println(resultatAffectation);
		return resultatAffectation;
	}

	public String creerListeScolarite(){
		for(int i=0; i<affectationTab.size(); i++){
			resultatScolarite += "\n\n\t\t Option n�" + i + ",";
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
//			System.out.println("Echec de cr�ation sortie scolarit�");			
//			e.printStackTrace();
//		}
		
		return resultatScolarite;
	}


	public void preventDoubleAffectation(){
		//System.out.println("prevent in");
		for(int i=0; i<listeAffectation.size(); i++){
			int creditRedistribuer = 0;
			for(int find=0; find<listeEtudiant.size(); find++){
				if(listeAffectation.get(i).idEtudiant == listeEtudiant.get(find).idEtudiant){ // On cherche l'�tudiant dans la liste g�n�rale
					Etudiant current = listeAffectation.get(i);  // l'�tudiant courant sur l'affectation actuelle
					Etudiant same = listeEtudiant.get(find);     // le m�me �tudiant sur une affectation ant�rieure
					for(int j=0; j<this.nombreUE; j++){
						if(current.creditOption.get(j) > 0){   // Pour chaque mati�re ou il y a des cr�dits
							String codeUE = this.optionData.get(j).getCodeModule();
							for(int check=0; check<same.affectationS.size(); check++){  // On regarde dans toutes les affectations d�ja faite
								Integer estAffecte = same.affectationS.get(check);
								if(estAffecte != null){
									if(codeUE == allAffectation.get(check).optionData.get(estAffecte).getCodeModule()){   // On a des cr�dits dans une mati�re ou on est d�j� affect�
										creditRedistribuer = current.creditOption.get(j);
										int indiceOption = 0;
										while (creditRedistribuer > 0){     // On redistribue les credits
											if(current.choice.get(indiceOption) != j){
												int tmp = current.choice.get(indiceOption);
												if(current.creditOption.get(tmp) < ((this.nombreUE*2)*70)/100){ // inf�rieur � 70% des credits totaux
													current.creditOption.set(tmp, current.creditOption.get(tmp) +1);
													creditRedistribuer --;
												}
												else indiceOption ++;
											}
											else indiceOption ++;
										}
										current.creditOption.set(j, 0);
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
		init();
		noChoice();
		printListeEtudiant();
		setupEffectif();
		getChoiceforEveryone();
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
	
	
	public void createCSV(ArrayList<Affectation> allAff) {
		try {
			FileWriter fw = new FileWriter("output" + File.separator + "Affectation.csv");
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
        int tabStat [] = new int[this.nombreUE]; 
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
            statAffect += ("CHOIX n" + i + " : " + tabStat[i] + " sur " + this.nombreEtudiant +  
            " etudiants = " + (tabStat[i]/this.nombreEtudiant)*100 + "% "); 
        } 
    } 
     
     
    public static String getSatisfaction() { 
    	return statAffect; 
    } 
     
    
    
	public void createPDF(String etu, String sco) {
        Document document = new Document(PageSize.A4, 20, 20, 20, 20 );		
        try {
			PdfWriter.getInstance(document, new FileOutputStream("output" + File.separator + "listeEtudiant.pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("\n\n                                 R�sultat des affectations des �tudiants :", font);	
		Font font2 = FontFactory.getFont(FontFactory.defaultEncoding, 16, BaseColor.BLACK);
		Chunk chunk2 = new Chunk(etu, font2);
		
		try {
			document.add(new Paragraph(chunk));
			document.add(new Paragraph(chunk2));

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		
		/***
		 * Liste scolarit�
		 */
		
        Document document2 = new Document(PageSize.A4, 20, 20, 20, 20 );		
        try {
			PdfWriter.getInstance(document2, new FileOutputStream("output" + File.separator + "listeScolarite.pdf"));
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		document2.open();
		Chunk chunkSco = new Chunk("\n\n                                 R�sultat des affectations par UE :", font);	
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
	
//	public static void main(String[] args) {
////		System.out.println("aaaaaaaaaaaa");
//		
//		Affectation a = new Affectation(1);
//		a.affecter();
//		allAffectation.add(a);
//		
//		//System.out.println("bbbbbbbbbbbb");
//
//		Affectation b = new Affectation(2);
//		b.affecter();
//		allAffectation.add(b);
//
//		System.out.println("Sortie normale :");
//		System.out.println(b.creerSortie());
////
//		System.out.println("\n\n\nSortie scolarit� :");
//		System.out.println(b.creerListeScolarite());
//		
//		b.creerSortie();
//		b.creerListeScolarite();
//		System.out.println("fichier de sortie cr��e");
//		createPDF();
//	}
	
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
//		System.out.println("fichier de sortie cr��e");
//		//createPDF();
//	}
}
