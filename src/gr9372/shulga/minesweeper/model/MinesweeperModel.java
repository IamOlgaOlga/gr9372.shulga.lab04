package gr9372.shulga.minesweeper.model;

import gr9372.shulga.minesweeper.iterator.MinesweeperModelIterator;

public class MinesweeperModel {
	private int cols;
	private int rows;
	private int mines;
	private Boolean[][] field;
	private MinesweeperModelIterator iterator = new MinesweeperModelIterator(
			this);

	public MinesweeperModel(int rows, int cols, int mines) {
		this.cols = cols;
		this.rows = rows;
		this.mines = mines;
		this.field = new Boolean[rows][cols];
	}

	public void createField() {
		int col = 0;
		int row = 0;
		Boolean element;
		
		while (iterator.hasNext()) {
			element = iterator.next();
			if (element == null) {
				field[iterator.getRow()][iterator.getCol()] = false;
			}
		}

		for (int i = 1; i <= mines; i++) {
			
			col = (int) Math.round(Math.random() * (cols - 2));
			row = (int) Math.round(Math.random() * (rows - 2));
			if(!field[row + 1][col + 1]){
				field[row + 1][col + 1] = true;
			}else{i--;}
			
			
		}

		
	}

	public int getMines() {
		return mines;
	}

	public Boolean getVal(int row, int col) {
		return field[row][col];
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

}
