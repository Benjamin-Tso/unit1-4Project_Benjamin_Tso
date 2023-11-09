import java.util.Scanner;
import java.io.*;
public class PolynomialRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Instructions i = new Instructions(1);
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_CYAN = "\\u001B[36m";
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
        boolean validStart = false;
        int start = 0;
        while(!validStart) {
            try {
                start = Integer.parseInt(s.nextLine());
                validStart = true;
            }
            catch(NumberFormatException e)
            {
                System.out.print("invalid\nStart: ");
            }
        }
        System.out.print("End: ");
        boolean validEnd = false;
        int end = 0;
        while(!validEnd) {
            try {
                end = Integer.parseInt(s.nextLine());
                validEnd = true;
            }
            catch(NumberFormatException e)
            {
                System.out.print("invalid\nEnd: ");
            }
        }
        System.out.println(ANSI_CYAN + p.solveExpressionB(start,end) + ANSI_RESET);
        i.setStep(4);
        System.out.println(i);
        boolean validX = true;
        while(validX)
            {
                System.out.print("X: ");
                try {
                    System.out.println("y: " + p.solveExpressionA(Double.parseDouble(s.nextLine())));
                }
                catch(NumberFormatException e)
                {
                    validX=false;
                }
            }
        System.out.println("goodbye");
        }
    }

