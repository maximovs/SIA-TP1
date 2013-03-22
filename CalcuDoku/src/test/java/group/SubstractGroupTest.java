package group;


import java.util.ArrayList;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sia.board.Cell;
import sia.group.Group;
import sia.group.SubstractGroup;


public class SubstractGroupTest {
	Group g = new SubstractGroup(5, 8);
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
		List<Integer> l = new ArrayList<>();
		List<List<Integer>> m = new ArrayList<>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(6);
		l.add(7);
		l.add(8);
		List<Integer> aux = new ArrayList<Integer>();
		aux.add(8);
		aux.add(3);
		m.add(aux);
		aux = new ArrayList<>();
		aux.add(7);
		aux.add(2);
		m.add(aux);
		aux = new ArrayList<>();
		aux.add(6);
		aux.add(1);
		m.add(aux);
		Assert.assertEquals(l,g.getPossibles());
		Assert.assertEquals(m,g.getPossibleLists());
		System.out.println(g.getPossibles());
		System.out.println(g.getPossibleLists());
	}
}
