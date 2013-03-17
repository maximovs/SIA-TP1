package test.board;

import static org.junit.Assert.*;
import group.DivideGroup;
import group.Group;

import org.junit.Before;
import org.junit.Test;

import board.Board;
public class BoardTest {
	Board board = null;
	
	@Before
	public void before(){
		board = new Board(2);
		Group grupo1 = new DivideGroup(2, 2);
		Group grupo2 = new DivideGroup(2, 2);
		
		grupo1.addCell(board.getCell(0, 0));
		grupo1.addCell(board.getCell(0, 1));
		
		grupo2.addCell(board.getCell(1, 0));
		grupo2.addCell(board.getCell(1, 1));
	}
	
	@Test
	public void isCorrect(){
		assertTrue(board.isCorrect());
	}
	
	@Test
	public void isNotComplete(){
		assertFalse(board.completed());
	}
	
	@Test
	public void isNotFinished(){
		assertFalse(board.isFinished());
	}
	
	@Test
	public void isCompleteButNotFinished(){		
		board.setCellValue(0, 0, 2);
		board.setCellValue(0, 1, 2);
		board.setCellValue(1, 0, 2);
		board.setCellValue(1, 1, 2);
		assertTrue(board.completed());
		assertFalse(board.isFinished());
	}
	
	@Test
	public void isCompleteAndFinished(){		
		board.setCellValue(0, 0, 2);
		board.setCellValue(0, 1, 1);
		board.setCellValue(1, 0, 1);
		board.setCellValue(1, 1, 2);
		assertTrue(board.completed());
		assertTrue(board.isFinished());
	}
	

}
