/**
 * The Instructions class determines which set of instructions the user should be given
 */
public class Instructions {
    private int step;

    /**
     * Constructor for the Instructions class, this creates a new instance of the Instructions class with the following parameters
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
     * toString method for the Instructions class, will return a string containing the instructions for the respective step the user is on
     * @return returns a string containing instructions for the user
     */
    public String toString()
    {
        if (step == 1){
            return "welcome to the polynomial \"solver\"\nplease enter a polynomial expression following the rules below\nuse ^ to represent to the power of\nuse x as your only variable\nterms should only be separated by + or -\ncoefficients, if present, should be integers or decimals\nexponents, if present, should be positive integers\nExpression: ";
        }
        else if (step == 2){
            return "invalid expression, please enter a different expression";
        }
        else if (step == 3){
            return "\nnow you will give a 2 integer or decimal values, a start and end, and 10 coordinate pairs between them will be found\n\nStart: ";
        }
        else if (step == 4)
        {
            return "now enter x values, integer or decimal, they will be plugged into your polynomial, and the y value will be found\nquit with non-numbers (letters/non-math symbol)\ntype ROC/Rate Of Change/Slope to go to rate of change calculator";
        }
        else
        {
            return "now you will enter 2 integer or decimal values, and the rate of change on your polynomial between those two x values will be found";
        }
    }

}
