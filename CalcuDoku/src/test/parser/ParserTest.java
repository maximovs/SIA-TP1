package test.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import board.Board;

import parser.Parser;

public class ParserTest {
	
	@Test
	public void WrongLevel(){
		Board b = Parser.parse("levels/wrongLevel.txt");
		assertTrue(b == null);
	}
	
	@Test
	public void RightLevel(){
		Board b = Parser.parse("levels/test.txt");
		assertFalse(b == null);
	}
	
	@Test
	public void size5X5(){
		Board b = Parser.parse("levels/5x5.txt");
		assertTrue(b != null);
		b.printBoard();
		b.printGroups();
	}

}
