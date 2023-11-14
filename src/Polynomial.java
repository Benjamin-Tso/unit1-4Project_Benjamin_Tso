import java.util.ArrayList;
/**
 * The polynomial class represents a polynomial expression, a polynomial expression contains an independent variable
 * typically represented with x, and optional coefficients and exponents on the x(s), and can have several x's that
 * are added/subtracted from each other
 */
public class Polynomial {
    //instance variables, will not be changed upon initialization, so all are final
    private final String expression;
    private final ArrayList<String> terms;
    private final ArrayList<Integer> signs;
    private final ArrayList<Double> coefficients;
    private final ArrayList<Integer> exponents;

    /**
     * Constructor for the polynomial class, creates ArrayLists representing the terms,
     * signs for each term, coefficient of each term, and exponent in each term of the inputted expression
     * @param expression represents an expression given by the user
     */
    public Polynomial(String expression)
    {
        this.expression = expression.replaceAll(" ","").toLowerCase();
        terms = parseTerms();
        signs = parseSigns();
        coefficients = parseCoefficients();
        exponents = parseExponents();
    }

    /**
     * The closerString method of the Polynomial class, finds whether + or - is closer in the string
     * @param text represents a string that the method is looking through for strings a and b
     * @return returns which string appears first in the word
     */
    //needed to know when to end current term, which will be at the closest +/-
    private String closerString(String text)
    {
        if (!text.contains("+"))
        {
            return "-";
        }
        else if (!text.contains("-"))
        {
            return "+";
        }
        else if (text.indexOf("+")>text.indexOf("-"))
        {
            return "-";
        }
        return "+";

    }

    /**
     * The roundTo2DecPlace method of the Polynomial class, rounds the double x to 2 decimal places
     * @param x represents a double that the method will round to 2 decimal places
     * @return returns the double x rounded to 2 decimal places
     */
    private double roundTo2DecPlace(double x)
    {
        return Math.round(x*100)/100.0;
    }

    /**
     * The parseTerms method of the Polynomial class, returns a list of strings ending at the next operation symbol (+/-), each string represents
     * a term in the expression, the operation for the next term will be contained in the previous term
     * @return a list of terms for future methods to further parse information from
     */
    private ArrayList<String> parseTerms()
    {
        ArrayList<String> t = new ArrayList<>();
        String tempExpression = expression;
        while(tempExpression.length()!=0)
        {
            if(!tempExpression.contains("+") && !tempExpression.contains("-"))
            {
                t.add(tempExpression);
                tempExpression = "";
                continue;
            }
            t.add(tempExpression.substring(0,tempExpression.indexOf(closerString(tempExpression))+1));
            tempExpression = tempExpression.substring(tempExpression.indexOf(closerString(tempExpression))+1);
        }
        return t;
    }

