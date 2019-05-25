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
 * �ڲ������࣬����ʵ��ͼ����
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
	 * 	��ͼ��ǰ��Ϊ�յı�־
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
		// ������
		g.setColor(bgColor);  
		g.fillRect(0, 0, uMapWidth, uMapHeight); 
		g.setColor(Color.red);
		g.drawRect(0, 0, uMapWidth, uMapHeight); 
		// ��ÿ����Ϸ����
		for(int i = 0, len = drawableObjList.size(); i < len; i++) {
			drawableObjList.get(i).draw(g);
		}
		// ִ��ÿ��action�����update����
		for(int i = 0, len = actionObjList.size(); i < len; i++) {
			actionObjList.get(i).update();
		}
		// ��ӡ��ͼ������̨,���ڲ���
		for(int i = 0;  i < mapHeight; i++){
			for(int j = 0;  j < mapWidth; j++){
				System.out.print(map[i][j] + "");
			}
			System.out.println();
		}
		System.out.println();
	}
	/**
	 * ת���ɵ�λ��С
	 * @param size
	 * @return
	 */
	public int getFactSize(int size) {
		return size * unity;
	}
	/**
	 * 	�����Ϸ����
	 * @param draw
	 */
	public boolean addGameObject(IDrawable draw) {
		// ����Ĵ�����Ϊ�˽�ÿ��Ҫ��ӵ���ͼ�ϵĿ�����ײ�����壬��¼��λ�á����������ײ
		if(draw instanceof ICollision){// �������ײ�ģ���ô����ӵ������м�¼
			ICollision iCollision = (ICollision)draw;
			Collision collision = iCollision.getCollision();
			for(int i = collision.y;  i < collision.y + collision.height; i++){// ��ӵ�λ���Ƿ��Ѿ�������ײ��
				for(int j = collision.x;  j < collision.x + collision.width; j++){
					if(map[i][j] != MAP_NULL_FLAG){// ������ײ���������ƶ������߷���
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
	 * 	��⴫�����ײ���Ƿ�����ƶ�����������
	 */
	public boolean canMoveLeft(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// �������Ƿ���ڱ߽�,���ߴ�����ײ��
		if(collision.x <= 0) return false;//	���߽�
		for(int i = collision.y; i < collision.y + collision.height; i++) {
			if(map[i][collision.x - 1] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	public boolean canMoveRight(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// �������Ƿ���ڱ߽�,���ߴ�����ײ��
		if(collision.x + collision.width >= mapWidth) return false;//	���߽�
		for(int i = collision.y; i < collision.y + collision.height; i++) {
			if(map[i][collision.x + collision.width] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	public boolean canMoveUp(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// �������Ƿ���ڱ߽�,���ߴ�����ײ��
		if(collision.y <= 0) return false;//	���߽�
		for(int i = collision.x; i < collision.x + collision.width; i++) {
			if(map[collision.y - 1][i] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	public boolean canMoveDown(ICollision iCollision) {
		Collision collision = iCollision.getCollision();
		// �������Ƿ���ڱ߽�,���ߴ�����ײ��
		if(collision.y + collision.height >= mapHeight) return false;//	���߽�
		for(int i = collision.x; i < collision.x + collision.width; i++) {
			if(map[collision.y + collision.height][i] != MAP_NULL_FLAG) {
				return false;
			}
		}
		return true;
	}
	/**
	 * 	�ƶ�ĳ����ײ�壬�������ͼ��
	 * 	�������Ҫ������ײ��⣬����ķ������ǲ���Ҫ��
	 */
	public boolean moveLeft(ICollision iCollision) {
		if(!canMoveLeft(iCollision)) return false;
		Collision collision = iCollision.getCollision();
		for(int j = collision.x; j < collision.x + collision.width; j++)
			for(int i = collision.y; i < collision.y + collision.height; i++) {
				map[i][j - 1] = map[i][j];
			}
		for(int j = collision.y; j < collision.y + collision.height; j++) {// ���ԭ���ұߵ�һ��ռ��
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
		for(int j = collision.y; j < collision.y + collision.height; j++) {// ���ԭ����ߵ�һ��ռ��
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
		for(int j = collision.x; j < collision.x + collision.width; j++) {// ���ԭ���±ߵ�һ��ռ��
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
		for(int j = collision.x; j < collision.x + collision.width; j++) {// ���ԭ���ϱߵ�һ��ռ��
			map[collision.y][j] = MAP_NULL_FLAG;
		}
		return true;
	}
}

