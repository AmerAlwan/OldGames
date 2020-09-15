package XO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	private int x, y, width, height;
	private String[][] grid = new String[3][3];
	
	public Canvas(int width, int height) {
		this.width = width;
		this.height = height;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				x = arg0.getX();
				y = arg0.getY();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		for(int i = 0; i < 3; i++) {
			for(int z = 0; z < 3; z++) {
				grid[i][z] = "";
			}
		}
		
	}
	
	public void paintComponent(Graphics ga) {
		//System.out.println(true);
		super.paintComponents(ga);
		Graphics2D g = (Graphics2D) ga;
		setBackground(Color.WHITE);
		
		for(int i = 0; i < 3; i++) {
			for(int z = 0; z < 3; z++) {
				int d = width / 3;
				int h = height / 3;
				g.drawRect(i * d, z * h, d, h);
			}
		}
		repaint();	
	}
	
	

}
