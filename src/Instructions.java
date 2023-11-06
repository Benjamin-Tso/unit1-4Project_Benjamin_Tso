public class Instructions {
    private int step;
    public Instructions(int s)
    {
        step = s;
    }
    public void setStep(int s)
    {
        step = s;
    }
    public String toString()
    {
        if (step == 1){
            return "welcome to the polynomial \"solver\"\nplease enter a polynomial expression following the rules below:\nuse ^ to represent to the power of\nuse x as your only variable\nterms should only be seperated by + or -\ncoefficients and exponents should be integers";
        }
        else if (step == 2){
            return "invalid expression, please enter a different expression";
        }
        else if (step == 3){
            return "enter two integer x values and 10 coordinate pairs between those values will be generated with your polynomial\nStart: ";
        }
        else
        {
            return "now enter x integer values to be solved for with your polynomial (invalid value to quit)";
        }
    }

}
