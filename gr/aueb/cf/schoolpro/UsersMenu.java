package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class UsersMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userTxt;
	private String username = "";

	public UsersMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				userTxt.setText("");
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(UsersMenu.class.getResource("/resources/eduv2.png")));
		setTitle("Αναζήτηση Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userLbl = new JLabel("Όνομα Χρήστη");
		userLbl.setForeground(new Color(128, 0, 0));
		userLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		userLbl.setBounds(142, 27, 144, 35);
		contentPane.add(userLbl);
		
		userTxt = new JTextField();
		userTxt.setForeground(new Color(192, 192, 192));
		userTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		userTxt.setText("Εισάγετε Username");
		userTxt.setBounds(112, 84, 195, 43);
		contentPane.add(userTxt);
		userTxt.setColumns(10);
		
		JButton closeBtn_1 = new JButton("Close");
		closeBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setVisible(true);
				Main.getUsersMenu().setEnabled(false);
			}
		});
		closeBtn_1.setForeground(Color.BLUE);
		closeBtn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn_1.setBounds(307, 204, 108, 49);
		contentPane.add(closeBtn_1);
		
		JButton searchBtn = new JButton("Αναζήτηση");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminUpdateDeleteUsersForm().setVisible(true);
				Main.getUsersMenu().setEnabled(false);
				username = userTxt.getText().trim();
			}
		});
		searchBtn.setForeground(Color.BLUE);
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn.setBounds(154, 150, 120, 49);
		contentPane.add(searchBtn);
	}
	
	public String getUser() {
		return username;
	}
}
