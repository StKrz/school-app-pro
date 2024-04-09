package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminMenu extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;

	
	public AdminMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminMenu.class.getResource("/resources/eduv2.png")));
		setTitle("Admin Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 505);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton teachersBtn = new JButton("");
		teachersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		teachersBtn.setBounds(32, 67, 40, 40);
		contentPane.add(teachersBtn);
		
		JLabel teachersLbl = new JLabel("Εκπαιδευτές");
		teachersLbl.setForeground(new Color(128, 64, 0));
		teachersLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		teachersLbl.setBounds(82, 73, 86, 29);
		contentPane.add(teachersLbl);
		
		JButton stedentsBtn = new JButton("");
		stedentsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getStudentsMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		stedentsBtn.setBounds(32, 124, 40, 40);
		contentPane.add(stedentsBtn);
		
		JLabel studentsLbl = new JLabel("Εκπαιδευόμενοι");
		studentsLbl.setForeground(new Color(128, 64, 0));
		studentsLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		studentsLbl.setBounds(82, 130, 117, 29);
		contentPane.add(studentsLbl);
		
		JButton usersBtn = new JButton("");
		usersBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUsersMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		usersBtn.setBounds(32, 181, 40, 40);
		contentPane.add(usersBtn);
		
		JLabel usersLbl = new JLabel("Χρήστες");
		usersLbl.setForeground(new Color(128, 64, 0));
		usersLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usersLbl.setBounds(82, 187, 70, 29);
		contentPane.add(usersLbl);
		
		JButton citiesBtn = new JButton("");
		citiesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getCitiesMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		citiesBtn.setBounds(32, 238, 40, 40);
		contentPane.add(citiesBtn);
		
		JLabel citiesLbl = new JLabel("Πόλεις");
		citiesLbl.setForeground(new Color(128, 64, 0));
		citiesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		citiesLbl.setBounds(82, 244, 51, 29);
		contentPane.add(citiesLbl);
		
		JButton specialitiesBtn = new JButton("");
		specialitiesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSpecialitiesMenu().setVisible(true);
				Main.getAdminMenu().setEnabled(false);
			}
		});
		specialitiesBtn.setBounds(32, 295, 40, 40);
		contentPane.add(specialitiesBtn);
		
		JLabel specialitiesLbl = new JLabel("Ειδικότητες");
		specialitiesLbl.setForeground(new Color(128, 64, 0));
		specialitiesLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		specialitiesLbl.setBounds(82, 301, 86, 29);
		contentPane.add(specialitiesLbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(32, 388, 270, 1);
		contentPane.add(separator);
		
		JButton btnNewButton_2 = new JButton("Close App");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_2.setForeground(new Color(0, 0, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(204, 417, 103, 40);
		contentPane.add(btnNewButton_2);
		
		JButton meetingsBtn = new JButton("");
		meetingsBtn.setBounds(32, 11, 40, 40);
		contentPane.add(meetingsBtn);
		
		JLabel meetingsLbl = new JLabel("Συναντήσεις");
		meetingsLbl.setForeground(new Color(128, 64, 0));
		meetingsLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		meetingsLbl.setBounds(82, 17, 95, 29);
		contentPane.add(meetingsLbl);
	}
}