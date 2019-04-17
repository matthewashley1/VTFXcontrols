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
 Created by Vern Technologies on 9/21/18. For package VTFXcontrols.
 */

package VTFXcontrols;

/**
 * <h1>AngleException Class</h1>
 * Thrown to indicate that the inputted Double to a method isn't within the ranges of +-360.0.
 * <p>
 * Typically used for the <code>setDirectionAngle(Double degrees)</code> method in the SwitchToggle class.
 *
 * @author Matthew Ashley
 * @since Version 0.0.0.1
 * @version 2.0.0.1
 *
 */

public class AngleException extends Exception {

    /**
     * Constructs an <code>AngleException</code> with the specified detailed message.
     * @param exception the detail message.
     */
    AngleException(String exception) {
        super(exception + " Input for degrees isn't within the allowable ranges of 0.0 to 360.0.");
    }

    /**
     * Constructs an <code>AngleException</code> with no detailed message.
     */
    public AngleException() {
        super();
    }
}
