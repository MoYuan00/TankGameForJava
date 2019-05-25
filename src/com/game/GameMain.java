package com.game;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameMain {
	
	public GameMap gameMap;
	private Rectangle mapSize = new Rectangle();
	private JFrame frame;
	// 用户的坦克
	private Tank userTank;
	public GameMain() {
		frame = new JFrame("123");
		gameMap = new GameMap(mapSize);
		mapSize.setBounds(500, 400, 600, 400);
		gameMap.setPreferredSize(mapSize.getSize());
		frame.setBounds(mapSize);
		
		frame.add(gameMap);
		// 记录按键
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				Input.keyReleasedEvent(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				Input.keyPressedEvent(e);
			}
		});
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					frame.repaint();
				}
			}
		});
		t.start();
		
		userTank = new Tank(0, 0, 3, 3, this.gameMap).setBullet(new Bullet());
		gameMap.addGameObject(userTank);
	}
	public static void main(String[] args) {
		new GameMain();
	}
	
}
