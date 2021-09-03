package OptionFrameStockDeRechange;

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
	private static final long serialVersionUID = -4245952642576128648L;
	private JPanel contentPane;
	private RoundJTextField typeField, mrqField, modField, qteField, daField, nSerieField;
	private String sql;
	private JLabel titLbl, lblNumroDeSrie, typeLbl, mrqLbl, modLbl, qteLbl, lblDateDacquisition;
	private RoundJButton modBtn;
	private Connection con;
	private PreparedStatement ps;

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
		setTitle("Mofication Option");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 355);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titLbl = new JLabel("Modifying Items");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 28));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(219, 30, 292, 34);
		contentPane.add(titLbl);
		
		typeLbl = new JLabel("Type d'\u00E9quipement");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		typeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		typeLbl.setBounds(10, 115, 152, 15);
		contentPane.add(typeLbl);
		
		mrqLbl = new JLabel("Marque");
		mrqLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mrqLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		mrqLbl.setBounds(57, 156, 58, 15);
		contentPane.add(mrqLbl);
		
		modLbl = new JLabel("Mod\u00E8le");
		modLbl.setHorizontalAlignment(SwingConstants.CENTER);
		modLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		modLbl.setBounds(57, 196, 58, 15);
		contentPane.add(modLbl);
		
		qteLbl = new JLabel("Quantit\u00E9");
		qteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		qteLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		qteLbl.setBounds(57, 239, 58, 15);
		contentPane.add(qteLbl);
		
		lblDateDacquisition = new JLabel("Date d'acquisition");
		lblDateDacquisition.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateDacquisition.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDateDacquisition.setBounds(28, 283, 116, 15);
		contentPane.add(lblDateDacquisition);
		
		lblNumroDeSrie = new JLabel("Num\u00E9ro de S\u00E9rie");
		lblNumroDeSrie.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumroDeSrie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumroDeSrie.setBounds(477, 168, 116, 15);
		contentPane.add(lblNumroDeSrie);
		
		typeField = new RoundJTextField(20);
		typeField.setHorizontalAlignment(SwingConstants.CENTER);
		typeField.setBounds(154, 113, 167, 20);
		contentPane.add(typeField);
		typeField.setColumns(10);
		
		mrqField = new RoundJTextField(20);
		autoCmp(mrqField, "Select mrq from marque;", "mrq");
		mrqField.setHorizontalAlignment(SwingConstants.CENTER);
		mrqField.setColumns(10);
		mrqField.setBounds(154, 154, 167, 20);
		contentPane.add(mrqField);
		
		modField = new RoundJTextField(20);
		modField.setHorizontalAlignment(SwingConstants.CENTER);
		modField.setColumns(10);
		modField.setBounds(154, 194, 167, 20);
		contentPane.add(modField);
		
		qteField = new RoundJTextField(20);
		qteField.setHorizontalAlignment(SwingConstants.CENTER);
		qteField.setColumns(10);
		qteField.setBounds(154, 237, 167, 20);
		contentPane.add(qteField);
		
		daField = new RoundJTextField(20);
		daField.setHorizontalAlignment(SwingConstants.CENTER);
		daField.setColumns(10);
		daField.setBounds(154, 281, 167, 20);
		contentPane.add(daField);
		
		nSerieField = new RoundJTextField(20);
		nSerieField.setHorizontalAlignment(SwingConstants.CENTER);
		nSerieField.setColumns(10);
		nSerieField.setBounds(451, 194, 167, 20);
		contentPane.add(nSerieField);
		
		modBtn = new RoundJButton("Modifier");
		modBtn.setBounds(477, 253, 116, 34);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try {
					if(verifField(nSerieField) == true) 
					{
						if(existanceVerif(nSerieField) == true) 
						{
							
							if(typeField.isEnabled() == true) 
							{
								if(verifField(typeField) == true)
								{
									Modify("Type", typeField, nSerieField);
								}
								else if(verifField(typeField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.typeFieldMsg);
								}
							}
							
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
									Modify("Qte", qteField, nSerieField);
								} else if(verifField(qteField) == false)
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.qteFieldMsg);
								}
							}
							
							if(daField.isEnabled() == true)
							{
								if(verifField(daField) == true) 
								{
									Modify("DateAcquisition", daField, nSerieField);
								} 
								else if(verifField(daField) == false)
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateAcqFieldMsg);
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
		con = DriverManager.getConnection(loginInfo.getUrl(), loginInfo.getUser(), loginInfo.getPwd());
	}
	
	private void Modify(String str, 
						JTextField jt, 
						JTextField jts) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "UPDATE gmao.stockderechange SET " + str + " = ? WHERE NdeSerie = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jt.getText().trim());
		ps.setString(2, jts.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException
	{
		boolean existVerif = false;
		Connect();
		String sqlVerifEx = "SELECT * FROM gmao.stockderechange where NdeSerie = ?;";
		ps = con.prepareStatement(sqlVerifEx);
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
		try {
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
		} catch (ClassNotFoundException | SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public RoundJTextField getTypeField() { return typeField; }

	public RoundJTextField getMrqField() { return mrqField; }

	public RoundJTextField getModField() { return modField; }

	public RoundJTextField getQteField() { return qteField; }

	public RoundJTextField getDaField() { return daField; }
	

	public JLabel getTypeLbl() { return typeLbl; }

	public JLabel getMrqLbl() { return mrqLbl; }

	public JLabel getModLbl() { return modLbl; }

	public JLabel getQteLbl() { return qteLbl; }
	

	public JLabel getLblDateDacquisition() { return lblDateDacquisition; }
}