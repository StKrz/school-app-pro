package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.security.SecUtil;
import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSeparator;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class AdminUpdateDeleteUsersForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField userTxt;
	private JLabel roleLbl;
	Connection conn;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private JTextField idTxt;
	private JPasswordField newPasswordTxt;
	private JPasswordField confirmPasswordTxt;
	private String inputNewPass;
	private String inputConfirmPass;
	private JTextField roleTxt;
	
	public AdminUpdateDeleteUsersForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminUpdateDeleteUsersForm.class.getResource("/resources/eduv2.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM USERS WHERE USERNAME LIKE ?";
				
				try {
					conn = DBUtil.getConnection();
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getUsersMenu().getUser() + "%");
					rs = ps.executeQuery();
					
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "No users found", "Result", JOptionPane.INFORMATION_MESSAGE);
						Main.getAdminUpdateDeleteUsersForm().setVisible(false);
						Main.getUsersMenu().setVisible(true);
						return;
					}
					
				
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						userTxt.setText(rs.getString("USERNAME"));
						roleTxt.setText(rs.getString("ROLE"));
				
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		});
		setTitle("Ενημέρωση / Διαγραφή Χρήστη");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 434);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		newPasswordTxt = new JPasswordField();
		newPasswordTxt.setBounds(143, 206, 217, 20);
		newPasswordTxt.setColumns(10);
		contentPane.add(newPasswordTxt);
		
		idTxt = new JTextField();
		idTxt.setBounds(143, 40, 69, 20);
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		idTxt.setBackground(new Color(255, 255, 0));
		contentPane.add(idTxt);
		
		JLabel userLbl = new JLabel("Username");
		userLbl.setBounds(54, 75, 80, 17);
		userLbl.setForeground(new Color(128, 0, 0));
		userLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(userLbl);
		
		userTxt = new JTextField();
		userTxt.setBounds(143, 73, 217, 20);
		contentPane.add(userTxt);
		userTxt.setColumns(10);
		
		roleLbl = new JLabel("Ρόλος");
		roleLbl.setBounds(84, 106, 50, 17);
		roleLbl.setForeground(new Color(128, 0, 0));
		roleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(roleLbl);
		
		roleTxt = new JTextField();
		roleTxt.setColumns(10);
		roleTxt.setBounds(143, 104, 217, 20);
		contentPane.add(roleTxt);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setBounds(96, 43, 29, 17);
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(idLbl);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(45, 273, 450, 2);
		contentPane.add(separator);
		
		JLabel changePasswordLbl = new JLabel("Αλλαγή Κωδικού");
		changePasswordLbl.setBounds(143, 175, 136, 17);
		changePasswordLbl.setForeground(new Color(128, 0, 0));
		changePasswordLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(changePasswordLbl);
		
		JLabel newPassLbl = new JLabel("Νέος Κωδικός");
		newPassLbl.setBounds(21, 205, 113, 17);
		newPassLbl.setForeground(new Color(128, 0, 0));
		newPassLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(newPassLbl);
		
		confirmPasswordTxt = new JPasswordField();
		confirmPasswordTxt.setBounds(143, 233, 217, 20);
		confirmPasswordTxt.setColumns(10);
		contentPane.add(confirmPasswordTxt);
		
		JLabel confirmPassLbl = new JLabel("Επιβεβαίωση");
		confirmPassLbl.setBounds(70, 236, 66, 17);
		confirmPassLbl.setForeground(new Color(128, 0, 0));
		confirmPassLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(confirmPassLbl);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(45, 158, 450, 2);
		contentPane.add(separator_1);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getUsersMenu().setEnabled(true);
				Main.getAdminUpdateDeleteUsersForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(279, 301, 108, 49);
		contentPane.add(closeBtn);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM USERS WHERE ID = ?";
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
					
					int response = JOptionPane.showConfirmDialog(null, "Ειστέ σιίγουρος", "Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null,  n + " rown affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
					} else {
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		deleteBtn.setForeground(Color.BLUE);
		deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		deleteBtn.setBounds(171, 301, 108, 49);
		contentPane.add(deleteBtn);
		
		JButton udpateBtn = new JButton("Update");
		udpateBtn.setBounds(171, 301, 90, 45);
		
		udpateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqlSelect = "SELECT PASSWORD FROM USERS WHERE ID = ?";
				String sqlUpdate = "UPDATE USERS SET PASSWORD = ? WHERE ID = ?";
			
            	inputNewPass = String.valueOf(newPasswordTxt.getPassword()).trim();
				inputConfirmPass = String.valueOf(confirmPasswordTxt.getPassword()).trim();
				
				if (inputNewPass.equals("") || inputConfirmPass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a password!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!inputNewPass.equals(inputConfirmPass)) {
					JOptionPane.showMessageDialog(null,  "'New password' not equal with 'confirmation password'. Please try again!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
					PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

					int userId = Integer.parseInt(idTxt.getText());
					
		            psSelect.setInt(1, userId);
		            ResultSet rs = psSelect.executeQuery();
		            
		            if (rs.next()) {
		            	String storedPassword = rs.getString("PASSWORD");
		            	
		            	if (SecUtil.checkPassword(inputNewPass, storedPassword)) {
		            		JOptionPane.showMessageDialog(null,  "New password = current password❌ Please insert a new one!", "PASSWORD", JOptionPane.WARNING_MESSAGE);
		            		newPasswordTxt.setText("");
							confirmPasswordTxt.setText("");
							return;
		            	}
				
						psUpdate.setString(1, SecUtil.hashPassword(inputNewPass));
						psUpdate.setInt(2, Integer.parseInt(idTxt.getText()));
							
						int numberOfRowsAffected = psUpdate.executeUpdate();
							
						JOptionPane.showMessageDialog(null, "Succesfull update\n" + numberOfRowsAffected + " rows affected", "UPDATE", JOptionPane.PLAIN_MESSAGE);
						newPasswordTxt.setText("");
						confirmPasswordTxt.setText("");
		            }
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		udpateBtn.setForeground(Color.BLUE);
		udpateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		udpateBtn.setBounds(63, 301, 108, 49);
		contentPane.add(udpateBtn);
		
		JLabel userInfoLdbl = new JLabel("Στοιχεία Χρήστη");
		userInfoLdbl.setForeground(new Color(128, 0, 0));
		userInfoLdbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		userInfoLdbl.setBounds(129, 10, 136, 17);
		contentPane.add(userInfoLdbl);
	}
}