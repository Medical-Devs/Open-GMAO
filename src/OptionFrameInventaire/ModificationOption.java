package OptionFrameInventaire;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class ModificationOption extends JFrame 
{
	private static final long serialVersionUID = -1676304805485778010L;
	private JPanel contentPane;
	private String sql;
	private PreparedStatement ps;
	private Connection con;
	private RoundJTextField srvField, typeEqField, desField, mrqField, nCtrField, nSerieField, modField, qteField;
	private JLabel srvLbl;
	private JLabel typeEqLbl;
	private JLabel desLbl;
	private JLabel mrqLbl;
	private JLabel nCtrLbl;
	private JLabel nSerieLbl;
	private JLabel modLbl;
	private JLabel qteLbl;
	private JLabel titLbl;
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
					ModificationOption frame = new ModificationOption();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ModificationOption() 
	{
		setResizable(false);
		setType(Type.POPUP);
		setTitle("Modification Options");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 533);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		srvField = new RoundJTextField(20);
		srvField.setHorizontalAlignment(SwingConstants.CENTER);
		srvField.setColumns(10);
		srvField.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					modBtn.doClick();
				}
			}
		});
		srvField.setBounds(130, 88, 152, 20);
		contentPane.add(srvField);
		
		srvLbl = new JLabel("Service");
		srvLbl.setHorizontalAlignment(SwingConstants.CENTER);
		srvLbl.setBounds(21, 88, 81, 20);
		contentPane.add(srvLbl);
		
		typeEqLbl = new JLabel("Type d'\u00E9quipement");
		typeEqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeEqLbl.setBounds(10, 148, 110, 20);
		contentPane.add(typeEqLbl);
		
		typeEqField = new RoundJTextField(20);
		typeEqField.setHorizontalAlignment(SwingConstants.CENTER);
		typeEqField.setColumns(10);
		typeEqField.setBounds(130, 148, 152, 20);
		contentPane.add(typeEqField);
		
		desField = new RoundJTextField(20);
		desField.setHorizontalAlignment(SwingConstants.CENTER);
		desField.setColumns(10);
		desField.setBounds(130, 212, 152, 20);
		contentPane.add(desField);
		
		desLbl = new JLabel("D\u00E9signation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(21, 212, 81, 20);
		contentPane.add(desLbl);
		
		mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(21, 274, 81, 20);
		contentPane.add(mrqLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		mrqField.setColumns(10);
		mrqField.setBounds(130, 274, 152, 20);
		contentPane.add(mrqField);
		
		nCtrLbl = new JLabel("Num\u00E9ro de Contrat");
		nCtrLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nCtrLbl.setBounds(10, 453, 106, 20);
		contentPane.add(nCtrLbl);
		
		nCtrField = new RoundJTextField(20);
		nCtrField.setHorizontalAlignment(SwingConstants.CENTER);
		nCtrField.setColumns(10);
		nCtrField.setBounds(130, 453, 152, 20);
		contentPane.add(nCtrField);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieField.setColumns(10);
		autoCmp(nSerieField, "SELECT NSerie from gmao.inventaire;", "NSerie");
		nSerieField.setBounds(476, 212, 152, 20);
		contentPane.add(nSerieField);
		
		nSerieLbl = new JLabel("Num\u00E9ro de Serie");
		nSerieLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieLbl.setBounds(347, 212, 101, 20);
		contentPane.add(nSerieLbl);
		
		modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(21, 391, 81, 20);
		contentPane.add(modLbl);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(130, 391, 152, 20);
		contentPane.add(modField);
		
		qteField = new RoundJTextField(20);
		qteField.setHorizontalAlignment(SwingConstants.CENTER);
		qteField.setColumns(10);
		contentPane.add(qteField);
		
		qteLbl = new JLabel("Quantité");
		qteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		qteLbl.setBounds(21, 331, 81, 20);
		contentPane.add(qteLbl);
		
		titLbl = new JLabel("Option de modification");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(245, 11, 212, 37);
		contentPane.add(titLbl);
		
		modBtn = new RoundJButton("Modifier");
		modBtn.setBounds(437, 274, 131, 43);
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
							if(srvField.isEnabled() == true) 
							{
								if(verifField(srvField) == true) 
								{
									Modifier("Service", srvField, nSerieField);
								} 
								else if(verifField(srvField) == false) 
								{
									 JOptionPane.showMessageDialog(null, emptyFieldsMessages.serviceFieldMsg);
								}	
							} 
							
							if(typeEqField.isEnabled() == true)
							{
								if(verifField(typeEqField) == true) 
								{
									Modifier("TypeEquipement", typeEqField, nSerieField);
								} 
								else if(verifField(typeEqField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeEqFieldMsg);
								}	
							} 
							 
							if(desField.isEnabled() == true)
							{
								if(verifField(desField) == true)
								{
									Modifier("Designation", desField, nSerieField);
								} 
								else if(verifField(desField) == false)
								{
									 JOptionPane.showMessageDialog(null, emptyFieldsMessages.desFieldMsg);
								}	
							} 
							
							if(mrqField.isEnabled() == true) 
							{
								if(verifField(mrqField) == true) 
								{
									Modifier("Marque", mrqField, nSerieField);
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
									Modifier("Modele", modField, nSerieField);
								} 
								else if(verifField(modField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.modFildMsg);
								}	
							} 
							
							if(qteField.isEnabled() == true) 
							{
								if(verifField(qteField) == true) 
								{
									Modifier("Quantite", qteField, nSerieField);
								} 
								else if(verifField(qteField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.qteFieldMsg);
								}	
							}
							
							if(nCtrField.isEnabled() == true) 
							{
								if(verifField(nCtrField) == true)
								{
									Modifier("NContrat", nCtrField, nSerieField);
								} 
								else if(verifField(nCtrField) == false) 
								{
									 JOptionPane.showMessageDialog(null, emptyFieldsMessages.nCtrFieldMsg);
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
				} catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(modBtn);
	}
	
	private void Modifier(String str, JTextField jtxt, JTextField jts) throws ClassNotFoundException, SQLException {
		Connect();
		sql = "UPDATE gmao.inventaire SET "+ str + " = ? WHERE NSerie = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jtxt.getText());
		ps.setString(2, jts.getText());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException
	{
		boolean verif = false;
		Connect();
		String sqlVerif = "SELECT * from gmao.inventaire where NSerie = ?;";
		ps = con.prepareStatement(sqlVerif);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(jt.getText().trim().equals(rs.getString("NSerie")) == true) 
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

	private void Connect() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
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
			while(rs.next()) 
			{
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
	
	public RoundJTextField getSrvField() { return srvField; }

	public RoundJTextField getTypeEqField() { return typeEqField; }

	public RoundJTextField getDesField() { return desField; }

	public RoundJTextField getMrqField() { return mrqField; }

	public RoundJTextField getnCtrField() { return nCtrField; }

	public RoundJTextField getModField() { return modField; }

	public RoundJTextField getQteField() { return qteField; }
	

	public JLabel getSrvLbl() { return srvLbl; }

	public JLabel getTypeEqLbl() { return typeEqLbl; }

	public JLabel getDesLbl() { return desLbl; }

	public JLabel getMrqLbl() { return mrqLbl; }

	public JLabel getnCtrLbl() { return nCtrLbl; }

	public JLabel getModLbl() { return modLbl; }

	public JLabel getQteLbl() { return qteLbl; }

	public JLabel getTitLbl() { return titLbl; }
}