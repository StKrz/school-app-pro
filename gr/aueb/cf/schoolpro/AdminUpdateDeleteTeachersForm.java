package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;
import gr.aueb.cf.schoolpro.util.DateUtil;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class AdminUpdateDeleteTeachersForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField firstnameTxt;
	private JTextField lastnameTxt;

	private JComboBox<String> specialityCombobox = new JComboBox<>();
	private Map<Integer, String> specialities;
	private DefaultComboBoxModel<String> specialitiesModel;
	private JComboBox<String> userCombobox = new JComboBox<>();
	private Map<Integer, String> users;
	private DefaultComboBoxModel<String> userModel;
	
	private ButtonGroup buttonGroup = new ButtonGroup(); 
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	private JButton firstBtn;
	private JButton prevBtn;
	private JButton nextBtn;
	private JButton lastBtn;
	Connection conn = null;
	private JButton updateBtn;
	private JButton deleteBtn;
	private JButton closeBtn;
	private JTextField ssnTxt;

	public AdminUpdateDeleteTeachersForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sql = "SELECT * FROM TEACHERS WHERE LASTNAME LIKE ?";
				// Connection conn = Login.getConnection();
				//Connection conn = null;
				try {
					conn = DBUtil.getConnection();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					//DBUtil.getConnection();
					//System.out.println("Search name" + Main.getStudentsMenu().getLastname().trim())
					ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					ps.setString(1, Main.getTeachersMenu().getLastname() + "%");
					rs = ps.executeQuery();
					
					if (!rs.next()) {
						JOptionPane.showMessageDialog(null, "Empty", "Result", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				
				PreparedStatement psSpecialities;
				ResultSet rsSpecialities;
			    try {
			    	String sqlSpecialities = "SELECT * FROM SPECIALITIES";
			    	psSpecialities = conn.prepareStatement(sqlSpecialities);
		    		rsSpecialities = psSpecialities.executeQuery();
			    	specialities = new HashMap<>();
			    	specialitiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsSpecialities.next()) {
			    		String speciality = rsSpecialities.getString("SPECIALITY");
			    		int id = rsSpecialities.getInt("ID");
			    		specialities.put(id, speciality);
			    		specialitiesModel.addElement(speciality);
			    	}
			    	specialityCombobox.setModel(specialitiesModel);
			    	specialityCombobox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    PreparedStatement psUsers;
			    ResultSet rsUsers;
			    
			    try {
		    	    String sqlUsers = "SELECT ID, USERNAME FROM USERS";
				    psUsers = conn.prepareStatement(sqlUsers);
		    		rsUsers = psUsers.executeQuery();
			    	users = new HashMap<>();
			    	userModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsUsers.next()) {
			    		String username = rsUsers.getString("USERNAME");
			    		int id = rsUsers.getInt("ID");
			    		users.put(id, username);
			    		userModel.addElement(username);
			    	}
			    	userCombobox.setModel(userModel);
			    	userCombobox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			    
			    try {
					idTxt.setText(Integer.toString(rs.getInt("ID")));
					ssnTxt.setText(rs.getString("SSN"));
					firstnameTxt.setText(rs.getString("FIRSTNAME"));
					lastnameTxt.setText(rs.getString("LASTNAME"));
					userCombobox.setSelectedItem(users.get(rs.getInt("USER_ID")));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 495, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "DELETE FROM TEACHERS WHERE ID = ?";
				
				try {
					Connection connection = Login.getConnection();
					ps = connection.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(idTxt.getText()));
					
					
					int response = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος", "Warning", JOptionPane.YES_NO_OPTION);
					if (response == JOptionPane.YES_OPTION) {
						int n = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, n + " rows affected", "Delete", JOptionPane.INFORMATION_MESSAGE);
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
		deleteBtn.setBounds(196, 378, 100, 46);
		contentPane.add(deleteBtn);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getTeachersMenu().setEnabled(true);
				Main.getAdminUpdateDeleteTeachersForm().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(300, 378, 100, 46);
		contentPane.add(closeBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
		        
		        try {   
		            Connection connection = Login.getConnection();
		            ps = connection.prepareStatement(sql);
		            String firstname = firstnameTxt.getText().trim();
		            String lastname = lastnameTxt.getText().trim();
		            String id = idTxt.getText();
		            
		            if (firstname.equals("") || lastname.equals("")) {
		                JOptionPane.showMessageDialog(null, "Empty firstname / lastname", "Input Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            
		            ps.setString(1, firstname);
		            ps.setString(2, lastname);
		            ps.setInt(3,  Integer.parseInt(id));
		            
		            int n = ps.executeUpdate();
		            
		            if (n > 0) {
		                JOptionPane.showMessageDialog(null, "Successful Update", "Update", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Update Error", "Update", JOptionPane.ERROR_MESSAGE);
		            }       
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        } 
		    }
		});

		updateBtn.setForeground(Color.BLUE);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		updateBtn.setBounds(92, 378, 100, 46);
		contentPane.add(updateBtn);
		
		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		idTxt.setBackground(new Color(255, 255, 128));
		idTxt.setBounds(164, 37, 59, 20);
		contentPane.add(idTxt);
		
		firstnameTxt = new JTextField();
		firstnameTxt.setColumns(10);
		firstnameTxt.setBounds(164, 114, 203, 19);
		contentPane.add(firstnameTxt);
		
		lastnameTxt = new JTextField();
		lastnameTxt.setColumns(10);
		lastnameTxt.setBounds(164, 160, 203, 20);
		contentPane.add(lastnameTxt);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(37, 291, 413, 2);
		contentPane.add(separator);
		
		JLabel specialityLbl = new JLabel("Ειδικότητα");
		specialityLbl.setForeground(new Color(128, 0, 0));
		specialityLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		specialityLbl.setBounds(56, 202, 81, 14);
		contentPane.add(specialityLbl);
		
		JLabel lblNewLabel = new JLabel("Επώνυμο");
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(56, 160, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel firstnameLbl = new JLabel("Όνομα");
		firstnameLbl.setForeground(new Color(128, 0, 0));
		firstnameLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		firstnameLbl.setBounds(78, 114, 49, 17);
		contentPane.add(firstnameLbl);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(128, 0, 0));
		idLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		idLbl.setBounds(85, 36, 37, 17);
		contentPane.add(idLbl);
		
		JButton firstBtn = new JButton("");
		firstBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						ssnTxt.setText(Integer.toString(rs.getInt("SSN")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						specialityCombobox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		firstBtn.setIcon(new ImageIcon(AdminUpdateDeleteTeachersForm.class.getResource("/resources/First record.png")));
		firstBtn.setBounds(133, 324, 59, 34);
		contentPane.add(firstBtn);
		
		JButton prevBtn = new JButton("");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						ssnTxt.setText(Integer.toString(rs.getInt("SSN")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						specialityCombobox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		prevBtn.setIcon(new ImageIcon(AdminUpdateDeleteTeachersForm.class.getResource("/resources/Previous_record.png")));
		prevBtn.setBounds(191, 324, 59, 34);
		contentPane.add(prevBtn);
		
		JButton nextBtn = new JButton("");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						ssnTxt.setText(Integer.toString(rs.getInt("SSN")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						specialityCombobox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		nextBtn.setIcon(new ImageIcon(AdminUpdateDeleteTeachersForm.class.getResource("/resources/Next_track.png")));
		nextBtn.setBounds(250, 324, 59, 34);
		contentPane.add(nextBtn);
		
		JButton lastBtn = new JButton("");
		lastBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						idTxt.setText(Integer.toString(rs.getInt("ID")));
						ssnTxt.setText(Integer.toString(rs.getInt("SSN")));
						firstnameTxt.setText(rs.getString("FIRSTNAME"));
						lastnameTxt.setText(rs.getString("LASTNAME"));
						specialityCombobox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		lastBtn.setIcon(new ImageIcon(AdminUpdateDeleteTeachersForm.class.getResource("/resources/Last_Record.png")));
		lastBtn.setBounds(308, 324, 59, 34);
		contentPane.add(lastBtn);
		
		JLabel ssnLbl = new JLabel("SSN");
		ssnLbl.setForeground(new Color(128, 0, 0));
		ssnLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		ssnLbl.setBounds(85, 75, 37, 17);
		contentPane.add(ssnLbl);
		
		ssnTxt = new JTextField();
		ssnTxt.setBackground(new Color(255, 255, 128));
		ssnTxt.setEditable(false);
		ssnTxt.setColumns(10);
		ssnTxt.setBounds(164, 76, 203, 19);
		contentPane.add(ssnTxt);
		
		specialityCombobox.setBounds(164, 200, 203, 22);
		contentPane.add(specialityCombobox);
		
		JLabel userLbl = new JLabel("Username");
		userLbl.setForeground(new Color(128, 0, 0));
		userLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		userLbl.setBounds(56, 249, 81, 14);
		contentPane.add(userLbl);
		
		userCombobox.setBounds(164, 247, 203, 22);
		contentPane.add(userCombobox);
	}
}
