package org.example;


import data.models.Monomial;
import data.models.Polynomial;
import org.junit.Test;

import static business.logic.Operations.*;
import static org.junit.Assert.assertEquals;

public class Tests {
    /*@Test
    public void testAdd() {
        assertEquals(5, 2+3);
    }*/

    @Test
    public void correctTestAddPolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(2, 1));
        q.addMonomial(new Monomial(1, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(3, 2));
        expected.addMonomial(new Monomial(7, 1));
        expected.addMonomial(new Monomial(-1, 0));

        String a = expected.toString();
        String b = addPolynomials(p, q).toString();
        assertEquals(a, b);
    }

    @Test
    public void correctTestSubtractPolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(2, 1));
        q.addMonomial(new Monomial(1, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(3, 2));
        expected.addMonomial(new Monomial(3, 1));
        expected.addMonomial(new Monomial(-3, 0));

        String a = expected.toString();
        String b = subtractPolynomials(p, q).toString();
        assertEquals(a, b);
    }


    @Test
    public void correctTestMultiplyPolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(2, 1));
        q.addMonomial(new Monomial(1, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(6, 3));
        expected.addMonomial(new Monomial(13, 2));
        expected.addMonomial(new Monomial(1, 1));
        expected.addMonomial(new Monomial(-2, 0));

        String a = expected.toString();
        String b = multiplyPolynomials(p, q).toString();
        assertEquals(a, b);
    }

    @Test
    public void correctTestDividePolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(1, 3));
        p.addMonomial(new Monomial(-2, 2));
        p.addMonomial(new Monomial(6, 1));
        p.addMonomial(new Monomial(-5, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(1, 2));
        q.addMonomial(new Monomial(-1, 0));

        String expected = "Quotient = " + "x-2" + "\nRemainder = " + "7x-7";
        assertEquals(expected, dividePolynomials(p, q));
    }

    @Test
    public void corectTestDerivePolynomial() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(6, 1));
        expected.addMonomial(new Monomial(5, 0));

        String a = expected.toString();
        String b = derivePolynomial(p).toString();
        assertEquals(a, b);
    }

    @Test
    public void correctTestIntegratePolynomial() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(1, 3));
        expected.addMonomial(new Monomial(2.5, 2));
        expected.addMonomial(new Monomial(-2, 1));

        String a = expected.toString();
        String b = integratePolynomial(p).toString();
        assertEquals(a, b);
    }


    /**
     * Wrong tests
     */

    @Test
    public void wrongTestAddPolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(2, 1));
        q.addMonomial(new Monomial(1, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(31, 2));
        expected.addMonomial(new Monomial(7, 1));

        String a = expected.toString();
        String b = addPolynomials(p, q).toString();
        assertEquals(a, b);
    }

    @Test
    public void wrongTestSubtractPolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(12, 1));
        q.addMonomial(new Monomial(1, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(-3, 2));
        expected.addMonomial(new Monomial(3, 1));
        expected.addMonomial(new Monomial(-3, 0));

        String a = expected.toString();
        String b = subtractPolynomials(p, q).toString();
        assertEquals(a, b);
    }

    @Test
    public void wrongTestMultiplyPolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(2, 1));
        q.addMonomial(new Monomial(1, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(6, 3));

        String a = expected.toString();
        String b = multiplyPolynomials(p, q).toString();
        assertEquals(a, b);
    }

    @Test
    public void wrongTestDividePolynomials() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(1, 3));
        p.addMonomial(new Monomial(-2, 2));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(1, 2));
        q.addMonomial(new Monomial(-1, 0));

        String expected = "Quotient = " + "x^19-2" + "\nRemainder = " + "-3x+1";
        assertEquals(expected, dividePolynomials(p, q));
    }

    @Test
    public void wrongTestDerivePolynomial() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(6, 4));
        expected.addMonomial(new Monomial(5, 5));
        expected.addMonomial(new Monomial(4, 3));
        expected.addMonomial(new Monomial(3, 2));

        String a = expected.toString();
        String b = derivePolynomial(p).toString();
        assertEquals(a, b);
    }

    @Test
    public void wrongTestIntegratePolynomial() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(3, 2));
        p.addMonomial(new Monomial(5, 1));
        p.addMonomial(new Monomial(-2, 0));

        Polynomial expected = new Polynomial();
        expected.addMonomial(new Monomial(1.9, 6));
        expected.addMonomial(new Monomial(2.5, 5));
        expected.addMonomial(new Monomial(-2, 4));
        expected.addMonomial(new Monomial(-2, 1));

        String a = expected.toString();
        String b = integratePolynomial(p).toString();
        assertEquals(a, b);
    }


}