    /**
     * the validPolynomial method of the Polynomial class, returns a boolean representing whether the given expression is valid
     * by making sure it only contains numbers, x, and + or -, also checks if all exponents are positive
     * @param expression represents an expression to check for validity
     * @return a boolean representing if the expression follows the rules
     */
    public static boolean validPolynomial(String expression)
    {
        if(expression.equals("")) {
            return false;
        }
        String validCharacters = "1234567890x^+-.";

        for(int i =0; i<expression.length();i++) {
            if (!validCharacters.contains(String.valueOf(expression.charAt(i))))
            {
                return false;
            }
            if((expression.charAt(i)=='^'))
            {
                if(!validCharacters.substring(0,10).contains(String.valueOf(expression.charAt(i + 1))))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * the parseSigns method of the Polynomial class, returns a list of -1s and/or 1s representing if
     * each term is to be added or subtracted from the previous term, when calculating, the order of elements in
     * signs matches their corresponding term
     * @return a list of 1s and -1s representing the operation to be done between terms
     */
    //signs represent whether a term is to be added or subtracted
    private ArrayList<Integer> parseSigns()
    {
        ArrayList<Integer> signs = new ArrayList<>();
        if(expression.charAt(0) == '-')
            signs.add(-1);
        else
            signs.add(1);
        for (int i = 0; i<terms.size()-1; i++)
        {
           if (terms.get(i).charAt(terms.get(i).length()-1)=='-')
           {
               signs.add(-1);
           }
           else
           {
               signs.add(1);
           }
        }
        return signs;
    }

    /**
     * the parseCoefficients method of the Polynomial class, returns a list of doubles representing the coefficients for each term
     * of the given expression, each coefficient is found by searching for the number occurring before a "x",
     * if none is found the coefficient will be 1
     * @return a list of doubles representing the coefficient of each term of the expression
     */
    private ArrayList<Double> parseCoefficients()
    {
        ArrayList<Double> coefficients = new ArrayList<>();
        for (String term : terms) {
            if (term.equals("-")) {
            } else if (term.indexOf("x") == 0) {
                coefficients.add(1.0);
            } else if (!term.contains("x")) {
                if (term.indexOf("+") == term.indexOf("-")) {
                    coefficients.add(Double.parseDouble(term));
                } else {
                    coefficients.add(Double.parseDouble(term.substring(0, term.length() - 1)));
                }
            } else {
                coefficients.add(Double.parseDouble(term.substring(0, term.indexOf("x"))));
            }
        }
        return coefficients;
    }

    /**
     * the parseExponents method of the Polynomial class, returns a list of integers representing each term's exponent,
     * each exponent is found by finding the number occurring after a "^", if no "^" is found, if a "x" is found the
     * exponent will be 1, if no "^" or "x" is found the exponent will be 0
     * @return a list of integers representing each term's exponent
     */
    private ArrayList<Integer> parseExponents()
    {
       ArrayList<Integer> exponents = new ArrayList<>();
        for (String term : terms) {
            if (term.equals("-")) {
            } else if (!term.contains("^")) {
                if (!term.contains("x")) {
                    exponents.add(0);
                } else {
                    exponents.add(1);
                }
            } else {
                if (term.indexOf("+") == term.indexOf("-")) {
                    exponents.add(Integer.parseInt(term.substring(term.indexOf("^") + 1)));
                } else {
                    exponents.add(Integer.parseInt(term.substring(term.indexOf("^") + 1, term.indexOf((closerString(term))))));
                }
            }
        }
       return exponents;
    }

    /**
     * the solveExpressionA method of the Polynomial class, returns the respective y value for a given x value when plugged into
     * the previously given expression
     * @param x represents the x value to solve a respective y value from
     * @return a string representing the y value corresponding to the given x value in the user's polynomial
     */
    //solves for individual coordinate pairs
    public double solveExpressionA(double x)
    {
        if(terms.get(0).equals("-"))
        {
            terms.remove(0);
            signs.remove(0);
        }
        double out = 0;
        for(int i = 0; i<terms.size();i++)
        {
            out+=Math.pow(x,exponents.get(i))*signs.get(i)*coefficients.get(i);
        }
        return roundTo2DecPlace(out);
    }

    /**
     * the solveExpressionB method of the Polynomial class, returns a set of 10 coordinate pairs that represents points along the polynomial between the start and
     * end value given by the user, each coordinate pair will be equally apart from each other and will be found using the solveExpressionA method
     * @param start represents the start of the section of the polynomial to find x and y values from
     * @param end represents the end of the section of the polynomial to find x and y values from
     * @return a string of coordinate pairs representing coordinates from the start to end value
     */
    //solves for a set of 10 coordinate pairs
    public String solveExpressionB(int start, int end)
    {
        String out = "";
        double increment = (end-start)/9.0;
        for (int i = 0; i<10; i++)
        {
            out+="("+ roundTo2DecPlace(start+i*increment) +", "+ solveExpressionA(start+i*increment)+")"+"\n";
        }
        return out;
    }

}

