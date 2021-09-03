package OptionFramePlannification;

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
	private static final long serialVersionUID = -8888850659169646681L;
	private JPanel contentPane;
	protected final JCheckBox mrqCB, modCB, typeMaintCB, datMaintCB, refRapCB;

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
		setTitle("S\u00E9lection des \u00E9lements");
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 422);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("S\u00E9lection des \u00E9lements");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(187, 27, 245, 50);
		contentPane.add(lblNewLabel);
		
		mrqCB = new JCheckBox("Marque");
		mrqCB.setSelected(true);
		mrqCB.setBounds(112, 115, 97, 23);
		contentPane.add(mrqCB);
		
		modCB = new JCheckBox("Mod\u00E8le");
		modCB.setSelected(true);
		modCB.setBounds(112, 171, 97, 23);
		contentPane.add(modCB);
		
		typeMaintCB = new JCheckBox("Type de maintenance");
		typeMaintCB.setSelected(true);
		typeMaintCB.setBounds(112, 233, 165, 23);
		contentPane.add(typeMaintCB);
		
		datMaintCB = new JCheckBox("Date de maintenance");
		datMaintCB.setSelected(true);
		datMaintCB.setBounds(412, 115, 165, 23);
		contentPane.add(datMaintCB);
		
		refRapCB = new JCheckBox("R\u00E9ference du rapport");
		refRapCB.setSelected(true);
		refRapCB.setBounds(412, 171, 165, 23);
		contentPane.add(refRapCB);
		
		JButton confBtn = new RoundJButton("Confirmer");
		confBtn.setBounds(256, 336, 117, 36);
		confBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ModifyOptionFrame mod = new ModifyOptionFrame();
				
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
					mod.getMdlLbl().setEnabled(true);
				} 
				else if(verifCb(modCB) == false) 
				{
					mod.getModField().setEnabled(false);
					mod.getMdlLbl().setEnabled(false);
				}
				
				if(verifCb(typeMaintCB) == true)
				{
					mod.getTypeMntLbl().setEnabled(true);
					mod.getTypeMntnField().setEnabled(true);
				} 
				else if(verifCb(typeMaintCB) == false) 
				{
					mod.getTypeMntLbl().setEnabled(false);
					mod.getTypeMntnField().setEnabled(false);
				}
				
				if(verifCb(datMaintCB) == true) 
				{
					mod.getDateMaintField().setEnabled(true);
					mod.getDateMaintLbl().setEnabled(true);
				} 
				else if(verifCb(datMaintCB) == false)
				{
					mod.getDateMaintField().setEnabled(false);
					mod.getDateMaintLbl().setEnabled(false);
				}
				
				if(verifCb(refRapCB) == true) 
				{
					mod.getRefRapField().setEnabled(true);
					mod.getRapRefLbl().setEnabled(true);
				} 
				else if(verifCb(refRapCB) == false) 
				{
					mod.getRefRapField().setEnabled(false);
					mod.getRapRefLbl().setEnabled(false);
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
		if(c.isSelected() == true) {
			return verif = true;
		}
		return verif;
	}
}