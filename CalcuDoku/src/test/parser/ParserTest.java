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

}
