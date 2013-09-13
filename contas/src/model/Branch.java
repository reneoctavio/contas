/**
 * 
 */
package model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author reneoctavio
 * 
 */
public class Branch {

    private String branchNum;

    private static final int BRANCH_NUM_LENGTH = 4;

    public Branch(String inNum) throws IOException {
	if (!standardizeNumber(inNum))
	    throw new IOException("Not a valid number!");
    }

    /**
     * Standardize bank number code to match the length of 3 adding leading
     * zeros if needed
     * 
     * @param inNum
     *            A string containing the number of the bank
     * @return Whether the input number is correct
     */
    public boolean standardizeNumber(String inNum) {
	// If the given string is not an integer return false
	if (!Utilities.isInteger(inNum))
	    return false;

	// If the length of the number bank is less than 0 and greater than 4,
	// return false
	if ((inNum.length() > 0) || (inNum.length() <= BRANCH_NUM_LENGTH)) {
	    // Adding leading zeros if needed
	    char[] zeros = new char[BRANCH_NUM_LENGTH];
	    Arrays.fill(zeros, '0');

	    // Create a decimal format to match the length of the string
	    DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
	    this.branchNum = df.format(Integer.getInteger(inNum));

	    return true;
	}
	return false;
    }

    /**
     * @return the branchNum
     */
    public String getBranchNum() {
	return branchNum;
    }

    /**
     * @param branchNum
     *            the branchNum to set
     * @throws IOException
     */
    public void setBranchNum(String branchNum) throws IOException {
	if (!standardizeNumber(branchNum))
	    throw new IOException("Not a valid number!");
    }

}
