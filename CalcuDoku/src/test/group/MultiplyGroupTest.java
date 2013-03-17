package test.group;

import java.util.ArrayList;
import java.util.List;

import group.Group;
import group.MultiplyGroup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import board.Cell;

public class MultiplyGroupTest {
	Group g = new MultiplyGroup(54, 7, 5);
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
		l.add(6);
		List<List<Integer>> m = new ArrayList<>();
		List<Integer> aux = new ArrayList<Integer>();
		aux.add(6);
		aux.add(3);
		aux.add(3);
		aux.add(1);
		aux.add(1);
		m.add(aux);
		aux = new ArrayList<>();
		aux.add(3);
		aux.add(3);
		aux.add(3);
		aux.add(2);
		aux.add(1);
		m.add(aux);
		Assert.assertEquals(l,g.getPossibles());
		Assert.assertEquals(m,g.getPossibleLists());
		System.out.println(g.getPossibleLists());
		System.out.println(g.getPossibles());
		g.satisfies();
	}
}
