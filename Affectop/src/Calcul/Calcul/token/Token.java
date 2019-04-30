package Calcul.Calcul.token;
import java.util.ArrayList;
import java.util.Random;
import Calcul.Calcul.exceptions.TokenLengthException;

/**
 * Class to generate Tokens
 * 
 * @author Valentin JABRE
 * @version 1.0
 */
public class Token {
	/**
	 * Generate a token list composed of alphanum char
	 * 
	 * @param length
	 *            length of the token (String)
	 * @param number
	 *            number of token to generate
	 * @return an {@link ArrayList} that contains the generated tokens
	 * @exception TokenLengthException
	 *                when the length isn't in [1,1024]
	 */
	public static ArrayList<String> generateTokenList(int length, int number) throws TokenLengthException {
		// A token is composed of [0-9 a-z]+ chars
		// Maximum length of an url using get is 2048 we take 1024 for safety
		//A length of 
		if (length < 1 || length > 1024) {
			throw new TokenLengthException();
		}

		ArrayList<String> list = new ArrayList<>();
		// Creation of an empty array that will contains the char that are
		// allowed for the tokens name
		char[] alphanumList = new char[36];

		// Used to get ASCII codes for the char '0' and 'a'
		Character zero = '0';
		Character a = 'a';

		// Writing in the array of the 0-9 chars
		for (int i = 0; i < 10; i++) {
			alphanumList[i] = (char) (zero + i);

		}
		// Writing in the array of the a-z chars
		for (int i = 0; i < 26; i++) {
			alphanumList[i + 10] = (char) (a + i);

		}

		// Random draw in order to make the tokens
		Random rand = new Random();
		for (int cpt = 0; cpt < number; cpt++) {
			String s = "";
			for (int i = 0; i < length; i++) {
				s += alphanumList[rand.nextInt(alphanumList.length)];

			}
			list.add(s);
		}

		return list;

	}
}
