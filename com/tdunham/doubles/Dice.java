package com.tdunham.doubles;

import java.util.concurrent.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.*;
public class Dice {
	private ScheduledExecutorService roller=Executors.newSingleThreadScheduledExecutor();
	private Random gen=new Random();
	
	class RollResult implements Callable<RollResult>{
		int a;
		int b;
		@Override
		public RollResult call() {
			a=1+gen.nextInt(6);
			b=1+gen.nextInt(6);
			return this;
		}
		
	}
}
