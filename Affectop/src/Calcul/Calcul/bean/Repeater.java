package Calcul.Calcul.bean;

public class Repeater {
	int optionId;
	int numEtudiant;
	
	public Repeater(int optionId, int numEtudiant) {
		this.optionId = optionId;
		this.numEtudiant = numEtudiant;
	}
	
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public int getNumEtudiant() {
		return numEtudiant;
	}
	public void setNumEtudiant(int numEtudiant) {
		this.numEtudiant = numEtudiant;
	}
}
