package parser;

import group.AddGroup;
import group.DivideGroup;
import group.Group;
import group.MultiplyGroup;
import group.SubstractGroup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import board.Board;

public class Parser {
	
	public static Board parse(String fileName){
		
		FileReader input;
		try {
			input = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("ARCHIVO INCORRECTO!");
			return null;
		}
		BufferedReader bufReader = new BufferedReader(input);
		String line = null;
		
		Integer blockSize = null;
		Integer result = null;
		String operation = null;
		
		try {
			line = bufReader.readLine();
		} catch (IOException e) {
			System.out.println("ARCHIVO INCORRECTO!");
			return null;
		}
		blockSize = Integer.valueOf(line);
		if(blockSize == null){
			System.out.println("ARCHIVO INCORRECTO!");
			return null;
		}
		
		Board board = new Board(blockSize);
		
		try {
			while((line = bufReader.readLine()) != null){
				String splited[] = line.split(",");
				if(splited.length != 3){
					System.out.println("ARCHIVO INCORRECTO!");
					return null;
				}
				result = Integer.valueOf(splited[0]);
				operation = splited[1];
				Group group;
				switch(operation.charAt(0)){
					case '/': group = new DivideGroup(result, blockSize);break;
					case '*': group = new MultiplyGroup(result, blockSize);break;
					case '+': group = new AddGroup(result, blockSize);break;
					case '-': group = new SubstractGroup(result, blockSize);break; 
					default:  System.out.println("ARCHIVO INCORRECTO!");return null;
				}
				board.addGroup(group);
				
				String cells[] = splited[2].split(";");
				for(String cell: cells){
					String[] coord = cell.split(":");
					if(coord.length != 2){
						System.out.println("ARCHIVO INCORRECTO!");
						return null;
					}
					Integer x = Integer.valueOf(coord[0]);
					Integer y = Integer.valueOf(coord[1]);
					if(x == null || y == null){
						System.out.println("ARCHIVO INCORRECTO!");
						return null;
					}
					group.addCell(board.getCell(x, y));
				}
				
			}
		} catch (Exception e) {
			System.out.println("ARCHIVO INCORRECTO!");
			return null;
		}
		
		try{
			input.close();
			bufReader.close();
		} catch (Exception e){}
		
		if(!board.isCorrect()){
			System.out.println("ARCHIVO INCORRECTO!");
			return null;
		}
		
		System.out.println("Parser ok");
		return board;
	}

}
