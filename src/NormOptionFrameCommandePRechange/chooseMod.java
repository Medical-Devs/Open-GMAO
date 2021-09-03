package NormOptionFrameCommandePRechange;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import EditMultipleObjects.RoundJButton;

public class chooseMod extends JFrame 
{
	private static final long serialVersionUID = 8315227181821390469L;
	private JPanel contentPane;
	public final JCheckBox fournCB = new JCheckBox("Fournisseur");
	protected final JCheckBox prixCB = new JCheckBox("Prix UHT");
	protected final JCheckBox qteCB = new JCheckBox("Quantit\u00E9");
	protected final JCheckBox servCB = new JCheckBox("Service concern\u00E9");
	protected final JCheckBox dateCB = new JCheckBox("Date de commande");

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					chooseMod frame = new chooseMod();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public chooseMod() 
	{
		setTitle("Selection des \u00E9l\u00E9ments");
		setType(Type.POPUP);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 349);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		fournCB.setSelected(true);
		fournCB.setBounds(121, 95, 139, 23);
		contentPane.add(fournCB);
		
		prixCB.setSelected(true);
		prixCB.setBounds(121, 146, 139, 23);
		contentPane.add(prixCB);
		
		qteCB.setSelected(true);
		qteCB.setBounds(121, 200, 97, 23);
		contentPane.add(qteCB);
		
		servCB.setSelected(true);
		servCB.setBounds(355, 95, 139, 23);
		contentPane.add(servCB);
		
		dateCB.setSelected(true);
		dateCB.setBounds(355, 146, 139, 23);
		contentPane.add(dateCB);
		
		JButton confBtn = new RoundJButton("Confirmer");
		confBtn.setBounds(213, 251, 113, 36);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptionFrame mod = new ModifyOptionFrame();
				
				if(verifCb(fournCB) == true) 
				{
					mod.getFournField().setEnabled(true);
					mod.getFounLbl().setEnabled(true);
				} 
				else if(verifCb(fournCB) == false) 
				{
					mod.getFournField().setEnabled(false);
					mod.getFounLbl().setEnabled(false);
				}
				
				if(verifCb(prixCB) == true) 
				{
					mod.getPrixField().setEnabled(true);
					mod.getPrixLbl().setEnabled(true);
				} 
				else if(verifCb(prixCB) == false) 
				{
					mod.getPrixField().setEnabled(false);
					mod.getPrixLbl().setEnabled(false);
				}
				
				if(verifCb(qteCB) == true) 
				{
					mod.getQteField().setEnabled(true);
					mod.getQteLbl().setEnabled(true);
				} 
				else if(verifCb(qteCB) == false) 
				{
					mod.getQteField().setEnabled(false);
					mod.getQteLbl().setEnabled(false);
				}
				
				if(verifCb(servCB) == true) 
				{
					mod.getServiceField().setEnabled(true);
					mod.getServConcLbl().setEnabled(true);
				} else if(verifCb(servCB) == false) 
				{
					mod.getServiceField().setEnabled(false);
					mod.getServConcLbl().setEnabled(false);
				}
				
				if(verifCb(dateCB) == true) 
				{
					mod.getDateField().setEnabled(true);
					mod.getDateLbl().setEnabled(true);
				} 
				else if(verifCb(dateCB) == false) 
				{
					mod.getDateField().setEnabled(false);
					mod.getDateLbl().setEnabled(false);
				}
				
				mod.setVisible(true);
				dispose();	
			}
		});
		contentPane.add(confBtn);
		
		JLabel lblNewLabel = new JLabel("Selection des \u00E9l\u00E9ments \u00E0 modifier");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(101, 24, 337, 46);
		contentPane.add(lblNewLabel);
	}
	
	private boolean verifCb(JCheckBox c) 
	{
		boolean verif = false;
		if(c.isSelected() == true) 
		{
			return verif = true;
		}
		return verif;
	}
}