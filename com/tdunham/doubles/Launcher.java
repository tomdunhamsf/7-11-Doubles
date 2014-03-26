package com.tdunham.doubles;
import java.util.*;
import java.util.concurrent.*;
public class Launcher {
	public static int maxDrinks;
	public static int diceSpeed;
	private static List<Player> players=new LinkedList<Player>();
	private static Player active;
	private static BlockingQueue<Object> pending=new LinkedBlockingQueue();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void hadTooMuch(Player inebriated){
		players.remove(inebriated);
	}

	public static void startTurn(){
		if(active!=null){
			players.add(active);
		}
		active=players.remove(0);
	}
	
	public static void addOutstandingDrink(){
		pending.add(new Object());
	}
	
	public static boolean shouldStopDice(){
		pending.remove();
		return pending.size()==0;
	}
}
