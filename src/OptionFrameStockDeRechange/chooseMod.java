package OptionFrameStockDeRechange;

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
import javax.swing.border.EmptyBorder;

public class chooseMod extends JFrame 
{
	private static final long serialVersionUID = 1169180868125231324L;
	private JPanel contentPane;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
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
		setResizable(false);
		setTitle("S\u00E9lection des \u00E9lements");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 373);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titLbl = new JLabel("S\u00E9lection des \u00E9lements");
		titLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titLbl.setBounds(142, 24, 232, 34);
		contentPane.add(titLbl);
		
		JCheckBox typeCB = new JCheckBox("Type");
		typeCB.setSelected(true);
		typeCB.setBounds(88, 99, 97, 23);
		contentPane.add(typeCB);
		
		JCheckBox mrqCB = new JCheckBox("Marque");
		mrqCB.setSelected(true);
		mrqCB.setBounds(88, 150, 97, 23);
		contentPane.add(mrqCB);
		
		JCheckBox modCB = new JCheckBox("Mod\u00E8le");
		modCB.setSelected(true);
		modCB.setBounds(88, 210, 97, 23);
		contentPane.add(modCB);
		
		JCheckBox qteCB = new JCheckBox("Quantit\u00E9");
		qteCB.setSelected(true);
		qteCB.setBounds(332, 99, 97, 23);
		contentPane.add(qteCB);
		
		JCheckBox datAcqCB = new JCheckBox("Date d'acquisition");
		datAcqCB.setSelected(true);
		datAcqCB.setBounds(332, 150, 127, 23);
		contentPane.add(datAcqCB);
		
		JButton confBtn = new JButton("Confirmer");
		confBtn.setBounds(208, 287, 97, 36);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptionFrame mod = new ModifyOptionFrame();
				if(verifCb(typeCB) == true) 
				{
					mod.getTypeField().setEnabled(true);
					mod.getTypeLbl().setEnabled(true);
				} 
				else if(verifCb(typeCB) == false) 
				{
					mod.getTypeField().setEnabled(false);
					mod.getTypeLbl().setEnabled(false);
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
				
				if(verifCb(datAcqCB) == true) 
				{
					mod.getDaField().setEnabled(true);
					mod.getLblDateDacquisition().setEnabled(true);
				} 
				else if(verifCb(datAcqCB) == false) 
				{
					mod.getDaField().setEnabled(false);
					mod.getLblDateDacquisition().setEnabled(false);
				}
				mod.setVisible(true);
				dispose();
			}
		});
		contentPane.add(confBtn);
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