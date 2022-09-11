package ua.itea.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	
	private static final int NUMBER_OF_THREADS = 5;
	
	public static void main(String[] args) throws Exception {

		ExecutorService es = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		List<Integer> list = new ArrayList<>();

		System.out.println("Start");
		
		int numberOfGenerator = 1;
		while (list.size() != 10) {
			
			List<Future<Integer>> fList = new ArrayList<>();
			for (int i = 0; i < NUMBER_OF_THREADS; i++) {
				Future<Integer> f = es.submit(new Generator(String.valueOf(numberOfGenerator)));
				numberOfGenerator++;
				fList.add(f);
			}

			for (int i = 0; i < NUMBER_OF_THREADS; i++) {
				int randomInt = fList.get(i).get();

				if (randomInt % 2 == 0) {
					list.add(randomInt);
				}

				if (list.size() == 10) {
					break;
				}

			}
		}
		
		es.shutdown();

		System.out.println("\n\n\n");
		for (Integer integer : list) {
			System.out.println(integer);
		}

		System.out.println("End");
	}
}
