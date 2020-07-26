package sudoku;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class Sudoku {
    
     
    public static void main(String[] args) {
        int [] [] sudoku = {
            {1, 4, 7, 0, 0, 0, 0, 0, 3},
            {2, 5, 0, 0, 0, 1, 0, 0, 0},
            {3, 0, 9, 0, 0, 0, 0, 0, 0},
            {0, 8, 0, 0, 2, 0, 0, 0, 4},
            {0, 0, 0, 4, 1, 0, 0, 2, 0},
            {9, 0, 0, 0, 0, 0, 6, 0, 0},
            {0, 0, 3, 0, 0, 0, 0, 0, 9},
            {4, 0, 0, 0, 0, 2, 0, 0, 0},
            {0, 0, 1, 0, 0, 8, 0, 0, 7},
            };
            
            printSudoku(sudoku);
        
            Scanner in = new Scanner(System.in);  
            System.out.print("\n Enter the row postion : ");  
            int row = in.nextInt();  
          
        
            System.out.print("\n Enter the column postion : ");  
            int column = in.nextInt();  
          
           
            System.out.print("\n Enter the value to be place from 1 to 9 : ");  
            int digit = in.nextInt();  
        
        if(checkValidity(sudoku, row, column, digit))
            System.out.println("The Number is Valid ");
        else 
            System.out.println("The Number is Not Valid");
    }
    public static void printSudoku(int [][] sudoku){
    for (int[] row : sudoku) {
      System.out.print("[");
      for (int y : row) {
        System.out.print(y + ", ");
      }
      System.out.println("]");
    }
  }
    
    //Method to check if the new row is valid 
    public static boolean validateNewRow(int row, int [][] newSudoku){
        Set<Integer> rowValues = new HashSet<Integer>();
        
        //get the specified row from the given sudoku
        int specificRow [] = newSudoku[row];
        //iterate through the row to validate the values
        for(int value : specificRow){
             //check if the value is from 0 to 9
            //0 is considered as valid because it represents blank space
            if (value < 0 || value > 9){
                System.out.println( "Invalid value" );
                return false;
            }
            
            else if (value != 0){
                if(rowValues.contains(value)){
                    return false;
                }else{
                    rowValues.add(value);
                }
            }    
        }
        return true;
    }
    
    
    //Method to check if the New column is valid 
    public static boolean validateNewColumn(int col, int [][] newSudoku){
        Set<Integer>columnValues = new HashSet<Integer>(); 
        //getting values of just one column from newSudoku 
        Integer[] specificColumn = new Integer[9]; 
        for(int i=0; i<9; i++){
            specificColumn[i] = newSudoku[i][col];
        }
        for(int i=0;i<specificColumn.length;i++){
            // Checking for values outside 0 and 9; 
          // 0 is considered valid because it denotes an empty cell.
            if (newSudoku[i][col] < 0 || newSudoku[i][col] > 9){
                System.out.println( "Invalid value" );
                return false;
            }
            //check if the any value is repeated in a column
            else if(newSudoku[i][col]!=0){
                if(columnValues.contains(newSudoku[i][col])){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean validateSubBlock(int [][] newSudoku){
        //checking new formed sudoku's all 9 blocks
        //as subblocks are considered to be  3X3
        for (int row = 0 ; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                Set<Integer>set = new HashSet<Integer>(); 
                for(int r = row; r < row+3; r++) {
                    for(int c= col; c < col+3; c++){
                        // Checking for values outside 0 and 9; 
                        //0 is considered valid because it denotes an empty cell.
                        if (newSudoku[r][c] < 0 || newSudoku[r][c] > 9){
                            System.out.println( "Invalid value" );
                            return false;
                        }
                        // Checking for repeated values.
                        
                        if(set.contains(newSudoku[r][c]))
                            return false;
                        else{
                            if(newSudoku[r][c]!=0)
                                set.add(newSudoku[r][c]);
                        }
                        
                    }
                }
            }   
        }
        return true;
    }
    
    public static boolean checkValidity(int[][] sudoku, int row, int column, int digit){
        if(digit==0){
            System.out.println("Please Enter a digit from 1-9 ");
            return false;
        }
        //make a new sudoku block by putting the given value in original sudoku block
        sudoku [row][column] = digit;
        //check if new sudoku' block's given row is valid
        if(validateNewRow(row, sudoku)){
            //check if new sudoku block's given column is valid
            if(validateNewColumn(row, sudoku)){
                // Check if the all sub blocks from sudoku are valid
                if(validateSubBlock(sudoku)){
                    return true;
                }
                else 
                    return false;
            }
            else 
                return false;
        }
        else 
            return false;
            //r
    }
   
    
}
