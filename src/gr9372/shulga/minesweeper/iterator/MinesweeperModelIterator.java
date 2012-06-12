package gr9372.shulga.minesweeper.iterator;

import gr9372.shulga.minesweeper.model.MinesweeperModel;

import java.util.Iterator;

public class MinesweeperModelIterator implements Iterator<Boolean> {
	private MinesweeperModel model;
	private int col = -1;
	private int row = 0;

	public MinesweeperModelIterator(MinesweeperModel model) {
		this.model = model;
	}

	@Override
	public boolean hasNext() {
		return col + 1 < model.getCols() || row + 1 < model.getRows();
	}

	@Override
	public Boolean next() {
		if (++col == model.getCols()) {
			col = 0;
			++row;
		}
		return model.getVal(row, col);
	}

	@Override
	public void remove() {
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
}
