import java.util.ArrayList;
import java.util.Scanner;

/*
 * This is a Polynomial class to perform 
 * various operations on a polynomial
 * 
 * @author Levent.Eren
 * @author Jojo.Ngai
 */
public class Polynomial {

    private int degree;
    private int size;

    private ArrayList<Term> terms;

    /*
     * public Polynomial ()
     * 
     * This method constructs a new zero polynomial object
     * 
     */
    public Polynomial() {
        degree = -1;

        terms = new ArrayList<Term>();
        terms.add(new Term());
        size = 1;
    }

    /*
     * public Polynomial (String str)
     * 
     * This method constructs a new polynomial object from it's string
     * representation
     * 
     * @param String polynomialAsString
     * 
     * @return
     * 
     * @throws IllegalArgumentException
     * 
     */
    public Polynomial(String polynomialAsString) {
        if (polynomialAsString == null || polynomialAsString.isEmpty()) {
            throw new IllegalArgumentException("Cannot build zero polynomial like this");
        }
        // initialize polynomial
        terms = new ArrayList<Term>();
        terms.add(new Term());
        size = 1;
        // create scanner and deal with each token as a term
        Scanner sPoly = new Scanner(polynomialAsString);
        boolean subtractTerm = false;
        while (sPoly.hasNext()) {
            String token = sPoly.next();
            int length = token.length();
            // if the "term" is only an + or -, do not count it as a term but note if - to
            // make next coefficient negative
            if (length == 1 && (token.equals("+") || token.equals("-"))) {
                if (token.equals("-")) {
                    subtractTerm = true;
                } else {
                    subtractTerm = false;
                }
            } else {
                double newCoefficient = 0;
                int newExponent = 0;
                int indexX;

                // check if invalid char
                for (int i = 0; i < length; i++) {
                    char c = token.charAt(i);
                    if (!((45 <= c && c <= 57) || c == 42 || c == 43 || c == 94 || c == 120)) {
                        throw new IllegalArgumentException("You have entered an invalid character");
                    }
                }

                // extract the coefficient & exponent
                if (token.contains("x")) {
                    indexX = token.indexOf(120);

                    // get coefficient
                    if (indexX == 0) { // if term starts with x, coefficient is 1
                        newCoefficient = 1;
                    } else {
                        newCoefficient = Double.parseDouble(token.substring(0, indexX));
                    }

                    // get exponent
                    if (token.contains("^")) {
                        if (token.indexOf(94) != indexX + 1 || token.indexOf(94) == length - 1) { // wrong place of ^
                            throw new IllegalArgumentException("Wrong use of ^");
                        } else {
                            newExponent = Integer.parseInt(token.substring(indexX + 2));
                        }
                    } else {
                        // if no exponent symbol, exponent is 1 unless they forgot to include throw an
                        // error if numbers after x with no ^
                        if (indexX != length - 1) {
                            throw new IllegalArgumentException("Forgot to include ^");
                        }
                        newExponent = 1;
                    }
                } else { // if no x in term it is exponent zero
                    if (token.contains("^")) {
                        throw new IllegalArgumentException("Forgot to include x");
                    }
                    newCoefficient = Double.parseDouble(token);
                    newExponent = 0;
                }
                // if this term is subtracted, make coefficient negative
                if (subtractTerm)
                    newCoefficient *= -1;
                // add term to polynomial
                this.update(newCoefficient, newExponent);
            }

        }
        degree = this.getDegree();
    }

    /*
     * update(double x, int y)
     * 
     * This method updates the terms within the polynomial by combining coefficients
     * when the degree exists,
     * or initializes a new term when the degree does not exist.
     * 
     * @param double coefficient
     * 
     * @param int exponent
     * 
     * @return
     * 
     * @throws IllegalArgumentException
     */
    public void update(double coefficient, int exponent) {
        size = terms.size();
        // check if exponent is negative
        if (exponent < 0) {
            throw new IllegalArgumentException("Cannot input negative exponenent");
        }
        // loop through terms
        for (int i = 0; i < size; i++) {
            // if looped all the way to the end of the terms and non matched, add new term.
            // else if they match, add the coefficients
            if (exponent != terms.get(i).getExpo() && i == size - 1) {
                terms.add(new Term(coefficient, exponent));
            } else if (exponent == terms.get(i).getExpo()) {
                terms.get(i).add(coefficient);
                break;
            }

        }
        degree = this.getDegree();
    }

    /*
     * getDegree()
     * 
     * This method returns the degree of the polynomial
     * 
     * @param
     * 
     * @return int degree
     */
    public int getDegree() {
        // sort then return first term's exponent
        this.sortPolynomial();

        if (terms.isEmpty())
            return -1;

        degree = terms.get(0).getExpo();
        if (terms.get(0).isZero()) {
            degree = -1;
        }
        return degree;
    }

    /*
     * getCoefficient(int x)
     * 
     * This method returns the coefficient of the specified degree
     * 
     * @param int d
     * 
     * @return double c
     * 
     * @throws IllegalArgumentExcepetion
     */
    public double getCoefficient(int d) {
        if (d < 0) {
            throw new IllegalArgumentException("Cannot input a negative exponent");
        }
        double c = 0;
        // loop through terms to find machting exponents
        for (int i = 0; i < terms.size(); i++) {
            if (terms.get(i).getExpo() == d) {
                // if found return right away
                return terms.get(i).getCoef();
            }
        }
        // if not found return 0
        return c;
    }

