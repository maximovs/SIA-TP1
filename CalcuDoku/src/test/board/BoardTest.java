package test.board;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import group.DivideGroup;
import group.Group;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import parser.Parser;

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
	
	@Test
	public void isFinished5X5(){		
		board = Parser.parse("levels/5X5.txt");
		board.setCellValue(0, 0, 4);
		board.setCellValue(0, 1, 1);
		board.setCellValue(0, 2, 5);
		board.setCellValue(0, 3, 3);
		board.setCellValue(0, 4, 2);

		board.setCellValue(1, 0, 3);
		board.setCellValue(1, 1, 2);
		board.setCellValue(1, 2, 1);
		board.setCellValue(1, 3, 5);
		board.setCellValue(1, 4, 4);

		board.setCellValue(2, 0, 5);
		board.setCellValue(2, 1, 3);
		board.setCellValue(2, 2, 2);
		board.setCellValue(2, 3, 4);
		board.setCellValue(2, 4, 1);

		board.setCellValue(3, 0, 1);
		board.setCellValue(3, 1, 5);
		board.setCellValue(3, 2, 4);
		board.setCellValue(3, 3, 2);
		board.setCellValue(3, 4, 3);

		board.setCellValue(4, 0, 2);
		board.setCellValue(4, 1, 4);
		board.setCellValue(4, 2, 3);
		board.setCellValue(4, 3, 1);
		board.setCellValue(4, 4, 5);
		
		assertTrue(board.isFinished());
	}
	
	@Test
	public void returnsCorrectPossibles(){		
		board = Parser.parse("levels/QuadOp6x6");
		//chequeo -
		board.setCellValue(0, 0, 2);
		board.setCellValue(0, 3, 4);
		board.setCellValue(0, 2, 3);
		board.setCellValue(4, 1, 3);
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(5);
		Assert.assertEquals(l, board.getPossibbles(0, 1));
		//chequeo *
		board.setCellValue(4, 3, 6);
		board.setCellValue(2, 4, 6);
		l = new ArrayList<>();
		l.add(2);
		l.add(5);
		Assert.assertEquals(l,board.getPossibbles(1, 3));
		//chequeo +
		board.setCellValue(5, 5, 4);
		board.setCellValue(2, 2, 1);
		l = new ArrayList<>();
		l.add(2);
		l.add(5);
		Assert.assertEquals(l,board.getPossibbles(5, 2));
		//chequeo + (vacío)
		l = new ArrayList<>();
		l.add(2);
		Assert.assertEquals(l,board.getPossibbles(4, 4));
		
		//chequeo /
		l = new ArrayList<>();
		l.add(3);
		Assert.assertEquals(l,board.getPossibbles(5, 3));
		//chequeo /
		board.setCellValue(4, 3, 0);
		l = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(6);
		System.out.println(board.getPossibbles(5, 3));
		Assert.assertEquals(l,board.getPossibbles(5, 3));
		
	}	

}
