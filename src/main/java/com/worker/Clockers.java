package com.worker;

import com.counters.Counter;
import com.interfaces.ICounters;

/**Class that handle the {@link Counter} clock, as a manager.*/
public class Clockers {
	ICounters counter;
	
	public Clockers(Integer min,Integer seg) {
		counter = new Counter(min, seg);
	}
	
	public void reduceCount() { counter.reduce(); }
	
	public int getMinutes() {
		return counter.getMins();
	}
	
	public int getSeconds() {
		return counter.getSegs();
	}
	
	public boolean isEnded() {
		return counter.isEnded();
	}
	
	@Override
	public String toString() {
		return counter.toString();
	}
	
}

