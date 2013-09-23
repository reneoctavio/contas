/**
 * 
 */
package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author reneoctavio
 * 
 */
public class AddCustomerDialog extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JLabel lbl_ID;
    private JTextField txtID;
    private JLabel lblTipo;
    private JLabel lblTypeID;
    private JButton btnAddCustomer;
    private JSeparator separator;
    private JSeparator separator_1;
    private JLabel lbl_Identity;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AddCustomerDialog frame = new AddCustomerDialog();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */
    public AddCustomerDialog() {
	setTitle("Adicionar Cliente");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 450, 185);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new FormLayout(
		new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
			FormFactory.RELATED_GAP_COLSPEC,
			ColumnSpec.decode("70dlu:grow"),
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC, }));

	JLabel lblName = new JLabel("Nome:");
	contentPane.add(lblName, "2, 2, right, default");

	txtName = new JTextField();
	contentPane.add(txtName, "4, 2, 3, 1, fill, default");
	txtName.setColumns(10);

	separator = new JSeparator();
	contentPane.add(separator, "2, 4, 5, 1");

	lbl_Identity = new JLabel("Identifica\u00E7\u00E3o:");
	contentPane.add(lbl_Identity, "2, 6");

	lblTipo = new JLabel("Tipo:");
	contentPane.add(lblTipo, "6, 6");

	lbl_ID = new JLabel("CPF/CNPJ:");
	contentPane.add(lbl_ID, "2, 8, right, default");

	txtID = new JTextField();
	contentPane.add(txtID, "4, 8, left, default");
	txtID.setColumns(10);

	lblTypeID = new JLabel("");
	contentPane.add(lblTypeID, "6, 8");

	separator_1 = new JSeparator();
	contentPane.add(separator_1, "2, 10, 5, 1");

	btnAddCustomer = new JButton("Adicionar");
	contentPane.add(btnAddCustomer, "6, 12");
    }

    public JTextField getTxtName() {
	return txtName;
    }

    public JTextField getTxtID() {
	return txtID;
    }

    public JLabel getLblTypeID() {
	return lblTypeID;
    }

    public JButton getBtnAddCustomer() {
	return btnAddCustomer;
    }
}
