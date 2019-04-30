package Calcul.Calcul.algorithms.calcul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;

/**
 * La classe chargée de faire l'affectation 
 * @author VALLET Mat
 */
public class Affectop {
	int nbDays;
	ArrayList<Student> students;
	ArrayList<ArrayList<Option>> options;

	/**
	 * Constructeur de la classe
	 * @param students les étudiants à affecter
	 * @param options  les options par ensembles
	 * @param nbDays   le nombre d'ensembles d'options
	 * @return le resultat du constructeur
	 */
	public Affectop(ArrayList<Student> students,ArrayList<ArrayList<Option>> options ,int nbDays) {
		this.students = students;
		this.options = options;
		this.nbDays = nbDays;
	}

	/**
	 * @param availableOptions la map des options disponibles des étudiants
	 * @param incompatibilities la liste des incompatibilités en 
	 * @param redoublements les options qu'un étudiant redouble
	 */
	public void handleRepeaterIncompabilities(HashMap<Student, LinkedList<Option>> availableOptions, HashMap<Option, LinkedList<Option>> incompatibilities, Map<Student,ArrayList<Option>> redoublements){
		for(Student s : redoublements.keySet()) {
			for(Option opt : redoublements.get(s)) {
				availableOptions.get(s).removeAll(incompatibilities.get(opt));
				availableOptions.get(s).remove(opt);
			}
		}
	}
	
	
	/**
	 * @param 	s l'etudiant dont on va retirer l'option a laquelle il a été affecté qu'il aime le moins
	 * @return	l'option retiree
	 */
	Option removeOptionLeastPrefered(Student s) {
		int leastFavoriteDay=0;
		int todayRank;
		Option removed;
		for(int d = 0 ; d < s.affected.length ; d++) {
			todayRank = s.preferences.get(d).indexOf(s.affected[d]);
			if(todayRank > leastFavoriteDay || todayRank == leastFavoriteDay
					&& s.affected[leastFavoriteDay].accepted.size() > s.affected[todayRank].accepted.size()) {
				leastFavoriteDay = d;
			}
		}
		removed = s.affected[leastFavoriteDay];
		s.affected[leastFavoriteDay] = null;
		
		return removed;
	}
	
	/**
	 * @param availableOptions le dictionnaire des options disponibles
	 * @param incompatibilities le dictionnaire des incompatibilites
	 * @param redoublements le dictionnaire des redoublements
	 */
	public void handleRepeaterAffectation(HashMap<Student, LinkedList<Option>> availableOptions, HashMap<Option, LinkedList<Option>> incompatibilities, Map<Student,ArrayList<Option>> redoublements){
		ArrayList<Option> optionsNotFull = new ArrayList<>();
		for(Student s : redoublements.keySet()) {
			for(int optionRedoublee = 0 ; optionRedoublee < redoublements.get(s).size();optionRedoublee++)
				optionsNotFull.add(removeOptionLeastPrefered(s));
		}
	}

	/**
	 * Affecte les etudiants à une option d'un ensemble d'option en tenant compte des incompatibilités avec les options precedement affectees
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @param d l'index de l'ensemble (le jour) dans lequel sont choisis les options
	 * @param available le dictionnaire des options disponibles  (clé : un(e) etudiant(e), valeur : toutes les options qui lui sont disponibles)
	 */
	private void affectOneDay(HashMap<Option, LinkedList<Option>> incompatibilities, int d) {
		HashMap<Student, LinkedList<Option>> availableOption = new HashMap<>();
		for(Student s : students) {
			LinkedList<Option> optToday = new LinkedList<Option>(s.preferences.get(d));
			for(int i = 0; i < s.affected.length ; i ++)
				if(s.affected[i] != null)
					optToday.removeAll(incompatibilities.get(s.affected[i]));
			availableOption.put(s,optToday);
		}		
		SimpleAffectop simpAffect = new SimpleAffectop(students,options.get(d),availableOption,d);
		simpAffect.mariagesStable();
		if(!simpAffect.isStable(students)) {
			System.out.println("ERREUR FATALE : PAS STABLE");
		}
	}
	
	/**
	 * Retourne la satisfaction totale de l'affectation courante
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @return la satisfaction de l'affectation
	 */
	private int getSatisfaction() {
		int satisfaction = 0;
		for(Student s : students)
			satisfaction+= s.getSatisfaction();
		satisfaction /= students.size();
		return satisfaction * satisfaction;
	}
	
	/**
	 * Affecte les etudiants à une option par ensemble ensemble d'options en tenant compte des incompatibilités
	 * @param incompatibilities le dictionnaire des incompatibilités entre options (clé : une option, valeur : toutes les options qui lui sont incompatibles)
	 * @return la satisfaction de l'affectation
	 */
	public int affectStable(HashMap<Option, LinkedList<Option>> incompatibilities) {		
		LinkedList<Integer> days = new LinkedList<>();
		for(int d = 0 ; d < nbDays; d ++) {
			days.add(d);
			Collections.shuffle(days);
			affectOneDay(incompatibilities, d);
		}
		return getSatisfaction();
	}
	
	/**
	 * Launch several times the algorithm to get the best result
	 * @param students the students
	 * @param options the options
	 * @param incompatibilities the incompatibilities beteween options
	 * @param nbDays the number of group of options
	 * @param nbLaunch the number of times you launch the algorithm to get the best affect
	 * @return the best affect
	 */
	public static HashMap<Student,Option[]> affectTop(ArrayList<Student> students, ArrayList<ArrayList<Option>> options, HashMap<Option, LinkedList<Option>> incompatibilities, int nbDays, int nbLaunch) {
		Affectop aff;
		long bestScore = 0;
		HashMap<Student,Option[]> affect = new HashMap<>();  
		for(int i = 0 ; i < nbLaunch ;i++) {
			aff = new Affectop(students, options, nbDays);
			long affectScore = aff.affectStable(incompatibilities);
			
			System.out.println(affectScore+"/"+bestScore);

			if(bestScore < affectScore) {
				bestScore = affectScore;
				affect.clear();
				for(Student s : students) {
					Option[] sOptions = s.affected.clone();
					affect.put(s,sOptions);
				}
				
			}
		}
		return affect;
	}
}
