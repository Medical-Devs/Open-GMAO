package OptionFrameMaintenance;

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
import javax.swing.JButton;
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

public class ModifyOptionsFrame extends JFrame 
{	
	private static final long serialVersionUID = -2701977653149171965L;
	private JPanel contentPane;
	private RoundJTextField srvField, desField, eqField, etatField, mrqField, 
							modField, nSerField, datPField, datIntField, decIntField, 
							durIntField, montIntField;
	public JLabel getMrqLbl() 
	{
		return mrqLbl;
	}

	private JLabel  srvLbl, desLbl, eqLbl ,etatLbl, mrqLbl, modLbl, 
					montIntLbl, datPLbl, decIntLbl, durIntLbl, datIntLbl;
	private Connection con;
	private PreparedStatement ps;
	private String sql;
	private JButton modBtn;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ModifyOptionsFrame frame = new ModifyOptionsFrame();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public ModifyOptionsFrame() 
	{
		setResizable(false);
		setTitle("Modification Option");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 753, 504);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel optTitLbl = new JLabel("Option de modification");
		optTitLbl.setFont(new Font("Tahoma", Font.PLAIN, 21));
		optTitLbl.setHorizontalAlignment(SwingConstants.CENTER);
		optTitLbl.setBounds(305, 21, 269, 46);
		contentPane.add(optTitLbl);
		
		srvField = new RoundJTextField(20);
		srvField.setHorizontalAlignment(SwingConstants.CENTER);
		srvField.setBounds(153, 102, 147, 20);
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
		contentPane.add(srvField);
		srvField.setColumns(10);
		
		srvLbl = new JLabel("Service");
		srvLbl.setHorizontalAlignment(SwingConstants.CENTER);
		srvLbl.setBounds(52, 102, 91, 20);
		contentPane.add(srvLbl);
		
		desLbl = new JLabel("Designation");
		desLbl.setHorizontalAlignment(SwingConstants.CENTER);
		desLbl.setBounds(52, 133, 91, 20);
		contentPane.add(desLbl);
		
		desField = new RoundJTextField(20);
		desField.setHorizontalAlignment(SwingConstants.CENTER);
		desField.setColumns(10);
		desField.setBounds(153, 133, 147, 20);
		desField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(desField);
		
		eqLbl = new JLabel("Equipement");
		eqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		eqLbl.setBounds(52, 164, 91, 20);
		contentPane.add(eqLbl);
		
		eqField = new RoundJTextField(20);
		eqField.setHorizontalAlignment(SwingConstants.CENTER);
		eqField.setColumns(10);
		eqField.setBounds(153, 164, 147, 20);
		eqField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(eqField);
		
		etatLbl = new JLabel("Etat");
		etatLbl.setHorizontalAlignment(SwingConstants.CENTER);
		etatLbl.setBounds(52, 195, 91, 20);
		contentPane.add(etatLbl);
		
		etatField = new RoundJTextField(20);
		etatField.setHorizontalAlignment(SwingConstants.CENTER);
		etatField.setColumns(10);
		etatField.setBounds(153, 195, 147, 20);
		etatField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(etatField);
		
		mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setBounds(52, 226, 91, 20);
		contentPane.add(mrqLbl);
		
		mrqField = new RoundJTextField(20);
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		mrqField.setColumns(10);
		mrqField.setBounds(153, 226, 147, 20);
		mrqField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(mrqField);
		
		modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setBounds(52, 257, 91, 20);
		contentPane.add(modLbl);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(153, 257, 147, 20);
		modField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(modField);
		
		montIntLbl = new JLabel("Montant d'intervention");
		montIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		montIntLbl.setBounds(12, 414, 131, 20);
		contentPane.add(montIntLbl);
		
