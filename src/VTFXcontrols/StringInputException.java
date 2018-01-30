/*
 * Copyright 2017-2022 VernTechnologies LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * @version 1.0.0.3
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
