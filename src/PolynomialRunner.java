import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;
public class PolynomialRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("welcome to the polynomial \"solver\"\nplease enter a polynomial expression following the rules below:\nuse ^ to represent to the power of\nuse x as your only variable\nterms should only be seperated by + or -\ncoefficients and exponents should be integers");
        String expression = s.nextLine().replaceAll(" ","");
        Polynomial p = new Polynomial(expression);
        System.out.print("enter two integer x values and 10 coordinate pairs between those values will be generated with your polynomial\nStart: ");
        int start = Integer.parseInt(s.nextLine());
        System.out.print("End: ");
        int end = Integer.parseInt(s.nextLine());
        System.out.println(p.solveExpressionB(start,end));
        boolean active = true;
        System.out.println("now enter x values to be solved for with your polynomial (invalid value to quit)");
        try
        {
            while(true)
            {
                System.out.print("X: ");
                System.out.println("y: "+ p.solveExpressionA(Integer.parseInt(s.nextLine())));
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println("goodbye");
        }
    }
}
