package Calcul.Calcul.algorithms.calcul;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
	/**
	 * La classe chargee de faire l'affectation pour un seul ensemble (jour) 
	 * @author VALLET Mat
	 */

	/**
	 * Constructeur
	 * @param students les etudiants
	 * @param options les options disponible pour l'ensemble
	 * @param day l'index de l'ensemble
	 * @param availableOptions les options disponibles pour chaques etudiants
	 */	
	public class SimpleAffectop {
		//Map<Option,ArrayList<Student>> classements;
		private Map<Student,LinkedList<Option>> available;
		private ArrayList<Student> students;
		private int day;

		/**
		 * Ici le classement est aleatoire
		 * @param students
		 * @param options
		 * @param 
		 * @param day
		 */
		public SimpleAffectop(ArrayList<Student> students,
							  ArrayList<Option> options,
							  Map<Student,LinkedList<Option>> available,
							  int day) {
			this.available = available;
			
			for(Student s : students)
				s.affected[day] = null;			
			this.students = students;
			this.day = day;
		}

		void replace(Student newStudent , Student replaced, Option o) {
			replaced.affected[day] = null;
			o.accepted.remove(replaced);
			
			newStudent.affected[day] = o;
			o.accepted.add(newStudent);
		}

		public void mariagesStable(){
			Stack<Student> celibataires = new Stack<Student>();
			celibataires.addAll(students);
			//Collections.shuffle(celibataires);
			while(!celibataires.isEmpty()) {
				
				Student m = celibataires.pop();
				
				for(Option w : available.get(m)) {
				
					if(!w.isFull()) {
						w.accepted.add(m);
						m.affected[day]= w;
						break;
					}
					
				}
			}
			
		}
		
		static int optionPreference(Option o, Student s, Map<Option,ArrayList<Student>> classements) {
			ArrayList<Student> Opref= classements.get(o);
			if(!Opref.contains(s)) return -10000;
			return- Opref.indexOf(s);
		}

		private int studentPreference(Student s, Option o) {
			return s.getPreferences(day).size() - s.getPreferences(day).indexOf(o);
		}
						
		public boolean areWillingToExchange(Student s1, Student s2) {
			Option o1 = s1.affected[day];
			Option o2 = s2.affected[day];
						
			return (studentPreference(s1, o2) > studentPreference(s1, o1) &&
				studentPreference(s2, o1) >= studentPreference(s2, o2)) ||  
				(studentPreference(s1, o2) >= studentPreference(s1, o1) &&
				 studentPreference(s2, o1) > studentPreference(s2, o2)) ;
		}
		/**
		 * @param students les etudiants
		 * @return vrai si l'affectation faite est stable (renvoie vrai si l'affectation n'a pas ete faite)
		 * @see https://fr.wikipedia.org/wiki/Probl%C3%A8me_des_mariages_stables#Principe_et_propri%C3%A9t%C3%A9s
		 */
		public boolean isStable(ArrayList<Student> students) {
			for (Student s1 : students)
				for (Student s2 : students)
					if (s1 != s2 && available.get(s1).contains(s2.affected[day]) && available.get(s2).contains(s1.affected[day]) && areWillingToExchange(s1, s2)) {
						return false;
					}
			return true;
		}
}