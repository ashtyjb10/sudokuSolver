package Sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Sudoku is a solver for a sudoku puzzle, a file is read in and then you can choose 
 * which method you would like to use to solve the recursive (brute force) or the Elimination
 * (guess) method. Each method will act on the rows and columns and boxes to verify that it is correct or
 * not.
 * 
 * 
 * @author Anastasia Gonzalez / Ashton Schmidt
 *
 */

public class Sudoku {

	public static final int[] puzzle = null;
	/**
	 * Global instance variables
	 */

	private int countRecursive = 0;
	private int puzzle1D[];
	private int arr2D[][];
	private final int empty = 0;
	private int count = 0;
	private static String [][] stringArray;
	private static String stringPossibilities;
	ArrayList<HashSet<Integer>> arr1D;
	HashSet<Integer> sudokuPossibleNumbers;

	/**
	 *  Constructor it initializes an array list containing a hashSet, a 1d array, and a 2d array.
	 */
	public Sudoku(){

		puzzle1D = new int[81];

		arr2D = new int[9][9];

		arr1D = new ArrayList<HashSet<Integer>>();

	}

	/**
	 * This reads in the buffered reader file and sets the values for each of the 
	 * arrays in the appropriate position. It gets the value by reading a line of the file
	 * storing it into a string and then breaking it up by white space to get one single 
	 * value to store in the array
	 * 
	 * Improper format (too many numbers per line, no numbers, too many total numbers, etc)
	 * should result in a runtime exception being thrown.
	 * @throws IOException 
	 *
	 */


	public Sudoku( BufferedReader reader ) throws IOException {

		puzzle1D = new int[81];

		arr2D = new int[9][9];

		arr1D = new ArrayList<HashSet<Integer>>();

		String line1 = null;
		String[] arr = null;

		try {


			while((line1 = reader.readLine()) != null) {

				arr = line1.split(" ");

				for (int i = 0; i < 9; i++) {

					puzzle1D[count] = Integer.parseInt(arr[i]);
					count++;

				}

			}

			reader.close();

		} 
		catch (FileNotFoundException e) 
		{

			e.printStackTrace();
		}

	}

	/**
	 * @return a copy of the puzzle as a 1D matrix
	 */
	public int [] get_puzzle_1D() {


		return puzzle1D;

	}

	/**
	 * @return a copy of the puzzle as a 2D matrix
	 */
	public int [][] get_puzzle_2D() {

		return arr2D;

	}


	/**
	 * @return how many guesses it took to recursively solve the problem.
	 */
	public int get_guess_count() {

		return countRecursive;

	}



	/**
	 * Takes two ints one is the row, the second is the number(key) we are looking to see if 
	 * it is valid. The array that is used to check is a 2d array to make it easier to find the 
	 * columns. Then it takes the row and the cols and sees if the number(key) value is stored,
	 * if it is already stored in the row it returns false.
	 *
	 */
	protected boolean valid_for_row( int row, int number ) {

		create2D(puzzle1D);

		for( int indexCol = 0; indexCol < 9; indexCol++ ) {

			if( number == arr2D[row][indexCol] ) {

				return false;

			}

		}

		return true;

	}



	/**
	 * Takes two ints one is the column, the second is the number(key) we are looking to see if 
	 * it is valid. The array that is used to check is a 2d array to make it easier to find the 
	 * rows. Then it takes the row and the cols and sees if the number(key) value is stored,
	 * if it is already stored in the col it returns false.
	 *
	 */
	protected boolean valid_for_column( int col, int number ) {

		create2D(puzzle1D);

		for( int indexRow = 0; indexRow < 9; indexRow++ ) {

			if( number == arr2D[indexRow][col] ) {

				return false;

			}

		}

		return true;
	}

