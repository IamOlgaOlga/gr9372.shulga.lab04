package gr9372.shulga.minesweeper.test;

import junit.framework.Assert;
import gr9372.shulga.minesweeper.iterator.MinesweeperModelIterator;
import gr9372.shulga.minesweeper.model.MinesweeperModel;

import org.junit.Test;

public class TestMinesweeperModelIterator {
	
	/**
	 * итератор обходит все элементы модели,неависмо от значений клеток модели
	 */
	
	@Test
	public void testItareteModelWithoutMines(){
		MinesweeperModel model=new MinesweeperModel(2, 2, 0);
		MinesweeperModelIterator iterator=new MinesweeperModelIterator(model);
		int countElement=0;
		while(iterator.hasNext()){
			Boolean element=iterator.next();
			countElement++;
		}
		Assert.assertEquals(countElement, 4);
	}
	
	@Test
	public void testIteratorModelWithMine(){
		MinesweeperModel model=new MinesweeperModel(2, 2, 0);
		model.setValue(0, 0, true);
		MinesweeperModelIterator iterator=new MinesweeperModelIterator(model);
		int countElement=0;
		while(iterator.hasNext()){
			Boolean element=iterator.next();
			countElement++;
		}
		Assert.assertEquals(countElement, 4);
	
	}

}
