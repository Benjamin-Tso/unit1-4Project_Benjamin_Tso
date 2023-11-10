import java.util.Scanner;
public class PolynomialRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Instructions i = new Instructions(1);
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        System.out.println(i);
        String expression = s.nextLine().replaceAll(" ","").toLowerCase();
        while(!Polynomial.validPolynomial(expression))
        {
            i.setStep(2);
            System.out.println(ANSI_RED_BACKGROUND + i + ANSI_RESET);
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
                System.out.print(ANSI_RED + "X: " + ANSI_RESET);
                try {
                    System.out.println(ANSI_PURPLE + "y: " + p.solveExpressionA(Double.parseDouble(s.nextLine())) + ANSI_RESET);
                }
                catch(NumberFormatException e)
                {
                    validX=false;
                    s.close();
                }
            }
        System.out.println(ANSI_GREEN_BACKGROUND + ANSI_BLACK + "goodbye" + ANSI_RESET);

        }
    }

