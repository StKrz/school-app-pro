package gr.aueb.cf.schoolpro;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminInsertTeachersForm extends JFrame {
	private static final long serialVersionUID = 123456;
	private JPanel contentPane;
	private JTextField firstnameTxt;
	private JLabel ssnLbl;
	private JTextField ssnTxt;
	private JLabel lastnameLbl;
	private JTextField lastnameTxt;
	private JLabel specialityLbl;
	private JComboBox<String> specialityCombobox;
	private JComboBox<String> userCombobox;
	private Map<String, Integer> specialities;
	private Map<String, Integer> users;
	private DefaultComboBoxModel<String> specialitiesModel;
	private DefaultComboBoxModel<String> usersModel;

	public AdminInsertTeachersForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				firstnameTxt.setText("");
				lastnameTxt.setText("");
				ssnTxt.setText("");
				specialityCombobox.setSelectedItem(null);
				userCombobox.setSelectedItem(null);	
			}
		});
		setTitle("Εισαγωγή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 468);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setBounds(55, 101, 56, 17);
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(firstnameLbl);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setBounds(129, 102, 207, 20);
		contentPane.add(firstnameTxt);
		firstnameTxt.setColumns(10);
		
		ssnLbl = new JLabel("Μητρώο");
		ssnLbl.setBounds(55, 63, 64, 17);
		ssnLbl.setForeground(new Color(128, 0, 0));
		ssnLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(ssnLbl);
		
		ssnTxt = new JTextField();
		ssnTxt.setBounds(129, 64, 207, 20);
		ssnTxt.setColumns(10);
		contentPane.add(ssnTxt);
		
		lastnameLbl = new JLabel("Επώνυμο");
		lastnameLbl.setBounds(46, 132, 73, 17);
		lastnameLbl.setForeground(new Color(128, 0, 0));
		lastnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lastnameLbl);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setBounds(129, 133, 207, 20);
		lastnameTxt.setColumns(10);
		contentPane.add(lastnameTxt);
		
		specialityLbl = new JLabel("Ειδικότητα");
		specialityLbl.setBounds(43, 195, 81, 17);
		specialityLbl.setForeground(new Color(128, 0, 0));
		specialityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(specialityLbl);
		
		specialityCombobox = new JComboBox<>();
		specialityCombobox.setBounds(129, 194, 207, 20);
		contentPane.add(specialityCombobox);
		
		specialityCombobox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String sql = "SELECT * FROM SPECIALITIES";
				
			    try (Connection connection = DBUtil.getConnection();
			    		PreparedStatement ps = connection.prepareStatement(sql);
			    		ResultSet rs = ps.executeQuery()) {
			    	
			    	specialities = new HashMap<>();
			    	specialitiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rs.next()) {
			    		String speciality = rs.getString("SPECIALITY");
			    		int id = rs.getInt("ID");
			    		specialities.put(speciality, id);
			    		specialitiesModel.addElement(speciality);
			    	}
			    	specialityCombobox.setModel(specialitiesModel);
			    	specialityCombobox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton insertBtn = new JButton("Εισαγωγή");
		
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (specialityCombobox.getSelectedItem() == null || userCombobox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Please select speciality / username", "INPUT", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String firstname = firstnameTxt.getText().trim();
				String lastname = lastnameTxt.getText().trim();
				String ssn = ssnTxt.getText().trim();
				String speciality = (String) specialityCombobox.getSelectedItem();
				String username = (String) userCombobox.getSelectedItem();
				int specialityId = specialities.get(speciality);
				int userId = users.get(username);
				
				if (firstname.equals("") || lastname.equals("") | ssn.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill firstname / lastname", "Basic info", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME, SSN, SPECIALITY_ID, USER_ID) VALUES (?, ?, ?, ?, ?)"; 
				
				try (Connection conn = DBUtil.getConnection();
						PreparedStatement ps = conn.prepareStatement(sql)) {
					
					ps.setString(1,  firstname);
					ps.setString(2,  lastname);
					ps.setString(3,  ssn);
					ps.setInt(4,  specialityId);
					ps.setInt(5,  userId);
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Η εγγραφή έγινε επιτυχώς", "Insert", JOptionPane.INFORMATION_MESSAGE);	
					
					firstnameTxt.setText("");
					lastnameTxt.setText("");
					ssnTxt.setText("");

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		insertBtn.setBounds(268, 302, 108, 47);
		insertBtn.setForeground(Color.BLUE);
		insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(insertBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenu().setEnabled(true);
				Main.getAdminInsertTeacehrsForm().setVisible(false);
			}
		});
		closeBtn.setBounds(388, 302, 108, 47);
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(closeBtn);
		
		JLabel usernameLbl = new JLabel("Username");
		usernameLbl.setForeground(new Color(128, 0, 0));
		usernameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		usernameLbl.setBounds(34, 226, 81, 17);
		contentPane.add(usernameLbl);

		userCombobox = new JComboBox<String>();
		userCombobox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String sql = "SELECT * FROM USERS";
				
				try (Connection connection = DBUtil.getConnection();
						PreparedStatement ps = connection.prepareStatement(sql);
						ResultSet rs = ps.executeQuery()) {
					
					users = new HashMap<>();
					usersModel = new DefaultComboBoxModel<>();
					
					while (rs.next()) {
						String username = rs.getString("USERNAME");
						int id = rs.getInt("ID");
						users.put(username, id);
						usersModel.addElement(username);
					}
					
					userCombobox.setModel(usersModel);
					userCombobox.setMaximumRowCount(5);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		userCombobox.setBounds(129, 225, 207, 20);
		contentPane.add(userCombobox);
	}
}