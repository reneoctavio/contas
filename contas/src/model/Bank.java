/**
 * 
 */
package model;

import java.io.IOException;

/**
 * @author reneoctavio
 * 
 */
public class Bank {

    private String number;
    private String name;

    private static final int BANK_NUM_LENGTH = 3;

    public Bank(String inNum, String inName) throws IOException {
	this.name = inName;
	setNumber(inNum);
    }

    /**
     * @return the number
     */
    public String getNumber() {
	return number;
    }

    /**
     * @param number
     *            the number to set
     * @throws IOException
     */
    public void setNumber(String number) throws IOException {
	if (Utilities.isValidNumber(number, BANK_NUM_LENGTH))
	    this.number = Utilities.standardizeNumber(number, BANK_NUM_LENGTH);
	else
	    throw new IOException("Invalid number!");
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }
}
