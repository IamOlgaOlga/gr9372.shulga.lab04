package gr9372.shulga.minesweeper.test;

import org.junit.Test;

import gr9372.shulga.minesweeper.iterator.AroundFieldIterator;
import gr9372.shulga.minesweeper.model.MinesweeperModel;
import junit.framework.Assert;

public class TestArowndFieldIterator {
	
	/**
	 * ���� � ������ ����� ���� ������ ����,��������� ������ ��������
	 */
	@Test 
	public void testSingleField(){
		MinesweeperModel model=new MinesweeperModel(1, 1, 0);
		AroundFieldIterator iterator=new AroundFieldIterator(0, 0, model);
		Boolean element=iterator.next();
		Assert.assertFalse(iterator.hasNext());
	}
	
	/**
	 * �������� ����������,���� �� ������������� ������ ������ ��������
	 */
	@Test
	public void testIteratorNextValue(){
		MinesweeperModel model=new MinesweeperModel(1, 2, 1);
		model.setValue(0, 0, true);
		AroundFieldIterator iterator=new AroundFieldIterator(0,1, model);
		Boolean element=iterator.next();
		Assert.assertTrue(element);
	}
	
	/**
	 * �������� ������ �������� ��� ���� ������ �������� ������
	 * 
	 */
	public void searchAllMines(){
		MinesweeperModel model=new MinesweeperModel(3, 3, 1);
		model.setValue(0, 0, true);
		model.setValue(2, 2, true);
		AroundFieldIterator iterator=new AroundFieldIterator(1,1, model);
		int countMines=0;
		while(iterator.hasNext()){
			Boolean element=iterator.next();
			if(element){
				countMines++;
			}
		}
		Assert.assertEquals(countMines, 2);
	}

}
