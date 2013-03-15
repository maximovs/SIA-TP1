package parser;

import group.AddGroup;
import group.DivideGroup;
import group.Group;
import group.MultiplyGroup;
import group.SubstractGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import board.Board;

public class Parser {
	public static void main(String [ ] args) throws IOException{
		
		FileReader input = new FileReader("levels/test.txt");
		BufferedReader bufReader = new BufferedReader(input);
		String line = null;
		
		Integer blockSize = null;
		Integer result = null;
		String operation = null;
		
		line = bufReader.readLine();
		blockSize = Integer.valueOf(line);
		if(blockSize == null){
			System.out.println("ARCHIVO INCORRECTO!");
			return;
		}
		
		Board board = new Board(blockSize);
		
		while((line = bufReader.readLine()) != null){
			String splited[] = line.split(",");
			if(splited.length != 3){
				System.out.println("ARCHIVO INCORRECTO!");
				return;
			}
			result = Integer.valueOf(splited[0]);
			operation = splited[1];
			Group group;
			switch(operation.charAt(0)){
				case '/': group = new DivideGroup(result, blockSize);break;
				case '*': group = new MultiplyGroup(result, blockSize);break;
				case '+': group = new AddGroup(result, blockSize);break;
				case '-': group = new SubstractGroup(result, blockSize);break; 
				default:  System.out.println("ARCHIVO INCORRECTO!");return;
			}
			board.addGroup(group);
			
			String cells[] = splited[2].split(";");
			for(String cell: cells){
				String[] coord = cell.split(":");
				if(coord.length != 2){
					System.out.println("ARCHIVO INCORRECTO!");
					return;
				}
				Integer x = Integer.valueOf(coord[0]);
				Integer y = Integer.valueOf(coord[1]);
				if(x == null || y == null){
					System.out.println("ARCHIVO INCORRECTO!");
					return;
				}
				group.addCell(board.getCell(x, y));
			}
			
		}
		if(!board.isCorrect()){
			System.out.println("ARCHIVO INCORRECTO!");
			return;
		}
	}

}
