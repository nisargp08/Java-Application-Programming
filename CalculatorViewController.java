
/**
 * Filename 	: 	CalculatorViewController.java
 * Author 		: 	Nisarg Patel - 040859993
 * Course 		: 	CST8221 - Java Application Programming
 * Lab Section 	: 	303
 * Assignment 	: 	1 
 * Part 		: 	1
 * Date 		: 	2018-10-17
 * Professor 	: 	Daniel Cormier
 * Purpose 		: 	To get familiar with the java swing concept by creating a simple calculator GUI
 * Class list 	:	Controller(Private class) implements ActionListener
 */

package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Description : This class is responsible for building the entire application's
 * GUI.It creates the calculator using multiple layouts and panels. A function
 * is used to create multiple buttons which takes five arguments.
 * 
 * @author : Nisarg Patel
 * @version : 1.0
 * @see : java.awt,javax.swing
 * @since : 1.8.0_181
 */
public class CalculatorViewController extends JPanel {

	/**
	 * @value :As CalculatorViewController is a serialized class variable
	 *        serialVersionUID with a long datatype has been defined
	 */
	private static final long serialVersionUID = 1L;
	private JTextField display1; // the calculator display1 field reference
	private JTextField display2; // the calculator display2 field reference
	private JLabel error; // the mode/error display label reference
	private JButton dotButton; // the decimal point (dot) button reference
	/* Array to hold all the keypad values */
	private final String keyPad[] = { "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "\u00B1",
			"+" };

