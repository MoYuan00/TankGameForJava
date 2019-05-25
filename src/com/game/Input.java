package com.game;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 	输入信息中心
 * 	在这里获取全局的键盘输入情况
 * @author yiyang
 *
 */
public class Input {
	
	private static final Map<Integer, Boolean> keyState = new HashMap<Integer, Boolean>();
	
	/**
	 * 	按键是否按下
	 * @param key
	 * @return
	 */
	public static boolean isKeyDown(int key) {
		if(keyState.get(key) == null) return false;
		return keyState.get(key);
	}
	/**
	 * 	按下按键
	 * @param e
	 */
	public static void keyPressedEvent(KeyEvent e) {
		keyState.put(e.getKeyCode(), true);
	}
	/**
	 * 	释放按键
	 * @param e
	 */
	public static void keyReleasedEvent(KeyEvent e) {
		keyState.put(e.getKeyCode(), false);
	}
	
}
