/**
 * 
 */
package view;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author reneoctavio
 * 
 */
public class CheckOptionsPanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JTextField txtCheckNumber;
    private JTextField txtBankNumber;
    private JTextField txtBranchNumber;
    private final ButtonGroup buttonGroupCashedChecks = new ButtonGroup();

    /**
     * Create the panel.
     */
    public CheckOptionsPanel() {
	setLayout(new FormLayout(new ColumnSpec[] {
		FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
		FormFactory.RELATED_GAP_COLSPEC,
		ColumnSpec.decode("max(109dlu;default):grow"),
		FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
		FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
		FormFactory.RELATED_GAP_COLSPEC,
		ColumnSpec.decode("default:grow"), }, new RowSpec[] {
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
		FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

	JLabel lblCustomer = new JLabel("Cliente");
	add(lblCustomer, "2, 2");

	// Create font.
	// Font Name : Default label font
	// Font Style : Bold
	// Font Size : Default label font size
	Font boldFont = new Font(lblCustomer.getFont().getName(), Font.BOLD,
		lblCustomer.getFont().getSize());

	// Set JLabel font using new created font
	lblCustomer.setFont(boldFont);

	JCheckBox chckbxPesquisarPorCliente = new JCheckBox(
		"Pesquisar por Cliente");
	add(chckbxPesquisarPorCliente, "4, 2");

	JLabel lblCheck = new JLabel("Cheque");
	add(lblCheck, "8, 2");
	lblCheck.setFont(boldFont);

	JCheckBox chckbxPesquisarPorPropiedade = new JCheckBox(
		"Pesquisar por Propiedade de Cheque");
	add(chckbxPesquisarPorPropiedade, "10, 2");

	JLabel lblCpfcnpj = new JLabel("CPF/CNPJ:");
	add(lblCpfcnpj, "2, 4, right, default");

	textField = new JTextField();
	add(textField, "4, 4, left, default");
	textField.setColumns(10);

	JLabel lblNmero = new JLabel("N\u00FAmero:");
	add(lblNmero, "8, 4, right, default");

	txtCheckNumber = new JTextField();
	add(txtCheckNumber, "10, 4, left, default");
	txtCheckNumber.setColumns(10);

	JLabel lblNome = new JLabel("Nome:");
	add(lblNome, "2, 6");

	JLabel lblName = new JLabel("");
	add(lblName, "4, 6");

	JLabel lblBanco = new JLabel("Banco:");
	add(lblBanco, "8, 6, right, default");

	txtBankNumber = new JTextField();
	add(txtBankNumber, "10, 6, left, default");
	txtBankNumber.setColumns(10);

	JSeparator separator = new JSeparator();
	separator.setOrientation(SwingConstants.VERTICAL);
	add(separator, "6, 2, 1, 13");

	JSeparator separator_1 = new JSeparator();
	add(separator_1, "2, 8, 3, 1");

	JLabel lblAgncia = new JLabel("Ag\u00EAncia:");
	add(lblAgncia, "8, 8, right, default");

	txtBranchNumber = new JTextField();
	add(txtBranchNumber, "10, 8, left, default");
	txtBranchNumber.setColumns(10);

	JLabel lblDate = new JLabel("Data Vcto");
	add(lblDate, "2, 10");
	lblDate.setFont(boldFont);

	JCheckBox chckbxPesquisarPorData = new JCheckBox("Pesquisar por Data");
	add(chckbxPesquisarPorData, "4, 10");

	JRadioButton rdbtnCompensado = new JRadioButton("Compensado");
	buttonGroupCashedChecks.add(rdbtnCompensado);
	add(rdbtnCompensado, "10, 10");

	JLabel lblDe = new JLabel("De:");
	add(lblDe, "2, 12, right, default");

	JXDatePicker pickerStartDate = new JXDatePicker();
	pickerStartDate.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
	add(pickerStartDate, "4, 12, left, default");

	JRadioButton rdbtnNoCompensado = new JRadioButton("N\u00E3o Compensado");
	buttonGroupCashedChecks.add(rdbtnNoCompensado);
	add(rdbtnNoCompensado, "10, 12");

	JLabel lblAt = new JLabel("At\u00E9:");
	add(lblAt, "2, 14, right, default");

	JXDatePicker pickerEndDate = new JXDatePicker();
	pickerEndDate.setDate(Calendar.getInstance().getTime());
	pickerEndDate.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
	add(pickerEndDate, "4, 14, left, default");

	JRadioButton rdbtnAmbos = new JRadioButton("Ambos");
	rdbtnAmbos.setSelected(true);
	buttonGroupCashedChecks.add(rdbtnAmbos);
	add(rdbtnAmbos, "10, 14");

	JButton btnPesquisar = new JButton("Pesquisar");
	add(btnPesquisar, "10, 16, right, default");

    }

}
