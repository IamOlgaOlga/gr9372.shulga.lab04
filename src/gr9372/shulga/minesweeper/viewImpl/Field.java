package gr9372.shulga.minesweeper.viewImpl;

import javax.swing.ImageIcon;

import gr9372.shulga.minesweeper.view.Element;

public class Field implements Element{
	private String value="- ";
	private Boolean open = false;
	private Boolean flag = false;
	private Boolean mine=false;
	
	
	@Override 
	public void setValue(int value) {
		this.value=value+" ";		
	}
	
	public String getValue(){
		return this.value;
	}
	@Override
	public void setMine() {
		this.value="* ";
		this.mine=true;
		
	}

	@Override
	public void setFlag(Boolean flag) {
		this.flag = flag;
		if (flag) {
			this.value="F ";
		} else {
			this.value="- ";
		}
		setOpen(flag);
		
	}

	@Override
	public Boolean Flag() {		
		return flag;
	}
	

	public void setOpen(Boolean flag) {
		this.open = flag;
	}

	public void setOpen() {
		this.open = true;
	}

	public Boolean open() {
		return this.open;
	}

	@Override
	public Boolean Mine() {		
		return mine;
	}

	

}
