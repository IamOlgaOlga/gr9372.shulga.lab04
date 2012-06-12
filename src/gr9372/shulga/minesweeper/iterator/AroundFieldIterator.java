package gr9372.shulga.minesweeper.iterator;

import gr9372.shulga.minesweeper.model.MinesweeperModel;

import java.util.Collection;
import java.util.Iterator;

public class AroundFieldIterator implements Iterator<Boolean> {

	private int row = -2;
	private int col = -1;
	private int startCol = -1;
	private int startRow = -1;
	private int curentCol;
	private int curentRow;
	private int cols;
	private int rows;
	private MinesweeperModel model;

	public AroundFieldIterator(int row, int col, MinesweeperModel model) {
		curentRow = row;
		curentCol = col;
		rows = Math.min(model.getRows(), row + 2) - Math.max(0, row - 1);
		cols = Math.min(model.getCols(), col + 2) - Math.max(0, col - 1);

		if (row == 0) {
			startRow = 0;
			this.row = -1;
		}

		if (col == 0) {
			this.col = startCol = 0;
		}

		this.model = model;
	}

	@Override
	public boolean hasNext() {
		return row - startRow + 1 < rows || col - startCol + 1 < cols;
	}

	@Override
	public Boolean next() {
		if (++row - startRow == rows) {
			row = startRow;
			++col;
		}
		return model.getVal(row + curentRow, col + curentCol);
	}

	@Override
	public void remove() {
	}

	public int getCol() {
		return col + curentCol;
	}

	public int getRow() {
		return row + curentRow;
	}
}
