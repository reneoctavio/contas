/**
 * 
 */
package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author reneoctavio
 * 
 */
public class Check {

    private String number;
    private String customer;
    private String bank;
    private String branch;
    private BigDecimal value;
    private Date issueDate;
    private Date dueDate;
    private boolean isCashed;

    private static final int CHECK_NUM_LENGTH = 5;

    /**
     * @param customer
     * @param bank
     * @param branch
     * @param value
     * @param issueDate
     * @param dueDate
     * @throws IOException
     */
    public Check(String number, String customer, String bank, String branch,
	    BigDecimal value, Date issueDate, Date dueDate) throws IOException {
	setNumber(number);
	this.customer = customer;
	this.bank = bank;
	this.branch = branch;
	this.value = value;
	this.issueDate = issueDate;
	this.dueDate = dueDate;
	this.isCashed = false;
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
	if (Utilities.isValidNumber(number, CHECK_NUM_LENGTH))
	    this.number = Utilities.standardizeNumber(number, CHECK_NUM_LENGTH);
	else
	    throw new IOException("Invalid number!");
	this.number = number;
    }

    /**
     * @return the customer
     */
    public String getCustomer() {
	return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(String customer) {
	this.customer = customer;
    }

    /**
     * @return the bank
     */
    public String getBank() {
	return bank;
    }

    /**
     * @param bank
     *            the bank to set
     */
    public void setBank(String bank) {
	this.bank = bank;
    }

    /**
     * @return the branch
     */
    public String getBranch() {
	return branch;
    }

    /**
     * @param branch
     *            the branch to set
     */
    public void setBranch(String branch) {
	this.branch = branch;
    }

    /**
     * @return the value
     */
    public BigDecimal getValue() {
	return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(BigDecimal value) {
	this.value = value;
    }

    /**
     * @return the issueDate
     */
    public Date getIssueDate() {
	return issueDate;
    }

    /**
     * @param issueDate
     *            the issueDate to set
     */
    public void setIssueDate(Date issueDate) {
	this.issueDate = issueDate;
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
	return dueDate;
    }

    /**
     * @param dueDate
     *            the dueDate to set
     */
    public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
    }

    /**
     * @return the isCashed
     */
    public boolean isCashed() {
	return isCashed;
    }

    /**
     * @param isCashed
     *            the isCashed to set
     */
    public void setCashed(boolean isCashed) {
	this.isCashed = isCashed;
    }
}
