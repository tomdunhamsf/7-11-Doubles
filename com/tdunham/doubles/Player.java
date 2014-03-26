package com.tdunham.doubles;
import java.util.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;
public class Player {
	private List<DrinkTask> drinks=new LinkedList(); 
	private String name;
	private int drinkTime;
	private ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
	private int drank=0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDrinkTime() {
		return drinkTime;
	}
	public void setDrinkTime(int drinkTime) {
		this.drinkTime = drinkTime;
	}
	public Player(String name,int time){
		this.name=name;
		drinkTime=time;
	}
	public synchronized void drink(){
		DrinkTask dt=new DrinkTask(this);
		drinks.add(dt);
		Launcher.addOutstandingDrink();
		if(drank+drinks.size()==Launcher.maxDrinks)
		service.schedule(dt, drinkTime, SECONDS);
	}
	public synchronized void finishDrink(DrinkTask gulp){
		drinks.remove(gulp);
		drank++;
		System.out.println("");
	 	
		if(drinks.size()>0){
			DrinkTask dt=drinks.get(0);
			service.schedule(dt, drinkTime, SECONDS);
			System.out.println(name+" has had "+drank+" drink(s), and is currently drinking "+drinks.size()+" more.");
		}else{
			System.out.println(name+" has had "+drank+" drink(s), and finished drinking.");
		}
		System.out.println("");
		Launcher.shouldStopDice();
	}
}
