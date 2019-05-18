package Calcul.Calcul.bean;

public class Preference {
	
	int groupId;
	int choice;
	int optionId;
	int numEtudiant;
	
	public Preference(int groupId, int choice, int optionId, int numEtudiant) {
		this.groupId = groupId;
		this.choice = choice;
		this.optionId = optionId;
		this.numEtudiant = numEtudiant;
	}

	public int getNumEtudiant() {
		return numEtudiant;
	}

	public void setNumEtudiant(int numEtudiant) {
		this.numEtudiant = numEtudiant;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	
	public String toString() {
		return "Option " + groupId + ", UE " + optionId + ", crédit: " + choice;
	}	
}
