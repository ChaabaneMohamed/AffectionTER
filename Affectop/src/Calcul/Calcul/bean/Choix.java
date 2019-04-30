package Calcul.Calcul.bean;

import java.util.List;

/**
 * Bean
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Choix {
	private List<Option> options;
	private List<Student> eleves;

	
	public Choix(List<Option> options, List<Student> eleves) {
		this.options = options;
		this.eleves = eleves;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public List<Student> getEleves() {
		return eleves;
	}

	public void setEleves(List<Student> eleves) {
		this.eleves = eleves;
	}

}
