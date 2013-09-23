/**
 * 
 */
package model;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author reneoctavio
 * 
 */
public class CheckRegister {

    private Database database;
    private final String CUSTOMER_TABLE = "customers";
    private final String BANKS_TABLE = "banks";
    private final String BRANCHES_TABLE = "branches";
    private final String CHECKS_TABLE = "checks";

    public CheckRegister() throws Exception {
	this.database = new Database();
	initializeDB();
    }

    /**
     * Check if table needed are created and if not, create them
     * 
     * @throws SQLException
     */
    public void initializeDB() throws SQLException {
	try {
	    database.update("CREATE TABLE "
		    + CUSTOMER_TABLE
		    + "( id INTEGER IDENTITY, custName VARCHAR(256), custID VARCHAR(14) NOT NULL UNIQUE);");

	    database.update("CREATE TABLE "
		    + BANKS_TABLE
		    + "( id INTEGER IDENTITY, bankName VARCHAR(256), bankNum VARCHAR(3) NOT NULL UNIQUE);");

	    database.update("CREATE TABLE "
		    + BRANCHES_TABLE
		    + "( id INTEGER IDENTITY, branchNum VARCHAR(4) NOT NULL, bankNum VARCHAR(3) NOT NULL);");

	    database.update("CREATE TABLE "
		    + CHECKS_TABLE
		    + "( id INTEGER IDENTITY, checkNum VARCHAR(5) NOT NULL, bankNum VARCHAR(3) NOT NULL, branchNum VARCHAR(4) NOT NULL, custID VARCHAR(14) NOT NULL, issueDate DATE, dueDate DATE NOT NULL, value DECIMAL(100,2) NOT NULL, isCashed BOOLEAN NOT NULL);");

	} catch (SQLException ex2) {
	    // Ignore since table already exists
	}
    }

