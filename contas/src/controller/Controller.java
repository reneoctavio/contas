/**
 * 
 */
package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.CheckRegister;
import model.Customer;
import model.Utilities;
import view.AddBankDialog;
import view.AddCustomerDialog;
import view.CheckOptionsPanel;
import view.MainWindow;

/**
 * @author reneoctavio
 * 
 */
public class Controller {

    private CheckRegister register;
    private MainWindow mainWindow;

    private AddCustomerDialog addCustomerDialog;
    private AddBankDialog addBankDialog;

    private CheckOptionsPanel chkOptPanel;

    public Controller(MainWindow mainWindow) throws Exception {
	this.register = new CheckRegister();
	this.mainWindow = mainWindow;

	// Add dialog objects
	this.addCustomerDialog = new AddCustomerDialog();
	this.addBankDialog = new AddBankDialog();

	// Report objects
	this.chkOptPanel = new CheckOptionsPanel();

	addMenuActionListeners();

	// Add buttons
	configCustomer();
	configBank();

	// Report buttons
	configReportCheck();
    }

    public void configCustomer() {
	// Add Customer
	mainWindow.getMntmAddCustomer().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// Show dialog
		addCustomerDialog.pack();
		addCustomerDialog.setVisible(true);

		// Action Listener for the add button of the dialog
		ActionListener aLBtnAdd = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			register.addCustomer(addCustomerDialog.getTxtID()
				.getText(), addCustomerDialog.getTxtName()
				.getText());
			addCustomerDialog.dispose();
		    }
		};

		// Document Listener for while you type
		DocumentListener dLTxtID = new DocumentListener() {

		    /**
		     * Try to find the ID type and update the status in the
		     * dialog window
		     */
		    private void find_IDType() {
			if (Utilities.isCPF(addCustomerDialog.getTxtID()
				.getText())) {
			    addCustomerDialog.getLblTypeID().setText(
				    Customer.TypeID.CPF.toString());
			} else if (Utilities.isCNPJ(addCustomerDialog
				.getTxtID().getText())) {
			    addCustomerDialog.getLblTypeID().setText(
				    Customer.TypeID.CNPJ.toString());
			} else {
			    addCustomerDialog.getLblTypeID()
				    .setText("Inv‡lido");
			}
		    }

		    @Override
		    public void changedUpdate(DocumentEvent arg0) {
			// Not used for text purposes
		    }

		    @Override
		    public void insertUpdate(DocumentEvent arg0) {
			find_IDType();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent arg0) {
			find_IDType();
		    }
		};

		// Add action listener for add button
		addCustomerDialog.getBtnAddCustomer().addActionListener(
			aLBtnAdd);

		// Add document listener for id text field
		addCustomerDialog.getTxtID().getDocument()
			.addDocumentListener(dLTxtID);
	    }
	});
    }

    public void configBank() {
	// Add bank
	mainWindow.getMntmAddBank().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// Show dialog
		addBankDialog.pack();
		addBankDialog.setVisible(true);

		// Action Listener for the add button of the dialog
		ActionListener aLBtnAdd = new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			register.addBank(
				addBankDialog.getTxtNumber().getText(),
				addBankDialog.getTxtName().getText());
			addBankDialog.dispose();
		    }
		};
		// Add action listener for add button
		addBankDialog.getBtnAdd().addActionListener(aLBtnAdd);
	    }
	});
    }

    public void configReportCheck() {
	// Report Check
	mainWindow.getMntmReportCheck().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		mainWindow.getNorthPanel().add(chkOptPanel, BorderLayout.NORTH);
		mainWindow.getNorthPanel().revalidate();
	    }
	});
    }

    public void addMenuActionListeners() {

	// Add Check
	mainWindow.getMntmAddCheck().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// TODO
	    }
	});

	// Remove Bank
	mainWindow.getMntmRemoveBank().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// TODO
	    }
	});
	// Remove Check
	mainWindow.getMntmRemoveCheck().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// TODO
	    }
	});
	// Remove Customer
	mainWindow.getMntmRemoveCustomer().addActionListener(
		new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			// TODO
		    }
		});
	// Report Bank
	mainWindow.getMntmReportBank().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		// TODO
	    }
	});

	// Report Customer
	mainWindow.getMntmReportCustomer().addActionListener(
		new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			// TODO
		    }
		});
    }
}
