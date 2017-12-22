package Sudoku;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class SudokuTest  {

	Sudoku sud;
	Sudoku puzzle;
	ArrayList<HashSet<Integer>> arr1D;
	HashSet<Integer> sudokuPossibleNumbers;
	int normalArr1D[];
	BufferedReader br;

	@Before 
	public void setUp() throws IOException{

		br = new BufferedReader(new FileReader(new File("p1.txt")));
		sud = new Sudoku(br);

	}
	//test the constructor and make sure that it works.
	@Test
	public void test_BufferedReader()
	{
		int[] expected = {5, 0, 0, 8, 2, 6, 0, 0, 0, 0, 4, 8, 0, 1, 9, 5, 0, 
				0, 1, 0, 9, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 3, 7, 
				0, 0, 9, 0, 0, 0, 0, 6, 0, 0, 0, 1, 0, 0, 0, 8, 0, 2, 9, 4, 0, 
				7, 0, 6, 0, 0, 8, 0, 0, 0, 9, 1, 0, 0, 0, 0, 6, 0, 0, 8, 4, 0, 0, 5} ;

		assertArrayEquals(expected, sud.get_puzzle_1D());

	}

	/**  * test the valid_for_row method to make sure that for the following puzzle I
	 * get the expected results  
	 *  
	 * Row Col  
	 *    0 1 2  3 4 5  6 7 8
	 * 0  0 0 0  1 0 0  0 0 0  
	 * 1  0 0 0  0 2 0  0 0 0
	 * 2  0 0 0  0 0 3  0 0 0 
	 * 3  0 0 0  0 0 0  0 0 0 
	 * 4  0 0 0  0 0 0  0 0 0 
	 * 5  0 0 0  0 0 0  0 0 0 
	 * 6  0 0 0  0 0 0  0 0 0
	 * 7  0 0 0  0 0 0  0 0 0
	 * 8  0 0 0  0 0 0  0 0 0
	 * 
	 */ 

	@Test public void test_valid_for_row_method() { 
		// build the above puzzle 
		puzzle = new Sudoku();
		puzzle.puzzle[3]  = 1; 
		puzzle.puzzle[13] = 2; 
		puzzle.puzzle[23] = 3;

		// Some constants 
		final int first_row  = 0;
		final int second_row = 1; 
		final int third_row  = 2; 
		final int last_row   = 8;
		// Initial tests 
		// test that 1 cannot be put in  the first row (row 0) 
		// test that 2 _can_ be put in  the first row (row 0) 
		// test that 2 _cannot_ be put in  the second_row (row 0) 
		// test that 5 _can_ be put in  the second_row (row 0)
		assertFalse(puzzle.valid_for_row(first_row,  1)); 
		assertTrue( puzzle.valid_for_row(first_row,  2)); 
		assertFalse(puzzle.valid_for_row(second_row, 2)); 
		assertTrue( puzzle.valid_for_row(second_row, 5));
		// Test all 9 values in row three. 
		// Every value should pass except for 1 
		for (int possible_num=1;possible_num<10;possible_num++) {
			assertEquals(possible_num!=3,puzzle.valid_for_row(third_row,
					possible_num)); }

		// Finally, test all 9 values are valid in the last row. 
		for (int possible_num=1;possible_num<10;possible_num++) {
			assertTrue(puzzle.valid_for_row(last_row, possible_num));
		}
	}

	//tests the valid for column (See above)
	@Test public void test_valid_for_col_method() { 
		// build the above puzzle 
		puzzle = new Sudoku();
		puzzle.puzzle[3]  = 1; 
		puzzle.puzzle[13] = 2; 
		puzzle.puzzle[23] = 3;

		// Some constants 
		final int first_col  = 0;
		final int second_col = 1; 
		final int third_col = 2; 
		final int last_col   = 8;
		// Initial tests 
		// test that 1 cannot be put in  the first column
		// test that 2 _can_ be put in  the first column
		// test that 2 _cannot_ be put in  the second_col 
		// test that 5 _can_ be put in  the second_col
		assertFalse(puzzle.valid_for_column(first_col,  1)); 
		assertTrue( puzzle.valid_for_column(first_col,  2)); 
		assertFalse(puzzle.valid_for_column(second_col, 2)); 
		assertTrue( puzzle.valid_for_column(second_col, 5));
		// Test all 9 values in col three. 
		// Every value should pass except for 1 
		for (int possible_num=1;possible_num<10;possible_num++) {
			assertEquals(possible_num!=3,puzzle.valid_for_column(third_col,
					possible_num)); }

		// Finally, test all 9 values are valid in the last col. 
		for (int possible_num=1;possible_num<10;possible_num++) {
			assertTrue(puzzle.valid_for_column(last_col, possible_num));
		}
	}

	//tests the vaild for box method (See above)
	@Test public void test_valid_for_box_method() { 
		// build the above puzzle 
		puzzle = new Sudoku();
		puzzle.puzzle[3]  = 1; 
		puzzle.puzzle[13] = 2; 
		puzzle.puzzle[23] = 3;

		// Some constants 
		final int first_box  = 0;
		final int second_box = 1; 
		final int third_box = 2; 
		final int last_box  = 8;
		// Initial tests 
		// test that 1 cannot be put in  the first box
		// test that 2 _can_ be put in  the first box
		// test that 2 _cannot_ be put in  the second_box
		// test that 5 _can_ be put in  the second_box
		assertFalse(puzzle.valid_for_box(first_box,  1)); 
		assertTrue( puzzle.valid_for_box(first_box,  2)); 
		assertFalse(puzzle.valid_for_box(second_box, 2)); 
		assertTrue( puzzle.valid_for_box(second_box, 5));
		// Test all 9 values in box three. 
		// Every value should pass except for 1 
		for (int possible_num=1;possible_num<10;possible_num++) {
			assertEquals(possible_num!=3,puzzle.valid_for_box(third_box,
					possible_num)); }

		// Finally, test all 9 values are valid in the last box. 
		for (int possible_num=1;possible_num<10;possible_num++) {
			assertTrue(puzzle.valid_for_box(last_box, possible_num));
		}
	}

	//tests the constructor without buffered reader
	@Test
	public void test_sudoku_constructor()
	{

		Sudoku sub1 = new Sudoku();

		int[] arr = {};
		int[][] arr1 = {};

		assertArrayEquals(arr, sub1.get_puzzle_1D());
		assertArrayEquals(arr1, sub1.get_puzzle_2D());

	}

	//tests the solve sudoku method
	@Test
	public void test_solve_sudoku()
	{

		assertTrue(sud.solve_sudoku()); 

	}

	//tests is valid method
	@Test
	public void test_is_valid()
	{

		assertTrue(sud.is_valid(2, 3));
		assertTrue(sud.is_valid(80, 7));
		assertFalse(sud.is_valid(2, 1));
	}

	//tests verify method
	@Test
	public void test_verify()
	{
		assertTrue(sud.verify());
	}

	//tests solve by elimination method
	@Test
	public void test_solve_by_elimination()
	{
		int[] arr = {};
		sud.solve_by_elimination();
		assertArrayEquals(arr, sud.get_puzzle_1D());

	}

	//tests prune row
	@Test
	public void test_prune_row()
	{

		for (int individualBox = 0; individualBox < arr1D.size(); individualBox++) {

			if(normalArr1D[individualBox] > 9){

				System.out.println("Not a vaild sudoku puzzle!");

			}

			if(normalArr1D[individualBox] == 0){

				for (int number = 1; number < 10; number++ ) {

					sudokuPossibleNumbers = new HashSet<Integer>();
					sudokuPossibleNumbers.add(number);

				}

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

			else{

				int number = normalArr1D[individualBox];

				sudokuPossibleNumbers = new HashSet<Integer>();
				sudokuPossibleNumbers.add(number);

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

		}

		sud.prune_row(arr1D, 0, 3);
	}

	//tests prune col
	@Test
	public void test_prune_col()
	{

		for (int individualBox = 0; individualBox < arr1D.size(); individualBox++) {

			if(normalArr1D[individualBox] > 9){

				System.out.println("Not a vaild sudoku puzzle!");

			}

			if(normalArr1D[individualBox] == 0){

				for (int number = 1; number < 10; number++ ) {

					sudokuPossibleNumbers = new HashSet<Integer>();
					sudokuPossibleNumbers.add(number);

				}

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

			else{

				int number = normalArr1D[individualBox];

				sudokuPossibleNumbers = new HashSet<Integer>();
				sudokuPossibleNumbers.add(number);

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

		}

		sud.prune_column(arr1D, 0, 3);

	}

	//tests prune box
	@Test
	public void test_prune_box()
	{

		for (int individualBox = 0; individualBox < arr1D.size(); individualBox++) {

			if(normalArr1D[individualBox] > 9){

				System.out.println("Not a vaild sudoku puzzle!");

			}

			if(normalArr1D[individualBox] == 0){

				for (int number = 1; number < 10; number++ ) {

					sudokuPossibleNumbers = new HashSet<Integer>();
					sudokuPossibleNumbers.add(number);

				}

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

			else{

				int number = normalArr1D[individualBox];

				sudokuPossibleNumbers = new HashSet<Integer>();
				sudokuPossibleNumbers.add(number);

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

		}

		sud.prune_box(arr1D, 0, 3);

	}

}