    /**
     * Check if a bank exists, if not, null is returned
     * 
     * @param inNumber
     *            The bank's number
     * @return The Bank found or null if does not exist.
     */
    public Bank findBank(String inNumber) {
	Bank bank;
	ResultSet rs;
	try {
	    bank = new Bank(inNumber, null);
	    rs = database.query("SELECT * FROM " + BANKS_TABLE
		    + " WHERE bankNum='" + bank.getNumber() + "';");

	    while (rs.next()) {
		if (rs.getString("bankNum").contains(inNumber)) {
		    bank.setName(rs.getString("bankName"));
		    bank.setNumber(rs.getString("bankNum"));
		    rs.close();
		    return bank;
		}
	    }
	    rs.close();
	    return null;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Create a list of banks with name that have a part of the input string
     * 
     * @param inName
     *            Bank's name
     * @return The list with banks that name is part of the input string
     */
    public List<Bank> findBankByName(String inName) {
	try {
	    List<Bank> bankList = new LinkedList<Bank>();
	    ResultSet rs = database.query("SELECT * FROM " + BANKS_TABLE);

	    while (rs.next()) {
		// Get rid of diacritical marks
		String normBankName = Normalizer
			.normalize(rs.getString("bankName"),
				Normalizer.Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.toLowerCase();

		String normInName = Normalizer
			.normalize(inName, Normalizer.Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.toLowerCase();

		if (normBankName.contains(normInName)) {
		    bankList.add(new Bank(rs.getString("bankNum"), rs
			    .getString("bankName")));
		}
	    }
	    rs.close();
	    if (bankList.size() == 0) {
		return null;
	    }
	    return bankList;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Add a bank to the list of banks
     * 
     * @param inNumber
     *            The number of the bank
     * @param inName
     *            The name of the bank
     * @return Whether the addition was successful
     */
    public boolean addBank(String inNumber, String inName) {
	try {
	    Bank bank = new Bank(inNumber, inName);
	    database.update("INSERT INTO " + BANKS_TABLE
		    + " (bankName,bankNum) VALUES('" + bank.getName() + "','"
		    + bank.getNumber() + "');");
	    return true;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Remove a bank from the list of banks
     * 
     * @param inNumber
     *            The number of the Bank
     * @return Whether the removal was successful
     */
    public boolean removeBank(String inNumber) {
	try {
	    Bank bank = new Bank(inNumber, null);
	    database.update("DELETE FROM " + BANKS_TABLE + " WHERE bankNum='"
		    + bank.getNumber() + "'");
	    return true;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Check if a customer exists, if not, null is returned
     * 
     * @param inIDNumber
     *            The customer's number
     * @return The Customer found or null if does not exist.
     */
    public Customer findCustomer(String inIDNumber) {
	Customer customer;
	ResultSet rs;
	try {
	    customer = new Customer(inIDNumber, null);
	    rs = database.query("SELECT * FROM " + CUSTOMER_TABLE
		    + " WHERE custID='" + customer.getIdNumber() + "'");

	    while (rs.next()) {
		if (rs.getString("custID").contains(inIDNumber)) {
		    customer.setName(rs.getString("custName"));
		    customer.setIdNumber(rs.getString("custID"));
		    rs.close();
		    return customer;
		}
	    }
	    rs.close();
	    return null;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Create a list of customer with the same name
     * 
     * @param inName
     *            Customer's name
     * @return The list with customers of the same name
     */
    public List<Customer> findCustomerByName(String inName) {
	try {
	    List<Customer> customerList = new LinkedList<Customer>();
	    ResultSet rs = database.query("SELECT * FROM " + CUSTOMER_TABLE);

	    while (rs.next()) {
		// Get rid of diacritical marks
		String normCustName = Normalizer
			.normalize(rs.getString("custName"),
				Normalizer.Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.toLowerCase();

		String normInName = Normalizer
			.normalize(inName, Normalizer.Form.NFD)
			.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
			.toLowerCase();

		if (normCustName.contains(normInName)) {
		    customerList.add(new Customer(rs.getString("custID"), rs
			    .getString("custName")));
		}
	    }
	    rs.close();
	    if (customerList.size() == 0)
		return null;
	    return customerList;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Add a customer to the customer list
     * 
     * @param inIDNumber
     *            Customer's ID
     * @param inName
     *            Customer's name
     * @return Whether the customer was added successfully
     */
    public boolean addCustomer(String inIDNumber, String inName) {
	try {
	    Customer customer = new Customer(inIDNumber, inName);
	    database.update("INSERT INTO " + CUSTOMER_TABLE
		    + " (custName,custID) VALUES('" + customer.getName()
		    + "','" + customer.getIdNumber() + "');");
	    return true;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Remove a customer from customer list
     * 
     * @param inIDNumber
     * @return Whether the removal was successful
     */
    public boolean removeCustomer(String inIDNumber) {
	try {
	    Customer customer = new Customer(inIDNumber, null);
	    database.update("DELETE FROM " + CUSTOMER_TABLE + " WHERE custID='"
		    + customer.getIdNumber() + "'");
	    return true;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Check if a branch exists, if not, null is returned
     * 
     * @param branchNum
     *            The branch's number
     * @param bankNum
     *            The branch's bank number
     * @return The Branch found or null if does not exist.
     */
    public Branch findBranch(String branchNum, String bankNum) {
	Branch branch;
	ResultSet rs;
	try {
	    branch = new Branch(branchNum, bankNum);
	    rs = database.query("SELECT * FROM " + BRANCHES_TABLE
		    + " WHERE branchNum='" + branch.getBranchNum() + "'");

	    while (rs.next()) {
		if (rs.getString("branchNum").contains(branchNum)
			&& rs.getString("bankNum").contains(bankNum)) {
		    branch.setBranchNum(rs.getString("branchNum"));
		    branch.setBankNum(rs.getString("bankNum"));
		    rs.close();
		    return branch;
		}
	    }
	    rs.close();
	    return null;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Add a branch to the branch list
     * 
     * @param branchNum
     *            Branch's number
     * @return Whether the branch was added successfully
     */
    public boolean addBranch(String branchNum, String bankNum) {
	try {
	    Bank bank = new Bank(bankNum, null);
	    Branch branch = new Branch(branchNum, bank.getNumber());
	    database.update("INSERT INTO " + BRANCHES_TABLE
		    + " (branchNum, bankNum) VALUES('" + branch.getBranchNum()
		    + "','" + branch.getBankNum() + "');");
	    return true;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Remove a branch
     * 
     * @param branchNum
     *            The branch number
     * @param bankNum
     *            The branch bank's number
     * @return Whether the removal was successful
     */
    public boolean removeBranch(String branchNum, String bankNum) {
	try {
	    Bank bank = new Bank(bankNum, null);
	    Branch branch = new Branch(branchNum, bank.getNumber());
	    database.update("DELETE FROM " + BRANCHES_TABLE
		    + " WHERE branchNum='" + branch.getBranchNum()
		    + "' AND bankNum='" + branch.getBankNum() + "'");
	    return true;

	} catch (IOException e) {
	    // e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Add a check to the register
     * 
     * @param number
     *            Number of the check
     * @param bankNum
     *            Number of the bank
     * @param branchNum
     *            Number of the branch
     * @param custID
     *            Number of the customer's id
     * @param dueDate
     *            Check's due date
     * @param value
     *            Value of the check
     * @return Whether the addition was successful or not
     * @throws NoBankException
     *             Bank does not exists
     * @throws NoCustomerException
     *             Customer does not exists
     */
    public boolean addCheck(String number, String bankNum, String branchNum,
	    String custID, Date dueDate, BigDecimal value)
	    throws NoBankException, NoCustomerException {
	Bank bank = findBank(bankNum);
	Customer customer = findCustomer(custID);
	Branch branch;

	if (bank == null) {
	    throw new NoBankException();
	} else {
	    branch = findBranch(branchNum, bank.getNumber());
	}

	if (customer == null)
	    throw new NoCustomerException();

	try {
	    if (branch == null) {
		branch = new Branch(branchNum, bank.getNumber());
		addBranch(branch.getBranchNum(), branch.getBankNum());
	    }

	    SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");

	    Check check = new Check(number, customer.getIdNumber(),
		    bank.getNumber(), branch.getBranchNum(), value, new Date(),
		    dueDate, false);
	    database.update("INSERT INTO "
		    + CHECKS_TABLE
		    + " (checkNum,bankNum,branchNum,custID,issueDate,dueDate,value,isCashed) VALUES('"
		    + check.getNumber() + "','" + check.getBank() + "','"
		    + check.getBranch() + "','" + check.getCustomer() + "','"
		    + dataformat.format(check.getIssueDate()) + "','"
		    + dataformat.format(check.getDueDate()) + "','"
		    + check.getValue() + "','" + check.isCashed() + "');");
	    return true;
	} catch (IOException e) {
	    e.printStackTrace();
	    return false;
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * Remove a check from register
     * 
     * @param check
     *            The check
     * @return Whether the removal was successful
     */
    public boolean removeCheck(Check check) {
	try {
	    SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd");
	    database.update("DELETE FROM " + CHECKS_TABLE + " WHERE checkNum='"
		    + check.getNumber() + "' AND bankNum='" + check.getBank()
		    + "' AND branchNum='" + check.getBranch()
		    + "' AND custID='" + check.getCustomer()
		    + "' AND issueDate='"
		    + dataformat.format(check.getIssueDate())
		    + "' AND dueDate='" + dataformat.format(check.getDueDate())
		    + "' AND value='" + check.getValue() + "'");
	    return true;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return false;
	}
    }

    /**
     * Find check by its number
     * 
     * @param number
     *            The number of the check
     * @return The list containing the checks or null if none found
     */
    public List<Check> findCheckbyNumber(String number) {
	try {
	    List<Check> checkList = new LinkedList<Check>();
	    ResultSet rs = database.query("SELECT * FROM " + CHECKS_TABLE);

	    while (rs.next()) {
		if (rs.getString("checkNum").contains(number)) {
		    checkList.add(new Check(rs.getString("checkNum"), rs
			    .getString("custID"), rs.getString("bankNum"), rs
			    .getString("branchNum"), rs.getBigDecimal("value"),
			    rs.getDate("issueDate"), rs.getDate("dueDate"), rs
				    .getBoolean("isCashed")));
		}
	    }
	    rs.close();
	    if (checkList.size() == 0)
		return null;
	    return checkList;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Find check by its customer id
     * 
     * @param custID
     *            The customer's id
     * @return The list containing the checks or null if none found
     */
    public List<Check> findCheckbyCustomer(String custID) {
	try {
	    List<Check> checkList = new LinkedList<Check>();
	    ResultSet rs = database.query("SELECT * FROM " + CHECKS_TABLE);

	    while (rs.next()) {
		if (rs.getString("custID").contains(custID)) {
		    checkList.add(new Check(rs.getString("checkNum"), rs
			    .getString("custID"), rs.getString("bankNum"), rs
			    .getString("branchNum"), rs.getBigDecimal("value"),
			    rs.getDate("issueDate"), rs.getDate("dueDate"), rs
				    .getBoolean("isCashed")));
		}
	    }
	    rs.close();
	    if (checkList.size() == 0)
		return null;
	    return checkList;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * Find check by its bank number
     * 
     * @param bankNum
     *            The customer's id
     * @return The list containing the checks or null if none found
     */
    public List<Check> findCheckbyBank(String bankNum) {
	try {
	    List<Check> checkList = new LinkedList<Check>();
	    ResultSet rs = database.query("SELECT * FROM " + CHECKS_TABLE);

	    while (rs.next()) {
		if (rs.getString("bankNum").contains(bankNum)) {
		    checkList.add(new Check(rs.getString("checkNum"), rs
			    .getString("custID"), rs.getString("bankNum"), rs
			    .getString("branchNum"), rs.getBigDecimal("value"),
			    rs.getDate("issueDate"), rs.getDate("dueDate"), rs
				    .getBoolean("isCashed")));
		}
	    }
	    rs.close();
	    if (checkList.size() == 0)
		return null;
	    return checkList;
	} catch (IOException e) {
	    // e.printStackTrace();
	    return null;
	} catch (SQLException e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    /**
     * @return the database
     */
    public Database getDatabase() {
	return database;
    }
}