	/**
	 *	
	 * Takes two ints one is the box, the second is the number(key) we are looking to see if 
	 * it is valid. The array that is used to check is a 2d array to make it easier to find the 
	 * box. Then it takes the row and the cols and some math is done to get the correct row and
	 * col of the array and sees if the number(key) value is stored, if it is already stored in the row it returns false.
	 *
	 */
	protected boolean valid_for_box( int box, int number ) {

		create2D(puzzle1D);

		int row = (box / 3) * 3;
		int col = (box % 3) * 3;
		int iterationsRow = row + 3;
		int iterationsCol = col + 3;

		for(int rowOfBox = row; row < iterationsRow; row++) {
			for(int colOfBox = col; col < iterationsCol; col++) {

				if(number == arr2D[row][col]){

					return false;
				}

			}
		}
		return true;
	}

	/**
	 *Takes in two values the position (1d) of the box we want to check and the 
	 *value (key) we have. If the normal array 1d at the position is 0 then we
	 *get the row and position using division and mod. It then gets the most top left 
	 *box in the sudoku set to check the row col and box number. It then enters the 
	 *valid_for_ (row, col, box) and if all of those are true then we return true that it is a valid
	 *number for that row, col, box. If one of the valid_for_ (row, col, box) returns false then 
	 *we return false.
	 */
	protected boolean is_valid( int position, int possible_value ) {

		if(puzzle1D[position] == empty){

			int row = position / 9;
			int col = position % 9;
			int[][] arrayOfBox = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
			int box;
			int rowOfBox = 0;
			int colOfBox = 0;

			if(row <= 2 && row >= 0){

				rowOfBox = 0;

			}

			else if(row <= 5 && row >= 3){

				rowOfBox = 1;

			}

			else if(row <= 8 && row >= 6){

				rowOfBox = 2;

			}

			if(col < 3 && col >= 0){

				colOfBox = 0;

			}

			else if(col <= 5 && col >= 3){

				colOfBox = 1;

			}
			else if(col <= 8 && col >= 6){

				colOfBox = 2;

			}

			box = arrayOfBox[rowOfBox][colOfBox];

			if(valid_for_row(row, possible_value)){

				if(valid_for_column(col, possible_value)){

					if(valid_for_box(box, possible_value)){

						return true;

					}

				}

			}
		}

		return false;
	}

	/**
	 * 
	 * solve the sudoku problem
	 * 
	 * @return true if successful
	 */
	public boolean solve_sudoku() {

		return solve_sudoku(0);

	}

	/**
	 * increments the countRecursive so that we know how many times it has been recursed.
	 * Base case: if the position is 81 then we can return true. If the 1d array at the given 
	 * position is 0 then we want to start recursing by using all of the numbers 1-9 if verify 
	 * returns true of the guess then it recurses. If the position doesnt = 0 then we leave that
	 * number and recurse to the next.
	 */
	public boolean solve_sudoku( int position ) {
		if(position == 81) {

			return true;

		}

		if(puzzle1D[position] == empty){

			for(int currentNumberGuess = 1; currentNumberGuess <= 9; currentNumberGuess++) {

				countRecursive++;

				if(is_valid(position, currentNumberGuess)) {

					puzzle1D[position] = currentNumberGuess;
					
					if(solve_sudoku(position + 1)){

						return true;
					
					}
					else{
						puzzle1D[position] = 0;
					}
					
				}

			}

		}
		
		else{
			
			return solve_sudoku(position + 1);
			
		}

		return false;

	}

	/**
	 * Function: toString()
	 * 
	 * has a string that contains all of the hashSets allong with the indexes
	 *
	 * @return a string showing the state of the board
	 *
	 */
	@Override
	public String toString() {

		String result = "";
		int row = 8;
		int threeNumbers = 2;
		int col = 26;

		for(int index = 0; index < puzzle1D.length; index++){

			result = result + puzzle1D[index] + " ";

			if(index == threeNumbers){

				result = result + " | ";
				threeNumbers = threeNumbers + 3;

			}

			if(index == col){

				result = result + "\n";
				col = col + 27;
			}



			if(index == row){

				result = result + "\n";
				row = row + 9;

			}


		}

		return result;


	}

