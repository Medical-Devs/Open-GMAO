package EditMultipleObjects;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFormattedTextField;

public class RoundJFormattedTextField extends JFormattedTextField
{

	private static final long serialVersionUID = 674074286503226381L;
	private Shape shape;
	
	public RoundJFormattedTextField() 
	{
		super();
		setOpaque(false);
	}
	
	protected void paintComponent(Graphics g) 
	{
		g.setColor(getBackground());
		g.fillRoundRect(0,0,getWidth() - 1, getHeight() - 1, 15, 15);
		super.paintComponent(g);
	}
	
	protected void paintBorder(Graphics g) 
	{
		g.setColor(getBackground());
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
	}
	
	public boolean contains(int x, int y) 
	{
		if(shape == null || !shape.getBounds().equals(getBounds())) 
		{
			shape = new RoundRectangle2D.Float(0,0,getWidth() - 1, getHeight() - 1, 15, 15);
		}
		return shape.contains(x,y);
	}
}