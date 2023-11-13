import java.util.Scanner;
public class PolynomialRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Instructions i = new Instructions(1);
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        System.out.print(ANSI_BLUE + i + ANSI_RESET);
        String expression = s.nextLine().replaceAll(" ","").toLowerCase();
        while(!Polynomial.validPolynomial(expression))
        {
            i.setStep(2);
            System.out.println(ANSI_RED_BACKGROUND + i + ANSI_RESET);
            expression = s.nextLine().replaceAll(" ","").toLowerCase();
        }
        Polynomial p = new Polynomial(expression);
        i.setStep(3);
        System.out.print(ANSI_BLUE + i + ANSI_RESET);
        boolean validStart = false;
        int start = 0;
        while(!validStart) {
            try {
                start = Integer.parseInt(s.nextLine());
                validStart = true;
            }
            catch(NumberFormatException e)
            {
                System.out.print(ANSI_RED_BACKGROUND + "invalid" + ANSI_RESET + ANSI_BLUE + "\nStart: " + ANSI_RESET);
            }
        }
        System.out.print(ANSI_BLUE + "End: " + ANSI_RESET);
        boolean validEnd = false;
        int end = 0;
        while(!validEnd) {
            try {
                end = Integer.parseInt(s.nextLine());
                validEnd = true;
            }
            catch(NumberFormatException e)
            {
                System.out.print(ANSI_RED_BACKGROUND + "invalid" + ANSI_RESET + ANSI_BLUE + "\nEnd: " + ANSI_RESET);
            }
        }
        System.out.println("\n" + ANSI_CYAN + p.solveExpressionB(start,end) + ANSI_RESET);
        i.setStep(4);
        System.out.println(ANSI_BLUE + i + ANSI_RESET);
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
                }
            }
        if(s.nextLine().equalsIgnoreCase("slope")|| s.nextLine().equalsIgnoreCase("roc")|| s.nextLine().equalsIgnoreCase("rate of change")){
            String start2,end2;
            i.setStep(5);
            System.out.println(i);
            while(!s.nextLine().equalsIgnoreCase("q"))
            {
                System.out.print(ANSI_BLUE + "start: " + ANSI_RESET);
                start2=s.nextLine();
                while(!Polynomial.validStartEnd(start2)){
                    System.out.println(ANSI_RED_BACKGROUND + "invalid" + ANSI_RESET + ANSI_BLUE + "\nStart: " + ANSI_RESET);
                    start2=s.nextLine();
                }
                System.out.print(ANSI_BLUE + "end: " + ANSI_RESET);
                end2=s.nextLine();
                while(!Polynomial.validStartEnd(start2)){
                    System.out.println(ANSI_RED_BACKGROUND + "invalid" + ANSI_RESET + ANSI_BLUE + "\nEnd: " + ANSI_RESET);
                    end2=s.nextLine();
                }
                System.out.println("the average rate of change between " + start2 + " and " + end2 + " is " + p.rateOfChange(Double.parseDouble(start2),Double.parseDouble(end2)));
            }
        }
        System.out.print("\ngoodbye");

        }
    }

