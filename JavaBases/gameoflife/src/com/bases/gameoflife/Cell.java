package com.bases.gameoflife;

public interface Cell {
	
	public boolean isAlive();
	public Cell nextGeneration(int nbAliveNeighbours);

}
