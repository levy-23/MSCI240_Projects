
/**
 * TestPolynomial
 * A test class that tests Polynomial
 * 
 * Uses JUnit 5
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.io.*;

public class TestPolynomial {

	////////////////////////////////
	// Constructors //
	////////////////////////////////

	// Polynomial()
	// Test for default constructor

	@Test
	/*
	 * testPolynomial_Typical
	 * 
	 * Typical case no expected errors
	 * 
	 * input:
	 * String: empty
	 * 
	 * output:
	 * int: -1
	 */
	public void testPolynomial_Typical() {
		Polynomial p = new Polynomial();
		assertNotNull(p);
		assertEquals(p.getDegree(), -1);
	}

	// Polynomial ( String )
	// Test for full constructor

	@Test
	/*
	 * testPolynomial_Degree2_Typical1
	 * 
	 * Purpose: Checking polynomial constructor with a standard positive degree 2
	 * polynomial in the correct order
	 * 
	 * input:
	 * String: x^2 + x + 1
	 * 
	 * output:
	 * String: x^2 + x + 1.0
	 */
	public void testPolynomial_Degree2_Typical1() {
		Polynomial p = new Polynomial("x^2 + x + 1");
		assertNotNull(p);
		assertEquals(p.toString(), "x^2 + x + 1.0");

	}

	@Test
	/*
	 * testPolynomial_Degree3_Typical2
	 * 
	 * Purpose: Checking polynomial constructor with a standard positive degree 3
	 * polynomial in the correct order
	 * 
	 * input:
	 * String: 3x^3 + 2x^2 + x + 4
	 * 
	 * output:
	 * String: 3.0x^3 + 2.0x^2 + x + 4.0
	 */
	public void testPolynomial_Degree3_Typical2() {
		Polynomial p = new Polynomial("3x^3 + 2x^2 + x + 4");
		assertNotNull(p);
		assertEquals(p.toString(), "3.0x^3 + 2.0x^2 + x + 4.0");

	}

	@Test
	/*
	 * testPolynomial_NegativeCoefficients_Typical3
	 * 
	 * Purpose: Checking polynomial constructor with a standard negative polynomial
	 * in the correct order
	 * 
	 * input:
	 * String: -3x^2 - 1
	 * 
	 * output:
	 * String: -3.0x^2 - 1.0
	 */
	public void testPolynomial_NegativeCoefficients_Typical3() {
		Polynomial p = new Polynomial("-3x^2 - 1");
		assertNotNull(p);
		assertEquals(p.toString(), "-3.0x^2 - 1.0");

	}

	@Test
	/*
	 * testPolynomial_OrderedWrong_Typical4
	 * 
	 * Purpose: Checking degree 2 polynomial constructor with a standard polynomial
	 * that is out of order in terms of degrees
	 * 
	 * input:
	 * String: x + x^2 + 1
	 * 
	 * output:
	 * String: x^2 + x + 1.0
	 */
	public void testPolynomial_OrderedWrong_Typical4() {
		Polynomial p = new Polynomial("x + x^2 + 1");
		assertNotNull(p);
		assertEquals(p.toString(), "x^2 + x + 1.0");

	}

	@Test
	/*
	 * testPolynomial_OrderedWrong2_Typical5
	 * 
	 * Purpose: Checking degree 3 polynomial constructor with a standard polynomial
	 * that is out of order in terms of degrees
	 * 
	 * input:
	 * String: 1 + 3x^3 + 2x + 5x^2
	 * 
	 * output:
	 * String: 3.0x^3 + 5.0x^2 + 2.0x + 1.0
	 */
	public void testPolynomial_OrderedWrong2_Typical5() {
		Polynomial p = new Polynomial("1 + 3x^3 + 2x + 5x^2");
		assertNotNull(p);
		assertEquals(p.toString(), "3.0x^3 + 5.0x^2 + 2.0x + 1.0");

	}

	@Test
	/*
	 * testPolynomial_CoefficientNumberFormat_Typical6
	 * 
	 * Purpose: Checking polynomial constructor with a standard polynomial that has
	 * different number formats as coefficients
	 * 
	 * input:
	 * String: 012341x^2 + 1.5x + 1
	 * 
	 * output:
	 * String: 12341.0x^2 + 1.5x + 1.0
	 */
	public void testPolynomial_CoefficientNumberFormat_Typical6() {
		Polynomial p = new Polynomial("012341x^2 + 1.5x + 1");
		assertNotNull(p);
		assertEquals(p.toString(), "12341.0x^2 + 1.5x + 1.0");

	}

	@Test
	/*
	 * testPolynomial_Degree4MixedSigns_Typical7
	 * 
	 * Purpose: Checking polynomial constructor with a standard polynomial that has
	 * positive and negative terms
	 * 
	 * input:
	 * String: -4x^4 + x^3 - 2x^2 + 3x
	 * 
	 * output:
	 * String: -4.0x^4 + x^3 - 2.0x^2 + 3.0x
	 */
	public void testPolynomial_Degree4MixedSigns_Typical7() {
		Polynomial p = new Polynomial("-4x^4 + x^3 - 2x^2 + 3x");
		assertNotNull(p);
		assertEquals(p.toString(), "-4.0x^4 + x^3 - 2.0x^2 + 3.0x");

	}

	@Test
	/*
	 * testPolynomial_Degree7_Typical8
	 * 
	 * Purpose: Checking polynomial constructor with a standard degree 7 polynomial
	 * 
	 * input:
	 * String: x^7 + 3x^6 + 4x^5 + 3x + 2
	 * 
	 * output:
	 * String: x^7 + 3.0x^6 + 4.0x^5 + 3.0x + 2.0
	 */
	public void testPolynomial_Degree7_Typical8() {
		Polynomial p = new Polynomial("x^7 + 3x^6 + 4x^5 + 3x + 2");
		assertNotNull(p);
		assertEquals(p.toString(), "x^7 + 3.0x^6 + 4.0x^5 + 3.0x + 2.0");

	}

	@Test
	/*
	 * testPolynomial_CoefficientNumberFormat2_Typical9
	 * 
	 * Purpose: Checking polynomial constructor with a standard polynomial that has
	 * decimals as coefficients
	 * 
	 * input:
	 * String: 3.0x^6 + 4.5x^5 + 3.1x^3 + 2x + 1
	 * 
	 * output:
	 * String: 3.0x^6 + 4.5x^5 + 3.1x^3 + 2.0x + 1
	 */
	public void testPolynomial_CoefficientNumberFormat2_Typical9() {
		Polynomial p = new Polynomial("3.0x^6 + 4.5x^5 + 3.1x^3 + 2x + 1");
		assertNotNull(p);
		assertEquals(p.toString(), "3.0x^6 + 4.5x^5 + 3.1x^3 + 2.0x + 1.0");

	}

	@Test
	/*
	 * testPolynomial_CoefficientZero_Extreme1
	 * 
	 * Purpose: Checking polynomial constructor with terms with a coefficient of 0
	 * 
	 * input:
	 * String: 0x^2 + x + 3
	 * 
	 * output:
	 * String: x + 3.0
	 */
	public void testPolynomial_CoefficientZero_Extreme1() {
		Polynomial p = new Polynomial("0x^2 + x + 3");
		assertNotNull(p);
		assertEquals(p.toString(), "x + 3.0");

	}

	@Test
	/*
	 * testPolynomial_ExponentZero_Extreme2
	 * 
	 * Purpose: Checking polynomial constructor with terms with an exponent of 0
	 * 
	 * input:
	 * String: 4x^0
	 * 
	 * output:
	 * String: 4.0
	 */
	public void testPolynomial_ExponentZero_Extreme2() {
		Polynomial p = new Polynomial("4x^0");
		assertNotNull(p);
		assertEquals(p.toString(), "4.0");

	}

	@Test
	/*
	 * testPolynomial_CoefficientOne_Extreme3
	 * 
	 * Purpose: Checking polynomial constructor with terms with a coefficient of 1
	 * 
	 * input:
	 * String: 1x^2 + 1x
	 * 
	 * output:
	 * String: x^2 + x
	 */
	public void testPolynomial_CoefficientOne_Extreme3() {
		Polynomial p = new Polynomial("1x^2 + 1x");
		assertNotNull(p);
		assertEquals(p.toString(), "x^2 + x");

	}

	@Test
	/*
	 * testPolynomial_LongCoefficient_Extreme4
	 * 
	 * Purpose: Checking polynomial constructor with terms with a large coefficient
	 * 
	 * input:
	 * String: 1234567x^2 + x
	 * 
	 * output:
	 * String: 1234567.0x^2 + x
	 */
	public void testPolynomial_LongCoefficient_Extreme4() {
		Polynomial p = new Polynomial("1234567x^2 + x");
		assertNotNull(p);
		assertEquals(p.toString(), "1234567.0x^2 + x");

	}

	@Test
	/*
	 * testPolynomial_LongCoefficient2_Extreme5
	 * 
	 * Purpose: Checking polynomial constructor with terms with a large coefficient
	 * 
	 * input:
	 * String: 512345x^2 + 334455x + 123
	 * 
	 * output:
	 * String: 512345.0x^2 + 334455.0x + 123.0
	 */
	public void testPolynomial_LongCoefficient2_Extreme5() {
		Polynomial p = new Polynomial("512345x^2 + 334455x + 123");
		assertNotNull(p);
		assertEquals(p.toString(), "512345.0x^2 + 334455.0x + 123.0");

	}

	@Test
	/*
	 * testPolynomial_LongExponent_Extreme6
	 * 
	 * Purpose: Checking polynomial constructor with terms with a large exponent
	 * 
	 * input:
	 * String: x^12345667
	 * 
	 * output:
	 * String: x^12345667
	 */
	public void testPolynomial_LongExponent_Extreme6() {
		Polynomial p = new Polynomial("x^12345667");
		assertNotNull(p);
		assertEquals(p.toString(), "x^12345667");

	}

	@Test
	/*
	 * testPolynomial_LongExponent2_Extreme7
	 * 
	 * Purpose: Checking polynomial constructor with terms with a large exponent
	 * 
	 * input:
	 * String: x^9876543 + 45x^998877 + 321
	 * 
	 * output:
	 * String: x^9876543 + 45.0x^998877 + 321.0
	 */
	public void testPolynomial_LongExponent2_Extreme7() {
		Polynomial p = new Polynomial("x^9876543 + 45x^998877 + 321");
		assertNotNull(p);
		assertEquals(p.toString(), "x^9876543 + 45.0x^998877 + 321.0");

	}

	@Test
	/*
	 * testPolynomial_SimilarTerms_Extreme8
	 * 
	 * Purpose: Checking polynomial constructor with repeating degrees (e.g 3x^3 +
	 * 5x^3)
	 * 
	 * input:
	 * String: 3x^3 + 5x^3 + x^2 + x + 6 + 3x^2 + 10x + 7
	 * 
	 * output:
	 * String: 8.0x^3 + 4.0x^2 + 11.0x + 13.0
	 */
	public void testPolynomial_SimilarTerms_Extreme8() {
		Polynomial p = new Polynomial("3x^3 + 5x^3 + x^2 + x + 6 + 3x^2 + 10x + 7");
		assertNotNull(p);
		assertEquals(p.toString(), "8.0x^3 + 4.0x^2 + 11.0x + 13.0");

	}

	@Test
	/*
	 * testPolynomial_SimilarTerms2_Extreme9
	 * 
	 * Purpose: Checking polynomial constructor with repeating degrees
	 * 
	 * input:
	 * String: 2x^2 + 2x + 3x^2 + 1
	 * 
	 * output:
	 * String: 5.0x^2 + 2.0x + 1.0
	 */
	public void testPolynomial_SimilarTerms2_Extreme9() {
		Polynomial p = new Polynomial("2x^2 + 2x + 3x^2 + 1");
		assertNotNull(p);
		assertEquals(p.toString(), "5.0x^2 + 2.0x + 1.0");

	}

	@Test
	/*
	 * testPolynomial_Empty_Failure1
	 * 
	 * Purpose: Checking polynomial constructor with an empty polynomial
	 * 
	 * Error case
	 * 
	 * input:
	 * String: 1x^2 + 1x
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_CoefficientOne_Failure1() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("");
		});
	}

	@Test
	/*
	 * testPolynomial_Null_Failure2
	 * 
	 * Purpose: Checking polynomial constructor with a null polynomial
	 * 
	 * Error case
	 * 
	 * input:
	 * String: 1x^2 + 1x
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_Null_Failure2() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial(null);
		});
	}

	@Test
	/*
	 * testPolynomial_MultiVar_Failure3
	 * 
	 * Purpose: Checking polynomial constructor with a polynomial with multiple
	 * 
	 * Error case
	 * 
	 * input:
	 * String: ax^2 + bx + c
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_MultiVar_Failure3() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("ax^2 + bx + c");
		});
	}

	@Test
	/*
	 * testPolynomial_VarNotX_Failure4
	 * 
	 * Purpose: Checking polynomial constructor with a polynomial with another
	 * variable aside from x
	 * 
	 * Error case
	 * 
	 * input:
	 * String: 4a^2 + 2a + 1
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_VarNotX_Failure4() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("4a^2 + 2a + 1");
		});
	}

	@Test
	/*
	 * testPolynomial_DecimalExponent_Failure5
	 * 
	 * Purpose: Checking polynomial constructor with a polynomial that has a term
	 * with a decimal exponent
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^0.5 + 1
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_DecimalExponent_Failure5() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^0.5 + 1");
		});
	}

	@Test
	/*
	 * testPolynomial_SpecialChar_Failure6
	 * 
	 * Purpose: Checking polynomial constructor with a polynomial with special
	 * characters
	 * 
	 * Error case
	 * 
	 * input:
	 * String: @x^2 + $x + !
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_SpecialChar_Failure6() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("@x^2 + $x + !");
		});
	}

	@Test
	/*
	 * testPolynomial_WrongAdditionFormat_Failure7
	 * 
	 * Purpose: Checking polynomial constructor with a polynomial written without
	 * spaces between “+” and “-” signs.
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^2+1
	 * 
	 * output:
	 * Exception: NumberFormatException
	 */
	public void testPolynomial_WrongAdditionFormat_Failure7() {
		Exception exception = assertThrows(NumberFormatException.class, () -> {
			Polynomial p = new Polynomial("x^2+1");
		});
	}

	@Test
	/*
	 * testPolynomial_Exponential_Failure8
	 * 
	 * Purpose: Checking polynomial constructor with polynomial with a term that has
	 * a character as its exponent
	 * 
	 * Error case
	 * 
	 * input:
	 * String: 3x^x
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_Exponential_Failure8() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("3x^x");
		});
	}

	@Test
	/*
	 * testPolynomial_NegativeExponent_Failure9
	 * 
	 * Purpose: Checking polynomial constructor with polynomial with terms that have
	 * negative exponents
	 * 
	 * Error case
	 * 
	 * input:
	 * String: 3x^ - 4 + 2x^ - 2
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_NegativeExponent_Failure9() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("3x^ - 4 + 2x^ - 2");
		});
	}

	@Test
	/*
	 * testPolynomial_DivisionCoefficient_Failure10
	 * 
	 * Purpose: Checking polynomial constructor with polynomial invalid division
	 * operation on the coefficient
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^2 + 3/4x
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_DivisionCoefficient_Failure10() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^2 + 3/4x");
		});
	}

	@Test
	/*
	 * testPolynomial_DivisionExponent_Failure11
	 * 
	 * Purpose: Checking polynomial constructor with polynomial invalid division
	 * operation on the exponent
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^2/4 + 3
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_DivisionExponent_Failure11() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^2/4 + 3");
		});
	}

	@Test
	/*
	 * testPolynomial_MultiplicationCoefficient_Failure12
	 * 
	 * Purpose: Checking polynomial constructor with polynomial invalid
	 * multiplication operation on the coefficent
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^2 + 3*4x
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_MultiplicationCoefficient_Failure12() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^2 + 3*4x");
		});
	}

	@Test
	/*
	 * testPolynomial_MultiplicationExponent_Failure13
	 * 
	 * Purpose: Checking polynomial constructor with polynomial invalid
	 * multiplication operation on the exponent
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^2*4 + 3
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_MultiplicationExponent_Failure13() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^2*4 + 3");
		});
	}

	////////////////////////////////
	// Accessors/Mutators //
	////////////////////////////////

	@Test
	/*
	 * testPolynomial_getDegree_Degree2_Typical1
	 * 
	 * Purpose: Checking getDegree accessor with a standard degree 2 polynomial
	 * 
	 * input:
	 * String: x^2 + x + 5
	 * 
	 * output:
	 * int: 2
	 */
	public void testPolynomial_getDegree_Degree2_Typical1() {
		Polynomial p = new Polynomial("x^2 + x + 5");
		assertEquals(p.getDegree(), 2);
	}

	@Test
	/*
	 * testPolynomial_getDegree_Degree3_Typical2
	 * 
	 * Purpose: Checking getDegree accessor with a standard degree 3 polynomial
	 * 
	 * input:
	 * String: 2x^3 + 3x^2 + 2x + 1
	 * 
	 * output:
	 * int: 3
	 */
	public void testPolynomial_getDegree_Degree3_Typical2() {
		Polynomial p = new Polynomial("2x^3 + 3x^2 + 2x + 1");
		assertEquals(p.getDegree(), 3);
	}

	@Test
	/*
	 * testPolynomial_getDegree_Degree4_Typical3
	 * 
	 * Purpose: Checking getDegree accessor with a standard degree 4 polynomial
	 * 
	 * input:
	 * String: 4x^4 + 3x^3 + 2x^2 + x + 9
	 * 
	 * output:
	 * int: 4
	 */
	public void testPolynomial_getDegree_Degree4_Typical3() {
		Polynomial p = new Polynomial("4x^4 + 3x^3 + 2x^2 + x + 9");
		assertEquals(p.getDegree(), 4);
	}

	@Test
	/*
	 * testPolynomial_getDegree_OutOfOrder_Extreme1
	 * 
	 * Purpose: Checking getDegree accessor with a standard polynomial that is out
	 * of
	 * 
	 * input:
	 * String: x + x^2 + 5
	 * 
	 * output:
	 * int: 2
	 */
	public void testPolynomial_getDegree_OutOfOrder_Extreme1() {
		Polynomial p = new Polynomial("x + x^2 + 5");
		assertEquals(p.getDegree(), 2);
	}

	@Test
	/*
	 * testPolynomial_getDegree_OutOfOrder2_Extreme2
	 * 
	 * Purpose: Checking getDegree accessor with a standard polynomial that is out
	 * of order in terms of degree
	 * 
	 * input:
	 * String: 3x + 4x^3 + 1
	 * 
	 * output:
	 * int: 3
	 */
	public void testPolynomial_getDegree_OutOfOrder2_Extreme2() {
		Polynomial p = new Polynomial("3x + 4x^3 + 1");
		assertEquals(p.getDegree(), 3);
	}

	@Test
	/*
	 * testPolynomial_getDegree_ZeroPolynomial_Extreme3
	 * 
	 * Purpose: Checking getDegree accessor with the zero polynomial
	 * 
	 * input:
	 * String: empty
	 * 
	 * output:
	 * int: -1
	 */
	public void testPolynomial_getDegree_ZeroPolynomial_Extreme3() {
		Polynomial p = new Polynomial();
		assertEquals(p.getDegree(), -1);
	}

	@Test
	/*
	 * testPolynomial_getCoefficient_Degree4_Typical1
	 * 
	 * Purpose: Checking the getCoefficient accessor with a standard degree 4
	 * polynomial
	 * 
	 * input:
	 * String: 3x^4 + 1
	 * 
	 * Param: 4
	 * 
	 * output:
	 * double: 3.0
	 */
	public void testPolynomial_getCoefficient_Degree4_Typical1() {
		Polynomial p = new Polynomial("3x^4 + 1");
		assertEquals(p.getCoefficient(4), 3.0);
	}

	@Test
	/*
	 * testPolynomial_getCoefficient_Degree3_Typical2
	 * 
	 * Purpose: Checking the getCoefficient accessor with a standard degree 3
	 * polynomial
	 * 
	 * input:
	 * String: 4x^3 + 2x^2 + 3x + 1
	 * 
	 * Param: 3,2,1
	 * 
	 * output:
	 * double: 4.0
	 * double: 2.0
	 * double: 3.0
	 */
	public void testPolynomial_getCoefficient_Degree3_Typical2() {
		Polynomial p = new Polynomial("4x^3 + 2x^2 + 3x + 1");
		assertEquals(p.getCoefficient(3), 4.0);
		assertEquals(p.getCoefficient(2), 2.0);
		assertEquals(p.getCoefficient(1), 3.0);
	}

	@Test
	/*
	 * testPolynomial_getCoefficient_CoefficientOne_Extreme1
	 * 
	 * Purpose: Checking the getCoefficient accessor with a polynomial with terms
	 * that have a coefficient of 1
	 * 
	 * input:
	 * String: x^420 + 1
	 * 
	 * Param: 420
	 * 
	 * output:
	 * double: 1.0
	 */
	public void testPolynomial_getCoefficient_CoefficientOne_Extreme1() {
		Polynomial p = new Polynomial("x^420 + 1");
		assertEquals(p.getCoefficient(420), 1.0);
	}

	@Test
	/*
	 * testPolynomial_getCoefficient_DegreeDNE_Extreme2
	 * 
	 * Purpose: Checking the getCoefficient accessor with a polynomial that does not
	 * have a term with the degree matching the parameter.
	 * 
	 * input:
	 * String: x^5 + 4
	 * 
	 * Param: 35363283
	 * 
	 * output:
	 * double: 0
	 */
	public void testPolynomial_getCoefficient_DegreeDNE_Extreme2() {
		Polynomial p = new Polynomial("x^5 + 4");
		assertEquals(p.getCoefficient(35363283), 0);
	}

	/*
	 * testPolynomial_getCoefficient_DegreeDNE_Extreme2
	 * 
	 * Purpose: Checking the getCoefficient accessor with a polynomial that does not
	 * have a term with the degree matching the parameter.
	 * 
	 * input:
	 * String: x^3 + 4
	 * 
	 * Param: 40
	 * 
	 * output:
	 * double: 0
	 */
	public void testPolynomial_getCoefficient_DegreeDNE2_Extreme3() {
		Polynomial p = new Polynomial("x^3 + 4");
		assertEquals(p.getCoefficient(40), 0);
	}

	@Test
	/*
	 * testPolynomial_getCoefficient_NegativeDegree_Failure1
	 * 
	 * Purpose: Checking the getCoefficient accessor with a polynomial and a
	 * parameter of a negative degree
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^8 + 4
	 * 
	 * Param: -69
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_getCoefficient_NegativeDegree_Failure1() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^8 + 4");
			p.getCoefficient(-69);
		});
	}

	@Test
	/*
	 * testPolynomial_update_CoefficientsCombined_Typical1
	 * 
	 * Purpose: Checking the update mutator with a standard polynomial and standard
	 * inputs
	 * 
	 * input:
	 * String: 2x^3 + 1
	 * 
	 * Param: 2,3
	 * 
	 * output:
	 * String: 4.0x^3 + 1.0
	 */
	public void testPolynomial_update_CoefficientsCombined_Typical1() {
		Polynomial p = new Polynomial("2x^3 + 1");
		p.update(2, 3);
		assertEquals(p.toString(), "4.0x^3 + 1.0");
	}

	@Test
	/*
	 * testPolynomial_update_InitializingCoefficient_Typical2
	 * 
	 * Purpose: Checking the update mutator with a standard polynomial and an input
	 * that will initialize a new term in the polynomial (e.g x^2)
	 * 
	 * input:
	 * String: x + 1
	 * 
	 * Param: 1,2
	 * 
	 * output:
	 * String: x^2 + x + 1.0
	 */
	public void testPolynomial_update_InitializingCoefficient_Typical2() {
		Polynomial p = new Polynomial("x + 1");
		p.update(1, 2);
		assertEquals(p.toString(), "x^2 + x + 1.0");
	}

	@Test
	/*
	 * testPolynomial_update_InitializingCoefficient2_Typical3
	 * 
	 * Purpose: Checking the update mutator with a standard polynomial and an input
	 * that will initialize a new term in the polynomial
	 * 
	 * input:
	 * String: 3x^3 + x^2 + x
	 * 
	 * Param: 2,4
	 * 
	 * output:
	 * String: 2.0x^4 + 3.0x^3 + x^2 + x
	 */
	public void testPolynomial_update_InitializingCoefficient2_Typical3() {
		Polynomial p = new Polynomial("3x^3 + x^2 + x");
		p.update(2, 4);
		assertEquals(p.toString(), "2.0x^4 + 3.0x^3 + x^2 + x");
	}

	@Test
	/*
	 * testPolynomial_update_PosToNeg_Typical4
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input that will
	 * flip the signs of a term(s) in the polynomial
	 * 
	 * input:
	 * String: x + 1
	 * 
	 * Param: -3,1
	 * 
	 * output:
	 * String: -2.0x + 1.0
	 */
	public void testPolynomial_update_PosToNeg_Typical4() {
		Polynomial p = new Polynomial("x + 1");
		p.update(-3, 1);
		assertEquals(p.toString(), "-2.0x + 1.0");
	}

	@Test
	/*
	 * testPolynomial_update_PosToNeg2_Typical5
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input that will
	 * flip the signs of a term(s) in the polynomial
	 * 
	 * input:
	 * String: 3x^2 + 1
	 * 
	 * Param: -7,2
	 * 
	 * output:
	 * String: -4.0x^2 + 1.0
	 */
	public void testPolynomial_update_PosToNeg2_Typical5() {
		Polynomial p = new Polynomial("3x^2 + 1");
		p.update(-7, 2);
		assertEquals(p.toString(), "-4.0x^2 + 1.0");
	}

	// JOJO THIS ONE NEW
	@Test
	/*
	 * testPolynomial_update_NegToPos_Typical6
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input that will
	 * flip the signs of a term(s) in the polynomial
	 * 
	 * input:
	 * String: -4x^2 + 1
	 * 
	 * Param: 7,2
	 * 
	 * output:
	 * String: 3.0x^2 + 1.0
	 */
	public void testPolynomial_update_NegToPos_Typical6() {
		Polynomial p = new Polynomial("-4x^2 + 1");
		p.update(7, 2);
		assertEquals(p.toString(), "3.0x^2 + 1.0");
	}

	@Test
	/*
	 * testPolynomial_update_SumCoefficientsZero_Extreme1
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input that will
	 * cause the sum of the coefficients to equal 0
	 * 
	 * input:
	 * String: 2x^2 + 1
	 * 
	 * Param: -2,2
	 * 
	 * output:
	 * String: 1.0
	 */
	public void testPolynomial_update_SumCoefficientsZero_Extreme1() {
		Polynomial p = new Polynomial("2x^2 + 1");
		p.update(-2, 2);
		assertEquals(p.toString(), "1.0");
	}

	@Test
	/*
	 * testPolynomial_update_AddZero_Extreme2
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input of
	 * coefficient 0 to a term
	 * 
	 * input:
	 * String: x^2
	 * 
	 * Param: 0,2
	 * 
	 * output:
	 * String: x^2
	 */
	public void testPolynomial_update_AddZero_Extreme2() {
		Polynomial p = new Polynomial("x^2");
		p.update(0, 2);
		assertEquals(p.toString(), "x^2");
	}

	@Test
	/*
	 * testPolynomial_update_InitializingDegreeZero_Extreme3
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input that will
	 * initialize degree 0
	 * 
	 * input:
	 * String: x^4
	 * 
	 * Param: 7,0
	 * 
	 * output:
	 * String: x^4 + 7.0
	 */
	public void testPolynomial_update_InitializingDegreeZero_Extreme3() {
		Polynomial p = new Polynomial("x^4");
		p.update(7, 0);
		assertEquals(p.toString(), "x^4 + 7.0");
	}

	@Test
	/*
	 * testPolynomial_update_NegativeDegree_Failure1
	 * 
	 * Purpose: Checking the update mutator with a polynomial and an input that
	 * includes a negative exponent
	 * 
	 * Error case
	 * 
	 * input:
	 * String: x^8 + x^9 + 4
	 * 
	 * Param: 540,-2
	 * 
	 * output:
	 * Exception: IllegalArgumentException
	 */
	public void testPolynomial_update_NegativeDegree_Failure1() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Polynomial p = new Polynomial("x^8 + x^9 + 4");
			p.update(540, -2);
		});
	}

	@Test
	/*
	 * testPolynomial_negate_Degree2AllPos_Typical1
	 *
	 * Purpose: Checking the negate accessor with a standard positive second degree
	 * polynomial
	 * 
	 * input:
	 * String: 4x^2 + 3x + 2
	 * 
	 * output:
	 * String: -4.0x^2 - 3.0x - 2.0
	 */
	public void testPolynomial_negate_Degree2AllPos_Typical1() {
		Polynomial p = new Polynomial("4x^2 + 3x + 2");
		assertEquals(p.negate().toString(), "-4.0x^2 - 3.0x - 2.0");
	}

	@Test
	/*
	 * testPolynomial_negate_Degree3AllNeg_Typical2
	 *
	 * Purpose: Checking the negate accessor with a standard negative third degree
	 * polynomial
	 * 
	 * input:
	 * String: -3x^3 - 2x^2 - 3x - 7
	 * 
	 * output:
	 * String: 3.0x^3 + 2.0x^2 + 3.0x + 7.0
	 */
	public void testPolynomial_negate_Degree3AllNeg_Typical2() {
		Polynomial p = new Polynomial("-3x^3 - 2x^2 - 3x - 7");
		assertEquals(p.negate().toString(), "3.0x^3 + 2.0x^2 + 3.0x + 7.0");
	}

	@Test
	/*
	 * testPolynomial_negate_Degree4MixedSigns_Typical3
	 *
	 * Purpose: Checking the negate accessor with a standard degree four polynomial
	 * with both positive and negative terms
	 * 
	 * input:
	 * String: 4x^4 - 2x^2 + 3
	 * 
	 * output:
	 * String: -4.0x^4 + 2.0x^2 - 3.0
	 */
	public void testPolynomial_negate_Degree4MixedSigns_Typical3() {
		Polynomial p = new Polynomial("4x^4 - 2x^2 + 3");
		assertEquals(p.negate().toString(), "-4.0x^4 + 2.0x^2 - 3.0");
	}

	@Test
	/*
	 * testPolynomial_negate_ZeroPolynomial_Extreme1
	 *
	 * Purpose: Checking the negate accessor with the 0 polynomial
	 * 
	 * input:
	 * String: empty
	 * 
	 * output:
	 * String: 0
	 */
	public void testPolynomial_negate_ZeroPolynomial_Extreme1() {
		Polynomial p = new Polynomial();
		assertEquals(p.negate().toString(), "0");
	}

	@Test
	/*
	 * testPolynomial_getValue_Degree3_Typical1
	 * 
	 * Purpose: Checking the getValue method with a standard degree 3 polynomial and
	 * a common input
	 * 
	 * input:
	 * String: 3x^3 + 4x^2 + 2x + 1
	 * 
	 * Param: 2
	 * 
	 * output:
	 * double: 45
	 */
	public void testPolynomial_getValue_Degree3_Typical1() {
		Polynomial p = new Polynomial("3x^3 + 4x^2 + 2x + 1");
		assertEquals(p.getValue(2), 45);
	}

	@Test
	/*
	 * testPolynomial_getValue_Degree2_Typical2
	 * 
	 * Purpose: Checking the getValue method with a standard degree 2 polynomial and
	 * a common input
	 * 
	 * input:
	 * String: 6x^2 + 3x + 1
	 * 
	 * Param: 4
	 * 
	 * output:
	 * double: 109
	 */
	public void testPolynomial_getValue_Degree2_Typical2() {
		Polynomial p = new Polynomial("6x^2 + 3x + 1");
		assertEquals(p.getValue(4), 109);
	}

	@Test
	/*
	 * testPolynomial_getValue_PolynomialZero_Extreme1
	 * 
	 * Purpose: Checking the getValue method with the 0 polynomial
	 * 
	 * input:
	 * String: empty
	 * 
	 * Param: 3
	 * 
	 * output:
	 * double: 0
	 */
	public void testPolynomial_getValue_PolynomialZero_Extreme1() {
		Polynomial p = new Polynomial();
		assertEquals(p.getValue(3), 0);
	}

	@Test
	/*
	 * testPolynomial_getValue_XZero_Extreme2
	 * 
	 * Purpose: Checking the getValue method with a standard polynomial and an input
	 * of 0
	 * 
	 * input:
	 * String: 2x^2 + 4x + 3
	 * 
	 * Param: 0
	 * 
	 * output:
	 * double: 3
	 */
	public void testPolynomial_getValue_XZero_Extreme2() {
		Polynomial p = new Polynomial("2x^2 + 4x + 3");
		assertEquals(p.getValue(0), 3);
	}

	@Test
	/*
	 * testPolynomial_getValue_LargePolynomial_Extreme3
	 * 
	 * Purpose: Checking the getValue method with a large polynomial and a common
	 * input
	 * 
	 * input:
	 * String: 3333x^7 + 2222x^6 + 1111x^8
	 * 
	 * Param: 2
	 * 
	 * output:
	 * double: 853248
	 */
	public void testPolynomial_getValue_LargePolynomial_Extreme3() {
		Polynomial p = new Polynomial("3333x^7 + 2222x^6 + 1111x^8");
		assertEquals(p.getValue(2), 853248);
	}

	@Test
	/*
	 * testPolynomial_getValue_LargeValue_Extreme4
	 * 
	 * Purpose: Checking the getValue method with a standard polynomial and a large
	 * input
	 * 
	 * input:
	 * String: x^2 + x + 1
	 * 
	 * Param: 6788
	 * 
	 * output:
	 * double: 46083733
	 */
	public void testPolynomial_getValue_LargeValue_Extreme4() {
		Polynomial p = new Polynomial("x^2 + x + 1");
		assertEquals(p.getValue(6788), 46083733);
	}

	@Test
	/*
	 * testPolynomial_getValue_SmallValue_Extreme5
	 *
	 * Purpose: Checking the getValue method with a standard polynomial and a
	 * small
	 * input
	 *
	 * input:
	 * String: x^3
	 *
	 * Param: 0.00397
	 *
	 * output:
	 * double: 0.000062570773
	 */
	public void testPolynomial_getValue_SmallValue_Extreme5() {
		Polynomial p = new Polynomial("x^3");
		assertEquals(p.getValue(0.0397), 0.000062570773);
	}

	////////////////////////////////
	// Complex Functionality //
	////////////////////////////////

	@Test
	/*
	 * testPolynomial_add_OverlappingDegrees_Typical1
	 * 
	 * Purpose: Checking the add method with two standard polynomials
	 * 
	 * input:
	 * String: 2x + 1
	 * String: 3x + 1
	 * 
	 * output:
	 * String: 5.0x + 2.0
	 * String: 5.0x + 2.0
	 */
	public void testPolynomial_add_OverlappingDegrees_Typical1() {
		Polynomial p = new Polynomial("2x + 1");
		Polynomial q = new Polynomial("3x + 1");
		assertEquals(p.add(q).toString(), "5.0x + 2.0");
		// check both ways
		assertEquals(q.add(p).toString(), "5.0x + 2.0");
	}

	@Test
	/*
	 * testPolynomial_add_NonOverlappingDegrees_Typical2
	 * 
	 * Purpose: Checking the add method with two polynomials that have terms that do
	 * not overlap one another
	 * 
	 * input:
	 * String: 3x^3 + 2x
	 * String: 2x^2
	 * 
	 * output:
	 * String: 3.0x^3 + 2.0x^2 + 2.0x
	 * String: 3.0x^3 + 2.0x^2 + 2.0x
	 */
	public void testPolynomial_add_NonOverlappingDegrees_Typical2() {
		Polynomial p = new Polynomial("3x^3 + 2x");
		Polynomial q = new Polynomial("2x^2");
		assertEquals(p.add(q).toString(), "3.0x^3 + 2.0x^2 + 2.0x");
		// check both ways
		assertEquals(q.add(p).toString(), "3.0x^3 + 2.0x^2 + 2.0x");
	}

	@Test
	/*
	 * testPolynomial_add_SumZero_Extreme1
	 *
	 * Purpose: Checking the add method when the sum of the two polynomials is equal
	 * to 0
	 *
	 * input:
	 * String: 2x^2 + x
	 * String: -2x^2 - x
	 *
	 * output:
	 * String: 0
	 * String: 0
	 */
	public void testPolynomial_add_SumZero_Extreme1() {
		Polynomial p = new Polynomial("2x^2 + x");
		Polynomial q = new Polynomial("-2x^2 - x");
		assertEquals(p.add(q).toString(), "0");
		// check both ways
		assertEquals(q.add(p).toString(), "0");
	}

	@Test
	/*
	 * testPolynomial_add_SumofLarge_Extreme2
	 * 
	 * Purpose: Checking the add method when two large polynomials are added
	 * together
	 * 
	 * input:
	 * String: 4x^37 + 3x^6 + x
	 * String: 2x^484020 + 3x^37 + 3637x^78
	 * 
	 * output:
	 * String: 2.0x^484 + 3637.0x^78 + 7.0x^37 + 3.0x^6 + x
	 * String: 2.0x^484 + 3637.0x^78 + 7.0x^37 + 3.0x^6 + x
	 */
	public void testPolynomial_add_SumofLarge_Extreme2() {
		Polynomial p = new Polynomial("4x^37 + 3x^6 + x");
		Polynomial q = new Polynomial("2x^484 + 3x^37 + 3637x^78");
		assertEquals(p.add(q).toString(), "2.0x^484 + 3637.0x^78 + 7.0x^37 + 3.0x^6 + x");
		// check both ways
		assertEquals(q.add(p).toString(), "2.0x^484 + 3637.0x^78 + 7.0x^37 + 3.0x^6 + x");
	}

	@Test
	/*
	 * testPolynomial_add_OutOfOrder_Extreme3
	 * 
	 * Purpose: Checking the add method when two polynomials that are out of order
	 * in terms of degree are added together
	 * 
	 * input:
	 * String: 7x^7 + 4x^37 + 4x
	 * String: 2x^484020 + 3x^37 + 3637x^78
	 * 
	 * output:
	 * String: 2.0x^484 + 3637.0x^78 + 7.0x^37 + 7.0x^7 + 4.0x
	 * String: 2.0x^484 + 3637.0x^78 + 7.0x^37 + 7.0x^7 + 4.0x
	 */
	public void testPolynomial_add_OutOfOrder_Extreme3() {
		Polynomial p = new Polynomial("7x^7 + 4x^37 + 4x");
		Polynomial q = new Polynomial("2x^484 + 3x^37 + 3637x^78");
		assertEquals(p.add(q).toString(), "2.0x^484 + 3637.0x^78 + 7.0x^37 + 7.0x^7 + 4.0x");
		// check both ways
		assertEquals(q.add(p).toString(), "2.0x^484 + 3637.0x^78 + 7.0x^37 + 7.0x^7 + 4.0x");
	}

	@Test
	/*
	 * testPolynomial_add_EmptyPolynomial_Extreme4
	 *
	 * Purpose: Checking the add method when one of the two polynomials are empty
	 * 
	 * input:
	 * String: 9x^5 + 80x^7
	 * String: empty
	 *
	 * output:
	 * String: 80.0x^7 + 9.0x^5
	 * String: 80.0x^7 + 9.0x^5
	 */
	public void testPolynomial_add_EmptyPoplynomial_Extreme4() {
		Polynomial p = new Polynomial("9x^5 + 80x^7");
		Polynomial q = new Polynomial();
		assertEquals(p.add(q).toString(), "80.0x^7 + 9.0x^5");
		// check both ways
		assertEquals(q.add(p).toString(), "80.0x^7 + 9.0x^5");
	}

	@Test
	/*
	 * testPolynomial_subtract_OverlappingDegrees_Typical1
	 * 
	 * Purpose: Checking the subtract method with two standard polynomials
	 * 
	 * input:
	 * String: 2x + 1
	 * String: 3x + 1
	 * 
	 * output:
	 * String: -x
	 * String: x
	 */
	public void testPolynomial_subtract_OverlappingDegrees_Typical1() {
		Polynomial p = new Polynomial("2x + 1");
		Polynomial q = new Polynomial("3x + 1");
		assertEquals(p.subtract(q).toString(), "-x");
		// check both ways
		assertEquals(q.subtract(p).toString(), "x");
	}

	@Test
	/*
	 * testPolynomial_subtract_NonOverlappingDegrees_Typical2
	 * 
	 * Purpose: Checking the subtract method with two polynomials that have terms
	 * that do not overlap one another
	 * 
	 * input:
	 * String: 3x^3 + 2x
	 * String: 2x^2
	 * 
	 * output:
	 * String: 3.0x^3 - 2.0x^2 + 2.0x
	 * String: -3.0x^3 + 2.0x^2 - 2.0x
	 */
	public void testPolynomial_subtract_NonOverlappingDegrees_Typical2() {
		Polynomial p = new Polynomial("3x^3 + 2x");
		Polynomial q = new Polynomial("2x^2");
		assertEquals(p.subtract(q).toString(), "3.0x^3 - 2.0x^2 + 2.0x");
		// check both ways
		assertEquals(q.subtract(p).toString(), "-3.0x^3 + 2.0x^2 - 2.0x");
	}

	@Test
	/*
	 * testPolynomial_subtract_DifferenceZero_Extreme1
	 *
	 * Purpose: Checking the subtract method with two polynomials whose difference
	 * is 0
	 * 
	 * input:
	 * String: 2x + x
	 * String: 2x + x
	 *
	 * output:
	 * String: 0
	 * String: 0
	 */
	public void testPolynomial_subtract_DifferenceZero_Extreme1() {
		Polynomial p = new Polynomial("2x + x");
		Polynomial q = new Polynomial("2x + x");
		assertEquals(p.subtract(q).toString(), "0");
		// check both ways
		assertEquals(q.subtract(p).toString(), "0");
	}

	@Test
	/*
	 * testPolynomial_subtract_DifofLarge_Extreme2
	 * 
	 * Purpose: Checking the subtract method with two large polynomials
	 * 
	 * input:
	 * String: 4x^37 + 3x^6 + x
	 * String: 2x^484020 + 3x^37 + 3637x^78
	 * 
	 * output:
	 * String: -2.0x^484 - 3637.0x^78 + x^37 + 3.0x^6 + x
	 * String: 2.0x^484 + 3637.0x^78 - x^37 - 3.0x^6 - x
	 */
	public void testPolynomial_subtract_DifofLarge_Extreme2() {
		Polynomial p = new Polynomial("4x^37 + 3x^6 + x");
		Polynomial q = new Polynomial("2x^484 + 3x^37 + 3637x^78");
		assertEquals(p.subtract(q).toString(), "-2.0x^484 - 3637.0x^78 + x^37 + 3.0x^6 + x");
		// check both ways
		assertEquals(q.subtract(p).toString(), "2.0x^484 + 3637.0x^78 - x^37 - 3.0x^6 - x");
	}

	@Test
	/*
	 * testPolynomial_subtract_OutOfOrder_Extreme3
	 * 
	 * Purpose: Checking the subtract method with two polynomials that are out of
	 * order in terms of degree
	 * 
	 * input:
	 * String: 7x^7 + 4x^37 + 4x
	 * String: 2x^484020 + 3x^37 + 3637x^78
	 * 
	 * output:
	 * String: -2.0x^484 - 3637.0x^78 + x^37 + 7.0x^7 + 4.0x
	 * String: 2.0x^484 + 3637.0x^78 - x^37 - 7.0x^7 - 4.0x
	 */
	public void testPolynomial_subtract_OutOfOrder_Extreme3() {
		Polynomial p = new Polynomial("7x^7 + 4x^37 + 4x");
		Polynomial q = new Polynomial("2x^484 + 3x^37 + 3637x^78");
		assertEquals(p.subtract(q).toString(), "-2.0x^484 - 3637.0x^78 + x^37 + 7.0x^7 + 4.0x");
		// check both ways
		assertEquals(q.subtract(p).toString(), "2.0x^484 + 3637.0x^78 - x^37 - 7.0x^7 - 4.0x");
	}

	@Test
	/*
	 * testPolynomial_subtract_EmptyPolynomial_Extreme4
	 * 
	 * Purpose: Checking the subtract method with at least one polynomial that is
	 * empty
	 * 
	 * input:
	 * String: 9x^5 + 80x^7
	 * String: empty
	 *
	 * output:
	 * String: 80.0x^7 + 9.0x^5
	 * String: -80.0x^7 - 9.0x^5
	 */
	public void testPolynomial_subtract_EmptyPoplynomial_Extreme4() {
		Polynomial p = new Polynomial("9x^5 + 80x^7");
		Polynomial q = new Polynomial();
		assertEquals(p.subtract(q).toString(), "80.0x^7 + 9.0x^5");
		// check both ways
		assertEquals(q.subtract(p).toString(), "-80.0x^7 - 9.0x^5");
	}

	@Test
	/*
	 * testPolynomial_getDerivative_Degree2_Typical1
	 * 
	 * Purpose: Checking the getDerivative method with a standard degree 2
	 * polynomial
	 * 
	 * input:
	 * String: 2x^2 + 3x + 5
	 * 
	 * output:
	 * String: 4.0x + 3.0
	 */
	public void testPolynomial_getDerivative_Degree2_Typical1() {
		Polynomial p = new Polynomial("2x^2 + 3x + 5");
		assertEquals(p.getDerivative().toString(), "4.0x + 3.0");
	}

	@Test
	/*
	 * testPolynomial_getDerivative_Degree3_Typical2
	 * 
	 * Purpose: Checking the getDerivative method with a standard degree 3
	 * polynomial
	 * 
	 * input:
	 * String: 3x^3 + 6x^2 + 8x + 9
	 * 
	 * output:
	 * String: 9.0x^2 + 12.0x + 8.0
	 */
	public void testPolynomial_getDerivative_Degree3_Typical2() {
		Polynomial p = new Polynomial("3x^3 + 6x^2 + 8x + 9");
		assertEquals(p.getDerivative().toString(), "9.0x^2 + 12.0x + 8.0");
	}

	@Test
	/*
	 * testPolynomial_getDerivative_Negative_Typical3
	 * 
	 * Purpose: Checking the getDerivative method with a standard polynomial that
	 * has negative terms
	 * 
	 * input:
	 * String: -90x^7 + 11x^9 - 2x^4 - 9
	 * 
	 * output:
	 * String: 99.0x^8 - 630.0x^6 - 8.0x^3
	 */
	public void testPolynomial_getDerivative_Negative_Typical3() {
		Polynomial p = new Polynomial("-90x^7 + 11x^9 - 2x^4 - 9");
		assertEquals(p.getDerivative().toString(), "99.0x^8 - 630.0x^6 - 8.0x^3");
	}

	@Test
	/*
	 * testPolynomial_getDerivative_LargePolynomial_Extreme1
	 * 
	 * Purpose: Checking the getDerivative method with a large polynomial
	 * 
	 * input:
	 * String: 8888x^99 + 7777x^88 + 6666x^14
	 * 
	 * output:
	 * String: 879912.0x^98 + 684376.0x^87 + 93324.0x^13
	 */
	public void testPolynomial_getDerivative_LargePolynomial_Extreme1() {
		Polynomial p = new Polynomial("8888x^99 + 7777x^88 + 6666x^14");
		assertEquals(p.getDerivative().toString(), "879912.0x^98 + 684376.0x^87 + 93324.0x^13");
	}

	@Test
	/*
	 * testPolynomial_getDerivative_ZeroPolynomial_Extreme2
	 * 
	 * Purpose: Checking the getDerivative method with the 0 polynomial
	 * 
	 * input:
	 * String: empty
	 * 
	 * output:
	 * String: 0
	 */
	public void testPolynomial_getDerivative_ZeroPolynomial_Extreme2() {
		Polynomial p = new Polynomial();
		assertEquals(p.getDerivative().toString(), "0");
	}

	@Test
	/*
	 * testPolynomial_toString_NegativeCoefficient_Typical1
	 * 
	 * Purpose: Checking the toString method with a polynomial with negative terms
	 * 
	 * input:
	 * String: -3x^2 - 1
	 * 
	 * output:
	 * String: -3.0x^2 - 1.0
	 */
	public void testPolynomial_toString_NegativeCoefficient_Typical1() {
		Polynomial p = new Polynomial("-3x^2 - 1");
		assertEquals(p.toString(), "-3.0x^2 - 1.0");
	}

	@Test
	/*
	 * testPolynomial_toString_CoefficientToDouble_Typical2
	 * 
	 * Purpose: Checking the toString method with a polynomial that requires turning
	 * coefficient from integer to double
	 * 
	 * input:
	 * String: 2x^2
	 * 
	 * output:
	 * String: 2.0x^2
	 */
	public void testPolynomial_toString_CoefficientToDouble_Typical2() {
		Polynomial p = new Polynomial("2x^2");
		assertEquals(p.toString(), "2.0x^2");
	}

	@Test
	/*
	 * testPolynomial_toString_StandardPolynomial_Typical3
	 * 
	 * Purpose: Checking the toString method with a standard second degree
	 * polynomial
	 * 
	 * input:
	 * String: 2x^2 + x + 1
	 * 
	 * output:
	 * String: 2.0x^2 + x + 1.0
	 */
	public void testPolynomial_toString_StandardPolynomial_Typical3() {
		Polynomial p = new Polynomial("2x^2 + x + 1");
		assertEquals(p.toString(), "2.0x^2 + x + 1.0");
	}

	@Test
	/*
	 * testPolynomial_toString_CoefficientZero_Extreme1
	 * 
	 * Purpose: Checking the toString method with a polynomial that has a term with
	 * a 0 coefficient
	 * 
	 * input:
	 * String: 0x^2 + x + 3
	 * 
	 * output:
	 * String: x + 3.0
	 */
	public void testPolynomial_toString_CoefficientZero_Extreme1() {
		Polynomial p = new Polynomial("0x^2 + x + 3");
		assertEquals(p.toString(), "x + 3.0");
	}

	@Test
	/*
	 * testPolynomial_toString_ExponentZero_Extreme2
	 * 
	 * Purpose: Checking the toString method with a polynomial that has a term with
	 * a 0 exponent
	 * 
	 * input:
	 * String: 6x^0 + 7
	 * 
	 * output:
	 * String: 13.0
	 */
	public void testPolynomial_toString_ExponentZero_Extreme2() {
		Polynomial p = new Polynomial("6x^0 + 7");
		assertEquals(p.toString(), "13.0");
	}

	@Test
	/*
	 * testPolynomial_toString_CoefficientOne_Extreme3
	 * 
	 * Purpose: Checking the toString method with a polynomial that has a term with
	 * a coefficient of 1
	 * 
	 * input:
	 * String: -1x^2 + 1x
	 * 
	 * output:
	 * String: -x^2 + x
	 */
	public void testPolynomial_toString_CoefficientOne_Extreme3() {
		Polynomial p = new Polynomial("-1x^2 + 1x");
		assertEquals(p.toString(), "-x^2 + x");
	}

	@Test
	/*
	 * testPolynomial_toString_ZeroPolynomial_Extreme4
	 * 
	 * Purpose: Checking the toString method with the 0 polynomial
	 * 
	 * input:
	 * String: 0
	 * 
	 * output:
	 * String: 0
	 */
	public void testPolynomial_toString_ZeroPolynomial_Extreme4() {
		Polynomial p = new Polynomial("0");
		assertEquals(p.toString(), "0");
	}
}