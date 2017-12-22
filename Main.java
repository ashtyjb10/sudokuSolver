package Sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {

		//solve by elimination
		for (int i = 0; i < 40; i++) {

			String fileName = "puzzle" +39 + ".txt";

			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			Sudoku sud = new Sudoku(br);

			System.out.println("puzzle: " + fileName);
			sud.solve_by_elimination();
			
			System.out.println(sud.toString());
			
		}
		
		//solve by recursion
//		for (int i = 0; i < 40; i++) {
//
//			String fileName = "puzzle" + i + ".txt";
//
//			BufferedReader br = new BufferedReader(new FileReader(new File("puzzle" + i + ".txt")));
//			Sudoku sud = new Sudoku(br);
//			
//			System.out.println("puzzle: " + fileName);
//			sud.solve_sudoku();
//			System.out.println(sud.toString());
//			System.out.println(sud.get_guess_count());
//
//			
//		}
	
	}
}


