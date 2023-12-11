package com.bases.gameoflife;

public class AliveCell implements Cell{

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	public Cell nextGeneration(int nbAliveNeighbours) {
		if(nbAliveNeighbours == 3 || nbAliveNeighbours == 2) {
			return this;
		}
		return new DeadCell();
	}
	
	@Override
	public String toString() {
		return " X ";
	}

}