	/**
	 * Checks to see if the Sudoku puzzle is solved by going through each element and
	 * if the is_valid function passes for every single number than the puzzle is solved!
	 * 
	 * 
	 * @return true if a validly solved puzzle
	 */
	public boolean verify() {

		for(int position = 0; position < puzzle1D.length; position++){
			for(int possibleNumber = 1; possibleNumber < 10; possibleNumber++){
				if(is_valid(position, possibleNumber)){
					return true;
				}
			}
		}

		return false;

	}

	/**
	 * Attempt to solve a sudoku by eliminating obviously wrong values. Checks if it is
	 * a valid puzzle.  If the box is 0 then it creates a hashset of 9, then that hashSet
	 * is put into an array. If the puzzle is not solved then it enters a loop then
	 * it checks to see if the length of the set is one if it is then it checks if
	 * it is valid,if so then that value is set to the array. At the end we make set the values 
	 * in the hashset to be in the normal 1d array.
	 * 
	 * 
	 */
	public void solve_by_elimination() {

		for (int individualBox = 0; individualBox < puzzle1D.length; individualBox++) {

			if(puzzle1D[individualBox] > 9){

				System.out.println("Not a vaild sudoku puzzle!");

			}

			if(puzzle1D[individualBox] == empty){

				sudokuPossibleNumbers = new HashSet<Integer>();

				for (int number = 1; number < 10; number++ ) {

					sudokuPossibleNumbers.add(number);

				}

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}

			else{

				int number = puzzle1D[individualBox];

				sudokuPossibleNumbers = new HashSet<Integer>();
				sudokuPossibleNumbers.add(number);

				arr1D.add(individualBox, sudokuPossibleNumbers);

			}
		}


		while (verify()){

			print_possibilities(arr1D);

			for(int index = 0; index < 81; index++) {
				for(int number = 1; number < 10; number++){

					if(arr1D.get(index).size() == 1){

						if(arr1D.get(index).contains(number)){

							//if(is_valid(index, number)){

								prune_box(arr1D, index, number);
								prune_row(arr1D, index, number);
								prune_column(arr1D, index, number);

							//}

						}


						reset1D(index);

					}

				}
			}

		}


	}




	/**
	 * prints out the index of each hashset and the value within.
	 */
	protected static void print_possibilities( ArrayList<HashSet<Integer>> possibilities ) {

		for(int i = 0; i < 81; i++)
		{
			System.out.println("index " + i + ":" + possibilities.get(i));
			//System.out.println("running......");
		}




		/* ****** TA we tried to get the pretty method to work but was unable to ***************
		stringArray = new String[27][9]; //might be wrong numbers

		for(int index = 0; index < 81; index++) {

			int row = index / 9 * 3;
			int col = index % 9;
			HashSet<Integer> ourSet = possibilities.get(index);
			if(ourSet.size() == 1){

				stringArray[row][col] = " ";
				stringArray[row + 1][col] = "" + possibilities.get(index);
				stringArray[row + 2][col] = " ";

			}

			else{
				for(int number = 1; number < 10; number++){

					setTheArrayStrings(possibilities, index, number, stringArray, row, col);

				}
			}

			for(int i = 0; i < stringArray.length; i++){
				System.out.println(Arrays.toString(stringArray[i]));
			}

		} 
		 */

	}

	/**
	 * Given a possibility constraint matrix (81 sets of [1-9]) remove the given number
	 * from every matrix in the given box
	 * 
	 * @param possibilities - 81 sets of [1-9]
	 * @param position      - where the value exists (transform to row,col)
	 * @param value         - the value to prune
	 */
	protected static void prune_box( ArrayList<HashSet<Integer>> possibilities, int position, Integer value ) {

		int row = 0;
		int col = 0;
		int colMath = position % 9;

		if(position < 27){

			row = 0;

		}

		else if(position < 54 || position > 26){

			row = 3;

		}

		else {

			row = 6;

		}

		if ((colMath) <= 2){

			col = 0;

		}
		else if ((colMath) < 6 && colMath > 3){

			col = 3;

		}

		else if((colMath) < 9){

			col = 6;

		}

		int endOfRow = row + 3;
		int endOfCol = col + 3;

		for(int indexRow = row; indexRow < endOfRow; indexRow++){
			for(int indexCol = col; indexCol < endOfCol; indexCol++){

				int number = indexRow * 9 + indexCol;

				if(number >= 81){

					return;

				}

				if(possibilities.get(number).size() == 1){

				}




				else {

					if(possibilities.get(number).contains(value)){

						possibilities.get(number).remove(value);

					}

				}

			}
		}
	}

