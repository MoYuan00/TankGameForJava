package com.game;

import com.game.BaseInterface.IAction;
import com.game.BaseInterface.IDestroy;

public abstract class GameObject implements IDestroy, IAction {
	private boolean destroy = false;
	@Override
	public boolean isDestroy() {
		return destroy;
	}
	@Override
	public void destroy() {
		destroy = true;
	}
	@Override
	public void update() {
		
	}
}
