package com.game.BaseInterface;

/**
 * 	���ƶ���
 * @author yiyang
 *
 */
public interface ICanMove {
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveDown();
	/**
	 *  change direction �޸ķ���
	 * @param dircetion direction
	 */
	public void changeDircetion(Direction direction);
}

