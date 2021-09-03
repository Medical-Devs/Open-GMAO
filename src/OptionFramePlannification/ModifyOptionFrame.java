package OptionFramePlannification;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import Notifications.emptyFieldsMessages;
import OptionXLogin.loginInfo;

public class ModifyOptionFrame extends JFrame 
{
	private static final long serialVersionUID = 3310807720144701968L;
	private JPanel contentPane;
	private String sql;
	private Connection con;
	private PreparedStatement ps;
	private RoundJTextField mrqField, modField, typeMntnField, dateMaintField, nSerieField, refRapField;
	private JLabel typeMntLbl,  nSerieLbl, rapRefLbl, mdlLbl, mrqLbl, dateMaintLbl, titLbl;
	private RoundJButton modBtn;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					ModifyOptionFrame frame = new ModifyOptionFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ModifyOptionFrame() 
	{
		setType(Type.POPUP);
		setResizable(false);
		setTitle("Option de Modification");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 365);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titLbl = new JLabel("Option de modification");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 17));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(251, 11, 211, 31);
		contentPane.add(titLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		mrqField.setBounds(158, 140, 134, 20);
		contentPane.add(mrqField);
		mrqField.setColumns(10);
		
		mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(50, 140, 98, 20);
		contentPane.add(mrqLbl);
		
		mdlLbl = new JLabel("Modele");
		mdlLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mdlLbl.setBounds(50, 184, 98, 20);
		contentPane.add(mdlLbl);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(158, 184, 134, 20);
		contentPane.add(modField);
		
		typeMntLbl = new JLabel("Type de maintenance");
		typeMntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeMntLbl.setBounds(32, 227, 116, 20);
		contentPane.add(typeMntLbl);
		
		typeMntnField = new RoundJTextField(20);
		typeMntnField.setHorizontalAlignment(SwingConstants.CENTER);
		typeMntnField.setColumns(10);
		typeMntnField.setBounds(158, 227, 134, 20);
		contentPane.add(typeMntnField);
		
		dateMaintLbl = new JLabel("Date de maintenance");
		dateMaintLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateMaintLbl.setBounds(393, 140, 134, 20);
		contentPane.add(dateMaintLbl);
		
		dateMaintField = new RoundJTextField(20);
		dateMaintField.setHorizontalAlignment(SwingConstants.CENTER);
		dateMaintField.setColumns(10);
		dateMaintField.setBounds(554, 140, 134, 20);
		contentPane.add(dateMaintField);
		
		nSerieLbl = new JLabel("Num\u00E9ro de Serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(409, 184, 98, 20);
		contentPane.add(nSerieLbl);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieField.setColumns(10);
		autoCmp(nSerieField, "SELECT nSerie from gmao.plannification;", "nSerie");
		nSerieField.setBounds(554, 184, 134, 20);
		contentPane.add(nSerieField);
		
		rapRefLbl = new JLabel("R\u00E9ference Rapport");
		rapRefLbl.setHorizontalAlignment(SwingConstants.CENTER);
		rapRefLbl.setBounds(409, 227, 98, 20);
		contentPane.add(rapRefLbl);
		
		refRapField = new RoundJTextField(20);
		refRapField.setHorizontalAlignment(SwingConstants.CENTER);
		refRapField.setColumns(10);
		refRapField.setBounds(554, 227, 134, 20);
		contentPane.add(refRapField);
		
		modBtn = new RoundJButton("Modify");
		modBtn.setBounds(293, 287, 116, 38);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(verifField(nSerieField) == true) 
					{
						if(existanceVerif(nSerieField) == true) 
						{
							if(mrqField.isEnabled() == true) 
							{
								if(verifField(mrqField) == true) 
								{
									Modify("Marque", mrqField, nSerieField);
								} 
								else if(verifField(mrqField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.mrqFieldMsg);
								}
							}
							
							if(modField.isEnabled() == true) 
							{
								if(verifField(modField) == true) 
								{
									Modify("Modele", modField, nSerieField);
								} else if(verifField(modField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.modFildMsg);
								}	
							}
							
							if(typeMntnField.isEnabled() == true) 
							{
								if(verifField(typeMntnField) == true) 
								{
									Modify("TypeMaintenance", typeMntnField, nSerieField);
								} else if(verifField(typeMntnField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeMntnFieldMsg);
								}	
							}
							
							if(refRapField.isEnabled() == true) 
							{
								if(verifField(refRapField) == true) 
								{
									Modify("RefRapport", refRapField, nSerieField);
								} 
								else if(verifField(refRapField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.refRappFieldMsg);
								}	
							}
							
							if(dateMaintField.isEnabled() == true) 
							{
								if(verifField(dateMaintField) == true) 
								{
									Modify("DateMaint", dateMaintField, nSerieField);
								} 
								else if(verifField(dateMaintField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateDeMaintFieldMsg);
								}	
							}
						} 
						else if(existanceVerif(nSerieField) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNserieNotFound);
						}					
					} 
					else if(verifField(nSerieField) == false) 
					{
						JOptionPane.showMessageDialog(null, emptyFieldsMessages.nSerFieldMsg);
					}			
				} 
				catch (ClassNotFoundException | SQLException e1) 
				{
					e1.printStackTrace();
				} 
			}
		});
		contentPane.add(modBtn);
	}
	
	private void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	private void Modify(String str, JTextField jtxt, JTextField jts) throws ClassNotFoundException, SQLException {
		Connect();
		sql="UPDATE gmao.plannification SET " + str + "= ? WHERE nSerie = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jtxt.getText().trim());
		ps.setString(2, jts.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException 
	{
		boolean verif = false;
		Connect();
		String slqVerif = "SELECT * FROM gmao.plannification WHERE nSerie = ?";
		ps = con.prepareStatement(slqVerif);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(jt.getText().trim().equals(rs.getString("nSerie")) == true) {
				ps.close();
				rs.close();
				con.close();
				return verif = true;
			}
		}
		ps.close();
		rs.close();
		con.close();
		return verif;
	}
	
	private boolean verifField(JTextField jt)
	{
		boolean verifField = false;
			if(jt.getText().trim().isEmpty() == false) 
			{
				return verifField = true;
			}
		return verifField;
	}
	
	private void autoCmp(JTextField jt, String str1, String str2) 
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = con.prepareStatement(sqlAuto);
			ResultSet rs;
			rs = ps.executeQuery();
			while(rs.next()) {
				auto.addItem(rs.getString(str2));
			}
			con.close();
			ps.close();
			rs.close();
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public RoundJTextField getMrqField() { return mrqField; }

	public RoundJTextField getModField() { return modField; }

	public RoundJTextField getTypeMntnField() { return typeMntnField; }

	public RoundJTextField getDateMaintField() { return dateMaintField; }

	public RoundJTextField getRefRapField() { return refRapField; }

	public JLabel getTypeMntLbl() { return typeMntLbl; }

	public JLabel getRapRefLbl() { return rapRefLbl; }

	public JLabel getMdlLbl() { return mdlLbl; }

	public JLabel getMrqLbl() { return mrqLbl; }

	public JLabel getDateMaintLbl() { return dateMaintLbl; }
}