package NormOptionFrameRapport_Intevention;

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
import javax.swing.border.EmptyBorder;
import com.mxrck.autocompleter.TextAutoCompleter;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.RoundJTextField;
import Notifications.MessageManager;
import Notifications.emptyFieldsMessages;
import OptionXLogin.loginInfo;

public class ModifyOptions extends JFrame 
{
	private static final long serialVersionUID = -7313840338503264522L;
	private JPanel contentPane;
	private RoundJTextField servField,desField,typeApField,mrqField,modField,nInvField,nSerieField,dateIntField,refRapField;
	private String sql;
	private JLabel 	servLbl, desLbl, typeApLbl, mrqLbl, modLbl, nInvLbl, nSerieLbl,dateIntLbl, refRapLbl;
	private Connection con;
	private PreparedStatement ps;
	private RoundJButton modBtn;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ModifyOptions frame = new ModifyOptions();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public ModifyOptions() 
	{
		setTitle("Modifying Options");
		setResizable(false);
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 792, 440);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titLbl = new JLabel("Modifying Options");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(285, 26, 183, 31);
		contentPane.add(titLbl);

		servLbl = new JLabel("Service");
		servLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servLbl.setBounds(10, 100, 119, 31);
		contentPane.add(servLbl);
		
		servField = new RoundJTextField(20);
		servField.setBounds(126, 105, 183, 20);
		contentPane.add(servField);
		servField.setColumns(10);
		
		desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(10, 142, 119, 31);
		contentPane.add(desLbl);
		
		desField = new RoundJTextField(20);
		desField.setColumns(10);
		desField.setBounds(126, 147, 183, 20);
		contentPane.add(desField);
		
		typeApLbl = new JLabel("Type d'appareil");
		typeApLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeApLbl.setBounds(10, 184, 119, 31);
		contentPane.add(typeApLbl);
		
		typeApField = new RoundJTextField(20);
		typeApField.setColumns(10);
		typeApField.setBounds(126, 189, 183, 20);
		contentPane.add(typeApField);
		
		mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(10, 226, 119, 31);
		contentPane.add(mrqLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setColumns(10);
		mrqField.setBounds(126, 231, 183, 20);
		contentPane.add(mrqField);
		
		modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(10, 268, 119, 31);
		contentPane.add(modLbl);
		
		modField = new RoundJTextField(20);
		modField.setColumns(10);
		modField.setBounds(126, 273, 183, 20);
		contentPane.add(modField);
		
		nInvLbl = new JLabel("Num\u00E9ro d'inventaire");
		nInvLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nInvLbl.setBounds(416, 100, 130, 31);
		contentPane.add(nInvLbl);
		
		nInvField = new RoundJTextField(20);
		nInvField.setColumns(10);
		nInvField.setBounds(556, 105, 183, 20);
		contentPane.add(nInvField);
		
		nSerieLbl = new JLabel("Num\u00E9ro de Serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(416, 142, 119, 31);
		contentPane.add(nSerieLbl);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setColumns(10);
		nSerieField.setBounds(556, 147, 183, 20);
		contentPane.add(nSerieField);
		
		dateIntLbl = new JLabel("Date d'intervention");
		dateIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateIntLbl.setBounds(416, 189, 130, 20);
		contentPane.add(dateIntLbl);
		
		dateIntField = new RoundJTextField(20);
		dateIntField.setColumns(10);
		dateIntField.setBounds(556, 189, 183, 20);
		contentPane.add(dateIntField);
		
		refRapLbl = new JLabel("R\u00E9f\u00E9rence du Rapport");
		refRapLbl.setHorizontalAlignment(SwingConstants.CENTER);
		refRapLbl.setBounds(416, 226, 130, 31);
		contentPane.add(refRapLbl);
		
		refRapField = new RoundJTextField(20);
		refRapField.setColumns(10);
		autoCmp(refRapField, "Select Reference_Rapport from gmao.rapport_intervention;", "Reference_Rapport");
		refRapField.setBounds(556, 231, 183, 20);
		contentPane.add(refRapField);
		
		modBtn = new RoundJButton("Modifier");
		modBtn.setBounds(315, 339, 105, 43);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					if(verifField(refRapField) == true) 
					{
						if(existanceVerif(refRapField) == true) 
						{
							if(servField.isEnabled() == true) 
							{
								if(verifField(servField) == true) 
								{
									Modify("Service", servField, refRapField);
								} 
								else if(verifField(servField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.serviceFieldMsg);
								}
							}
							
							if(desField.isEnabled() == true) 
							{
								if(verifField(desField) == true) 
								{
									Modify("Designation", desField, refRapField);
								} 
								else if(verifField(desField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.designationMsg);
								}
							}
							
							if(typeApField.isEnabled() == true) 
							{
								if(verifField(typeApField) == true) 
								{
									Modify("TypeAppareil", typeApField, refRapField);
								} 
								else if(verifField(typeApField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeApFieldMsg);
								}
							} 
							
							if(mrqField.isEnabled() == true) 
							{
								if(verifField(mrqField) == true) 
								{
									Modify("Marque", mrqField, refRapField);
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
									Modify("Modele", modField, refRapField);
								} 
								else if(verifField(modField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.modFildMsg);
								}
							} 
							
							if(nInvField.isEnabled() == true) 
							{
								if(verifField(nInvField) == true) 
								{
									Modify("NInventaire", nInvField, refRapField);
								} 
								else if(verifField(nInvField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.nInvMsg);
								}
							} 
							
							if(nSerieField.isEnabled() == true) 
							{
								if(verifField(nSerieField) == true) 
								{
									Modify("NSerie", nSerieField, refRapField);
								} 
								else if(verifField(nSerieField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.nSerFieldMsg); 
								}
							} 
							
							if(dateIntField.isEnabled() == true) 
							{
								if(verifField(dateIntField) == true) 
								{
									Modify("Date_Intervention", dateIntField, refRapField);
								} 
								else if(verifField(dateIntField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateIntFieldMsg);
								}
							} 
						} 
						else if(existanceVerif(refRapField) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemRefRapportNotFound);
						}
					} 
					else if(verifField(refRapField) == false) 
					{
						JOptionPane.showMessageDialog(null, emptyFieldsMessages.refRappFieldMsg);
					}
				} 
				catch(Exception e1) 
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
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Modify(String str, JTextField jt, JTextField jts) throws ClassNotFoundException, SQLException {
		Connect();
		sql = "UPDATE gmao.rapport_intervention SET " + str + " = ? WHERE Reference_Rapport = ?;";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jt.getText());
		ps.setString(2, jts.getText());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException
	{
		Connect();
		boolean verif = false;
		String sqlVerifEx = "SELECT * FROM gmao.rapport_intervention where Reference_Rapport = ?;";
		ps = (PreparedStatement) con.prepareStatement(sqlVerifEx);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(jt.getText().trim().equals(rs.getString("Reference_Rapport")) == true) 
			{
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
			ps = (PreparedStatement) con.prepareStatement(sqlAuto);
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
	
	public JLabel getServLbl() { return servLbl; }

	public JLabel getDesLbl() { return desLbl; }

	public JLabel getTypeApLbl() { return typeApLbl; }

	public JLabel getMrqLbl() { return mrqLbl; }

	public JLabel getModLbl() { return modLbl; }

	public JLabel getnInvLbl() { return nInvLbl; }

	public JLabel getnSerieLbl() { return nSerieLbl; }

	public JLabel getDateIntLbl() { return dateIntLbl; }
	

	public RoundJTextField getServField() { return servField; }

	public RoundJTextField getDesField() { return desField; }

	public RoundJTextField getMrqField() { return mrqField; }

	public RoundJTextField getModField() { return modField; }

	public RoundJTextField getnInvField() { return nInvField; }

	public RoundJTextField getnSerieField() { return nSerieField; }

	public RoundJTextField getDateIntField() { return dateIntField; }
	
	public RoundJTextField getTypeApField() { return typeApField; }
}