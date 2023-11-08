import java.lang.reflect.Array;
import java.util.*;
public class Polynomial {
    private String expression;
    private ArrayList<String> terms;
    private ArrayList<Integer> signs;
    private ArrayList<Double> coefficients;
    private ArrayList<Integer> exponents;

    /**
     * Constructor for the polynomial class, creates ArrayLists representing the terms,
     * signs for each term, coefficient of each term, and exponent in each term of the inputted expression
     * @param e represents an expression given by the user
     */
    public Polynomial(String e)
    {
        expression = e.replaceAll(" ","").toLowerCase();
        terms = parseTerms();
        signs = parseSigns();
        coefficients = parseCoefficients();
        exponents = parseExponents();
    }

    /**
     * The closerString method of the Polynomial class, accepts three strings, and finds the which of the first two strings occurs earlier in the third string
     * @param a represents a string that the method is comparing the index of to b
     * @param b represents a string that the method is comparing the index of to a
     * @param text represents a string that the method is looking through for strings a and b
     * @return returns which string appears first in the word
     */
    private String closerString(String a, String b, String text)
    {
        if (!text.contains(a))
        {
            return b;
        }
        else if (!text.contains(b))
        {
            return a;
        }
        else if (text.indexOf(a)>text.indexOf(b))
        {
            return b;
        }
        return a;

    }

    /**
     * The round2 method of the Polynomial class, rounds the double x to 2 decimal places
     * @param x represents a double that the method will round to 2 decimal places
     * @return returns the double x rounded to 2 decimal places
     */
    private double round2(double x)
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
        ArrayList<String> t = new ArrayList<String>();
        String tempExpression = expression;
        while(tempExpression.length()!=0)
        {
            if(!tempExpression.contains("+") && !tempExpression.contains("-"))
            {
                t.add(tempExpression);
                tempExpression = "";
                continue;
            }
            t.add(tempExpression.substring(0,tempExpression.indexOf(closerString("+","-",tempExpression))+1));
            tempExpression = tempExpression.substring(tempExpression.indexOf(closerString("+","-",tempExpression))+1);
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
        boolean validExponent = true;
        for(int i = 0; i<expression.length();i++)
        {
            if((expression.charAt(i)=='^'))
            {
                if(!validCharacters.substring(0,10).contains(String.valueOf(expression.charAt(i + 1))))
                {
                    return false;
                }
            }
        }

        for(int i =0; i<expression.length();i++) {
            if (!validCharacters.contains(String.valueOf(expression.charAt(i))))
            {
                return false;
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
    private ArrayList<Integer> parseSigns()
    {
        ArrayList<Integer> s = new ArrayList<Integer>();
        if(expression.charAt(0) == '-')
            s.add(-1);
        else
            s.add(1);
        for (int i = 0; i<terms.size()-1; i++)
        {
           if (terms.get(i).charAt(terms.get(i).length()-1)=='-')
           {
               s.add(-1);
           }
           else
           {
               s.add(1);
           }
        }
        return s;
    }

    /**
     * the parseCoefficients method of the Polynomial class, returns a list of doubles representing the coefficients for each term
     * of the given expression, each coefficient is found by searching for the number occurring before a "x",
     * if none is found the coefficient will be 1
     * @return a list of doubles representing the coefficient of each term of the expression
     */
    private ArrayList<Double> parseCoefficients()
    {
        ArrayList<Double> c = new ArrayList<Double>();
        for (String term : terms) {
            if (term.equals("-")) {
            } else if (term.indexOf("x") == 0) {
                c.add(1.0);
            } else if (!term.contains("x")) {
                if (term.indexOf("+") == term.indexOf("-")) {
                    c.add(Double.parseDouble(term));
                } else {
                    c.add(Double.parseDouble(term.substring(0, term.length() - 1)));
                }
            } else {
                c.add(Double.parseDouble(term.substring(0, term.indexOf("x"))));
            }
        }
        return c;
    }

    /**
     * the parseExponents method of the Polynomial class, returns a list of integers representing each term's exponent,
     * each exponent is found by finding the number occurring after a "^", if no "^" is found, if a "x" is found the
     * exponent will be 1, if no "^" or "x" is found the exponent will be 0
     * @return a list of integers representing each term's exponent
     */
    private ArrayList<Integer> parseExponents()
    {
       ArrayList<Integer> e = new ArrayList<Integer>();
        for (String term : terms) {
            if (term.equals("-")) {
            } else if (!term.contains("^")) {
                if (!term.contains("x")) {
                    e.add(0);
                } else {
                    e.add(1);
                }
            } else {
                if (term.indexOf("+") == term.indexOf("-")) {
                    e.add(Integer.parseInt(term.substring(term.indexOf("^") + 1)));
                } else {
                    e.add(Integer.parseInt(term.substring(term.indexOf("^") + 1, term.indexOf((String.valueOf(closerString("+", "-", term)))))));
                }
            }
        }
       return e;
    }

    /**
     * the solveExpressionA method of the Polynomial class, returns the respective y value for a given x value when plugged into
     * the previously given expression
     * @param x represents the x value to solve a respective y value from
     * @return a string representing the y value corresponding to the given x value in the user's polynomial
     */
    public String solveExpressionA(double x)
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
        return String.valueOf(out);
    }

    /**
     * the solveExpressionB method of the Polynomial class, returns a set of 10 coordinate pairs that represents points along the polynomial between the start and
     * end value given by the user, each coordinate pair will be equally apart from each other and will be found using the solveExpressionA method
     * @param start represents the start of the section of the polynomial to find x and y values from
     * @param end represents the end of the section of the polynomial to find x and y values from
     * @return a string of coordinate pairs representing coordinates from the start to end value
     */
    public String solveExpressionB(int start, int end)
    {
        String out = "";
        double increment = (end-start)/9.0;
        for (int i = 0; i<10; i++)
        {
            out+="("+ round2(start+i*increment) +", "+ round2(Double.parseDouble(solveExpressionA(start+i*increment)))+")"+"\n";
        }
        return out;
    }
}
