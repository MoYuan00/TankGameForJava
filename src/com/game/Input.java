package com.game;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 	������Ϣ����
 * 	�������ȡȫ�ֵļ����������
 * @author yiyang
 *
 */
public class Input {
	
	private static final Map<Integer, Boolean> keyState = new HashMap<Integer, Boolean>();
	
	/**
	 * 	�����Ƿ���
	 * @param key
	 * @return
	 */
	public static boolean isKeyDown(int key) {
		if(keyState.get(key) == null) return false;
		return keyState.get(key);
	}
	/**
	 * 	���°���
	 * @param e
	 */
	public static void keyPressedEvent(KeyEvent e) {
		keyState.put(e.getKeyCode(), true);
	}
	/**
	 * 	�ͷŰ���
	 * @param e
	 */
	public static void keyReleasedEvent(KeyEvent e) {
		keyState.put(e.getKeyCode(), false);
	}
	
}
