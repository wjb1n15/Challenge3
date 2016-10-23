package challenge3;

import java.awt.Color;
import java.awt.Graphics;

public class Spirograph {
	protected int fixedRad = 100;
	protected int movRad = 50;
	protected int offset = 20;
	protected Color colour = Color.black;
	
	public void paint(Graphics g)
	{
		g.setColor(colour);
		
		int firstX = fixedRad + offset;
		int firstY = 0;
		double lastX = firstX, lastY = firstY;
		int sumR = fixedRad - movRad;
		double ratio = (double)sumR / (double)movRad;
		int sumO = movRad + offset;
		
		for(double t = 0.0; (((int)lastX != firstX || (int)lastY != firstY) || t < 0.5) && t < 1000.0; t += 0.01) {
			double x = (double)sumR * Math.cos(t) + sumO * Math.cos(ratio * t);
			double y = (double)sumR * Math.sin(t) - sumO * Math.sin(ratio * t);
			
			g.drawLine((int)lastX, (int)lastY, (int)x, (int)y);
			lastX = x;
			lastY = y;
		}
	}
	
	public int getFixedRad()
	{
		return fixedRad;
	}
	
	public int getMovRad()
	{
		return movRad;
	}
	
	public int getOffset()
	{
		return offset;
	}
	
	public Color getColour()
	{
		return colour;
	}
	
	public void setFixedRad(int fixedRad)
	{
		this.fixedRad = fixedRad;
	}
	
	public void setMovRad(int movRad)
	{
		this.movRad = movRad;
	}
	
	public void setOffset(int offset)
	{
		this.offset = offset;
	}
	
	public void setColour(Color colour)
	{
		this.colour = colour;
	}
}
