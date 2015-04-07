package example;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameFrame extends JPanel implements ActionListener {
	Timer mainTimer;
	

	public GameFrame(){
		setFocusable(true);
		
		mainTimer = new Timer(10, this);
	}
	public void paint(Graphics g){
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawString("MARIO",20, 100);
		
		ImageIcon ic = new ImageIcon("images/mario_small.png");
		Image i  = ic.getImage();
		
		g2d.drawImage(i,600,150,null);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
}
