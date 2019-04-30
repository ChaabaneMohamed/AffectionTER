package Calcul.Calcul.algorithms.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;

import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;
import Calcul.Calcul.algorithms.calcul.Affectop;


public class AffectopTest {
	
	
	private boolean areIncompatibles(Option opt1, Option opt2,HashMap<Option, LinkedList<Option>> incompatibilities) {
		return opt1.day == opt2.day || incompatibilities.get(opt1).contains(opt2) ||incompatibilities.get(opt2).contains(opt1);
	}
	
	static boolean respectsIncompatibilities(ArrayList<Student> students,HashMap<Option,LinkedList<Option>> incompatibilities) {
		for(Student s : students)
			if(!studentRespectsIncompatibilities(s,incompatibilities))
				return false;
		return true;
	}
	static boolean studentRespectsIncompatibilities(Student s,HashMap<Option,LinkedList<Option>> incompatibilities) {
		for(int optIndex=0 ; optIndex < s.nbDays ;optIndex++ ) {
			Option opt = s.affected[optIndex];
			if(opt == null) continue;
			for(int optCompatible=0 ; optCompatible < s.nbDays ; optCompatible++ ) {
				Option compatible = s.affected[optCompatible] ;
				if(compatible == null) continue;
				if(incompatibilities.get(opt).contains(compatible) && opt != compatible) {
					System.out.println("INCOMPATIBILITE");
					System.out.println("opt: "+opt +" "+compatible);
					System.out.println("st : "+s+"   "+s.affected);
					return false;
				}
			}
		}
		return true;
	}
	
	boolean respectConstraints(ArrayList<Student> students,ArrayList<ArrayList<Option>> options, int nbDays, HashMap<Option, LinkedList<Option>> incompatibilities) {
		for(Student s : students)
			for(Option o1 : s.affected)
				for(Option o2 : s.affected)
					if(o1 != o2 && areIncompatibles(o1, o2,incompatibilities)) {
						System.out.println("incompatibles :"+s);
						return false;
					}
		for(ArrayList<Option> optToday : options)
			for(Option opt : optToday)
				if(opt.accepted.size() > opt.size) {
					System.out.println("oversized : "+opt.accepted.size()+" "+opt.size);
					return false;
				}
		return true;
	}
	
	LinkedList<Option> makeOptList(int[] preference,int optionsSize, ArrayList<Option> options){
		LinkedList<Option> pref = new LinkedList<Option>();
		for(int i = 0 ; i < optionsSize; i ++) {
			pref.addLast(options.get(preference[i]-1));
		}
		return pref;
	}
	
	boolean areWillingToExchange(Student s1,Option opt1, Student s2,Option opt2,int group) {
		if(s1.isAffectedTo(opt2) || s2.isAffectedTo(opt1))
			return false;
		for(Option o : s1.affected)
			if(o!=null)
				if(o!= opt1 && (opt2.day == o.day || opt2.nom == o.nom)) {
					return false;
				}
		for(Option o : s2.affected)
			if(o!=null)
				if(o!= opt2 && opt1.day == o.day || opt1.nom == o.nom) {
					return false;
				}
		return false;
	}

	static boolean incompatibilitiesContains(Student s, Option opt, HashMap<Option, LinkedList<Option>> incompatibilities) {
		for(Option opt_affected : s.affected)
			if(opt_affected!=null)
				if(incompatibilities.get(opt_affected).contains(opt))
					return true;
		return false;
	}
	
	
	public static ArrayList<Student> randomStudents(int nbStudents, ArrayList<ArrayList<Option>> options, int nbDays){
		ArrayList<Student> result = new ArrayList<>();
		for(int s = 0 ; s < nbStudents; s ++) {
			ArrayList<LinkedList<Option>> preferences = new ArrayList<>();
			for(int d = 0 ; d < nbDays; d ++) {
				LinkedList<Option> choicesToday = new LinkedList<Option>();
				choicesToday.addAll(options.get(d));
				Collections.shuffle(choicesToday);
				preferences.add(choicesToday);
			}
			result.add(new Student("s"+s, preferences,nbDays));
		}
		return result;
	}
	
	HashMap<Option, LinkedList<Option>> makeEmptyIncompatibilities(ArrayList<ArrayList<Option>> options) {
		HashMap<Option, LinkedList<Option>> incompatibilities = new HashMap<>();
		for(ArrayList<Option> optToday : options) {
			for(Option opt : optToday)
				incompatibilities.put(opt, new LinkedList<>());
		}
		return incompatibilities;
	}
	
