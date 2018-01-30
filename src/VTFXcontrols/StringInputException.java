/*
  Created by Vern Technologies on 7/23/17. For package VTFXcontrols.
 */

package VTFXcontrols;

/**
 * <h1>StringInputException Class</h1>
 * Thrown to indicate that the inputted String to a method is not in the right format.
 * <p>
 * Typically used for the <code>addComasDouble()</code> or <code>addComasInteger()</code>
 * method in the Functions class.
 *
 * @author Matthew Ashley
 * @since Version 0.0.0.1
 *
 */

public class StringInputException extends Exception {

    /**
     * Constructs an <code>StringInputException</code> with the
     * specified detail message.
     *
     * @param exception the detail message.
     */
    public StringInputException(String exception) {

        super(exception + " Inputted String not in the right condition");
    }

    /**
     * Constructs an <code>StringInputException</code> with no
     * detail message.
     */
    public StringInputException() {super();}

}
