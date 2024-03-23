import data.models.Monomial;
import data.models.Polynomial;

import static business.logic.Operations.*;

public class Main {
    public static void main(String[] args) {
        /*Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(1, 3));
        p.addMonomial(new Monomial(-2, 2));
        p.addMonomial(new Monomial(6, 1));
        p.addMonomial(new Monomial(-5, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(1, 2));
        q.addMonomial(new Monomial(-1, 0));


        System.out.println("p(x) = " + p);
        System.out.println("q(x) = " + q + "\n");



        try {
            System.out.println("p + x = " + addPolynomials(p, q));
            System.out.println("p - x = " + subtractPolynomials(p, q));
            System.out.println("p * x = " + multiplyPolynomials(p, q));
            System.out.println(dividePolynomials(p, q));
            System.out.println("p' = " + derivePolynomial(p));
            System.out.println("Sp = " + integratePolynomial(p));

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial(1, 1));
        p.addMonomial(new Monomial(2, 0));

        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial(1, 1));
        q.addMonomial(new Monomial(2, 0));

        System.out.println("p(x) = " + p);
        System.out.println("q(x) = " + q + "\n");
        System.out.println(dividePolynomials(p, q));


    }

}