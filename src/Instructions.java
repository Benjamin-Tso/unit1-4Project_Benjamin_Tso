/**
 * The Instructions class determines which set of instructions the user should be given
 */
public class Instructions {
    private int step;

    /**
     * Constructor for the Instructions class, this creates a new instance of the Intructions class with thte following parameters
     * @param s represents which step the user is on
     */
    public Instructions(int s)
    {
        step = s;
    }

    /**
     * The set step method for the Instructions class, will change which step the user is on
     * @param s, represents which step the user is on
     */
    public void setStep(int s)
    {
        step = s;
    }

    /**
     * toString method for the Instructions class, will return a string containing the instructions for the resepctive step the user is on
     * @return returns a string containing instructions for the user
     */
    public String toString()
    {
        if (step == 1){
            return "welcome to the polynomial \"solver\"\nplease enter a polynomial expression following the rules below:\nuse ^ to represent to the power of\nuse x as your only variable\nterms should only be seperated by + or -\ncoefficients should be integers\nexponents, if present, should be positive integers";
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
