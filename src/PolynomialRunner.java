import java.util.Scanner;
public class PolynomialRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("welcome to the polynomial \"solver\"\nplease enter a polynomial expression following the rules below:\nuse ^ to represent to the power of\nuse x as your only variable\nterms should only be seperated by + or -");
        String expression = s.nextLine().replaceAll(" ","");
        Polynomial p = new Polynomial(expression);
    }
}