	// créé une incompatibilité par jour 
	public static HashMap<Option, LinkedList<Option>> makeRandomIncompatibilities(ArrayList<ArrayList<Option>> options) {
		HashMap<Option, LinkedList<Option>> incompatibilities = new HashMap<>();
		Random r = new Random();
		for(int d = 0; d < options.size() ; d ++) {
			for(Option opt : options.get(d))
			incompatibilities.put(opt, new LinkedList<>());
		}
		
		for(int d1 = 0; d1 < options.size() ; d1 ++) {
			for(Option opt1 : options.get(d1)) {
				for(int d2 = 0; d2 < options.size() ; d2 ++) {
					if(d1 == d2)
						continue;
					int randIndex = Math.abs(r.nextInt()%(options.get(d2).size()));
					Option opt2 = options.get(d2).get(randIndex);//une option au hasard dans le groupement numéroté par d2
					incompatibilities.get(opt1).add(opt2);//incompatibilités mutuelles
					incompatibilities.get(opt2).add(opt1);//incompatibilités mutuelles
				}
			}
		}
		return incompatibilities;	
	}
	
	public static ArrayList<ArrayList<Option>> randomOptions(int nbDays, int[]minNbOptions,int[]maxNbOptions, int[]minSizeOptions,int[]maxSizeOptions){
		ArrayList<ArrayList<Option>> answers = new ArrayList<ArrayList<Option>>();
		
		Random r = new Random();
		
		for(int d = 0; d < nbDays; d++) {
			ArrayList<Option> todayOptions = new ArrayList<>();
			
			int nbOptionToday = minNbOptions[d]+Math.abs(r.nextInt()%(1+maxNbOptions[d]-minNbOptions[d]));
			for(int o = 0; o < nbOptionToday ; o++) {
				int opSize = minSizeOptions[d]+Math.abs(r.nextInt()%(1+maxSizeOptions[d]-minSizeOptions[d]));
				todayOptions.add(new Option(opSize, "o"+o+"_"+d, d, 0));
			}
			answers.add(todayOptions);
		}
		return answers;
	}
	
	
	@Test
	public void randTest() {
		int nbDays = 3;
		ArrayList<ArrayList<Option>> options= randomOptions(nbDays,new int[]{3,3,3},new int[]{5,5,5},new int[]{15,15,15},new int[]{20,20,29});
		ArrayList<Student> students = randomStudents(150,options, nbDays);
		HashMap<Option, LinkedList<Option>> incompatibilities = makeRandomIncompatibilities(options);
		for(ArrayList<Option> opts : options)
			for(Option opt : opts)
				incompatibilities.put(opt,new LinkedList<>());
		for(Student s : students)
			for(int d = 0 ; d < nbDays ; d ++)
			s .affected[d] = null;
		for (ArrayList<Option> optionToday : options) {
			for(Option o : optionToday)
				o.accepted.clear();
		}			
		Affectop currentAffect = new Affectop(students, options,nbDays);
		currentAffect.affectStable(incompatibilities);			
		assertTrue(respectsIncompatibilities(students,incompatibilities));
	}
	
	@Test
	public  void smallRandTest() {
		int nbDays = 2;
		ArrayList<ArrayList<Option>> options= randomOptions(nbDays,new int[]{2,2},new int[]{3,3},new int[]{2,2},new int[]{3,3});
		ArrayList<Student> students = randomStudents(10,options, nbDays);
		HashMap<Option, LinkedList<Option>> incompatibilities = makeRandomIncompatibilities(options);
		System.out.println(incompatibilities);

		//for(int i = 0 ; i < 10 ;i++){
			for(Student s : students)
				for(int d = 0 ; d < nbDays ; d ++)
					s .affected[d] = null;			
			for (ArrayList<Option> optionToday : options) {
				for(Option o : optionToday)
					o.accepted.clear();
			}
			
			Affectop currentAffect = new Affectop(students, options,nbDays);
			currentAffect.affectStable(incompatibilities);
			
			
			for(int d = 0 ; d < nbDays ; d++) {
				System.out.println(d+1+":");
				for(Option opt: options.get(d)) 
					System.out.println(opt.accepted.size()+"/"+opt.size);
			}
			assertTrue(respectsIncompatibilities(students,incompatibilities));
	}
	

	@Test
	public void multipleCall() {
		for(int i = 0 ; i < 100 ; i ++) {
			System.out.println("==RANDTEST== "+i);
			randTest();
		}
	}
	
	@Test 
	public void affectTopTest() {
		int nbDays = 5;
		ArrayList<ArrayList<Option>> options= randomOptions(nbDays,new int[]{2,2,2,2,2},new int[]{4,4,4,4,4},new int[]{40,40,40,40,40},new int[]{50,50,50,50,50});
		ArrayList<Student> students = randomStudents(300,options, nbDays);
		HashMap<Option, LinkedList<Option>> incompatibilities = makeRandomIncompatibilities(options);
		System.out.println(">>>"+Affectop.affectTop(students, options, incompatibilities, 5, 2));
	} 
}