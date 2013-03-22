package sia.parser;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sia.board.Board;
import sia.group.AddGroup;
import sia.group.DivideGroup;
import sia.group.Group;
import sia.group.MultiplyGroup;
import sia.group.SubstractGroup;


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
		Integer cellsCant = null;
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
				if(splited.length != 4){
					System.out.println("ARCHIVO INCORRECTO!");
					return null;
				}
				result = Integer.valueOf(splited[0]);
				operation = splited[1];
				cellsCant = Integer.valueOf(splited[2]);
				if(cellsCant == null){
					System.out.println("ARCHIVO INCORRECTO!");
					return null;
				}
				Group group;
				switch(operation.charAt(0)){
					case '/': group = new DivideGroup(result, blockSize);break;
					case '*': group = new MultiplyGroup(result, blockSize, cellsCant);break;
					case '+': group = new AddGroup(result, blockSize, cellsCant);break;
					case '-': group = new SubstractGroup(result, blockSize);break; 
					default:  System.out.println("ARCHIVO INCORRECTO!");return null;
				}
				board.addGroup(group);
				
				String cells[] = splited[3].split(";");
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
