package Calcul.Calcul.algorithms.calcul;

import java.util.ArrayList;
import Calcul.Calcul.bean.Option;
import Calcul.Calcul.bean.Student;

public class Result implements Comparable<Result>{
	public class Affectation {
		public Student s;
		public ArrayList<Option> options;
		public Affectation(Student s, ArrayList<Option> options) {
			this.s =s;
			this.options = options;
		}
	}
	
	public ArrayList<Affectation> results;
	public Result(ArrayList<Student> students) {
		results = new ArrayList<>();
		for(Student s : students) {
			ArrayList<Option> options = new ArrayList<>();
			for(int i = 0; i < s.affected.length;i++)
				options.add(s.affected[i]);
			results.add(new Affectation(s, options));
		}
	}
	
	
	int satisfaction() {
		int satisfaction = 0;
		for(Affectation a : results) {
			for(Option opt  : a.s.affected)
				satisfaction += a.s.preferences.get(opt.day).size() - a.s.preferences.get(opt.day).indexOf(opt);
			satisfaction -= (a.options.size() - a.s.preferences.size()) * 10;
		}
		return satisfaction;
	}
	@Override
	public int compareTo(Result r) {
		return this.satisfaction()-r.satisfaction();
	}
}