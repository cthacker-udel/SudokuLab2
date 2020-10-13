package pkgHelper;
import pkgEnum.ePuzzleViolation;

public class PuzzleViolation extends LatinSquare{
	private int iValue;
	private ePuzzleViolation ePuzzleViolation;
	
	public PuzzleViolation() {
		super();
	}
	public PuzzleViolation(ePuzzleViolation ePuzzleViolation, int iValue){
		try {
			super.isLatinSquare();
		}
		catch(ePuzzleViolation ePuzzle) {
			throw new Exception(ePuzzle);
		}
	}
	public ePuzzleViolation getePuzzleViolation() {
		return this.ePuzzleViolation;
	}
	
	public int getiValue() {
		return this.iValue;
	}
}
