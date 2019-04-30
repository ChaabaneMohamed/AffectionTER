package Calcul.Calcul.base;

public class main {

	public static void main(String[] args) {
		System.out.println("creation br");
		BaseReader br = new BaseReader();
		String token="a8nsgfo14";
		System.out.println(br.nameRequest(token));
		System.out.println(br.tokenIdentification(token));
	}

}
