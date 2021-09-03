package Filters;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import EditMultipleObjects.RoundJButton;
import EditMultipleObjects.WindowSize;

public class Help extends JFrame 
{

	private static final long serialVersionUID = 550336463258923408L;
	private JPanel contentPane;
	
	int windowHeight = 427;
	int windowWidth = 814;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				try 
				{
					Help frame = new Help();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Help() 
	{
		setResizable(false);
		setTitle("Help");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		setBounds(WindowSize.widthScreen / 2 - windowWidth / 2, 
				  WindowSize.heightScreen / 2 - windowHeight / 2, 
				  windowWidth, 
				  windowHeight
				  );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel testLbl = new JLabel("<html> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas mattis ante maximus sem blandit, non malesuada mauris volutpat. <br>"
				+ " Nunc aliquam magna nunc, non lobortis dui congue egestas. Curabitur scelerisque malesuada velit at egestas. Maecenas ac sodales ex, eu cursus purus. <br>"
				+ " Curabitur urna nisi, euismod eleifend tortor non, tincidunt mollis turpis. Cras sit amet nibh est. <br>"
				+ " Vivamus nec congue mauris, et ullamcorper magna. Cras a finibus ex. <br>"
				+ " In ut ante odio. Integer tristique mi ut turpis pellentesque, vitae tristique lectus molestie. <br>"
				+ " Aliquam nec nisi dignissim, faucibus mauris nec, condimentum metus. Quisque facilisis at magna quis hendrerit. Fusce felis metus, molestie a dignissim in, ornare iaculis arcu. <br>"
				+ " Nunc semper, felis eu aliquet iaculis, lacus dolor tincidunt augue, at feugiat massa tellus at nibh. Quisque pellentesque sapien et eros tempor tincidunt. <br>"
				+ " Suspendisse ullamcorper massa vel pharetra euismod. Donec lobortis odio nisl, et tincidunt erat semper sed. Nullam at enim ligula. <br>"
				+ " Interdum et malesuada fames ac ante ipsum primis in faucibus. Mauris in fringilla nulla. Fusce sodales dui magna. Suspendisse dignissim risus fermentum lacus placerat, vel posuere sapien sodales. <br>"
				+ " Sed semper justo eget condimentum elementum. Pellentesque vulputate tempor nisl non vulputate. Fusce est lectus, ultricies at facilisis ut, laoreet at quam. Suspendisse odio nunc, venenatis quis dictum vel, varius quis mauris. <br>"
				+ " Cras tincidunt dignissim nulla, non fermentum felis tristique quis. Sed porttitor ligula blandit lacinia auctor. Quisque quis vestibulum orci, ut convallis ligula. Praesent vel turpis neque. In varius, arcu eu ullamcorper ultrices, <br>"
				+ " velit libero malesuada nisi, in porttitor dui mi nec ligula. Nullam pellentesque mauris eros. <br> </html>", SwingConstants.CENTER);
		
		testLbl.setBounds(10, 11, 777, 294);
		contentPane.add(testLbl);
		
		RoundJButton btn = new RoundJButton("J'ai compris");
		btn.setBounds(316, 337, 129, 38);
		btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}			
		});
		contentPane.add(btn);
	}
}