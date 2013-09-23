/**
 * 
 */
package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Customer.TypeID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author reneoctavio
 * 
 */
public class CheckRegisterTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
	CheckRegister chkreg = new CheckRegister();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
	CheckRegister chkreg = null;
    }

    @Test
    public void test() throws Exception {
	// Initialize register and database
	CheckRegister chkreg = new CheckRegister();

	// Check that are not any data in the database
	assertTrue(chkreg.findBank("001") == null);
	assertTrue(chkreg.findBankByName("Banco do Brasil") == null);
	assertTrue(chkreg.findBranch("236", "1") == null);
	assertTrue(chkreg.findCustomer("11111111111") == null);
	assertTrue(chkreg.findCustomerByName("Joao") == null);

	// Add data
	SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
	Date dueDate = dataformat.parse("19/09/2013");

	assertTrue(chkreg.addBank("001", "Banco do Brasil"));
	assertTrue(chkreg.addBranch("2918", "001"));
	assertTrue(chkreg.addCustomer("03312558123",
		"René Octavio Queiroz Dias"));
	assertTrue(chkreg.addCheck("1", "1", "2918", "03312558123", dueDate,
		new BigDecimal(1999.50)));

	// Test finders
	// Test bank
	Bank bank = chkreg.findBank("1");
	assertTrue(bank.getName().contains("Banco do Brasil"));
	assertTrue(bank.getNumber().contains("001"));

	assertTrue(chkreg.findBankByName("brasil").size() == 1);

	// Test branch
	Branch branch = chkreg.findBranch("2918", "1");
	assertTrue(branch.getBankNum().contains("001"));
	assertTrue(branch.getBranchNum().contains("2918"));

	// Test customer
	// Test customer by id
	Customer customer = chkreg.findCustomer("03312558123");
	assertTrue(customer.getIdNumber().contains("03312558123"));
	assertTrue(customer.getName().contains("René Octavio Queiroz Dias"));
	assertTrue(customer.getTypeID() == TypeID.CPF);
	// Test customer by name
	customer = chkreg.findCustomerByName("rene").get(0);
	assertTrue(customer.getIdNumber().contains("03312558123"));
	assertTrue(customer.getName().contains("René Octavio Queiroz Dias"));
	assertTrue(customer.getTypeID() == TypeID.CPF);

	// Test check
	List<Check> checkList = chkreg.findCheckbyNumber("1");
	assertTrue(checkList.size() == 1);

	Check check = checkList.get(0);
	assertTrue(check.getBank().contains("001"));
	assertTrue(check.getBranch().contains("2918"));
	assertTrue(check.getCustomer().contains("03312558123"));
	assertTrue(check.getDueDate().compareTo(dueDate) == 0);
	assertTrue(check.getValue().compareTo(new BigDecimal(1999.50)) == 0);
	assertFalse(check.isCashed());

	assertTrue(chkreg.findCheckbyBank("1").size() == 1);
	assertTrue(chkreg.findCheckbyCustomer("03312558123").size() == 1);

	// Remove data
	assertTrue(chkreg.removeBank("1"));
	assertTrue(chkreg.removeBranch("2918", "1"));
	assertTrue(chkreg.removeCustomer("03312558123"));
	assertTrue(chkreg.removeCheck(check));

	// Check that are not any data in the database
	assertTrue(chkreg.findBank("001") == null);
	assertTrue(chkreg.findBankByName("Banco do Brasil") == null);
	assertTrue(chkreg.findBranch("2918", "1") == null);
	assertTrue(chkreg.findCustomer("03312558123") == null);
	assertTrue(chkreg.findCustomerByName("Rene Octavio Queiroz Dias") == null);

	// Shutdown the database
	chkreg.getDatabase().shutdown();
    }
}
