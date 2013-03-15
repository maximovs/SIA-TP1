package test.group;

import java.util.ArrayList;

import group.DivideGroup;
import group.Group;

import org.junit.Before;
import org.junit.Test;

import board.Cell;

public class DivideGroupTest {
	Group g = new DivideGroup(1, 6);
	ArrayList<Cell> cells = new ArrayList<>();
	@Before
	public void before(){
		cells.add(new Cell());
		cells.add(new Cell());
		for(Cell c: cells){
			g.addCell(c);
		}
	}
	
	@Test
	public void test1(){
		System.out.println(g.getPossibles());
		System.out.println(g.getPossibleLists());
	}
}
