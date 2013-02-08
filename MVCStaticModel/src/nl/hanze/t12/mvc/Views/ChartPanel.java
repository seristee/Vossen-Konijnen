package nl.hanze.t12.mvc.Views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ChartPanel extends JPanel{
	private int hunterOffset = 10;
	private int foxOffset = 40;
	private int rabbitOffset = 25;
	private int killerbunnyOffset = 25;
	private char mode = 'p';
	private int PieChartSize = 400;
	private int baseOffsetX = 130;
	private int baseOffsetY = 400;
	
	public ChartPanel(){}
	
	public void paintComponent(Graphics g)
	{
		if(mode == 'p')
		{
			// PIE CHART
			
			int h = (int) Math.round(new Double(hunterOffset) * 3.6);
			int r = (int) Math.round(new Double(rabbitOffset) * 3.6);
			int f = (int) Math.round(new Double(foxOffset) * 3.6);
			int k = (int) Math.round(new Double(killerbunnyOffset) * 3.6);
						
				g.setColor(Color.RED);		
				g.fillArc(130, 10, PieChartSize, PieChartSize, 90, k);
				
				g.setColor(Color.BLUE);
				g.fillArc(130, 10, PieChartSize, PieChartSize, k+90, r);
				
				g.setColor(Color.GREEN);
				g.fillArc(130, 10, PieChartSize, PieChartSize, k+r+90, f);
				
				g.setColor(Color.PINK);
				g.fillArc(130, 10, PieChartSize,PieChartSize, k+f+r+90, h);
			
		}
		else
		{
			g.setColor(Color.RED);
			g.fillRect(baseOffsetX , baseOffsetY - (hunterOffset * 4), 90, hunterOffset * 4);
			
			g.setColor(Color.BLUE);
			g.fillRect(baseOffsetX + 100, baseOffsetY - (rabbitOffset*4), 90, rabbitOffset*4);
			
			g.setColor(Color.GREEN);
			g.fillRect(baseOffsetX + 200, baseOffsetY - (foxOffset*4), 90, foxOffset*4);
			
			g.setColor(Color.PINK);
			g.fillRect(baseOffsetX + 300, baseOffsetY - (killerbunnyOffset*4), 90, killerbunnyOffset*4);
		}
	}
	
	public void setAantallen(int[] aantallen)
	{
		hunterOffset = aantallen[0];
		rabbitOffset = aantallen[1];
		foxOffset = aantallen[2];
		killerbunnyOffset = aantallen[3];
	}
	
	public void setMode(char mode){
		this.mode = mode;
	}
}
