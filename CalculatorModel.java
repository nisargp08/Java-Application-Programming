package calculator;

public class CalculatorModel {
	/* Holds three calculation parameters i.e 1+1 */
	private String firstOperand;
	private String secondOperand;
	private String operation;
	/* To record the errorState and currentState of the calculator*/
	private boolean errorState;
	/* Used to display certain error messages in the calculator*/
	private String errMsg = null;
	private int currentState;
	/* mode of the calculator Float or Integer*/
	private int mode;
	/* Variable for when precision is selected in the calculator */
	private int precisionMode;
	/** {@value} : constant value of firstoperand*/
	public static final int FIRSTOPERAND= 1;
	/** {@value} : constant value of secondoperand*/
	public static final int SECONDOPERAND= 2;
	/** {@value} : constant value of secondoperand*/
	public static final int INTEGER = 3;
	/** {@value} : constatnt value of state after calculation of firstOperand and secondOperand */
	public static final int FINALVAL = 4;
	/** {@value} : constant value for single precision calculations : .0 */
	public static final int SINGLEPRECISION = 5;
	/** {@value} : constant value for single precision calculations : .00 */
	public static final int DOUBLEPRECISION = 6;
	/** {@value} : constant value for Scientific calculations*/
	public static final int SCICALCULATION = 7;
	/** {@value}* : constant for integer mode*/
	public static final int INT = 8;
	/**
	 *  Default constructor for the model class
	 * @return 
	 */
	public CalculatorModel(){
		firstOperand = null;
		secondOperand = null;
		operation = null;
		errorState = false;
		precisionMode = SINGLEPRECISION;
		currentState = FIRSTOPERAND;
	}
	/** To clear the value in the text field display1
	 */
	public void clearFields(){
		firstOperand = null;
		secondOperand = null;
		operation = null;
		errorState = false;
		currentState = FIRSTOPERAND;
	}
	/**Set method for the first operand of string type
	 * @param  fo : String
	 * */
	public void setFirstOperand(String fo) {
		firstOperand = fo;
		currentState = FIRSTOPERAND;
	}
	/**Set method for the second operand of string type
	 * @param  so : String
	 * */
	public void setSecondOperand(String so) {
		secondOperand = so;
		currentState = SECONDOPERAND;
	}
	/**Set method to set the arithmetic operation
	 * @param  ao : String
	 * */
	public void setArithmeticOperation(String ao) {
		operation = ao;
		currentState = SECONDOPERAND;
	}
	/** Set method to set the operational mode 
	 * @param  mode : int
	 * */
	public void setOperationMode(int mode) {
		this.mode = mode;
		
	}
	/** Set method to set floating point precision
	 * 
	 * @param precision : int
	 */
	public void setFLoatingPrecision(int precision) {
		precisionMode = precision;
	}
	/** Get method to get floating point precision
	 * 
	 * @param none
	 */
	public int getFloatingPrecision() {
		return precisionMode;
	}
	/** Get method to return the result from the operation in formatted form based on 
	 *  current precision.The result is implemented in virtual field
	 */
	public String getOperationResult() {
		String opResult = calculate() ;
		if(opResult == null)
			clearFields();
		else
			firstOperand = opResult;
		return opResult;
	}
	/** Set method to set error when the calculation is not valid
	 * 
	 * @param errorState : boolean
	 */
	public void setError(boolean errorState) {
		/* When the result of the calculation is Nan,Infinity,Illeagal Operands or operation mode errorState is set to true*/
		if(Double.isInfinite(FIRSTOPERAND) || Double.isInfinite(SECONDOPERAND) || Double.isNaN(FIRSTOPERAND) || Double.isNaN(SECONDOPERAND)
				|| firstOperand == null || secondOperand == null || operation.isEmpty())
			errorState = true;
		this.errorState = errorState;
	}
	/** Get method to Get errorstate
	 * 
	 * @return errorState : boolean
	 */
	public boolean getError() {
		return errorState;
	}
	/** Private class calculates the result based on the current operand,arithmetic operation and operational mode(Integer or Float)
	 * 
	 * @return
	 */
	private String calculate() {
		if(mode == INTEGER) {
			int intOperand1 = 0;
			int intOperand2 = 0;
			try {
				/* Returns an Integer object of first and second Operand so that we can use local variable operand1,2 to make changes*/
				intOperand1 = Integer.valueOf(firstOperand);
				intOperand2 = Integer.valueOf(secondOperand);
			}
			catch(Exception e) {
				errorState = true;
				return null;
			}
			/* When user divides any number with zero */
			if(intOperand2 == 0 && operation == "/") {
				errMsg = "Cannot divide by zero";
				/* Clearing all the fields and changing the errorstate to true*/
				errorState = true;
				clearFields();
				return null;
			}
			/* When user divides 0 by 0*/
			if(intOperand1 == 0 && operation == "/" && intOperand2 == 0) {
				errMsg = "Result is undefined";
				/* Clearing all the fields and changing the errorstate to true*/
				errorState = true;
				clearFields();
				return null;
			}
			currentState = FINALVAL;
			/* Calculation according to the arithmetic operation */
			/* Returning formatted string with the ouptup of the calculation using String.format*/
			if(operation == "/") {
				return String.format("%d", intOperand1 / intOperand2);
			}
			if(operation == "+") {
				return String.format("%d", intOperand1 + intOperand2);
			}
			if(operation == "-") {
				return String.format("%d", intOperand1 - intOperand2);
			}
			if(operation == "*") {
				return String.format("%d", intOperand1 * intOperand2);
			}
		}/* Integer mode if ends*/
		
		/*Floating point mode begins*/
		else {
			double flOperand1 = 0.0;
			double flOperand2 = 0.0;
			try {
				/* Returns an Double object of first and second Operand so that we can use local variable flOperand1,2 to make changes*/
				flOperand1 = Double.valueOf(firstOperand);
				flOperand2 = Double.valueOf(secondOperand);
			}
			catch(Exception e) {
				errorState = true;
				return null;
			}
			/* When user divides any number with zero */
			if(flOperand1 == 0 && operation == "/") {
				errMsg = "Cannot divide by zero";
				/* Clearing all the fields and changing the errorstate to true*/
				errorState = true;
				clearFields();
				return null;
			}
			/* When user divides 0 by 0*/
			if(flOperand1 == 0 && operation == "/" && flOperand2 == 0) {
				errMsg = "Result is undefined";
				/* Clearing all the fields and changing the errorstate to true*/
				errorState = true;
				clearFields();
				return null;
			}
			currentState = FINALVAL;
			/* Calculation for single point precision */
			if(precisionMode == SINGLEPRECISION) {
				/* Arithmetic operations for single point precision*/
				if(operation == "/") {
					return String.format("%.1f", flOperand1 / flOperand2);
				}
				if(operation == "+") {
					return String.format("%.1f", flOperand1 + flOperand2);
				}
				if(operation == "-") {
					return String.format("%.1f", flOperand1 - flOperand2);
				}
				if(operation == "*") {
					return String.format("%.1f", flOperand1 * flOperand2);
				}
				}
			
			/* Calculation for double point precision */
			if(precisionMode == DOUBLEPRECISION) {
				/* Arithmetic operations for single point precision*/
				if(operation == "/") {
					return String.format("%.2f", flOperand1 / flOperand2);
				}
				if(operation == "+") {
					return String.format("%.2f", flOperand1 + flOperand2);
				}
				if(operation == "-") {
					return String.format("%.2f", flOperand1 - flOperand2);
				}
				if(operation == "*") {
					return String.format("%.2f", flOperand1 * flOperand2);
				}
				}
			
			/* Calculation for scientific mode calculations */
			if(precisionMode == SCICALCULATION) {
				/* Arithmetic operations for scientific mode calculations*/
				if(operation == "/") {
					return String.format("%E", flOperand1 / flOperand2);
				}
				if(operation == "+") {
					return String.format("%E", flOperand1 + flOperand2);
				}
				if(operation == "-") {
					return String.format("%E", flOperand1 - flOperand2);
				}
				if(operation == "*") {
					return String.format("%E", flOperand1 * flOperand2);
				}
				}
			
		}/* Floating point mode ends*/
		
		/* When none of the condition is satisfied it returns null*/
		return null;
		
	}/* Calculate ends*/
	/**
	 * returns currentState variable
	 * @return currentState : int
	 */
	public int getCurrentState() {
		return currentState;
	}
	/**
	 * returns mode as INT
	 * @return mode : boolean
	 */
	public boolean integerMode() {
		return mode == INT;
	}
}/* Class ends*/
