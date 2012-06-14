package gr9372.shulga.minesweeper.test;

import junit.framework.Assert;
import gr9372.shulga.minesweeper.model.MinesweeperModel;
import gr9372.shulga.minesweeper.service.MinesweeperService;
import gr9372.shulga.minesweeper.view.Element;
import gr9372.shulga.minesweeper.view.MinesweeperView;
import gr9372.shulga.minesweeper.viewImpl.MinesweeperViewSwing;

import org.junit.Test;

public class TestServise {
	
	/**
	 * Если открывается минное поле,то полю сообщается,что оно заминировано,
	 * и всё поле блокируется,т.е. больше нельзя ничего открыть.
	 */	
	@Test
	public void testOpenMine(){
		MinesweeperModel model=new MinesweeperModel(1, 2, 1);
		model.setValue(0, 0, true);
		MinesweeperView view=new MinesweeperViewSwing(model);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(view);
		MinesweeperService.getInstance().open(0, 0);
		Element[][] field=(Element[][]) view.getListElement();
		Assert.assertTrue(field[0][0].isMine());
		Assert.assertTrue(field[0][1].isOpen());
		Assert.assertTrue(field[0][0].isOpen());		
	}
	
	/**
	 * если открывается заминированая клетка,всё поле блокируется и взрывается
	 */
	@Test
	public void testBlowUpField(){
		MinesweeperModel model=new MinesweeperModel(2, 2, 2);
		model.setValue(0, 0, true);
		model.setValue(1, 1, true);
		MinesweeperView view=new MinesweeperViewSwing(model);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(view);
		MinesweeperService.getInstance().open(0, 0);
		Element[][] field=(Element[][]) view.getListElement();
		Assert.assertTrue(field[0][0].isMine());
		Assert.assertTrue(field[1][1].isMine());
		Assert.assertTrue(field[0][1].isOpen());
		Assert.assertTrue(field[0][0].isOpen());
		Assert.assertTrue(field[1][1].isOpen());
		Assert.assertTrue(field[1][0].isOpen());
	}
	/**
	 * Если открывается пустое поле,то рассчитывается количество мин вокруг,
	 * поле становиться открытым и заблокированным.
	 */
	
	@Test
	public void testOpenFieldWithoutMine(){
		MinesweeperModel model=new MinesweeperModel(1, 2, 1);
		model.setValue(0, 0, true);
		MinesweeperView view=new MinesweeperViewSwing(model);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(view);
		MinesweeperService.getInstance().open(0, 1);
		Element[][] field=(Element[][]) view.getListElement();
		Assert.assertFalse(field[0][1].isMine());
		Assert.assertEquals(field[0][1].getNumberMines(), 1);
		
	}
	
	/**
	 *если на поле ставиться флаг,то это поле невозможно случайно открыть
	 * (клетка блокируется для открытия)
	 */
	@Test
	public void testSetFlag(){
		MinesweeperModel model=new MinesweeperModel(2, 2, 1);
		model.setValue(0, 0, true);
		MinesweeperView view=new MinesweeperViewSwing(model);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(view);
		MinesweeperService.getInstance().setFlag(0, 0);
		Element[][] field=(Element[][]) view.getListElement();
		Assert.assertTrue(field[0][0].isFlag());
		Assert.assertTrue(field[0][0].isOpen());
	}
	
	/**
	 * Если фраг убрать(повторный выбор поля для флага), клетка становиться опять активной.
	 */
	@Test 
	public void getAwayFlag(){
		MinesweeperModel model=new MinesweeperModel(2, 2, 1);
		model.setValue(0, 0, true);
		MinesweeperView view=new MinesweeperViewSwing(model);
		MinesweeperService.getInstance().setModel(model);
		MinesweeperService.getInstance().setView(view);
		MinesweeperService.getInstance().setFlag(0, 0);
		MinesweeperService.getInstance().setFlag(0, 0);
		Element[][] field=(Element[][]) view.getListElement();
		Assert.assertFalse(field[0][0].isFlag());
		Assert.assertFalse(field[0][0].isOpen());
	}
	

}
