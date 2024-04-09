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

public class CitiesMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private ResultSet rs = null;
	Connection conn = null;
	private JPanel contentPane;
	private JComboBox<String> citiesCombobox = new JComboBox();
	private DefaultComboBoxModel<String> citiesModel;
	private Map<Integer, String> cities;

	public CitiesMenu() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				PreparedStatement psCities;
				ResultSet rsCities;
			    try {
			    	conn = DBUtil.getConnection();
			    	String sqlCities = "SELECT * FROM CITIES";
			    	psCities = conn.prepareStatement(sqlCities);
		    		rsCities = psCities.executeQuery();
			    	cities = new HashMap<>();
			    	citiesModel = new DefaultComboBoxModel<>();
			    	
			    	while (rsCities.next()) {
			    		String city = rsCities.getString("CITY");
			    		int id = rsCities.getInt("ID");
			    		cities.put(id, city);
			    		citiesModel.addElement(city);
			    	}
			    	citiesCombobox.setModel(citiesModel);
			    	citiesCombobox.setMaximumRowCount(5);
			    	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			    
			    try {
			    	citiesCombobox.setSelectedItem(cities.get(rs.getInt("CITY_ID")));
			    } catch (SQLException e1) {
			    	e1.printStackTrace();
			    }
				
			}
		});
		
		setTitle("Πόλεις");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel citiesLbl = new JLabel("Πόλεις");
		citiesLbl.setForeground(new Color(128, 64, 0));
		citiesLbl.setFont(new Font("Tahoma", Font.BOLD, 19));
		citiesLbl.setBounds(170, 37, 72, 48);
		contentPane.add(citiesLbl);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(144, 72, 127, 2);
		contentPane.add(separator);
		
		citiesCombobox = new JComboBox();
		citiesCombobox.setBounds(107, 100, 204, 32);
		contentPane.add(citiesCombobox);
		
		JButton closeBtn_1 = new JButton("Close");
		closeBtn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getAdminMenu().setEnabled(true);
				Main.getCitiesMenu().setVisible(false);
			}
		});
		closeBtn_1.setForeground(Color.BLUE);
		closeBtn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		closeBtn_1.setBounds(318, 204, 108, 49);
		contentPane.add(closeBtn_1);
	}
}
