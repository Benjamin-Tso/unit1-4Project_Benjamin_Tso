import java.lang.reflect.Array;
import java.util.*;
public class Polynomial {
    private String expression;
    private ArrayList<String> terms;
    private ArrayList<Integer> signs;
    private ArrayList<Double> coefficients;
    private ArrayList<Integer> exponents;
    public Polynomial(String e)
    {
        expression = e.replaceAll(" ","").toLowerCase();
        terms = parseTerms();
        signs = parseSigns();
        coefficients = parseCoefficients();
        exponents = parseExponents();
    }
    private String closerString(String a, String b, String word)
    {
        if (word.indexOf(a)==-1)
        {
            return b;
        }
        else if (word.indexOf(b)==-1)
        {
            return a;
        }
        else if (word.indexOf(a)>word.indexOf(b))
        {
            return b;
        }
        return a;
    }
    private double round2(double x)
    {
        return (int)(x*100)/100.0;
    }
    private ArrayList<String> parseTerms()
    {
        ArrayList<String> t = new ArrayList<String>();
        String tempExpression = expression;
        while(tempExpression.length()!=0)
        {
            if(tempExpression.indexOf("+")==-1 && tempExpression.indexOf("-")==-1)
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
    public static boolean validPolynomial(String a)
    {
        String validCharacters = "1234567890x^+-";
        if(a.equals(""))
            return false;
        for(int i =0; i<a.length();i++) {
            if (!validCharacters.contains(a.charAt(i) + ""))
            {
                return false;
            }
        }
        return true;
    }
    private ArrayList<Integer> parseSigns()
    {
        ArrayList<Integer> s = new ArrayList<Integer>();
        if(expression.substring(0,1)=="-")
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
    private ArrayList<Double> parseCoefficients()
    {
        ArrayList<Double> c = new ArrayList<Double>();
        for(int i =0; i<terms.size();i++)
        {
            if(terms.get(i).equals("-")) {}
            else if(terms.get(i).indexOf("x")==0)
            {
                c.add(1.0);
            }
            else if(terms.get(i).indexOf("x")==-1)
            {
                if (terms.get(i).indexOf("+")==terms.get(i).indexOf("-"))
                {
                    c.add(Double.parseDouble(terms.get(i)));
                }
                else
                {
                    c.add(Double.parseDouble(terms.get(i).substring(0,terms.get(i).length()-1)));
                }
            }
            else
            {
                c.add(Double.parseDouble(terms.get(i).substring(0, terms.get(i).indexOf("x"))));
            }
        }
        return c;
    }
    private ArrayList<Integer> parseExponents()
    {
       ArrayList<Integer> e = new ArrayList<Integer>();
       for(int i =0; i<terms.size();i++)
       {
           if(terms.get(i).equals("-")) {}
           else if(terms.get(i).indexOf("^")==-1)
           {
               if(terms.get(i).indexOf("x")==-1)
               {
                   e.add(0);
               }
               else
               {
                   e.add(1);
               }
           }
           else
           {
               if(terms.get(i).indexOf("+")==terms.get(i).indexOf("-"))
               {
                   e.add(Integer.parseInt(terms.get(i).substring(terms.get(i).indexOf("^") + 1)));
               }
               else
               {
                   e.add(Integer.parseInt(terms.get(i).substring(terms.get(i).indexOf("^")+1, terms.get(i).indexOf((String.valueOf(closerString("+","-",terms.get(i))))))));
               }
           }


       }
       return e;
    }
    public String solveExpressionA(double x)
    {
        if(terms.get(0).equals("-"))
        {
            terms.remove(0);
            signs.remove(0);
        }
        int out = 0;
        for(int i = 0; i<terms.size();i++)
        {
            out+=Math.pow(x,exponents.get(i))*signs.get(i)*coefficients.get(i);
        }
        return String.valueOf(out);
    }
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