		montIntField = new RoundJTextField(20);
		montIntField.setHorizontalAlignment(SwingConstants.CENTER);
		montIntField.setColumns(10);
		montIntField.addKeyListener(new KeyAdapter()
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
		montIntField.setBounds(153, 414, 147, 20);
		contentPane.add(montIntField);
		
		datPLbl = new JLabel("Date prevue");
		datPLbl.setHorizontalAlignment(SwingConstants.CENTER);
		datPLbl.setBounds(52, 288, 91, 20);
		contentPane.add(datPLbl);
		
		datPField = new RoundJTextField(20);
		datPField.setHorizontalAlignment(SwingConstants.CENTER);
		datPField.setColumns(10);
		datPField.setBounds(153, 288, 147, 20);
		datPField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(datPField);
		
		datIntLbl = new JLabel("Date d'intervention");
		datIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		datIntLbl.setBounds(19, 319, 124, 20);
		contentPane.add(datIntLbl);
		
		datIntField = new RoundJTextField(20);
		datIntField.setHorizontalAlignment(SwingConstants.CENTER);
		datIntField.setColumns(10);
		datIntField.setBounds(153, 319, 147, 20);
		datIntField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					modBtn.doClick();
				}
			}
		});
		contentPane.add(datIntField);
		
		decIntLbl = new JLabel("D\u00E9calage d'intervention");
		decIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		decIntLbl.setBounds(19, 350, 124, 20);
		contentPane.add(decIntLbl);
		
		decIntField = new RoundJTextField(20);
		decIntField.setHorizontalAlignment(SwingConstants.CENTER);
		decIntField.setColumns(10);
		decIntField.setBounds(153, 350, 147, 20);
		decIntField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(decIntField);
		
		durIntLbl = new JLabel("Dur\u00E9e d'intervention");
		durIntLbl.setHorizontalAlignment(SwingConstants.CENTER);
		durIntLbl.setBounds(18, 381, 125, 20);
		contentPane.add(durIntLbl);
		
		durIntField = new RoundJTextField(20);
		durIntField.setHorizontalAlignment(SwingConstants.CENTER);
		durIntField.setColumns(10);
		durIntField.setBounds(153, 381, 147, 20);
		durIntField.addKeyListener(new KeyAdapter() 
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
		contentPane.add(durIntField);
		
		modBtn = new RoundJButton("Modifier");
		modBtn.setBounds(532, 306, 111, 46);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{		
				try 
				{
					if(verifField(nSerField) == true) 
					{		
						if(existanceVerif(nSerField) == true) 
						{	
							if(srvField.isEnabled() == true) 
							{
								if(verifField(srvField) == true) 
								{
									Modify("Service", srvField, nSerField);
								} 
								else if(verifField(srvField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.serviceFieldMsg);
								}	
							}
							
							if(desField.isEnabled() == true) 
							{
								if(verifField(desField) == true) 
								{
									Modify("Designation", desField, nSerField);
								} 
								else if(verifField(desField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.desFieldMsg);
								}	
							}
							
							if(eqField.isEnabled() == true) 
							{
								if(eqField.getText().trim().isEmpty() == false) 
								{
									Modify("Equipement", eqField, nSerField);
								} 
								else if(verifField(eqField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.eqFieldMsg);
								}
							}
							
							if(etatField.isEnabled() == true) 
							{
								if(verifField(eqField) == true) 
								{
									Modify("Etat", etatField, nSerField);
								}
								else if(verifField(etatField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.etatFieldMsg);
								}	
							}
							
							if(mrqField.isEnabled() == true) 
							{
								if(verifField(mrqField) == true) 
								{
									Modify("Marque", mrqField, nSerField);
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
									Modify("Modele", modField, nSerField);
								}
								else if(verifField(modField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.modFildMsg);
								}	
							}
							
							if(datPField.isEnabled() == true) 
							{
								if(verifField(datPField) == true)
								{
									Modify("DatePrevu", datPField, nSerField);
								} 
								else if(verifField(datPField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.datPFieldMsg);
								}	
							}
							
							if(datIntField.isEnabled() == true) 
							{
								if(verifField(datIntField) == true) 
								{
									Modify("Date_Intervention", datIntField, nSerField);
								} 
								else if(verifField(datIntField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateIntFieldMsg);
								}	
							}
							
							if(decIntField.isEnabled() == true) 
							{
								if(verifField(decIntField) == true) 
								{
									Modify("Decalage_intervention", decIntField, nSerField);
								}
								else if(verifField(decIntField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.decIntFieldMsg);
								}	
							}
							
							if(durIntField.isEnabled() == true) 
							{
								if(verifField(durIntField) == true) 
								{
									Modify("Duree_Intervention", durIntField, nSerField);
								}
								else if(verifField(durIntField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.durIntFieldMsg);
								}	
							}
							
							if(montIntField.isEnabled() == true)
							{
								if(verifField(montIntField) == true) 
								{
									Modify("Montant_Intervention", montIntField, nSerField);
								} 
								else if(verifField(montIntField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.montIntFieldMsg);
								}	
							}	
						} 
						else if(existanceVerif(nSerField) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNserieNotFound);
						}
					} 
					else if(verifField(nSerField) == false) 
					{
						JOptionPane.showMessageDialog(null, emptyFieldsMessages.nSerFieldMsg);
					}	
				} 
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(modBtn);
		
		nSerField = new RoundJTextField(20);
		autoCmp(nSerField, "Select NdeSerie from gmao.maintenance;", "NdeSerie");
		nSerField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerField.setColumns(10);
		nSerField.addKeyListener(new KeyAdapter() 
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
		nSerField.setBounds(517, 257, 147, 20);
		contentPane.add(nSerField);
		
		JLabel nSerLbl = new JLabel("Num\u00E9ro de Serie");
		nSerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nSerLbl.setBounds(376, 257, 131, 20);
		contentPane.add(nSerLbl);
	}
	
	protected void Modify(String str, JTextField jtxt, JTextField jts) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "UPDATE gmao.maintenance SET " + str + "= ? WHERE NdeSerie = ?;";
		ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, jtxt.getText().trim());
		ps.setString(2, jts.getText().trim());
		ps.executeUpdate();
	}
	
	protected void Connect() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = (Connection) DriverManager.getConnection(loginInfo.getUrl(), 
													   loginInfo.getUser(), 
													   loginInfo.getPwd()
													   );
	}
	
	protected boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException
	{
		boolean existVerif = false;
		Connect();
		String sqlVerifEx = "SELECT * FROM gmao.maintenance where NdeSerie = ?;";
		ps = (PreparedStatement) con.prepareStatement(sqlVerifEx);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) 
		{
			if(jt.getText().trim().equals(rs.getString("NdeSerie")) == true) 
			{
				ps.close();
				rs.close();
				con.close();
				return existVerif = true;
			}
		}
		ps.close();
		rs.close();
		con.close();
		return existVerif;
	}
	
	protected boolean verifField(JTextField jt)
	{
		boolean verifField = false;
			if(jt.getText().trim().isEmpty() == false) 
			{
				return verifField = true;
			}
		return verifField;
	}
	
	protected void autoCmp(JTextField jt, String str1, String str2)
	{
		try 
		{
			Connect();
			TextAutoCompleter auto = new TextAutoCompleter(jt);
			String sqlAuto = str1;
			ps = (PreparedStatement) con.prepareStatement(sqlAuto);
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

	public RoundJTextField getDesField() { return desField; }

	public RoundJTextField getEqField() { return eqField; }

	public RoundJTextField getEtatField() { return etatField; }

	public RoundJTextField getMrqField() { return mrqField; }

	public RoundJTextField getModField() { return modField; }

	public RoundJTextField getDatPField() { return datPField; }

	public RoundJTextField getDatIntField() { return datIntField; }

	public RoundJTextField getDecIntField() { return decIntField; }

	public RoundJTextField getDurIntField() { return durIntField; }

	public RoundJTextField getMontIntField() { return montIntField; }
	

	public JLabel getSrvLbl() { return srvLbl; }

	public JLabel getDesLbl() { return desLbl; }

	public JLabel getEqLbl() { return eqLbl; }

	public JLabel getEtatLbl() { return etatLbl; }

	public JLabel getModLbl() { return modLbl; }

	public JLabel getMontIntLbl() { return montIntLbl; }

	public JLabel getDatPLbl() { return datPLbl; }

	public JLabel getDecIntLbl() { return decIntLbl; }

	public JLabel getDurIntLbl() { return durIntLbl; }

	public JLabel getDatIntLbl() { return datIntLbl; }
}