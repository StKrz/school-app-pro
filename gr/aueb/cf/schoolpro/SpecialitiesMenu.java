package gr.aueb.cf.schoolpro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gr.aueb.cf.schoolpro.util.DBUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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

public class SpecialitiesMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ResultSet rs = null;
	Connection conn = null;
	private JComboBox<String> specialitiesCombobox = new JComboBox();
	private DefaultComboBoxModel<String> specialitiesModel;
	private Map<Integer, String> specialities;

	public SpecialitiesMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				PreparedStatement psSpecialities;
				ResultSet rsSpecialities;
			    try {
			    	conn = DBUtil.getConnection();
			    	String sqlSpecialities = "SELECT * FROM SPECIALITIES";
			    	psSpecialities = conn.prepareStatement(sqlSpecialities);
		    		rsSpecialities = psSpecialities.executeQuery();
			    	specialities = new HashMap<>();
			    	specialitiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsSpecialities.next()) {
			    		String speciality = rsSpecialities.getString("Speciality");
			    		int id = rsSpecialities.getInt("ID");
			    		specialities.put(id, speciality);
			    		specialitiesModel.addElement(speciality);
			    	}
			    	specialitiesCombobox.setModel(specialitiesModel);
			    	specialitiesCombobox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    try {
			    	specialitiesCombobox.setSelectedItem(specialities.get(rs.getInt("SPECIALITY_ID")));
			    } catch (SQLException e1) {
			    	e1.printStackTrace();
			    }
			}
		});
		setTitle("Ειδικότητες");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel specialitiesLbl = new JLabel("Ειδικότητες");
		specialitiesLbl.setForeground(new Color(128, 64, 0));
		specialitiesLbl.setFont(new Font("Tahoma", Font.BOLD, 19));
		specialitiesLbl.setBounds(139, 44, 141, 48);
		contentPane.add(specialitiesLbl);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(133, 81, 127, 2);
		contentPane.add(separator);
		
		specialitiesCombobox = new JComboBox();
		specialitiesCombobox.setBounds(99, 102, 204, 32);
		contentPane.add(specialitiesCombobox);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(true);
				Main.getSpecialitiesMenu().setVisible(false);
			}
		});
		closeBtn.setForeground(Color.BLUE);
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn.setBounds(298, 204, 108, 49);
		contentPane.add(closeBtn);
	}

}
