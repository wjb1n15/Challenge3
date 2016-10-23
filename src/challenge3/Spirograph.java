package challenge3;

import java.awt.Color;
import java.awt.Graphics;

public class Spirograph {
	protected int fixedRad = 300;
	protected int movRad = 103;
	protected int offset = 0;
	protected Color colour = Color.black;
	
	public void paint(Graphics g)
	{
		g.setColor(colour);
		
		int firstX = fixedRad - offset;
		int firstY = 0;
		double lastX = firstX, lastY = firstY;
		int sumR = fixedRad - movRad;
		double ratio = (double)sumR / (double)movRad;
		int sumO = movRad - offset;
		double stop = 2 * 3.14159;
//		int increment;
//		int prevIncrement = fixedRad;
//		for(increment = movRad; prevIncrement % increment > 0; prevIncrement = increment) {
//			stop *= (double)prevIncrement / (double)increment;
//			increment = prevIncrement % increment;
//		}
//		stop *= fixedRad / increment;
		
		for(double t = 0.0;  t < 3000; t += 0.1) {
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
		if(movRad > 0)
			this.movRad = movRad;
		else
			this.movRad = 1;
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
