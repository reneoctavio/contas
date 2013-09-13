/**
 * 
 */
package model;

import java.io.IOException;

/**
 * @author reneoctavio
 * 
 */
public class Identification {

    enum Type {
	CPF, CNPJ;
    }

    private Type type;
    private String idNumber;

    public Identification(String inNum) throws IOException {
	setIdNumber(inNum);
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
	    this.type = Type.CPF;
	else if (Utilities.isCNPJ(idNum))
	    this.type = Type.CNPJ;
	else
	    return false;
	return true;
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

    /**
     * @return the type
     */
    public Type getType() {
	return type;
    }
}
