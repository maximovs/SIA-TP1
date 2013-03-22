package parser;

import static org.junit.Assert.*;

import org.junit.Test;


import sia.board.Board;
import sia.parser.Parser;

public class ParserTest {
	
	@Test
	public void WrongLevel(){
		Board b = Parser.parse("src/main/resources/levels/wrongLevel");
		assertTrue(b == null);
	}
	
	@Test
	public void RightLevel(){
		Board b = Parser.parse("src/main/resources/levels/test.txt");
		assertFalse(b == null);
	}
	
	@Test
	public void size5X5(){
		Board b = Parser.parse("src/main/resources/levels/5x5");
		assertTrue(b != null);
		b.printBoard();
		b.printGroups();
	}

}
