import java.util.Scanner;
public class PolynomialRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Instructions i = new Instructions(1);
        System.out.println(i);
        String expression = s.nextLine().replaceAll(" ","").toLowerCase();
        while(!Polynomial.validPolynomial(expression))
        {
            i.setStep(2);
            System.out.println(i);
            expression = s.nextLine().replaceAll(" ","").toLowerCase();
        }
        Polynomial p = new Polynomial(expression);
        i.setStep(3);
        System.out.print(i);
        int start = Integer.parseInt(s.nextLine());
        System.out.print("End: ");
        int end = Integer.parseInt(s.nextLine());
        System.out.println(p.solveExpressionB(start,end));
        boolean active = true;
        i.setStep(4);
        System.out.println(i);
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
