package snake__game;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;

//import com.sun.glass.ui.Timer;

import javax.swing.*;

import java.util.Arrays;
//import java.util.Collections;

public class Snakepanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int width=500;
	static final int height=500;
	static final int unit=25;
	final int game_unit=(width*height)/unit;
	static int delay=100;
	final int x[]=new int[game_unit];
	final int y[]=new int[game_unit];
	int body=5;
	int appleEaten=0;
	int applex;
	int appley;
	char direction='D';
	boolean running=false;
	Timer timer1;
	Random random;

	Snakepanel(){
		random=new Random();
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.gray);
		this.setFocusable(true);
		this.addKeyListener(new MyKey());
		start();
		
	}
	
	public void start() {
		newApple();
		running=true;
		timer1 = new Timer(delay, this);
        timer1.start();
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
		
	}
	public void draw(Graphics g) {
		
	if(running) {
//		for(int i=0;i<height/unit;i++) {
//			g.drawLine(i*unit, 0, i*unit, height);
//			g.drawLine(0, i*unit, width, i*unit);
//			
//		}
		g.setColor(Color.red);
		g.fillRect(applex, appley, unit, unit);
		
		for(int i=0;i<body;i++) {
			if(i==0) {
				g.setColor(Color.red);
				g.fillOval(x[i], y[i], unit, unit);
			}
			else {
				g.setColor(Color.yellow);
				g.fillOval(x[i], y[i], unit, unit);
			}
		}
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free",Font.BOLD, 30));
		FontMetrics metrics=getFontMetrics(g.getFont());
		g.drawString("Score: "+appleEaten, (width-metrics.stringWidth("Score   "))/2,g.getFont().getSize());
	}
	else {
		gameover(g);
	}
	
	}
	public void newApple() {
		applex=random.nextInt((int)width/unit)*unit;
		appley=random.nextInt((int)height/unit)*unit;
		
		
	}
	public void move() {
		for(int i=body;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
		case'U':
			y[0]=y[0]-unit;
			break;
		case'D':
			y[0]=y[0]+unit;
			break;
		case'R':
			x[0]=x[0]+unit;
			break;
		case'L':
			x[0]=x[0]-unit;
			break;
		
			
		}
	}
	public void checkApple() {
		if(x[0]==applex&& y[0]==appley) {
			body++;
			appleEaten++;
			//if(appleEaten>1) {
			//	delay=10;
			//}
			newApple();
			
		}
		
	}
	public void collision() {
		for(int i=body;i>0;i--) {
			if((x[0]==x[i])&&(y[0]==y[i])){
				running=false;
			}
		}
		if(x[0]<0|| x[0]>=width) {
			running=false;
		}
		if(y[0]<0 || y[0]>=height) {
			running=false;
		}
		if(!running) {
			timer1.stop();
		}
	}
	public void gameover(Graphics g) {
		g.setColor(Color.blue);
		g.setFont(new Font("Ink Free",Font.BOLD, 50));
		FontMetrics metrics=getFontMetrics(g.getFont());
		g.drawString("Game Over", (width-metrics.stringWidth("Game Over"))/2, height/2);
		
		g.setFont(new Font("Ink Free",Font.BOLD, 30));
		FontMetrics metrics1=getFontMetrics(g.getFont());
		g.drawString("Score: "+appleEaten, (width-metrics1.stringWidth("Score   "))/2,height/2+g.getFont().getSize());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(running) {
			move();
			checkApple();
			collision();
			
		}
		repaint();
		
	}

	public class MyKey extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent k) {
			switch(k.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!='R') {
					direction='L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!='L') {
					direction='R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!='D') {
					direction='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!='U') {
					direction='D';
				}
				break;
			case KeyEvent.VK_ENTER:
				//running=true;
				body=6;
				appleEaten=0;
				direction='D';
				Arrays.fill(x,0);
				Arrays.fill(y,0);
				start();
			//	break;
			}
		}
	
}}