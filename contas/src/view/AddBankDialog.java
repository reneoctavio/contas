/**
 * 
 */
package view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author reneoctavio
 * 
 */
public class AddBankDialog extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtNumber;
    private JButton btnAdd;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    AddBankDialog frame = new AddBankDialog();
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
    public AddBankDialog() {
	setTitle("Adicionar Banco");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 450, 190);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	GridBagLayout gbl_contentPane = new GridBagLayout();
	gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
	gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
	gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0,
		Double.MIN_VALUE };
	gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
		Double.MIN_VALUE };
	contentPane.setLayout(gbl_contentPane);

	JLabel lblName = new JLabel("Nome:");
	GridBagConstraints gbc_lblName = new GridBagConstraints();
	gbc_lblName.anchor = GridBagConstraints.EAST;
	gbc_lblName.insets = new Insets(0, 0, 5, 5);
	gbc_lblName.gridx = 0;
	gbc_lblName.gridy = 0;
	contentPane.add(lblName, gbc_lblName);

	txtName = new JTextField();
	GridBagConstraints gbc_txtName = new GridBagConstraints();
	gbc_txtName.gridwidth = 2;
	gbc_txtName.insets = new Insets(0, 0, 5, 5);
	gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
	gbc_txtName.gridx = 1;
	gbc_txtName.gridy = 0;
	contentPane.add(txtName, gbc_txtName);
	txtName.setColumns(10);

	JLabel lblNumber = new JLabel("N\u00FAmero:");
	GridBagConstraints gbc_lblNumber = new GridBagConstraints();
	gbc_lblNumber.anchor = GridBagConstraints.EAST;
	gbc_lblNumber.insets = new Insets(0, 0, 5, 5);
	gbc_lblNumber.gridx = 0;
	gbc_lblNumber.gridy = 2;
	contentPane.add(lblNumber, gbc_lblNumber);

	txtNumber = new JTextField();
	GridBagConstraints gbc_txtNumber = new GridBagConstraints();
	gbc_txtNumber.ipadx = -50;
	gbc_txtNumber.anchor = GridBagConstraints.WEST;
	gbc_txtNumber.insets = new Insets(0, 0, 5, 5);
	gbc_txtNumber.gridx = 1;
	gbc_txtNumber.gridy = 2;
	contentPane.add(txtNumber, gbc_txtNumber);
	txtNumber.setColumns(10);

	btnAdd = new JButton("Adicionar");
	GridBagConstraints gbc_btnAdd = new GridBagConstraints();
	gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
	gbc_btnAdd.gridx = 2;
	gbc_btnAdd.gridy = 2;
	contentPane.add(btnAdd, gbc_btnAdd);
    }

    public JTextField getTxtName() {
	return txtName;
    }

    public JTextField getTxtNumber() {
	return txtNumber;
    }

    public JButton getBtnAdd() {
	return btnAdd;
    }
}
