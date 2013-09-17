/**
 * 
 */
package model;

import java.io.IOException;

/**
 * @author reneoctavio
 * 
 */
public class Branch {

    private String branchNum;
    private String bankNum;

    private static final int BRANCH_NUM_LENGTH = 4;

    public Branch(String inNum, String inBankNum) throws IOException {
	setBranchNum(inNum);
	this.bankNum = inBankNum;
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
	if (Utilities.isValidNumber(branchNum, BRANCH_NUM_LENGTH))
	    this.branchNum = Utilities.standardizeNumber(branchNum,
		    BRANCH_NUM_LENGTH);
	else
	    throw new IOException("Not a valid number!");
    }

    /**
     * @return the bankNum
     */
    public String getBankNum() {
	return bankNum;
    }

    /**
     * @param bankNum
     *            the bankNum to set
     */
    public void setBankNum(String bankNum) {
	this.bankNum = bankNum;
    }

}
