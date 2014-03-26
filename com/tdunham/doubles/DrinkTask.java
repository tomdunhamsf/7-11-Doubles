package com.tdunham.doubles;

public class DrinkTask implements Runnable {
	private Player myPlayer;
	public DrinkTask(Player p){
		myPlayer=p;
	}
	
	@Override
	public void run() {
		myPlayer.finishDrink(this);
	}

}
