import java.util.*;
public class Polynomial {
    private String expression;
    private ArrayList<String> terms;
    private ArrayList<Double> coefficients;
    private ArrayList<Integer> exponents;
    public Polynomial(String e)
    {
        expression = e.replaceAll(" ","");
        terms = parseTerms();
        coefficients = parseCoefficients();
        exponents = parseExponents();
    }
    private ArrayList<String> parseTerms()
    {
        ArrayList<String> t = new ArrayList<String>();
        for (int i = 0; i<expression.length(); i++)
        {

        }
        return t;
    }
    private ArrayList<Double> parseCoefficients()
    {
        ArrayList<Double> c = new ArrayList<Double>();
        for(String term: terms)
        {

        }
        return c;
    }
    private ArrayList<Integer> parseExponents()
    {
       ArrayList<Integer> e = new ArrayList<Integer>();
       for(String term: terms)
       {

       }
       return e;
    }
    public String solveExpressionA(int x)
    {
        int out = 0;
        for(int i = 0; i<coefficients.size();i++);
        {

        }
        return String.valueOf(out);
    }
    public String solveExpressionB(int start, int end)
    {
        String out = "";
        for (int i = start; i<=end; i+=(end-start)/10)
        {

        }
        return out;
    }
}
