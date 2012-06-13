package gr9372.shulga.minesweeper.viewImpl;

import gr9372.shulga.minesweeper.model.MinesweeperModel;
import gr9372.shulga.minesweeper.service.MinesweeperService;
import gr9372.shulga.minesweeper.view.MinesweeperView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MinesweeperViewSwing extends JFrame implements MinesweeperView {

	private MinesweeperModel model;
	private Boolean flag;
	private int value;
	JButton buttonTimer;
	JButton buttonFlag;
	private MinesweeperButton[][] listButton;
	private JPanel panelPole;

	public MinesweeperViewSwing(MinesweeperModel model) {
		super("Minesweeper");
		this.model = model;
		setLayout(new BorderLayout());
		createPanelMenu();
		createBoarder();
	}

	public void createPanelMenu() {
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		JMenuBar menuBar = createMenuBar();
		panelMenu.add(menuBar, BorderLayout.NORTH);
		JButton button = new JButton("!restart!");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				ckickRestart(event);

			}
		});
		panelMenu.add(button, BorderLayout.CENTER);
		buttonTimer = new JButton("0");
		panelMenu.add(buttonTimer, BorderLayout.EAST);
		buttonFlag = new JButton("0/" + model.getMines());
		panelMenu.add(buttonFlag, BorderLayout.WEST);
		add(panelMenu, BorderLayout.NORTH);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuGame = new JMenu("Игра");
		JMenu menuNewGame = new JMenu("Новая игра");
		menuNewGame.add(getJMenuItem("Новичок"));
		menuNewGame.add(getJMenuItem("Любитель"));
		menuNewGame.add(getJMenuItem("Профессионал"));
		menuGame.add(menuNewGame);
		menuBar.add(menuGame);
		return menuBar;
	}

	private JMenuItem getJMenuItem(String name) {
		JMenuItem jMenuItem = new JMenuItem(name);
		jMenuItem.setName(name);

		jMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				activateMenu(event);

			}
		});

		return jMenuItem;
	}

	public void createBoarder() {
		this.listButton = new MinesweeperButton[model.getRows()][model
				.getCols()];
		this.panelPole = new JPanel();
		panelPole.setLayout(new GridLayout(model.getRows(), model.getCols()));
		for (int i = 0; i < model.getRows(); i++) {
			for (int j = 0; j < model.getCols(); j++) {
				MinesweeperButton button = new MinesweeperButton();
				button.setGridY(i);
				button.setGridX(j);
				listButton[i][j] = button;
				button.addMouseListener(new MinesweeperButtonListener() {

					@Override
					public void mouseClicked(MouseEvent event) {
						click(event);
					}

				});
				panelPole.add(button);
			}
		}
		add(panelPole, BorderLayout.CENTER);
	}

	private void activateMenu(ActionEvent event) {
		int width = 0;
		int height = 0;
		MinesweeperModel newModel = null;
		JMenuItem jMenuItem = (JMenuItem) event.getSource();
		String nameJMenuItem = jMenuItem.getName();
		if (nameJMenuItem.equals("Новичок")) {
			model = new MinesweeperModel(8, 8, 10);
			width = 400;
			height = 400;
		}
		if (nameJMenuItem.equals("Любитель")) {
			model = new MinesweeperModel(16, 16, 40);
			width = 600;
			height = 500;
		}
		if (nameJMenuItem.equals("Профессионал")) {
			model = new MinesweeperModel(16, 30, 99);
			width = 800;
			height = 800;
		}
		setSize(width, height);
		createNewField();
	}
/*
	@Override
	public void setVal(int value) {
		this.value = value;
	}**/

	@Override
	public Object getListElement() {
		return listButton;
	}

	@Override
	public void setCountMines(int countMines) {
		this.buttonFlag.setText("" + countMines + "/" + model.getMines());
	}

	@Override
	public void setTime(int time) {
		this.buttonTimer.setText("" + time);

	}

	@Override
	public void stopGame(Boolean situation) {
		String text = null;
		for (int i = 0; i < model.getRows(); i++) {
			for (int j = 0; j < model.getCols(); j++) {
				listButton[i][j].setOpen();
			}
		}
		if (situation) {
			text = "Поздравляем!Вы выйграли!Лалала";
		} else {
			text = "Вы проиграли.";
		}
		createWindowEnd(text);
	}

	private void click(MouseEvent event) {
		MinesweeperButton button = (MinesweeperButton) event.getSource();
		int col = button.getGridX();
		int row = button.getGridY();
		Boolean flag = null;

		switch (event.getButton()) {
		case MouseEvent.BUTTON1:
			if (!button.isOpen()) {
				button.setOpen();
				MinesweeperService.getInstance().open(row, col);
			}
			break;
		case MouseEvent.BUTTON3:
			MinesweeperService.getInstance().setFlag(row, col);
			break;
		default:
			break;
		}

	}

	private void createWindowEnd(String text) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setText(text);
		frame.add(label, BorderLayout.CENTER);
		frame.setSize(350, 100);
		frame.setVisible(true);
	}

	private void ckickRestart(ActionEvent event) {
		model = new MinesweeperModel(model.getRows(), model.getCols(),
				model.getMines());
		createNewField();
		this.buttonFlag.setText("0/" + model.getMines());
		this.buttonTimer.setText("0");
	}

	private void createNewField() {
		setVisible(false);
		panelPole.setVisible(false);
		MinesweeperService.getInstance().createField(model);
		createBoarder();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(this);

	}
}
