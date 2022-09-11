package ua.itea.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Generator implements Callable<Integer> {
	private String name;

	public Generator(String name) {
		this.name = name;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println(name + " is working now...");
		TimeUnit.SECONDS.sleep(3);
		System.out.println(name + " stoped");
		return (int) (Math.random() * 1000);
	}

}