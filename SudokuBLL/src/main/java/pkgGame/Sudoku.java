package pkgGame;

import pkgHelper.LatinSquare;
// make a subclass from LatinSquare
public class Sudoku extends LatinSquare {
	private int iSqrtSize;
	private int iSize;
	public Sudoku() {
		super();
	}
	public Sudoku(int iSize) throws java.lang.Exception {
        this.iSize = iSize;
        double sq = Math.sqrt(iSize); 
        if ((sq - Math.floor(sq)) == 0) {
            iSqrtSize = (int)sq;
        } else {
            throw new Exception("The size for sudoku input is wrong");
        }

    }
	public Sudoku(int[][] puzzle) throws Exception{
		super(puzzle);
		int iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		}
		else {
			throw new Exception("Invalid Size");
		}
	}
	
	protected int[][] getPuzzle(){
		return super.getLatinSquare();
	}
	
	// get the region of the sqrtsize * sqrtsize area
	protected int[] getRegion(int RegionNbr) {//start of getRegion(RegionNbr)
		int[] reg = new int[super.getLatinSquare().length];//array to store region values
		int i = (RegionNbr % iSqrtSize) * iSqrtSize;//starting column
		int j = (RegionNbr / iSqrtSize) * iSqrtSize;//starting row
		int iMax = i + iSqrtSize;//max column size
		int jMax = j + iSqrtSize;//max row size
		int iCnt = 0;//count for region
		
		for (int row = j; row < jMax; row++) {//for loop to go through the rows
			for(int col = i; col < iMax; col++) {//nested for loop to go through the columns
				reg[iCnt] = super.getLatinSquare()[row][col];//save the value to reg[]
				iCnt++;//move position in array reg[]
			}//end column for loop
		}//end row for loop
		return reg;//return array reg
	}// end of getRegion(regionNbr)
	
	
	protected int[] getRegion(int Row, int Col) {//method to get values in region based off row and column values
		return getRegion(Row - (Row % this.iSqrtSize) + (Col / this.iSqrtSize));// call the getRegion(regionNbr)
	}//end of getRegion( int Row, int Col)
	
	@Override
	protected boolean hasDuplicates() {
		if(super.hasDuplicates()) {
			return true;
		}
		for (int i = 0; i < this.getLatinSquare().length; i++) {
			if (hasDuplicates(getRegion(i)))
				return true;
		}
		return false;
	}
	
	@Override
	protected boolean hasAllValues() {
		
		for (int i = 1; i < this.getLatinSquare().length; i++) {

			if (!hasAllValues(getRow(0), getRegion(i))) {
				return false;
			}
		}
		return true;
	}
	// check the conditions of Sudoku 
	protected boolean isSudoku(){
		if(hasDuplicates()) {
			return false;
		}
		if(!hasAllValues()) {
			return false;
		}
		if(super.ContainsZero()) {//check the number has zero
			return false;
		}
		return true;
		
	}
	// check the conditions of a part of Sudoku
	protected boolean isPartialSudoku() {
		if(hasDuplicates()) {
			return false;
		}
		if(super.ContainsZero()) {
			return true;
		}
		return false;
	}
	// Put in a row, column, and value and check if the value is valid. Value is valid only if it does not already exist in the Row, Column, and corresponding region
	protected boolean isValidValue(int Row, int Col, int Value){
		if(super.doesElementExist(super.getRow(Row), Value)) {  //checks if Value already exists in Row
			return false;
		}
		else if(super.doesElementExist(super.getColumn(Col), Value)) { //checks if Value already exists in Col
			return false;
		}
		else if(super.doesElementExist(this.getRegion(Row,Col),Value)) { //checks if Value exists in the region given by Row and Col
			return false;
		}
		else {
			return true; //Return true if the Value does not exist in Row, Col, and the region
		}
	}
	
	
}
