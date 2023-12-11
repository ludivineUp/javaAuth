package com.bases.gameoflife;

import java.util.Iterator;
import java.util.Random;

public class World {
	
	// constantes
	private final static int LINES = 30;
	private final static int COLUMNS = 30;
	
	private Cell[][] world;
	private int[][] neighbours;
	
	public World() {
		this.world = new Cell[LINES][COLUMNS];
		this.neighbours = new int[LINES][COLUMNS];
		initCells();
	}
	
	private void initCells() {
		// foreach => acces en lecture
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				this.world[i][j] = new DeadCell();
			}
		}
		Random random = new Random();
		int nbAlive = (int) (LINES * COLUMNS * 0.3);
		for (int i = 0; i < nbAlive; i++) {
			world[random.nextInt(LINES)][random.nextInt(COLUMNS)] = new AliveCell();
		}
		nbAliveNeighbours();
	}
	
	private void nbAliveNeighbours() {
		this.neighbours = new int[LINES][COLUMNS];
		for (int y = 0; y < world.length; y++) {
			for (int x = 0; x < world[y].length; x++) {
				int sum = 0;
				int yMin = y - 1 >= 0 ? y - 1 : 0;
				int yMax = y + 1 < world.length ? y + 1 : world.length - 1;
				int xMin = x - 1 > 0 ? x - 1 : 0;
				int xMax = x + 1 < world[y].length ? x + 1
						: world[y].length - 1;
				for (int y2 = yMin; y2 <= yMax; y2++) {
					for (int x2 = xMin; x2 <= xMax; x2++) {
						if ((x2 != x || y2 != y) && world[y2][x2].isAlive()) {
							sum++;
						}
					}
				}
				neighbours[y][x] = sum;
			}
		}
	}
	
	public void nextGeneration() {
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				world[i][j] = world[i][j].nextGeneration(neighbours[i][j]);
			}
		}
		nbAliveNeighbours();
	}

	@Override
	public String toString() {
		String s = "--------------------------------------------------\n"; 
		for(Cell[] line : this.world) {
			for(Cell c : line) {
				s += c.toString();
			}
			s +="\n";
		}
		s += "--------------------------------------------------\n"; 
		return s;
	}
}