    /*
     * add(Polynomial x)
     * 
     * This method returns the result of adding the polynomial to another.
     * 
     * @param Polynomial other
     * 
     * @return Polynomial p
     */
    public Polynomial add(Polynomial other) {
        Polynomial p = new Polynomial();
        ArrayList<Term> otherTerms = other.getTerms();
        // clone original terms to new polynomial by adding terms to empy polynomial
        for (int i = 0; i < terms.size(); i++) {
            p.update(terms.get(i).getCoef(), terms.get(i).getExpo());
        }
        // add new terms
        for (int i = 0; i < otherTerms.size(); i++) {
            p.update(otherTerms.get(i).getCoef(), otherTerms.get(i).getExpo());
        }
        return p;
    }

    /*
     * subtract(Polynomial x)
     * 
     * This method returns the result of subtract the polynomial with another.
     * 
     * @param Polynomial other
     * 
     * @return Polynomial p
     */
    public Polynomial subtract(Polynomial other) {
        Polynomial p = new Polynomial();
        ArrayList<Term> otherTerms = other.getTerms();
        // clone original terms to new polynomial by adding terms to empy polynomial
        for (int i = 0; i < terms.size(); i++) {
            p.update(terms.get(i).getCoef(), terms.get(i).getExpo());
        }
        // subtract new terms by adding the -1 multiplication of the coefficient
        for (int i = 0; i < otherTerms.size(); i++) {
            p.update(-1 * otherTerms.get(i).getCoef(), otherTerms.get(i).getExpo());
        }
        return p;
    }

    /*
     * negate()
     *
     * This method returns the result of negating the polynomial.
     * 
     * @param
     * 
     * @return Polynomial p
     */
    public Polynomial negate() {
        Polynomial p = new Polynomial();
        // add terms to new polynomial by adding terms to empy polynomial by multiplying
        // the coefficients by -1
        for (int i = 0; i < terms.size(); i++) {
            p.update(-1 * terms.get(i).getCoef(), terms.get(i).getExpo());
        }
        return p;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    /*
     * getValue(double x)
     * 
     * This method returns the result of evaluating the polynomial for the given
     * value of the variable "x".
     * 
     * @param double x
     * 
     * @return double value
     */
    public double getValue(double x) {
        double value = 0;
        for (int i = 0; i < terms.size(); i++) {
            // multiply x by itself exponenet number of times
            double temp = x;
            for (int j = 1; j < terms.get(i).getExpo(); j++) {
                temp *= x;

            }
            // if exponent is zero just add coefficient
            if (terms.get(i).getExpo() == 0) {
                value += terms.get(i).getCoef();
            } else { // lastly multiply by coefficient before adding
                value += temp * terms.get(i).getCoef();
            }
        }
        if ((int) value == value)
            return (int) value;
        value = Math.round(value * 1000000000000.000) / 1000000000000.000;
        return value;
    }

    /*
     * getDerivative()
     * 
     * This method returns a polynomial object representing the derivative of this
     * polynomial.
     * 
     * @param
     * 
     * @return Polynomial p
     */
    public Polynomial getDerivative() {
        Polynomial p = new Polynomial();
        // find derivative of each term
        if (degree == -1) {
            return p;
        }
        for (int i = 0; i < terms.size(); i++) {
            int e = terms.get(i).getExpo();
            double c = terms.get(i).getCoef();
            // bring exponent down and subtract one from exponent
            c *= e;
            e--;
            // don't update negative exponents, they just desapear since they were
            // originally exponent zero
            if (e >= 0) {
                p.update(c, e);
            }
        }
        return p;
    }

    /*
     * toString()
     * 
     * This method returns the string representation of the polynomial
     * 
     * @param
     * 
     * @return String full
     */
    public String toString() {
        // sort first
        this.sortPolynomial();
        String full = "";

        if (terms.size() == 1 && terms.get(0).getCoef() == 0 && terms.get(0).getExpo() == 0)
            degree = -1;

        // zero polynomial
        if (degree == -1)
            return "0";

        // remove is ceofficient is zero
        terms.removeIf(n -> (n.getCoef() == 0));

        if (terms.isEmpty())
            return "0";

        // if first term is negative treat it differently
        if (terms.get(0).getCoef() < 0) {
            full += "-" + terms.get(0);
        } else {
            full += terms.get(0);
        }

        // add the rest of the terms
        for (int i = 1; i < terms.size(); i++) {
            // if empty term don't add it
            if (!(terms.get(i).getCoef() == 0 && terms.get(i).getExpo() == 0)) {
                // if term negative, subtract, otherwise add
                if (terms.get(i).getCoef() < 0) {
                    full += " - " + terms.get(i);
                } else {
                    full += " + " + terms.get(i);
                }
            }
        }
        return full;
    }

    /*
     * sortPolynomial ()
     * 
     * This method sorts the polynomial from largest degree to smallest
     * 
     * @param
     * 
     * @return
     */
    private void sortPolynomial() {

        boolean unsorted = true;
        boolean changed = false;
        while (unsorted) {
            // checks if term behind it has a higher exponent. If so they swap places untill
            // no changes have been made.
            for (int i = 0; i < terms.size() - 1; i++) {
                if (terms.get(i).getExpo() < terms.get(i + 1).getExpo()) {
                    // store in temp term to swap
                    Term t = terms.get(i + 1);
                    terms.remove(i + 1);
                    terms.add(i, t);
                    changed = true;
                }
            }
            // terms are sorted only if no changes were made through a full run
            if (changed) {
                changed = false;
            } else {
                unsorted = false;
            }
        }

    }
}