	CalculatorViewController() {
		/* INITIAL DECLARATION */
		/* Calculator Model Class object */
		CalculatorModel cmObj = new CalculatorModel();
		/*
		 * This object will be used to set the actionCommand when the any button is
		 * clicked
		 */
		Controller controlObj = new Controller(cmObj);
		/*
		 * topPanel represents the top part of the calculator which consists of
		 * following components Error label,Display textfield,Backspace Button,Checkbox
		 * and radioButtons.Color of the panel is set to yellow here
		 */
		JPanel topPanel = new JPanel();
		topPanel.setBackground(Color.YELLOW);
		/* This panel is used to display the two textFields */
		JPanel displayPanel = new JPanel();
		/* This panel is used to hold radiobutton and checkbox */
		JPanel selectionPanel = new JPanel();
		/* This panel will hold entire bottom part of the calculator */
		JPanel bottomPanel = new JPanel();
		/* This panel will hold the keypad */
		JPanel keypadPanel = new JPanel();
		keypadPanel.setBackground(Color.WHITE);
		/* This panel will hold both cButton and keypad panel */
		JPanel midPanel = new JPanel();
		midPanel.setBackground(Color.BLACK);
		/*
		 * Creating BackSpace button here and setting the text to arrow using unicode
		 * \u21da : Leftwards triple arrow
		 */
		JButton backspaceButton = new JButton("\u21da");
		/* Text in error label is set to 'F' here */
		error = new JLabel("F");
		/* Checkbox is assigned with the text 'int' */
		JCheckBox checkBox = new JCheckBox("Int");
		/* THree radioButtons */
		JRadioButton onePrecision = new JRadioButton(".0");
		JRadioButton twoPrecision = new JRadioButton(".00");
		JRadioButton sciPrecision = new JRadioButton("Sci");
		/* To include checkbox and radiobutton into a box */
		Box selectionBox = Box.createHorizontalBox();
		/* To group checkbox and radiobutton together */
		ButtonGroup selectionGroup = new ButtonGroup();
		/* Equal Button */
		JButton equalButtonOne = new JButton("=");
		JButton equalButtonTwo = new JButton("=");

		/* Setting the layout of all the panels here */
		setLayout(new BorderLayout());
		topPanel.setLayout(new BorderLayout());
		displayPanel.setLayout(new GridLayout(2, 0));
		selectionPanel.setLayout(new BorderLayout());
		bottomPanel.setLayout(new BorderLayout());
		midPanel.setLayout(new BorderLayout());
		keypadPanel.setLayout(new GridLayout(4, 4, 2, 2));

		/* Setting the border of all the panels,buttons,labels,etc here */
		setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK));
		displayPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK));
		backspaceButton.setBorder(BorderFactory.createMatteBorder(1, 5, 5, 1, Color.BLACK));
		error.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.BLACK));
		selectionPanel.setBorder(BorderFactory.createEmptyBorder(0, 1, 5, 1));
		keypadPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		equalButtonOne.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.BLACK));
		equalButtonTwo.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));

		/* Size of the label is set to (46,55) */
		error.setPreferredSize(new Dimension(46, 55));
		/* Background of the label 'error' is set to Yellow */
		error.setBackground(Color.YELLOW);
		/* Making error label visible here */
		error.setOpaque(true);
		/* Setting the size of 'F' to 20 here */
		Font errorFont = new Font(error.getFont().getFontName(), error.getFont().getStyle(), 20);
		error.setFont(errorFont);
		/* Aligning the letter 'F' in the middle here */
		error.setHorizontalAlignment(error.CENTER);

		/*
		 * Initializing the display1 TextField here and setting following things Size :
		 * 16 Columns and Height to 30 ;Background : White ; Editable : No ;
		 * TextAlignment : Right ;Border : No Border(So that there is no space between
		 * two displays
		 */
		display1 = new JTextField();
		display1.setPreferredSize(new Dimension(16, 30));
		display1.setBackground(Color.WHITE);
		display1.setEditable(false);
		display1.setHorizontalAlignment(JTextField.RIGHT);
		display1.setBorder(BorderFactory.createEmptyBorder());
		/*
		 * Initializing the display2 TextField here and setting following things Size :
		 * 16 Columns and Height to 30 ; Background : White ; Editable : No ;
		 * TextAlignment : Right ; Default Text : 0.0 ; Border : No Border(So that there
		 * is no space between two displays
		 */
		display2 = new JTextField();
		display2.setPreferredSize(new Dimension(16, 30));
		display2.setBackground(Color.WHITE);
		display2.setEditable(false);
		display2.setHorizontalAlignment(JTextField.RIGHT);
		display2.setText("0.0");
		display2.setBorder(BorderFactory.createEmptyBorder());
		/* Adding Both displays to Grid Layout */
		displayPanel.add(display1);
		displayPanel.add(display2);

		/*
		 * Assigning Arrow in unicode format to backspace button here and setting some
		 * properties
		 */
		backspaceButton.setPreferredSize(new Dimension(45, 55));
		/* Making backspaceButton transparent here */
		backspaceButton.setContentAreaFilled(false);
		/* Tooltip of the button is assigned here */
		backspaceButton.setToolTipText("Backspace (Alt-B)");
		/*
		 * Setting mnemonic for the button so that it also reacts when ALT-B is pressed
		 */
		backspaceButton.setMnemonic('b');
		// backspaceButton.setActionCommand("\u21da");
		/*
		 * Setting actionlistener here so that the event when button is pressed can be
		 * captured
		 */
		backspaceButton.addActionListener(controlObj);

		/* Assigning Black to selectionPanel */
		selectionPanel.setBackground(Color.BLACK);

		/* Setting checkbox attributes */
		checkBox.setPreferredSize(new Dimension(40, 0));
		checkBox.setBackground(Color.GREEN);

		/*
		 * Setting actionlistener here for checkbox and radiobutton so that the event
		 * when button is pressed can be captured
		 */
		checkBox.addActionListener(controlObj);
		onePrecision.addActionListener(controlObj);
		twoPrecision.addActionListener(controlObj);
		sciPrecision.addActionListener(controlObj);

		/* Including checkbox and radioButton in a button group */
		selectionGroup.add(checkBox);
		selectionGroup.add(onePrecision);
		selectionGroup.add(twoPrecision);
		selectionGroup.add(sciPrecision);

		/* Setting RadioButton color to Yellow */
		onePrecision.setBackground(Color.YELLOW);
		twoPrecision.setBackground(Color.YELLOW);
		sciPrecision.setBackground(Color.YELLOW);
		/* By Default selection */
		twoPrecision.setSelected(true);

		/* Adding Radiobuttons and Checkbox to Box */
		selectionBox.setBackground(Color.BLACK);
		selectionBox.add(checkBox);
		selectionBox.add(Box.createHorizontalGlue());
		selectionBox.add(onePrecision);
		selectionBox.add(twoPrecision);
		selectionBox.add(sciPrecision);

		/* Adding Box to selectionPanel */
		selectionPanel.add(selectionBox);

		/* Moving to the Middle part of the panel,Top part has been concluded */

		/* Setting attributes for equalButton */
		equalButtonOne.setBackground(Color.YELLOW);
		equalButtonOne.setForeground(Color.BLACK);
		equalButtonOne.setPreferredSize(new Dimension(46, 55));
		Font eqOne = new Font(equalButtonOne.getFont().getFontName(), equalButtonOne.getFont().getStyle(), 20);
		equalButtonOne.setFont(eqOne);
		equalButtonOne.addActionListener(controlObj);

		equalButtonTwo.setBackground(Color.YELLOW);
		equalButtonTwo.setForeground(Color.BLACK);
		equalButtonTwo.setPreferredSize(new Dimension(46, 55));
		Font eqTwo = new Font(equalButtonTwo.getFont().getFontName(), equalButtonTwo.getFont().getStyle(), 20);
		equalButtonTwo.setFont(eqTwo);
		equalButtonTwo.addActionListener(controlObj);
		/*
		 * Creating Two C buttons here which will be at the top and bottom of mid Panel
		 */
		JButton cButtonOne = new JButton("C");
		cButtonOne.setBackground(Color.RED);
		cButtonOne.setForeground(Color.BLACK);
		cButtonOne.setPreferredSize(new Dimension(0, 45));
		Font cOne = new Font(cButtonOne.getFont().getFontName(), cButtonOne.getFont().getStyle(), 20);
		cButtonOne.setFont(cOne);
		cButtonOne.addActionListener(controlObj);

		JButton cButtonTwo = new JButton("C");
		cButtonTwo.setBackground(Color.RED);
		cButtonTwo.setForeground(Color.BLACK);
		cButtonTwo.setPreferredSize(new Dimension(0, 45));
		Font cTwo = new Font(cButtonTwo.getFont().getFontName(), cButtonTwo.getFont().getStyle(), 20);
		cButtonTwo.setFont(cTwo);
		cButtonTwo.addActionListener(controlObj);
		/*
		 * Calling createButton Function to create keypad button and storing them in
		 * keypadPanel
		 */
		for (int i = 0; i < keyPad.length; i++) {
			/* Temp button to hold the newly created button */
			JButton tempButton = new JButton();
			/*
			 * If the button is '.' then storing the reference in dotButton variable and
			 * then adding it to the panel
			 */
			if (keyPad[i] == ".") {
				dotButton = createButton(keyPad[i], keyPad[i], Color.BLACK, Color.BLUE, controlObj);
				dotButton.addActionListener(controlObj);
				keypadPanel.add(dotButton);

			}
			/*
			 * If the button is '/,*,-,+' then background color is set to CYAN and then
			 * added to the panel
			 */
			else if (keyPad[i] == "/" || keyPad[i] == "*" || keyPad[i] == "-" || keyPad[i] == "+") {
				tempButton = createButton(keyPad[i], keyPad[i], Color.BLACK, Color.CYAN, controlObj);
				tempButton.addActionListener(controlObj);
				keypadPanel.add(tempButton);
			}
			/*
			 * for the +ontopof- the background color is set to PINK and then added to the
			 * panel
			 */
			else if (keyPad[i] == "\u00B1") {
				tempButton = createButton(keyPad[i], keyPad[i], Color.BLACK, Color.PINK, controlObj);
				tempButton.addActionListener(controlObj);
				keypadPanel.add(tempButton);

			}
			/* Else when it is a number button (1,2,3...etc) */
			else {
				tempButton = createButton(keyPad[i], keyPad[i], Color.BLACK, Color.BLUE, controlObj);
				tempButton.addActionListener(controlObj);
				keypadPanel.add(tempButton);
			}
		}

		/* Adding created panels to the topPanel */
		topPanel.add(error, BorderLayout.WEST);
		topPanel.add(displayPanel, BorderLayout.CENTER);
		topPanel.add(backspaceButton, BorderLayout.EAST);
		topPanel.add(selectionPanel, BorderLayout.SOUTH);

		/* Adding Two cButtons and keyPanel to the midPanel */
		midPanel.add(cButtonOne, BorderLayout.NORTH);
		midPanel.add(keypadPanel, BorderLayout.CENTER);
		midPanel.add(cButtonTwo, BorderLayout.SOUTH);

		/* Adding created panels to the bottomPanel */
		bottomPanel.add(equalButtonOne, BorderLayout.WEST);
		bottomPanel.add(equalButtonTwo, BorderLayout.EAST);
		bottomPanel.add(midPanel, BorderLayout.CENTER);

		/* Adding the topPanel and bottomPanel to the entirePanel */
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
	}

	/**
	 * Description : This function will create a JButton and will assign attributes
	 * to that button according to the arguments and then will return the created
	 * button.Purpose of this function is minimized the process of creating multiple
	 * manually by the programmer
	 * 
	 * @param : String - text ; String - ac ; Color - fg ; Color - bg ;
	 *        ActionListener - handler
	 * @return : JButton - calcButton
	 */
	private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {
		/* Calculator button is created here with the text */
		JButton calcButton = new JButton(text);
		/* Background and Foreground color of the button gets set here */
		calcButton.setBackground(bg);
		calcButton.setForeground(fg);
		/* Action command for the button gets set here if Action command is not null */
		if (ac != null) {
			calcButton.setActionCommand(ac);
		}
		/*
		 * Setting the size font to 20 here and keeping the fontname and style the same
		 */
		Font buttonFont = new Font(calcButton.getFont().getFontName(), calcButton.getFont().getStyle(), 20);
		calcButton.setFont(buttonFont);
		/* Registering the handler as an ActionListener for calcButton */
		calcButton.addActionListener(handler);
		/* Returning the reference of the created button (calcButton) */
		return calcButton;
	}

	/**
	 * Description : This class is responsible for displaying the clicked button on
	 * the calculator on display2.
	 * 
	 * @author : Nisarg Patel
	 * @version : 1.0
	 * @see : java.awt,javax.swing
	 * @since : 1.8.0_181
	 */

	/* Private inner class controller is implemented here */
	private class Controller implements ActionListener {
		/* Object of Calculator Model Class */
		CalculatorModel modelObj;
		/* to check if the backspace key is on or off */
		Boolean backspaceOnFlag = false;
		/* Initiaal stage is set to false for errors */
		Boolean errorFlag = false;
		/* used to decide if the application is in overlap state or not */
		Boolean overlapFlag = false;

		/**
		 * Default constructor for the class.Setting the calculator model object to
		 * modelObj
		 * 
		 * @param cm - CalculatorModel instance
		 */
		public Controller(CalculatorModel cm) {
			modelObj = cm;
		}

		/**
		 * Reverts back to base view according to user selection of the mode i.e Int or
		 * Float
		 */
		public void baseView() {
			if (modelObj.integerMode()) {
				display1.setText(" ");
				display2.setText("0");
			} else {
				display1.setText(" ");
				display2.setText("0.0");
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			/* Regex to match digits [0-9] */
			 String digitRegex = "^\\d+$";
			/* If user clicked on number then numbers are displayed on the textfield */
			 if (e.getActionCommand().matches(digitRegex)) {
				if (modelObj.getError() != true) {
					/*
					 * Checking if the user input is greater than the actual display fields if yes
					 * then setting the text to empty string and clearing the display
					 */
					if (display1.getColumns() < display1.getText().length()
							&& display2.getColumns() < display2.getText().length()) {
						display2.setText("");
						modelObj.clearFields();
					} else {
						if (modelObj.getCurrentState() == CalculatorModel.FIRSTOPERAND) {
							if (overlapFlag == true) {
								overlapFlag = false;
								display2.setText(e.getActionCommand());
							} else
								display2.setText(display2.getText().concat(e.getActionCommand()));
						}

						else if (modelObj.getCurrentState() == CalculatorModel.SECONDOPERAND) {
							if (overlapFlag == true) {
								overlapFlag = false;
								display2.setText(e.getActionCommand());
							} else
								display2.setText(display2.getText().concat(e.getActionCommand()));
						}

						else if (modelObj.getCurrentState() == CalculatorModel.FINALVAL) {
							display1.setText("");
							display2.setText(e.getActionCommand());
						} else {
							/* If none of the conditions are satisfied than set the error true */
							modelObj.setError(true);
						}
					} /* else ends */
					/*
					 * After completing the above case we are checking if any errors then set
					 * errorFlag to true
					 */
					if (modelObj.getError() == true)
						errorFlag = true;
				} /* modelObj error check ends here */

				/* Setting backspace flag ON here */
				backspaceOnFlag = true;

			} /* Regex match ends here */
			else if (e.getActionCommand() == "+" || e.getActionCommand() == "-" || e.getActionCommand() == "/"
					|| e.getActionCommand() == "*") {
				if (modelObj.getError() != true) {
					if (modelObj.getCurrentState() == CalculatorModel.FIRSTOPERAND) {
						overlapFlag = true;
						modelObj.setFirstOperand(display2.getText());
						modelObj.setArithmeticOperation(e.getActionCommand());
						display1.setText(display2.getText().concat(e.getActionCommand()));
					} else if (modelObj.getCurrentState() == CalculatorModel.SECONDOPERAND) {
						if (overlapFlag == true) {
							modelObj.setArithmeticOperation(e.getActionCommand());
							display1.setText(display2.getText().concat(e.getActionCommand()));
						}
					} else if (modelObj.getCurrentState() == CalculatorModel.FINALVAL) {
						overlapFlag = true;
						modelObj.setArithmeticOperation(e.getActionCommand());
						display1.setText(display2.getText().concat(e.getActionCommand()));
						display2.getText();
					} else
						/* If none of the conditions are satisfied than set the error true */
						modelObj.setError(true);
					/*
					 * After completing the above case we are checking if any errors then set
					 * errorFlag to true
					 */
					if (modelObj.getError() == true)
						errorFlag = true;
				} /* modelObj error check ends here */

				/* Setting backspace flag ON here */
				backspaceOnFlag = true;

			} /* Else if for [+*-/] ends here */
			else if (e.getActionCommand() == "=") {
				if (modelObj.getError() != true) {
					if (modelObj.getCurrentState() == CalculatorModel.SECONDOPERAND) {
						String temp = modelObj.getOperationResult();
						modelObj.setSecondOperand(display2.getText());
						/* Setting error if returned string is null */
						if (temp == null) {
							modelObj.setError(true);
						}
						/* If not empty then clearing display1 and displaying the result in display2 */
						else {
							display1.setText("");
							display2.setText(temp);
						}
					} else if (modelObj.getCurrentState() == CalculatorModel.FINALVAL) {
						String temp = modelObj.getOperationResult();
						display2.setText(temp);
					} else
						/* If none of the conditions are satisfied than set the error true */
						modelObj.setError(true);
				}
				if (modelObj.getError() == true)
					errorFlag = true;

				/* Setting backspace flag ON here */
				backspaceOnFlag = true;

			} /* '=' action elseif ends */
			else if (e.getActionCommand() == "C") {
				if (modelObj.getError() != true) {
					baseView();
					modelObj.clearFields();
				}
			}
		}/*
			 * 'C' elseif ends }/* Action Performed Method ends
			 */

	}/* Controller Class ends */
}/* CalculatorViewController ends */
