package gr9372.shulga.minesweeper.viewImpl;

import java.util.Scanner;

import gr9372.shulga.minesweeper.iterator.MinesweeperModelIterator;
import gr9372.shulga.minesweeper.model.MinesweeperModel;
import gr9372.shulga.minesweeper.service.MinesweeperService;
import gr9372.shulga.minesweeper.view.MinesweeperView;

public class MinesweeperViewConsole implements MinesweeperView {
	private MinesweeperModel model;
	private Scanner scanner= new Scanner(System.in);
	private Field[][] field;
	private int mines=0;
	private int time=0;
	private int row=0;
	private int col=0;
	private Boolean exit=false;
	public MinesweeperViewConsole(MinesweeperModel model){
		this.model=model;
		cteateBoarder();
		
	}
	
	private void cteateBoarder(){
		this.field=new Field[model.getRows()][model.getCols()];
		for(int i=0;i<model.getRows();i++){
			for(int j=0;j<model.getCols();j++){
				Field element=new Field();
				field[i][j]=element;
				System.out.print(field[i][j].getValue());
			}
			System.out.println();
		}
	}
	
	public void game(){
		while(!exit){
		System.out.println("поставить флаг или открыть поле?");
		System.out.println("1-флаг;");
		System.out.println("2-открыть поле.");
		int action=scanner.nextInt();
		System.out.println("Ввидите координаты поля:");
		System.out.println("введите значение строки:");
		row=scanner.nextInt()-1;
		System.out.println("введите значение столбца:");
		col=scanner.nextInt()-1;		
		switch (action) {
		case 1:
			MinesweeperService.getInstance().setFlag(row, col);
			System.out.println("Осталось флажков:"+mines+"/"+model.getMines());
			break;
		case 2:
		
			MinesweeperService.getInstance().open(row, col);
			break;
		default:
			break;
		}
		drawBoarder();
		
		}
	}
	
	public void drawBoarder(){
		MinesweeperModelIterator iterator = new MinesweeperModelIterator(model);
		while(iterator.hasNext()){
			Boolean element=iterator.next();
			System.out.print(field[iterator.getRow()][iterator.getCol()].getValue());
			if(iterator.getCol()==7){System.out.println();}
		}
	}


	@Override
	public Object getListElement() {
		return field;
	}

	@Override
	public void setTime(int time) {
		this.time=time;
		
	}

	@Override
	public void stopGame(Boolean situation) {
		
		String text = null;
		for (int i = 0; i < model.getRows(); i++) {
			for (int j = 0; j < model.getCols(); j++) {
				field[i][j].setOpen();
			}
		}
		if (situation) {
			text = "Поздравляем!Вы выйграли!Лалала";
		} else {
			text = "Вы проиграли.";
		}	
		System.out.println(text);
		System.out.println("Ваше время:"+time);
		System.out.println("------------------------------");
		System.out.println("Что дальше?");
		System.out.println("1-заново;");
		System.out.println("2-новая игра;");
		System.out.println("3-выход");
		int key=scanner.nextInt();
		switch (key) {
		case 1:
			model = new MinesweeperModel(model.getRows(), model.getCols(),
					model.getMines());
			
			break;
		case 2:
			System.out.println("какой уровень?");
			System.out.println("1-новичок;");
			System.out.println("2-любитель;");
			System.out.println("3-профессионал");
			key=scanner.nextInt();
			newGame(key);
			break;
		case 3:
			this.exit=true;
			break;

		default:
			break;
		}
		createNewField();
		game();
	}
	
	private void newGame(int key){
		switch (key) {
		case 1:
			model = new MinesweeperModel(8, 8, 10);
			break;
		case 2:
			model = new MinesweeperModel(16, 16, 40);
			break;
		case 3:
			model = new MinesweeperModel(16, 30, 99);
			break;
		default:
			break;
		}
	}
	
	private void createNewField(){
		model.createField();
		cteateBoarder();
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(this);
	}

	@Override
	public void setCountMines(int countMines) {
		this.mines=countMines;
		
	}

}
