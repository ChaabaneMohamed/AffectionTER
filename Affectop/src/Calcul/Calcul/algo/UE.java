package Calcul.Calcul.algo;

public class UE {
	private String codeModule;
	private String nomUE;
	private int effectif;
	
	UE(String codeModule, String nomUE, int effectif){
        this.codeModule = codeModule;           // Le code qui correspond a la l'UE 
        this.nomUE = nomUE;                     // Le nom de l'UE
        this.effectif = effectif;               // L'effectif de l'UE
    }
	
	void printUE() {
		System.out.println("UE = " + this.nomUE + " | Code module : "+ this.codeModule + " | Effectif : " + this.effectif);
	}
	
	// Liste des getter
	String getCodeModule(){ return this.codeModule; }  
    String getName(){ return this.nomUE; }
    int getEffectif(){ return this.effectif; }
}
