package gr9372.shulga.minesweeper.game;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import gr9372.shulga.minesweeper.model.MinesweeperModel;
import gr9372.shulga.minesweeper.service.MinesweeperService;
import gr9372.shulga.minesweeper.view.Element;
import gr9372.shulga.minesweeper.view.MinesweeperView;
import gr9372.shulga.minesweeper.viewImpl.MinesweeperViewConsole;
import gr9372.shulga.minesweeper.viewImpl.MinesweeperViewSwing;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		MinesweeperModel model = new MinesweeperModel(8, 8, 10);
		MinesweeperView view = null;
		System.out.println("Какой ввод?");
		System.out.println("1-concole");
		System.out.println("2-Swing");
		int key = scanner.nextInt();
		switch (key) {
		case 1:
			view=new MinesweeperViewConsole(model);
			
			
			break;
		case 2:
			view=new MinesweeperViewSwing(model);
			((Window) view).setSize(400, 400);
			((JFrame) view).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			((Window) view).setVisible(true);
			break;

		default:
			break;
		}	
		MinesweeperService.getInstance().createField(model);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(view);
		if(key==1){((MinesweeperViewConsole) view).game();}
	}

}
