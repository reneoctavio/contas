/**
 * 
 */
package model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author reneoctavio
 * 
 */
public class Bank {

    private String number;
    private String name;

    private List<Branch> branchList;

    private static final int BANK_NUM_LENGTH = 3;

    public Bank(String inNum, String inName) throws IOException {
	this.name = inName;
	this.branchList = new ArrayList<Branch>();

	if (!standardizeNumber(inNum))
	    throw new IOException("Not a valid number!");
    }

    /**
     * Add a branch to the Branch List of this Bank
     * 
     * @param inNumBranch
     *            The number of the branch
     * @return Whether it was added successfully
     * @throws IOException
     */
    public boolean addBranch(String inNumBranch) throws IOException {
	for (Branch branch : this.branchList) {
	    if (branch.getBranchNum().contains(inNumBranch))
		return false;
	}
	this.branchList.add(new Branch(inNumBranch));
	return true;
    }

    /**
     * Remove a branch from the Branch List of this Bank
     * 
     * @param inNumBranch
     *            The branch number
     * @return Whether the removal was successful
     */
    public boolean removeBranch(String inNumBranch) {
	for (Branch branch : this.branchList) {
	    if (branch.getBranchNum().contains(inNumBranch)) {
		this.branchList.remove(branch);
		return true;
	    }
	}
	return false;
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

	// If the length of the number bank is less than 0 and greater than 3,
	// return false
	if ((inNum.length() > 0) || (inNum.length() <= BANK_NUM_LENGTH)) {
	    // Adding leading zeros if needed
	    char[] zeros = new char[BANK_NUM_LENGTH];
	    Arrays.fill(zeros, '0');

	    // Create a decimal format to match the length of the string
	    DecimalFormat df = new DecimalFormat(String.valueOf(zeros));
	    this.number = df.format(Integer.getInteger(inNum));

	    return true;
	}
	return false;
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
	if (!standardizeNumber(number))
	    throw new IOException("Not a valid number!");
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

    /**
     * @return the branchList
     */
    public List<Branch> getBranchList() {
	return branchList;
    }
}
