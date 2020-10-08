package pkgGame;

import pkgHelper.LatinSquare;

public class Sudoku extends LatinSquare {
	int iSqrtSize;
	public Sudoku() {
		super();
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
	
	protected boolean isSudoku(){
		if(hasDuplicates()) {
			return false;
		}
		if(!hasAllValues()) {
			return false;
		}
		if(super.ContainsZero()) {
			return false;
		}
		return true;
		
	}
	
	protected boolean isPartialSudoku() {
		if(hasDuplicates()) {
			return false;
		}
		if(super.ContainsZero()) {
			return true;
		}
		return false;
	}
	
	protected boolean isValidValue(int Row, int Col, int Value){
		if(super.doesElementExist(super.getRow(Row), Value)) {
			return false;
		}
		if(super.doesElementExist(super.getColumn(Col), Value)) {
			return false;
		}
		if(super.doesElementExist(this.getRegion(Row,Col),Value)) {
			return false;
		}
		return true;
	}
	
	
}
