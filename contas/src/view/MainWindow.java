/**
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;

import controller.Controller;

/**
 * @author reneoctavio
 * 
 */
public class MainWindow {

    private JFrame frame;
    private JTable table;
    private JTextPane txtpnStatsBar;
    private JMenuItem mntmAddBank;
    private JMenuItem mntmAddCheck;
    private JMenuItem mntmAddCustomer;
    private JMenuItem mntmRemoveBank;
    private JMenuItem mntmRemoveCheck;
    private JMenuItem mntmRemoveCustomer;
    private JMenuItem mntmReportCheck;
    private JMenuItem mntmReportCustomer;
    private JMenuItem mntmReportBank;
    private JPanel northPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    MainWindow window = new MainWindow();
		    @SuppressWarnings("unused")
		    Controller controller = new Controller(window);
		    window.frame.setVisible(true);
		    window.frame.setExtendedState(window.frame
			    .getExtendedState() | JFrame.MAXIMIZED_BOTH);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public MainWindow() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 450, 300);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JMenuBar menuBar = new JMenuBar();
	frame.setJMenuBar(menuBar);

	JMenu mnAdd = new JMenu("Adicionar");
	menuBar.add(mnAdd);

	mntmAddBank = new JMenuItem("Banco");
	mnAdd.add(mntmAddBank);

	mntmAddCheck = new JMenuItem("Cheque");
	mnAdd.add(mntmAddCheck);

	mntmAddCustomer = new JMenuItem("Cliente");
	mnAdd.add(mntmAddCustomer);

	JMenu mnRemove = new JMenu("Remover");
	menuBar.add(mnRemove);

	mntmRemoveBank = new JMenuItem("Banco");
	mnRemove.add(mntmRemoveBank);

	mntmRemoveCheck = new JMenuItem("Cheque");
	mnRemove.add(mntmRemoveCheck);

	mntmRemoveCustomer = new JMenuItem("Cliente");
	mnRemove.add(mntmRemoveCustomer);

	JMenu mnReports = new JMenu("Relat\u00F3rios");
	menuBar.add(mnReports);

	mntmReportBank = new JMenuItem("Banco");
	mnReports.add(mntmReportBank);

	mntmReportCheck = new JMenuItem("Cheque");
	mnReports.add(mntmReportCheck);

	mntmReportCustomer = new JMenuItem("Cliente");
	mnReports.add(mntmReportCustomer);

	table = new JTable();
	frame.getContentPane().add(table, BorderLayout.CENTER);

	txtpnStatsBar = new JTextPane();
	txtpnStatsBar.setText("");
	frame.getContentPane().add(txtpnStatsBar, BorderLayout.SOUTH);

	northPanel = new JPanel();
	frame.getContentPane().add(northPanel, BorderLayout.NORTH);
    }

    public JMenuItem getMntmAddBank() {
	return mntmAddBank;
    }

    public JMenuItem getMntmAddCheck() {
	return mntmAddCheck;
    }

    public JMenuItem getMntmAddCustomer() {
	return mntmAddCustomer;
    }

    public JMenuItem getMntmRemoveBank() {
	return mntmRemoveBank;
    }

    public JMenuItem getMntmRemoveCheck() {
	return mntmRemoveCheck;
    }

    public JMenuItem getMntmRemoveCustomer() {
	return mntmRemoveCustomer;
    }

    public JMenuItem getMntmReportBank() {
	return mntmReportBank;
    }

    public JMenuItem getMntmReportCheck() {
	return mntmReportCheck;
    }

    public JMenuItem getMntmReportCustomer() {
	return mntmReportCustomer;
    }

    public JFrame getFrame() {
	return this.frame;
    }

    public JPanel getNorthPanel() {
	return northPanel;
    }
}
