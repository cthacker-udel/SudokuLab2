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
	
	/*
	protected int[] getRegion(int RegionNbr) {
		int[] reg = new int[super.getLatinSquare().length];
		int i = (RegionNbr % iSqrtSize) * iSqrtSize;
		int j = (RegionNbr / iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;
		int iCnt = 0;
		
		for (int row = j; row < jMax; row++) {
			for(int col = i; col < iMax; col++) {
				reg[iCnt] = super.getLatinSquare()[row][col];
				iCnt++;
			}
		}
		return reg;
	}
	*/
	
	protected int[] getRegion(int Row, int Col) {
		//return getRegion(Row - (Row % this.iSqrtSize) + (Col / this.iSqrtSize));
	}
	
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
