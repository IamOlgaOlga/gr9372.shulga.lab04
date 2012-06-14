package gr9372.shulga.minesweeper.view;

public interface MinesweeperView {
	
	public Object getListElement();
	public void setTime(int time);
	public void stopGame(Boolean situation);
	public void setCountMines(int countMines);

}
