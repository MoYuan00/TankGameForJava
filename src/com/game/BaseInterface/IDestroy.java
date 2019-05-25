package com.game.BaseInterface;

/**
 * 	是否消失，如果消失，那么就可以在游戏中删除此对象
 * 	赋予程序释放废弃对象的能力
 * @author yiyang
 *
 */
public interface IDestroy {
	/**
	 * 	是否已经消失
	 * 	如果已经消失那么返回true
	 * 	根据此返回值可以是否该将对象删除
	 * @return
	 */
	boolean isDestroy();
	/**
	 * 	要使对象消失时调用
	 */
	void destroy();
}
