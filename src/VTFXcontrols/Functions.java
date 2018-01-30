package VTFXcontrols;

/*
  Created by Vern Technologies on 7/19/17. For package VTFXcontrols.
 */

import java.io.File;
import java.util.List;

/**
 * <h1>Functions Class</h1>
 * Provides extra functionality for Strings and Arrays.
 * <p>
 *
 * Multiple methods that evaluate and check for defined conditions.
 *
 * @author Matthew Ashley
 * @since Version 0.0.0.1
 * @version 1.0.0.3
 *
 */

public class Functions {

    /**
     * Checks the format of the inputted String for Integer format.
     * Variable check is the inputted String variable.
     * @param check contains the inputted String.
     * @return Returns true if the inputted String contains numbers in Integer format and false if it doesn't.
     */
    public boolean checkIntegerFormat(String check) {
        boolean checkNumberInput = false;
        char[] numberCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] inputNumberArray = check.toCharArray();
        int z = 0;
        for (char anInputNumberArray : inputNumberArray) {
            for (char aNumberCheck : numberCheck) {
                if (anInputNumberArray == aNumberCheck) { z++; }
            }
        }
        if (z == inputNumberArray.length) { checkNumberInput = true; }
        return checkNumberInput;
    }

    /**
     * Checks the format of the inputted String for currency format.
     * Variable check is the inputted String variable.
     * @param check contains the inputted String.
     * @return Returns true if the inputted String contains currency characters and false if it doesn't.
     */
    public boolean checkCurrencyFormat(String check) {
        boolean checkCurrencyInput = false;
        char[] currencyCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','};
        char[] inputCurrencyArray = check.toCharArray();
        int z = 0;
        for (char anInputCurrencyArray : inputCurrencyArray) {
            for (char aCurrencyCheck : currencyCheck) {
                if (anInputCurrencyArray == aCurrencyCheck) { z++; }
            }
        }
        if (z == inputCurrencyArray.length) { checkCurrencyInput = true; }
        return checkCurrencyInput;
    }

    /**
     * Adds comas to a String variable. Used for
     * setting numbers in String format to the right condition of Double with comas.
     * Variable checkAdd is the inputted String variable.
     * @param checkAdd contains the inputted String.
     * @return Returns the inputted String with comas added.
     * @exception StringInputException if inputted String isn't in
     * the correct format.
     * @throws StringInputException when the inputted String is
     * determined to not be in the correct format.
     */
    public String addComasDouble(String checkAdd) throws StringInputException {
        int characterCheckCount = 0;
        boolean inserted = false;
        boolean checkInput;
        boolean doubleCheck = true;
        StringBuilder buildAdd = new StringBuilder(checkAdd);
        char[] inputCharacterCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
        char[] inputToArray = checkAdd.toCharArray();
        for (char anInputToArray : inputToArray) {
            for (char anInputCharacterCheck : inputCharacterCheck) {
                if (anInputToArray == anInputCharacterCheck) {
                    characterCheckCount++;
                }
            }
        }
        if ((inputToArray[inputToArray.length - 3]) != '.') {
            doubleCheck = false;
        }
        checkInput = characterCheckCount == inputToArray.length && doubleCheck;
        if (checkInput) {
            buildAdd.reverse();
            int q = 0;
            for (int z = 0; z < buildAdd.length(); z++) {
                q = (q + 1);
                if (q == 7 || (inserted && q == 4)) {
                    buildAdd.insert(z, ",");
                    inserted = true;
                    q = 0;
                }
            }
            buildAdd.reverse();
            return String.valueOf(buildAdd);
        } else {
            throw new StringInputException(checkAdd);
        }
    }

    /**
     * Adds comas to a String variable. Used for
     * setting numbers in String format to the right condition of Integer with comas.
     * Variable checkAdd is the inputted String variable.
     * @param checkAdd contains the inputted String.
     * @return Returns the inputted String with comas added.
     * @exception StringInputException if inputted String isn't in
     * the correct format.
     * @throws StringInputException when the inputted String is
     * determined to not be in the correct format.
     */
    public String addComasInteger(String checkAdd) throws StringInputException {
        int characterCheckCount = 0;
        boolean checkInput;
        StringBuilder buildAdd = new StringBuilder(checkAdd);
        char[] inputCharacterCheck = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] inputToArray = checkAdd.toCharArray();
        for (char anInputToArray : inputToArray) {
            for (char anInputCharacterCheck : inputCharacterCheck) {
                if (anInputToArray == anInputCharacterCheck) {
                    characterCheckCount++;
                }
            }
        }
        checkInput = characterCheckCount == inputToArray.length;
        if (checkInput) {
            buildAdd.reverse();
            int q = 0;
            for (int z = 0; z < buildAdd.length(); z++) {
                q++;
                if (q == 4) {
                    buildAdd.insert(z, ",");
                    q = 0;
                }
            }
            buildAdd.reverse();
            return String.valueOf(buildAdd);
        } else {
            throw new StringInputException(checkAdd);
        }
    }

    /**
     * Removes comas from a String variable. Used for
     * setting a String variable to the right conditions for phrasing.
     * Variable checkRemove is the inputted String variable.
     * @param checkRemove contains the inputted String.
     * @return Returns the inputted String with comas removed.
     */
    public String removeComas(String checkRemove) {
        StringBuilder buildRemove = new StringBuilder(checkRemove);
        for (int x = 0; x < buildRemove.length(); x++) {
            if (buildRemove.charAt(x) == ',') { buildRemove.deleteCharAt(x); }
        }
        return  String.valueOf(buildRemove);
    }

    /**
     * Checks the format of an inputted number in String format.
     * Checks for Integer or Double.
     * Will return true if number is in the right format and false for an incorrect format.
     * Variable check is the inputted number in String format.
     * @param check contains the inputted number in String format.
     * @return Returns true if inputted number in String format is in the right format for
     * either Integer or Double.
     */
    public boolean checkNumberFormat (String check) {
        int a, b, c = 0, d = 0, e = 0;
        boolean checkIfNumber = checkCurrencyFormat(check);
        boolean checkNumberFormat = true;
        boolean noDecimalCheck = false;
        char[] numberFormatCheck = {'.'};
        char[] numberFormatCheckArray = check.toCharArray();
        StringBuilder numberCheck = new StringBuilder(check);
        numberCheck.reverse();
        if (checkIfNumber) {
            for (int x = 0; x < numberCheck.length(); x++) {
                if (numberCheck.length() > 2) {
                    if (numberCheck.charAt(2) == '.') {
                        if (numberCheck.charAt(x) == ',') {
                            a = (6 + (d * 4));
                            if (x != a) {
                                checkNumberFormat = false;
                            }
                            d++;
                        }
                    }
                }
                if (numberCheck.charAt(x) == '.') {
                    if (x != 2) {
                        checkNumberFormat = false;
                    }
                }
            }
            for (char aNumberFormatCheckArray : numberFormatCheckArray) {
                for (char aNumberFormatCheck : numberFormatCheck) {
                    if (aNumberFormatCheckArray != aNumberFormatCheck) {
                        c++;
                    }
                }
            }
            if (c == numberFormatCheckArray.length) {
                noDecimalCheck = true;
            }
            if (noDecimalCheck) {
                for (int q = 0; q < numberCheck.length(); q++) {
                    if (numberCheck.charAt(q) == ',') {
                        b = (3 + (e * 4));
                        if (q != b) {
                            checkNumberFormat = false;
                        }
                        e++;
                    }
                }
            }
        } else {
            checkNumberFormat = false;
        }
        return checkNumberFormat;
    }

    /**
     * Adds a character to an inputted String.
     * @param addString contains the inputted String for a character to be added to.
     * @param indexAt is the index at which a character will be added to the inputted String.
     * @param character is the character that is to be added to the inputted String.
     * @return Returns the inputted String with the specified character added at the defined index.
     */
    public String addStringCharacter(String addString, int indexAt, char character) {
        StringBuilder adding = new StringBuilder(addString);
        adding.insert(indexAt, character);
        return String.valueOf(adding);
    }

    /**
     * Removes the file extension from a file path.
     * Sample: .xlsx, .docx
     * Variable locationChoose is the inputted file path.
     * @param locationChoose contains the inputted file path.
     * @return Returns the inputted file path without file extension in String format.
     */
    public String fileRemoveExtension(File locationChoose) {
        int backslashLocation = 0;
        StringBuilder chosenFilePath = new StringBuilder();
        StringBuilder fileLocation = new StringBuilder(String.valueOf(locationChoose));
        if (locationChoose != null) {
            fileLocation.reverse();
            char[] locationArray = String.valueOf(fileLocation).toCharArray();
            for (int x = 0; x < locationArray.length; x++) {
                if ((locationArray[x] == '/' || locationArray[x] == '\\') && backslashLocation == 0) {
                    backslashLocation = x;
                }
            }
            for (int y = backslashLocation + 1; y < locationArray.length; y++) {
                chosenFilePath.append(locationArray[y]);
            }
            chosenFilePath.reverse();
        }
        return String.valueOf(chosenFilePath);
    }

    /**
     * Sorts the inputted array of numbers into ascending order.
     * Variable array is a array of numbers.
     * @param ascend contains the array of numbers.
     * @return Returns the inputted array sorted into ascending order.
     */
    public double[] ascending(double ascend []) {
        double temp;
        for (int i = 0; i < ascend.length; i++) {
            for (int j = i + 1; j < ascend.length; j++) {
                if (ascend[i] > ascend[j]) {
                    temp = ascend[i];
                    ascend[i] = ascend[j];
                    ascend[j] = temp;
                }
            }
        }
        return ascend;
    }

    /**
     * Sorts the inputted list of numbers into ascending order.
     * Variable ascend is a list of numbers.
     * @param ascend contains the list of numbers.
     * @return Returns the inputted List sorted into ascending order.
     */
    public List<Double> ascending(List<Double> ascend) {
        double temp;
        for (int i = 0; i < ascend.size(); i++) {
            for (int j = i + 1; j < ascend.size(); j++) {
                if (ascend.get(i) > ascend.get(j)) {
                    temp = ascend.get(i);
                    ascend.set(i, ascend.get(j));
                    ascend.set(j, temp);
                }
            }
        }
        return ascend;
    }

    /**
     * Sorts the inputted array of numbers into descending order.
     * Variable array is a array of numbers.
     * @param descend contains the array of numbers.
     * @return Returns the inputted array sorted into descending order.
     */
    public double[] descending(double descend []) {
        double temp;
        for (int i = 0; i < descend.length; i++) {
            for (int j = i + 1; j < descend.length; j++) {
                if (descend[i] < descend[j]) {
                    temp = descend[i];
                    descend[i] = descend[j];
                    descend[j] = temp;  }
            }
        }
        return descend;
    }

    /**
     * Sorts the inputted list of numbers into descending order.
     * Variable array is a list of numbers.
     * @param descend contains the list of numbers.
     * @return Returns the inputted List sorted into descending order.
     */
    public List<Double> descending(List<Double> descend) {
        double temp;
        for (int i = 0; i < descend.size(); i++) {
            for (int j = i + 1; j < descend.size(); j++) {
                if (descend.get(i) < descend.get(j)) {
                    temp = descend.get(i);
                    descend.set(i, descend.get(j));
                    descend.set(j, temp);
                }
            }
        }
        return descend;
    }

    /**
     * Calculates the sum of the numbers in the list.
     * Variable array is a list of numbers.
     * @param numbers contains the list of numbers.
     * @return Returns the sum of the elements of the inputted array as a Double variable.
     */
    public double sum(double numbers []) {
        double sum = 0;
        for (double number : numbers) {
            sum = (sum + number);
        }
        return sum;
    }

    /**
     * Calculates the sum of the numbers in the list.
     * Variable array is a list of numbers.
     * @param numbers contains the list of numbers.
     * @return Returns the sum of the elements of the inputted List as a Double variable.
     */
    public double sum(List<Double> numbers) {
        double sum = 0;
        for (Double number : numbers) {
            sum = (sum + number);
        }
        return sum;
    }

    /**
     * Sorts the inputted array of numbers into Odd and Even list.
     * Variable array is a array of numbers.
     * @param sortEO contains the array of numbers.
     * @return Returns two arrays sorted from the elements of the inputted array into even and odd arrays as one Object.
     */
    public Object[] evenAndOdd(double sortEO []) {
        int a = 0, b = 0, j = 0, l = 0;
        for (double anArray : sortEO) {
            if (anArray % 2 == 0.0) {
                a++;
            } else {
                b++;
            }
        }
        double[] Jeff = new double[b]; //Odd array!
        double[] Bill = new double[a]; //Even array!
        for (double anArray : sortEO) {
            if (anArray % 2 == 0) {
                Bill[j] = anArray;
                j++;
            } else {
                Jeff[l] = anArray;
                l++;
            }
        }
        return new Object[]{Bill, Jeff};
    }

    /**
     * Sorts the inputted array of numbers into Odd and Even list.
     * Variable array is a array of numbers.
     * @param sortEO contains the arrat of numbers.
     * @return Returns two arrays sorted from the elements of the inputted List into even and odd arrays as one Object.
     */
    public Object[] evenAndOdd(List<Double> sortEO) {
        int a = 0, b = 0, j = 0, l = 0;
        for (double anArray : sortEO) {
            if (anArray % 2 == 0.0) {
                a++;
            } else {
                b++;
            }
        }
        double[] Jeff = new double[b]; // Odd array!
        double[] Bill = new double[a]; //Even array!
        for (double anArray : sortEO) {
            if (anArray % 2 == 0) {
                Bill[j] = anArray;
                j++;
            } else {
                Jeff[l] = anArray;
                l++;
            }
        }
        return new Object[]{Bill, Jeff};
    }

    /**
     * Takes in a Integer variable and returns that number spelled out in
     * String format.
     * Variable number is the inputted Integer number
     * @param number contains the Integer number to be spelled out.
     * @return Returns a String equalling the inputted Integer spelled out.
     */
    public String intSpell(int number) {
        int a = 0, b, c = 0, jump = 0, temp = number;
        StringBuilder numberSpell = new StringBuilder();
        if (temp == 0) { numberSpell.append("Zero"); }
        while (number > 0) {
            number = (number / 10);
            a++;}
        int [] array = new int[a];
        b = (array.length - 1);
        for (int i = 0; 0 < temp; i++) {
            array [i] = (temp % 10);
            temp = (temp / 10);  }
        for (int anArray : array) {
            if ((b == 1) || (b == 4) || (b == 7)) {
                if (b == 1) {
                    c = 0;
                }
                if (b == 4) {
                    c = 3;
                }
                if (b == 7) {
                    c = 6;
                }
                switch (array[b]) {
                    case 1:
                        jump = 1;
                        switch (array[c]) {
                            case 0:
                                numberSpell.append(" Ten ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 1:
                                numberSpell.append(" Eleven ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 2:
                                numberSpell.append(" Twelve ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 3:
                                numberSpell.append(" Thirteen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 4:
                                numberSpell.append(" Fourteen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 5:
                                numberSpell.append(" Fifteen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 6:
                                numberSpell.append(" Sixteen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 7:
                                numberSpell.append(" Seventeen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 8:
                                numberSpell.append(" Eighteen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                            case 9:
                                numberSpell.append(" Nineteen ");
                                if (b == 4) {
                                    numberSpell.append("Thousand");
                                }
                                if (b == 7) {
                                    numberSpell.append("Million");
                                }
                                break;
                        }
                        break;
                    case 2:
                        numberSpell.append(" Twenty");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 3:
                        numberSpell.append(" Thirty");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 4:
                        numberSpell.append(" Forty");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 5:
                        numberSpell.append(" Fifty");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 6:
                        numberSpell.append(" Sixty");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 7:
                        numberSpell.append(" Seventy");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 8:
                        numberSpell.append(" Eighty");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                    case 9:
                        numberSpell.append(" Ninety");
                        if ((array[c] == 0) && (b == 4)) {
                            numberSpell.append(" Thousand");
                        }
                        if ((array[c] == 0) && (b == 7)) {
                            numberSpell.append(" Million");
                        }
                        break;
                }
            }
            if ((b == 0) || (b == 2) || (b == 3) || (b == 5) || (b == 6) || (b == 8) || (b == 9)) {
                if ((b == 2) || (b == 5)) {
                    jump = 0;
                }
                if (jump == 1) {
                } else if (array[b] == 1) {
                    numberSpell.append(" One ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 2) {
                    numberSpell.append(" Two ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 3) {
                    numberSpell.append(" Three ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 4) {
                    numberSpell.append(" Four ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 5) {
                    numberSpell.append(" Five ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 6) {
                    numberSpell.append(" Six ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 7) {
                    numberSpell.append(" Seven ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 8) {
                    numberSpell.append(" Eight ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                } else if (array[b] == 9) {
                    numberSpell.append(" Nine ");
                    if (b == 3) {
                        numberSpell.append("Thousand");
                    }
                    if ((b == 2) || (b == 5)) {
                        numberSpell.append("Hundred");
                        if ((b == 5) && (array[4] == 0) && (array[3] == 0)) {
                            numberSpell.append(" Thousand");
                        }
                    }
                    if (b == 6) {
                        numberSpell.append("Million");
                    }
                    if (b == 8) {
                        numberSpell.append("Hundred");
                        if ((array[7] == 0) && (array[6] == 0)) {
                            numberSpell.append(" Million");
                        }
                    }
                    if (b == 9) {
                        numberSpell.append("Billion");
                    }
                }
            }
            c = 0;
            b--;
        }
        return String.valueOf(numberSpell);
    }

}
