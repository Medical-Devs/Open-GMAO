package NormOptionFrameCommandePRechange;

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
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private RoundJTextField fournField,nComField, prixField, qteField,serviceField,dateField;
	private String sql;
	private JLabel titLabel, founLbl, nCommandeLbl, prixLbl, qteLbl, servConcLbl, dateLbl;
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
		setResizable(false);
		setTitle("Options de modifications");
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 698, 429);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titLabel = new JLabel("Option de modifications");
		titLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titLabel.setBounds(220, 43, 286, 34);
		contentPane.add(titLabel);
		
		founLbl = new JLabel("Fournisseur");
		founLbl.setHorizontalAlignment(SwingConstants.CENTER);
		founLbl.setBounds(46, 134, 82, 23);
		contentPane.add(founLbl);
		
		fournField = new RoundJTextField(20);
		fournField.setBounds(155, 135, 173, 20);
		contentPane.add(fournField);
		fournField.setColumns(10);
		
		nComField = new RoundJTextField(20);
		nComField.setColumns(10);
		autoCmp(nComField, "Select NCommande from gmao.commandePieceRechange;", "NCommande");
		nComField.setBounds(454, 217, 173, 20);
		contentPane.add(nComField);
		
		nCommandeLbl = new JLabel("Num\u00E9ro de Commande");
		nCommandeLbl.setHorizontalAlignment(SwingConstants.CENTER);
		nCommandeLbl.setBounds(454, 183, 173, 23);
		contentPane.add(nCommandeLbl);
		
		prixField = new RoundJTextField(20);
		prixField.setColumns(10);
		prixField.setBounds(155, 183, 173, 20);
		contentPane.add(prixField);
		
		prixLbl = new JLabel("Prix UHT");
		prixLbl.setHorizontalAlignment(SwingConstants.CENTER);
		prixLbl.setBounds(46, 182, 82, 23);
		contentPane.add(prixLbl);
		
		qteField = new RoundJTextField(20);
		qteField.setColumns(10);
		qteField.setBounds(155, 231, 173, 20);
		contentPane.add(qteField);
		
		qteLbl = new JLabel("Quantit\u00E9");
		qteLbl.setHorizontalAlignment(SwingConstants.CENTER);
		qteLbl.setBounds(46, 230, 82, 23);
		contentPane.add(qteLbl);
		
		serviceField = new RoundJTextField(20);
		serviceField.setColumns(10);
		serviceField.setBounds(155, 280, 173, 20);
		contentPane.add(serviceField);
		
		servConcLbl = new JLabel("Service Concern\u00E9");
		servConcLbl.setHorizontalAlignment(SwingConstants.CENTER);
		servConcLbl.setBounds(31, 279, 109, 23);
		contentPane.add(servConcLbl);
		
		dateField = new RoundJTextField(20);
		dateField.setColumns(10);
		dateField.setBounds(155, 331, 173, 20);
		contentPane.add(dateField);
		
		dateLbl = new JLabel("Date de Commande");
		dateLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dateLbl.setBounds(21, 331, 124, 20);
		contentPane.add(dateLbl);
		
		modBtn = new RoundJButton("Modifier");
		modBtn.setBounds(477, 260, 130, 40);
		modBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				try 
				{	
					if(verifField(nComField) == true) 
					{
						if(existanceVerif(nComField) == true) 
						{
							if(fournField.isEnabled() == true) 
							{
								if(verifField(fournField) == true) 
								{
									Modify("Fournisseur", fournField, nComField);
								} 
								else if(verifField(fournField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.fournFieldMsg);
								}
							}
							
							if(prixField.isEnabled() == true) 
							{
								if(verifField(prixField) == true) 
								{
									Modify("PrixUHT", prixField, nComField);
								} 
								else if(verifField(prixField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.prixFieldMsg);		
								}	
							}
							
							if(qteField.isEnabled() == true) 
							{
								if(verifField(qteField) == true) 
								{
									Modify("Qte", qteField, nComField);
								} 
								else if(verifField(qteField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.qteFieldMsg);
								}	
							}
							
							if(serviceField.isEnabled() == true) 
							{
								if(verifField(serviceField) == true) 
								{
									Modify("Service_Concerne", serviceField, nComField);
								} 
								else if(verifField(serviceField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.serviceFieldMsg);
								}
							}
							
							if(dateField.isEnabled() == true) 
							{
								if(verifField(dateField) == true) 
								{
									Modify("Date_Commande", dateField, nComField);
								} 
								else if(verifField(dateField) == false) 
								{
									JOptionPane.showMessageDialog(null, emptyFieldsMessages.dateFieldMsg);
								}
							}	
						}
						else if(existanceVerif(nComField) == false) 
						{
							JOptionPane.showMessageDialog(null, MessageManager.itemNCommandeNotFound);
						}	
					} 
					else if(verifField(nComField) == false) 
					{
						JOptionPane.showMessageDialog(null, emptyFieldsMessages.numComFieldMsg);
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
	
	private void Modify(String str, JTextField jtxt, JTextField jts) throws ClassNotFoundException, SQLException 
	{
		Connect();
		sql = "UPDATE gmao.commandepiecerechange SET " + str + " = ? WHERE NCommande = ?;";
		ps = con.prepareStatement(sql);
		ps.setString(1, jtxt.getText().trim());
		ps.setString(2, jts.getText().trim());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	private boolean existanceVerif(JTextField jt) throws ClassNotFoundException, SQLException
	{
		boolean existVerif = false;
		Connect();
		String sqlVerifEx = "SELECT * FROM gmao.commandepiecerechange where NCommande = ?;";
		ps = con.prepareStatement(sqlVerifEx);
		ps.setString(1, jt.getText().trim());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			if(jt.getText().trim().equals(rs.getString("NCommande")) == true) {
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
	
	public RoundJTextField getFournField() { return fournField ;}

	public RoundJTextField getPrixField() { return prixField; }

	public RoundJTextField getQteField() { return qteField; }

	public RoundJTextField getServiceField()  { return serviceField; }

	public RoundJTextField getDateField()  { return dateField; }
	

	public JLabel getFounLbl()  { return founLbl; }

	public JLabel getPrixLbl() { return prixLbl; }

	public JLabel getQteLbl() { return qteLbl; }

	public JLabel getServConcLbl()  { return servConcLbl; }

	public JLabel getDateLbl()  { return dateLbl; }
}