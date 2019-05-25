package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.game.BaseInterface.Direction;
import com.game.BaseInterface.ICanMove;
import com.game.BaseInterface.ICollision;
import com.game.BaseInterface.IDrawable;

/**
 * 
 * @author yiyang
 *	TODO 碰撞还没有做， 也就是说还不能实现子弹打击效果，暂时只能移动 已经实现 @time{5/22}
 */

public class Tank extends GameObject implements IDrawable, ICollision, ICanMove{
	public GameMap map;
	public Direction direction;// dircetion
	
	public Collision collision = new Collision();
	
	public int headX;// head position on map
	public int headY;
	
	public int fHeadX;// head position on map
	public int fHeadY;
	
	public int headWidth;// tank head collision.width
	public int fHeadWidth;
	
	public int headHeight;// tank head collision.height
	public int fHeadHeight;
	
	public Tank(int x, int y, int width, int height, GameMap map){
		this.collision.x = x; this.collision.y = y; this.collision.width = width; this.collision.height = height;
		this.headWidth = collision.width / 3; this.headHeight = collision.height / 3; this.map = map;
		// 转换成单位距离
		collision.fX = map.getFactSize(x);
		collision.fY = map.getFactSize(y);
		collision.fWidth = map.getFactSize(collision.width);
		collision.fHeight = map.getFactSize(collision.height);
		fHeadWidth = map.getFactSize(headWidth);
		fHeadHeight = map.getFactSize(headHeight);
		changeDircetion(Direction.UP);// default direction is up
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		int tW = collision.fWidth - fHeadWidth;
		int tH = collision.fHeight - fHeadHeight;
		switch(direction) {// draw each tank on every direction
		case UP:{
			g.fillRect(collision.fX, collision.fY + fHeadHeight, collision.fWidth, tH);// draw body
			g.fillRect(collision.fX + (tW >> 1), collision.fY, fHeadWidth, fHeadHeight);// draw head
		}break;
		case DOWN:
			g.fillRect(collision.fX, collision.fY, collision.fWidth, tH);// draw body
			g.fillRect(collision.fX + (tW >> 1), collision.fY + tH, fHeadWidth, fHeadHeight);// draw head
			break;
		case LEFT:
			g.fillRect(collision.fX + fHeadHeight, collision.fY, tH, collision.fWidth);// draw body
			g.fillRect(collision.fX, collision.fY + (tH >> 1), fHeadHeight, fHeadWidth);// draw head
			break;
		case RIGHT:
			g.fillRect(collision.fX, collision.fY, tH, collision.fWidth);// draw body
			g.fillRect(collision.fX + tH, collision.fY + (tH >> 1), fHeadHeight, fHeadWidth);// draw head
			break;
		}
	}
	@Override
	public void update() {
		if(Input.isKeyDown(KeyEvent.VK_D)) {
			moveRight();
		}else if(Input.isKeyDown(KeyEvent.VK_A)) {
			moveLeft();
		}else if(Input.isKeyDown(KeyEvent.VK_W)) {
			moveUp();
		}else if(Input.isKeyDown(KeyEvent.VK_S)) {
			moveDown();
		}else if(Input.isKeyDown(KeyEvent.VK_K)) {
			attack();
		}
	}
	private Bullet bullet;
	public Tank setBullet(Bullet bullet) {
		this.bullet = bullet;
		return this;
	}
	/**
	 * fire bullet
	 */
	public void attack() {
		map.addGameObject(bullet.newBullet(headX, headY, headWidth, headHeight, direction, map));
	}
	@Override
	public void moveLeft() {
		if(direction != Direction.LEFT) {// 转向
			changeDircetion(Direction.LEFT);
			return;// 转向并且不移动
		}
		if(!map.moveLeft(this)) return;
		collision.x -= 1;
		collision.fX = map.getFactSize(collision.x);
		updateHeadPosition();// update head position
	}
	@Override
	public void moveRight() {
		if(direction != Direction.RIGHT) {// 转向
			changeDircetion(Direction.RIGHT);
			return;
		}
		if(!map.moveRight(this)) return;
		collision.x += 1;
		collision.fX = map.getFactSize(collision.x);
		updateHeadPosition();
	}
	@Override
	public void moveUp() {
		if(direction != Direction.UP) {// 转向
			changeDircetion(Direction.UP);
			return;
		}
		if(!map.moveUp(this)) return;
		collision.y -= 1;
		collision.fY = map.getFactSize(collision.y);
		updateHeadPosition();
	}
	@Override
	public void moveDown() {
		if(direction != Direction.DOWN) {// 转向
			changeDircetion(Direction.DOWN);
			return;
		}
		if(!map.moveDown(this)) return;
		collision.y += 1;
		collision.fY = map.getFactSize(collision.y);
		updateHeadPosition();
	}
	
	@Override
	public void changeDircetion(Direction dircetion) {
		this.direction = dircetion;
		updateHeadPosition();
	}
	/**
	 *  update the tank's head position
	 *  	更新坦克的头部坐标
	 */
	private void updateHeadPosition() {
		int tW = collision.width - headWidth;
		int tH = collision.height - headHeight;
		int uTW = collision.fWidth - fHeadWidth;
		int uTH = collision.fHeight - fHeadHeight;
		switch (direction) {
		case DOWN:
			headX = collision.x + (tW >> 1);
			headY = collision.y + collision.height;
			fHeadX = collision.fX + (uTW >> 1);
			fHeadY = collision.fY + collision.fHeight;
			break;
		case LEFT:
			headX = collision.x;
			headY = collision.y + (tH >> 1);
			fHeadX = collision.fX;
			fHeadY = collision.fY + (uTH >> 1);
			break;
		case RIGHT:
			headX = collision.x + collision.width;
			headY = collision.y + (tH >> 1);
			fHeadX = collision.fX + collision.fWidth;
			fHeadY = collision.fY + (uTH >> 1);
			break;
		case UP:
			headX = collision.x + (tW >> 1);
			headY = collision.y;
			fHeadX = collision.fX + (uTW >> 1);
			fHeadY = collision.fY;
			break;
		default:
			break;
		}
	}
	@Override
	public Collision getCollision() {
		return collision;
	}
}