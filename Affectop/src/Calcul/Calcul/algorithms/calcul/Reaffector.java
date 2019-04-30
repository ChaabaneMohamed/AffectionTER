package Calcul.Calcul.algorithms.calcul;

import java.util.ArrayList;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;
public class Reaffector {
	ArrayList<Student> scatterbrainStudent;
	ArrayList<ArrayList<Option>> options;
	int nbDays;

	public Reaffector(ArrayList<Student> scatterbrainStudent, ArrayList<ArrayList<Option>> options, int nbDays) {
		this.scatterbrainStudent = scatterbrainStudent;
		this.options = options;
		this.nbDays = nbDays;
	}

	Option getMostEmpty(ArrayList<Option> options) {
		Option min = options.get(0);
		for (Option o : options) {
			min = (o.accepted.size() < min.accepted.size()) ? o : min;
		}
		return min;
	}

	void affect(Option opt, Student s) {
		assert(!opt.isFull());
		opt.accepted.add(s);
	}
	
	void affectOneDay(ArrayList<Option> notFull, ArrayList<Student> unaffectedStudent) {
		while(!unaffectedStudent.isEmpty() && !notFull.isEmpty()) {
			Student s  = unaffectedStudent.remove(0);
			Option min = getMostEmpty(notFull);
			affect(min,s);
			if(min.isFull()) {
				notFull.remove(min);
			}
		}
	}

	void affect() {
		for (int d = 0; d < nbDays; d++) {
			ArrayList<Option> notFull = new ArrayList<>(options.get(d));
			for (Option o : notFull)
				if (o.isFull())
					notFull.remove(o);
			affectOneDay(notFull, scatterbrainStudent);// TODO: on oublie les redoublants ? '-'
		}
	}

}