	/**
	 * See above
	 * @param possibilities
	 * @param position
	 * @param value
	 */
	protected static void prune_column( ArrayList<HashSet<Integer>> possibilities, int position, Integer value ) {

		int col = position % 9;
		int lastNumberInCol = col + 72;

		for(int number = col; number <= lastNumberInCol; number += 9) {

			if(possibilities.get(number).size() == 1){

			}

			else{

				if(possibilities.get(number).contains(value)){

					possibilities.get(number).remove(value);

				}

			}

		}

	}


	/**
	 * see above
	 * @param possibilities
	 * @param position
	 * @param value
	 */
	protected static void prune_row( ArrayList<HashSet<Integer>> possibilities, int position, Integer value ) {

		int row = (position / 9) * 9;
		int lastNumberInRow = row + 9;

		for(int number = row; number < lastNumberInRow; number++){

			if(possibilities.get(number).size() == 1) {

			}


			else{

				if(possibilities.get(number).contains(value)){

					possibilities.get(number).remove(value);

				}

			}

		}

	}

	/**
	 * This creates a 2d Array from a 1D array 
	 * @param 1d array
	 */

	protected void create2D (int[] arr1D) {

		for (int index = 0; index < 81; index++) {

			int row = index / 9;
			int col = index % 9;

			int number = arr1D[index];

			arr2D[row][col] = number;

		}

	}
	/**
	 * This resets to the 1d array values that are solidified in the hashSet
	 * @param index - current position of the solidified hashSet value
	 */

	protected void reset1D(int index){

		for(int numberInSet = 1; numberInSet < 10; numberInSet++){

			if(arr1D.get(index).contains(numberInSet)){

				puzzle1D[index] = numberInSet;

			}

		}

	}

	/**
	 * This resets values in the 1d array to the 2d array
	 * @param current index
	 * @param number is the number to be reset
	 */

	protected void reset1DAnd2DArray(int index, int number){

		puzzle1D[index] = number;
		create2D(puzzle1D);

	}

	/**
	 * 
	 * This method helps print the 27 x 27 matrix,
	 * 
	 * @param possibilities
	 * @param index
	 * @param number
	 * @param stringArray
	 * @param row
	 * @param col
	 */

	protected static void setTheArrayStrings(ArrayList<HashSet<Integer>> possibilities, int index, int number, String[][] stringArray, int row, int col) {

		String previousAvailableNumbers = "";

		if(number < 4){

			if(possibilities.get(index).contains(number)){

				//fill that number into the array
				stringArray[row][col] = previousAvailableNumbers + number + " ";

			}

			else{

				//put a space in that numbers place
				stringArray[row][col] = previousAvailableNumbers + "0";

			}



		}

		else if(number >= 4 && number < 7){

			if(possibilities.get(index).contains(number)){

				//fill that number into the array
				stringArray[row + 1][col] = previousAvailableNumbers + number + " ";

			}

			else{

				//put a space in that numbers place
				stringArray[row + 1][col] = previousAvailableNumbers + "0";

			}


		}

		else if(number >= 7 && number < 10){

			if(possibilities.get(index).contains(number)){


				stringArray[row + 2][col] = previousAvailableNumbers + number + " ";


			}

			else{

				stringArray[row + 2][col] = previousAvailableNumbers + "0";

			}



		}

	}



}
