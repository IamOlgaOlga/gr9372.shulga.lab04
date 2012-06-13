package gr9372.shulga.minesweeper.view;

public interface Element {
	
	public void setValue(int value);
	public void setMine();
	public void setFlag(Boolean flag);
	public Boolean isFlag();
	public Boolean isMine();
	public Boolean isOpen();
	public void setOpen();
	public void setOpen(Boolean flag);
	public int getNumberMines();
	
 

}
