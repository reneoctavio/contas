/**
 * 
 */
package model;

import java.util.InputMismatchException;

/**
 * @author reneoctavio
 * 
 */
public class Utilities {
    /**
     * Check if a string can be parsed
     * 
     * @param str
     *            A string to be parsed
     * @return Whether the string was parsed or not
     */
    public static boolean isInteger(String str) {
	try {
	    Integer.parseInt(str);
	} catch (NumberFormatException nfe) {
	    return false;
	}
	return true;
    }

    /**
     * Check if a CPF number is valid
     * 
     * Further reading:
     * http://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java
     * 
     * @author Omero Francisco Bertol
     * @param str
     *            The string containing the CPF number
     * @return Whether this is a valid CPF of not
     */
    public static boolean isCPF(String str) {
	// Sequence of same number is not valid
	if (str.equals("00000000000") || str.equals("11111111111")
		|| str.equals("22222222222") || str.equals("33333333333")
		|| str.equals("44444444444") || str.equals("55555555555")
		|| str.equals("66666666666") || str.equals("77777777777")
		|| str.equals("88888888888") || str.equals("99999999999")
		|| (str.length() != 11))
	    return (false);
	char dig10, dig11;
	int sm, i, r, num, weight;
	try {
	    // 1st Digit Calculation
	    sm = 0;
	    weight = 10;
	    for (i = 0; i < 9; i++) {
		// Transform the character in a string obeying the ASCII table
		num = (int) (str.charAt(i) - 48);
		// Multiply the number by its respective weights
		sm = sm + (num * weight);
		weight = weight - 1;
	    }

	    r = 11 - (sm % 11);
	    if ((r == 10) || (r == 11))
		dig10 = '0';
	    else
		dig10 = (char) (r + 48);

	    // 2nd Digit Calculation
	    sm = 0;
	    weight = 11;
	    for (i = 0; i < 10; i++) {
		num = (int) (str.charAt(i) - 48);
		sm = sm + (num * weight);
		weight = weight - 1;
	    }
	    r = 11 - (sm % 11);
	    if ((r == 10) || (r == 11))
		dig11 = '0';
	    else
		dig11 = (char) (r + 48);

	    // Verify if the calculated digits are the same as give
	    if ((dig10 == str.charAt(9)) && (dig11 == str.charAt(10)))
		return (true);
	    else
		return (false);
	} catch (InputMismatchException err) {
	    return (false);
	}
    }

    /**
     * Check if a CNPJ number is valid
     * 
     * Further reading:
     * http://www.devmedia.com.br/validando-o-cnpj-em-uma-aplicacao-java/22374
     * 
     * @author Omero Francisco Bertol
     * @param str
     *            The string containing the CPF number
     * @return Whether this is a valid CPF of not
     */
    public static boolean isCNPJ(String str) {
	if (str.equals("00000000000000") || str.equals("11111111111111")
		|| str.equals("22222222222222") || str.equals("33333333333333")
		|| str.equals("44444444444444") || str.equals("55555555555555")
		|| str.equals("66666666666666") || str.equals("77777777777777")
		|| str.equals("88888888888888") || str.equals("99999999999999")
		|| (str.length() != 14))
	    return (false);
	char dig13, dig14;
	int sm, i, r, num, peso;
	try {
	    // 1st Digit Calculation
	    sm = 0;
	    peso = 2;
	    for (i = 11; i >= 0; i--) {
		// Transform the character in a string obeying the ASCII table
		num = (int) (str.charAt(i) - 48);
		sm = sm + (num * peso);
		peso = peso + 1;
		if (peso == 10)
		    peso = 2;
	    }

	    r = sm % 11;
	    if ((r == 0) || (r == 1))
		dig13 = '0';
	    else
		dig13 = (char) ((11 - r) + 48);

	    // 2nd Digit Calculation
	    sm = 0;
	    peso = 2;
	    for (i = 12; i >= 0; i--) {
		num = (int) (str.charAt(i) - 48);
		sm = sm + (num * peso);
		peso = peso + 1;
		if (peso == 10)
		    peso = 2;
	    }

	    r = sm % 11;
	    if ((r == 0) || (r == 1))
		dig14 = '0';
	    else
		dig14 = (char) ((11 - r) + 48);

	    // Verify if the calculated digits are the same as give
	    if ((dig13 == str.charAt(12)) && (dig14 == str.charAt(13)))
		return (true);
	    else
		return (false);
	} catch (InputMismatchException erro) {
	    return (false);
	}
    }
}
