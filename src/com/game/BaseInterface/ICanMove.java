package com.game.BaseInterface;

/**
 * 	可移动的
 * @author yiyang
 *
 */
public interface ICanMove {
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveDown();
	/**
	 *  change direction 修改方向
	 * @param dircetion direction
	 */
	public void changeDircetion(Direction direction);
}

