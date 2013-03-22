package group;

import java.util.ArrayList;
import java.util.List;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sia.board.Cell;
import sia.group.AddGroup;
import sia.group.Group;


public class AddGroupTest {
	Group g = new AddGroup(7, 5, 3);
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
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		l.add(5);
		List<List<Integer>> m = new ArrayList<>();
		List<Integer> aux = new ArrayList<Integer>();
		aux.add(5);
		aux.add(1);
		aux.add(1);
		m.add(aux);
		aux = new ArrayList<>();
		aux.add(4);
		aux.add(2);
		aux.add(1);
		m.add(aux);
		aux = new ArrayList<>();
		aux.add(3);
		aux.add(3);
		aux.add(1);
		m.add(aux);
		aux = new ArrayList<>();
		aux.add(3);
		aux.add(2);
		aux.add(2);
		m.add(aux);
		Assert.assertEquals(l,g.getPossibles());
		Assert.assertEquals(m,g.getPossibleLists());
		System.out.println(g.getPossibleLists());
		System.out.println(g.getPossibles());
	}
}
