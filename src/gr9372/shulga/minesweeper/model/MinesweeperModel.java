package gr9372.shulga.minesweeper.model;

import gr9372.shulga.minesweeper.iterator.MinesweeperModelIterator;

public class MinesweeperModel {
	private int cols;
	private int rows;
	private int mines;
	private Boolean[][] field;
	

	public MinesweeperModel(int rows, int cols, int mines) {
		this.cols = cols;
		this.rows = rows;
		this.mines = mines;
		this.field = new Boolean[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				field[i][j]=false;				
			}
		}
		
	}


	public int getMines() {
		return mines;
	}
	
	public void setValue(int row,int col,Boolean mine){
		this.field[row][col]=mine;
	}

	public Boolean getValue(int row, int col) {
		return field[row][col];
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

}
