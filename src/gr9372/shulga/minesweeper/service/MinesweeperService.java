package gr9372.shulga.minesweeper.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import gr9372.shulga.minesweeper.iterator.AroundFieldIterator;
import gr9372.shulga.minesweeper.iterator.MinesweeperModelIterator;
import gr9372.shulga.minesweeper.model.MinesweeperModel;
import gr9372.shulga.minesweeper.view.Element;
import gr9372.shulga.minesweeper.view.MinesweeperView;

public class MinesweeperService {
	public static final MinesweeperService INSTANCE = new MinesweeperService();
	private MinesweeperModel model;
	private MinesweeperView view;
	private static Boolean WIN = true;
	private static Boolean FAIL = false;
	private int countMines = 0;
	private int countFlag = 0;
	private int row;
	private int col;
	private Boolean elementArea;
	private Boolean element;
	private Boolean[][] openedElement;
	private Element[][] listElement;
	private int time = 0;
	private Timer timer = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			time++;
			view.setTime(time);
		}
	});

	private MinesweeperService() {

	}

	public void setFlag(int row, int col) {
		Boolean flag = !listElement[row][col].isFlag();
		listElement[row][col].setFlag(flag);
		if (flag) {
			countFlag++;
		} else {
			countFlag--;
		}
		view.setCountMines(countFlag);
	}

	public void open(int row, int col) {
		if (time == 0) {
			this.timer.start();
		}
		if (model.getValue(row, col)) {
			listElement[row][col].setMine();
			blowUp();
		} else {
			countMines--;
			showElement(row, col);
			if (countMines == model.getMines()) {
				view.stopGame(WIN);
				this.timer.stop();
			}
		}
	}

	private void blowUp() {
		this.timer.stop();
		MinesweeperModelIterator iterator = new MinesweeperModelIterator(model);
		while (iterator.hasNext()) {
			element = iterator.next();
			if (element) {
				listElement[iterator.getRow()][iterator.getCol()].setMine();
				
			}
		}
		view.stopGame(FAIL);
	}

	private void showElement(int row, int col) {
		openedElement[row][col] = true;
		int minesAround = CalcMinesAroundField(row, col);
		listElement[row][col].setValue(minesAround);

		if (minesAround == 0) {
			AroundFieldIterator iteratorArea = new AroundFieldIterator(row,
					col, model);
			while (iteratorArea.hasNext()) {
				elementArea = iteratorArea.next();
				if (!openedElement[iteratorArea.getRow()][iteratorArea.getCol()]) {
					open(iteratorArea.getRow(), iteratorArea.getCol());
				}
			}
		}
	}

	private int CalcMinesAroundField(int row, int col) {
		int count = 0;
		AroundFieldIterator iterator = new AroundFieldIterator(row, col, model);
		while (iterator.hasNext()) {
			element = iterator.next();
			if (element) {
				count++;
			}
		}

		return count;
	}

	public static MinesweeperService getInstance() {
		return INSTANCE;
	}
	
	public void createField(MinesweeperModel model) {
		int col = 0;
		int row = 0;
		Boolean element;
		for (int i = 1; i <= model.getMines(); i++) {
			
			col = (int) Math.round(Math.random() * (model.getCols() - 2));
			row = (int) Math.round(Math.random() * (model.getRows() - 2));
			if(!model.getValue(row+1, col+1)){
				model.setValue(row+1, col+1, true);
			}else{i--;}			
		}
		
			
	}

	public void setModel(MinesweeperModel model) {
		timer.stop();
		this.model = model;
		this.countFlag = 0;
		this.countMines = model.getCols() * model.getRows();
		this.time = 0;
		this.openedElement = new Boolean[model.getRows()][model.getCols()];
		for (int i = 0; i < model.getRows(); i++) {
			for (int j = 0; j < model.getCols(); j++) {
				openedElement[i][j] = false;
			}
		}

	}

	public void setView(MinesweeperView view) {
		this.view = view;
		this.listElement = (Element[][]) view.getListElement();
		
	}

}
