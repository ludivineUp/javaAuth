package com.bases.gameoflife;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		World w = new World();
		System.out.println(w);
		for (int i = 0; i < 10; i++) {
			w.nextGeneration();
			System.out.println(w);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
