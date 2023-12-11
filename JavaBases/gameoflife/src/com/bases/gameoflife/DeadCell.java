package com.bases.gameoflife;

public class DeadCell implements Cell{

	@Override
	public boolean isAlive() {
		return false;
	}

	@Override
	public Cell nextGeneration(int nbAliveNeighbours) {
		if(nbAliveNeighbours == 3) {
			return new AliveCell();
		}
		return this;
	}
	
	@Override
	public String toString() {
		return " . ";
	}

}
