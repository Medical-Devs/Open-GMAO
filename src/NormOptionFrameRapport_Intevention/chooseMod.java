package NormOptionFrameRapport_Intevention;

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
	private static final long serialVersionUID = 3473931811421479584L;
	private JPanel contentPane;

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
		setType(Type.POPUP);
		setTitle("S\u00E9lection des \u00E9lements ");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 392);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox servCB = new JCheckBox("Service");
		servCB.setSelected(true);
		servCB.setBounds(71, 121, 97, 23);
		contentPane.add(servCB);
		
		JCheckBox desCB = new JCheckBox("D\u00E9signation");
		desCB.setSelected(true);
		desCB.setBounds(71, 159, 97, 23);
		contentPane.add(desCB);
		
		JCheckBox typeApCB = new JCheckBox("Type d'appareil");
		typeApCB.setSelected(true);
		typeApCB.setBounds(71, 203, 128, 23);
		contentPane.add(typeApCB);
		
		JCheckBox mrqCB = new JCheckBox("Marque");
		mrqCB.setSelected(true);
		mrqCB.setBounds(262, 121, 97, 23);
		contentPane.add(mrqCB);
		
		JCheckBox modCB = new JCheckBox("Mod\u00E8le");
		modCB.setSelected(true);
		modCB.setBounds(262, 159, 97, 23);
		contentPane.add(modCB);
		
		JCheckBox numCB = new JCheckBox("Num\u00E9ro d'inventaire");
		numCB.setSelected(true);
		numCB.setBounds(262, 203, 156, 23);
		contentPane.add(numCB);
		
		JCheckBox nSerCB = new JCheckBox("Num\u00E9ro de serie");
		nSerCB.setSelected(true);
		nSerCB.setBounds(453, 121, 136, 23);
		contentPane.add(nSerCB);
		
		JCheckBox dateIntCB = new JCheckBox("Date d'intervention");
		dateIntCB.setSelected(true);
		dateIntCB.setBounds(453, 159, 136, 23);
		contentPane.add(dateIntCB);
		
		JButton confBtn = new RoundJButton("Confirmer");
		confBtn.setBounds(233, 283, 136, 40);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptions mod = new ModifyOptions();
				if(verifCb(servCB) == true) 
				{
					mod.getServField().setEnabled(true);
					mod.getServLbl().setEnabled(true);
				} 
				else if(verifCb(servCB) == false) 
				{
					mod.getServField().setEnabled(false);
					mod.getServLbl().setEnabled(false);	
				}
				
				if(verifCb(desCB) == true) 
				{
					mod.getDesField().setEnabled(true);
					mod.getDesLbl().setEnabled(true);
				} 
				else if(verifCb(desCB) == false) 
				{
					mod.getDesField().setEnabled(false);
					mod.getDesLbl().setEnabled(false);
				}
				
				if(verifCb(typeApCB) == true) 
				{
					mod.getTypeApLbl().setEnabled(true);
					mod.getTypeApField().setEnabled(true);
				} 
				else if(verifCb(typeApCB) == false) 
				{
					mod.getTypeApLbl().setEnabled(false);
					mod.getTypeApField().setEnabled(false);
				}
				
				if(verifCb(mrqCB) == true) 
				{
					mod.getMrqField().setEnabled(true);
					mod.getMrqLbl().setEnabled(true);
				} 
				else if(verifCb(mrqCB) == false) 
				{
					mod.getMrqField().setEnabled(false);
					mod.getMrqLbl().setEnabled(false);
				}
				
				if(verifCb(modCB) == true) 
				{
					mod.getModField().setEnabled(true);
					mod.getModLbl().setEnabled(true);
				} 
				else if(verifCb(modCB) == false) 
				{
					mod.getModField().setEnabled(false);
					mod.getModLbl().setEnabled(false);
				}
				
				if(verifCb(numCB) == true) 
				{
					mod.getnInvField().setEnabled(true);
					mod.getnInvLbl().setEnabled(true);
				} 
				else if(verifCb(numCB) == false) 
				{
					mod.getnInvField().setEnabled(false);
					mod.getnInvLbl().setEnabled(false);
				}
				
				if(verifCb(nSerCB) == true) 
				{
					mod.getnSerieField().setEnabled(true);
					mod.getnSerieLbl().setEnabled(true);
				} 
				else if(verifCb(nSerCB) == false) 
				{
					mod.getnSerieField().setEnabled(false);
					mod.getnSerieLbl().setEnabled(false);
				}
				
				if(verifCb(dateIntCB) == true) 
				{
					mod.getDateIntField().setEnabled(true);
					mod.getDateIntLbl().setEnabled(true);
				} 
				else if(verifCb(dateIntCB) == false) 
				{
					mod.getDateIntField().setEnabled(false);
					mod.getDateIntLbl().setEnabled(false);
				}
				
				mod.setVisible(true);
				dispose();
			}
		});
		contentPane.add(confBtn);
		
		JLabel lblNewLabel = new JLabel("S\u00E9l\u00E9ction des \u00E9l\u00E9ments");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(213, 37, 229, 34);
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