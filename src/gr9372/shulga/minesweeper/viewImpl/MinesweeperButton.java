package gr9372.shulga.minesweeper.viewImpl;

import gr9372.shulga.minesweeper.view.Element;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MinesweeperButton extends JButton implements Element {

	private int gridX = 0;
	private int gridY = 0;
	private Boolean open = false;
	private Boolean flag = false;
	private Boolean mine=false;
	private int value;
	private ImageIcon image = null;

	@Override
	public Boolean isFlag() {
		return flag;
	}

	@Override
	public void setFlag(Boolean flag) {
		this.flag = flag;
		if (flag) {
			image = new ImageIcon(
					"C:\\Users\\workspace\\gr9372.shulga.minesweeper\\images\\flag.jpg");
		} else {
			image = null;
		}
		setIcon(image);
		setOpen(flag);
	}

	@Override
	public void setValue(int value) {
		this.value = value;
		switch (value) {
		case 0:
			image = new ImageIcon(getClass().getResource("/0.jpg"));
			break;
		case 1:
			image = new ImageIcon(getClass().getResource("/1.jpg"));
			break;
		case 2:
			image = new ImageIcon(getClass().getResource("/2.jpg"));
			break;
		case 3:
			image = new ImageIcon(getClass().getResource("/3.jpg"));
			break;
		case 4:
			image = new ImageIcon(getClass().getResource("/4.jpg"));
			break;
		case 5:
			image = new ImageIcon(getClass().getResource("/5.jpg"));
			break;
		case 6:
			image = new ImageIcon(getClass().getResource("/6.jpg"));
			break;
		case 7:
			image = new ImageIcon(getClass().getResource("/7.jpg"));
			break;
		case 8:
			image = new ImageIcon(getClass().getResource("/8.jpg"));
			break;
		default:
			break;
		}
		setIcon(image);
	}

	@Override
	public void setMine() {
		this.mine=true;
		image = new ImageIcon(getClass().getResource("/bomb.jpg"));
		setIcon(image);
	}
	
	@Override
	public void setOpen(Boolean flag) {
		this.open = flag;
	}


	@Override
	public void setOpen() {
		this.open = true;
	}


	@Override
	public Boolean isOpen() {
		return this.open;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

	public int getGridX() {
		return gridX;
	}

	public int getGridY() {
		return gridY;
	}

	@Override
	public Boolean isMine() {		
		return mine;
	}

	@Override
	public int getNumberMines() {		
		return value;
	}

}
