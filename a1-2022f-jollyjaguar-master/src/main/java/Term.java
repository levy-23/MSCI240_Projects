public class Term {
    private int exponent;
    private double coefficient;

    /*
     * public Term ()
     * 
     * This method constructs a new zero term object
     * 
     */
    public Term() {
        exponent = 0;
        coefficient = 0;
    }

    /*
     * public Term (double coefficient, int exponent)
     * 
     * This method constructs a new tern object from given exponent and coefficient
     * 
     * @param Double coefficient
     * 
     * @param Int exponent
     * 
     * @return
     * 
     */
    public Term(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    /*
     * add()
     * 
     * This method is an accessor that returns the exponent
     * 
     * @param
     * 
     * @return Int exponent
     */
    public int getExpo() {
        return exponent;
    }

    /*
     * add()
     * 
     * This method is an accessor that returns the coefficient
     * 
     * @param
     * 
     * @return Double coefficient
     */
    public double getCoef() {
        return coefficient;
    }

    /*
     * add()
     * 
     * This method is a mutator that sets an exponent
     * 
     * @param Int exponent
     * 
     * @return
     */
    public void setExpo(int exponent) {
        this.exponent = exponent;
    }

    /*
     * add()
     * 
     * This method is a mutator that sets a coefficient
     * 
     * @param Double coefficient
     * 
     * @return
     */
    public void setCoef(double coefficient) {
        this.coefficient = coefficient;
    }

    /*
     * add()
     * 
     * This method is a mutator that adds a double to the term's coefficient
     * 
     * @param Double coef
     * 
     * @return
     */
    public void add(double coef) {
        this.coefficient += coef;
    }

    /*
     * isZero()
     * 
     * This method returns if the term is zero
     * 
     * @param
     * 
     * @return Boolean
     */
    public boolean isZero() {
        if (coefficient == 0 && exponent == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * toString()
     * 
     * This method returns the string representation of the polynomial
     * 
     * @param
     * 
     * @return String
     */
    public String toString() {
        String expo = "" + exponent;
        String coef = "" + coefficient;
        boolean ex1 = false;
        if (exponent == 0 && coefficient == 0)
            return "0";
        // if exponent is zero then write only the coefficient since x^0 = 1
        if (exponent == 0) {
            if (coefficient < 0) {
                double c = coefficient * -1;
                return c + "";
            }
            return coef;
        } else if (exponent == 1) { // if exponent is 1 dont write the exponenet
            ex1 = true;
        }

        // if magnintude of the coefficient is 1, don't write the coefficient
        if (Math.abs(coefficient) == 1.0) {
            coef = "";
        } else if (coefficient < 0) { // if coefficient is negative, don't write the negative. Polynomial class will
                                      // subtract the term instead.

            double c = coefficient * -1;
            coef = c + "";
        }
        if (ex1)
            return coef + "x";
        return coef + "x^" + expo;
    }
}
