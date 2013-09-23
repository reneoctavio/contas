/**
 * 
 */
package model;

import java.io.IOException;
import java.util.Date;

/**
 * @author reneoctavio
 * 
 */
public class Customer {

    public enum TypeID {
	CPF, CNPJ;
    }

    private String idNumber;
    private String name;
    private TypeID typeID;
    private Date insertDate;

    /**
     * Create a customer with a valid ID and a Name
     * 
     * @param idNumber
     * @param name
     * @throws IOException
     */
    public Customer(String idNumber, String name) throws IOException {
	setIdNumber(idNumber);
	this.name = name;
	this.insertDate = new Date();
    }

    /**
     * Identify which is the type of the identification number
     * 
     * @param idNum
     *            The identification number
     * @return Whether the given number fits in one of the categories
     */
    public boolean identifyType(String idNum) {
	if (Utilities.isCPF(idNum))
	    this.typeID = TypeID.CPF;
	else if (Utilities.isCNPJ(idNum))
	    this.typeID = TypeID.CNPJ;
	else
	    return false;
	return true;
    }

    /**
     * @return the insertDate
     */
    public Date getInsertDate() {
	return insertDate;
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
     * @return the typeID
     */
    public TypeID getTypeID() {
	return typeID;
    }

    /**
     * @return the idNumber
     */
    public String getIdNumber() {
	return idNumber;
    }

    /**
     * @param idNumber
     *            the idNumber to set
     * @throws IOException
     */
    public void setIdNumber(String idNumber) throws IOException {
	if (identifyType(idNumber))
	    this.idNumber = idNumber;
	else
	    throw new IOException("Number not valid!");
    }

}
