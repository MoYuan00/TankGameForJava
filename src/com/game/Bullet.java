package com.game;

import java.awt.Color;
import java.awt.Graphics;

import com.game.BaseInterface.Direction;
import com.game.BaseInterface.IDrawable;


public class Bullet implements IDrawable{
	public GameMap map;
	public Direction direction = Direction.UP;// direction
	
	public int x;// tank draw position in map x
	public int uX;
	
	public int y;// tank draw position in map y
	public int uY;
	
	public int width;// tank width
	public int uWidth;
	
	public int height;// tank height
	public int uHeight;
	
	public int speed = 1;
	public Bullet() {
	}
	public Bullet(int x, int y, int width, int height, Direction direction, GameMap map) {
		this.x = x; this.y = y; this.width = width; this.height = height;
		this.direction = direction; this.map = map;
		// 单位化大小
		this.uX = map.getFactSize(x);
		this.uY = map.getFactSize(y);
		this.uWidth = map.getFactSize(width);
		this.uHeight = map.getFactSize(height);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		switch(direction) {// draw each tank on every dircetion
		case UP: 
		case DOWN:
			g.fillRect(uX, uY, uWidth, uHeight);
			break;
		case LEFT:
		case RIGHT:
			g.fillRect(uX, uY,
					uHeight, uWidth);
			break;
		}
		g.setColor(map.bgColor);
		// 自己移动
		switch(direction) {
		case DOWN:
			moveDown();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		case UP:
			moveUp();
			break;
		default:
			break;
		}
	}
	public Bullet newBullet(int x, int y, int width, int height, Direction direction, GameMap map) {
		return new Bullet(x, y, width, height, direction, map);
	}
	public void moveLeft() {
		x -= 1;
		uX = map.getFactSize(x);
	}
	public void moveRight() {
		x += 1;
		uX = map.getFactSize(x);
	}
	public void moveUp() {
		y -= 1;
		uY = map.getFactSize(y);
	}
	public void moveDown() {
		y += 1;
		uY = map.getFactSize(y);
	}
}
