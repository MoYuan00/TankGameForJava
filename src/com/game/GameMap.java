package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import com.game.BaseInterface.IAction;
import com.game.BaseInterface.ICollision;
import com.game.BaseInterface.IDrawable;

/**
 * 内部容器类，用于实现图像处理
 */
public class GameMap extends JPanel {
	private static final long serialVersionUID = 16578987565248L;
	public Color bgColor = Color.black;
	
	public int unity = 15;// draw a unity on map
	public int mapWidth = 30;
	public int mapHeight = 25;
	public int uMapWidth;
	public int uMapHeight;
	public int[][] map;
	/**
	 * 	地图当前点为空的标志
	 */
	private final int MAP_NULL_FLAG = 0;
	Rectangle mapSize = null;
	public GameMap(Rectangle mapSize) {
		unity = 15;
		this.mapSize = mapSize;
		uMapWidth = getFactSize(mapWidth);
		uMapHeight = getFactSize(mapHeight);
		map = new int[mapHeight + 2][mapWidth + 2];
	}
	private List<IDrawable> drawableObjList = new CopyOnWriteArrayList<IDrawable>();
	private List<IAction> actionObjList = new CopyOnWriteArrayList<IAction>();
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 画背景
		g.setColor(bgColor);  
		g.fillRect(0, 0, uMapWidth, uMapHeight); 
		g.setColor(Color.red);
		g.drawRect(0, 0, uMapWidth, uMapHeight); 
		// 画每个游戏物体
		for(int i = 0, len = drawableObjList.size(); i < len; i++) {
			drawableObjList.get(i).draw(g);
		}
		// 执行每个action物体的update方法
		for(int i = 0, len = actionObjList.size(); i < len; i++) {
			actionObjList.get(i).update();
		}
		// 打印地图到控制台,用于测试
		for(int i = 0;  i < mapHeight; i++){
			for(int j = 0;  j < mapWidth; j++){
				System.out.print(map[i][j] + "");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * 转换成单位大小
	 * @param size
	 * @return
	 */
	public int getFactSize(int size) {
		return size * unity;
	}
	/**
	 * 	添加游戏物体
	 * @param draw
	 */
	public boolean addGameObject(IDrawable draw) {
		// 这里的代码是为了将每个要添加到地图上的可以碰撞的物体，记录其位置。用来检测碰撞
		if(draw instanceof ICollision){// 如果是碰撞的，那么就添加到数组中记录
			ICollision iCollision = (ICollision)draw;
			Collision collision = iCollision.getCollision();
			for(int i = collision.y;  i < collision.y + collision.height; i++){// 添加的位置是否已经存在碰撞体
				for(int j = collision.x;  j < collision.x + collision.width; j++){
					if(map[i][j] != MAP_NULL_FLAG){// 存在碰撞，不可以移动，或者放置
						return false;
					}
				}
			}
			for(int i = collision.y;  i < collision.y + collision.height; i++){
				for(int j = collision.x;  j < collision.x + collision.width; j++){
					map[i][j] = 1;
				}
			}
		}
		if(draw instanceof IAction) {
			actionObjList.add((IAction)draw);
		}
		drawableObjList.add(draw);
		return true;
	}
	/**
	 * 	检测传入的碰撞体是否可以移动到各个方向
	 */
	public boolean canMoveLeft(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// 检测左边是否存在边界,或者存在碰撞体
		if(collision.x <= 0) return false;//	检测边界
		for(int i = collision.y; i < collision.y + collision.height; i++) {
			if(map[i][collision.x - 1] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	public boolean canMoveRight(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// 检测左边是否存在边界,或者存在碰撞体
		if(collision.x + collision.width >= mapWidth) return false;//	检测边界
		for(int i = collision.y; i < collision.y + collision.height; i++) {
			if(map[i][collision.x + collision.width] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	public boolean canMoveUp(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// 检测左边是否存在边界,或者存在碰撞体
		if(collision.y <= 0) return false;//	检测边界
		for(int i = collision.x; i < collision.x + collision.width; i++) {
			if(map[collision.y - 1][i] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	public boolean canMoveDown(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// 检测左边是否存在边界,或者存在碰撞体
		if(collision.y + collision.height >= mapHeight) return false;//	检测边界
		for(int i = collision.x; i < collision.x + collision.width; i++) {
			if(map[collision.y + collision.height][i] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 	移动某个碰撞体，在数组地图中
	 * 	如果不需要经行碰撞检测，这里的方法都是不必要的
	 */
	public boolean moveLeft(ICollision iCollision) {
		if(!canMoveLeft(iCollision)) return false;
		Collision collision = iCollision.getCollision();
		for(int j = collision.x; j < collision.x + collision.width; j++)
			for(int i = collision.y; i < collision.y + collision.height; i++) {
				map[i][j - 1] = map[i][j];
			}
		for(int j = collision.y; j < collision.y + collision.height; j++) {// 清空原来右边的一行占有
			map[j][collision.x + collision.width - 1] = MAP_NULL_FLAG;
		}
		return true;
	}
	public boolean moveRight(ICollision iCollision) {
		if(!canMoveRight(iCollision)) return false;
		Collision collision = iCollision.getCollision();
		for(int j = collision.x + collision.width; j > collision.x; j--)
			for(int i = collision.y; i < collision.y + collision.height; i++) {
				map[i][j] = map[i][j - 1]; 
			}
		for(int j = collision.y; j < collision.y + collision.height; j++) {// 清空原来左边的一行占有
			map[j][collision.x] = MAP_NULL_FLAG;
		}
		return true;
	}
	public boolean moveUp(ICollision iCollision) {
		if(!canMoveUp(iCollision)) return false;
		Collision collision = iCollision.getCollision();
		for(int j = collision.y; j < collision.y + collision.height; j++)
			for(int i = collision.x; i < collision.x + collision.width; i++) {
				map[j - 1][i] = map[j][i];
			}
		for(int j = collision.x; j < collision.x + collision.width; j++) {// 清空原来下边的一行占有
			map[collision.y + collision.height - 1][j] = MAP_NULL_FLAG;
		}
		return true;
	}
	public boolean moveDown(ICollision iCollision) {
		if(!canMoveDown(iCollision)) return false;
		Collision collision = iCollision.getCollision();
		for(int j = collision.y + collision.height; j > collision.y ; j--)
			for(int i = collision.x; i < collision.x + collision.width; i++) {
				map[j][i] = map[j - 1][i];
			}
		for(int j = collision.x; j < collision.x + collision.width; j++) {// 清空原来上边的一行占有
			map[collision.y][j] = MAP_NULL_FLAG;
		}
		return true;
	}
}

