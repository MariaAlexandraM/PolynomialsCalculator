package business.logic;

import data.models.Monomial;
import data.models.Polynomial;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operations {

    public static Polynomial addPolynomials(Polynomial p, Polynomial q) {

        Polynomial r = new Polynomial();
        for (Map.Entry<Integer, Double> i : p.polynomial.entrySet()) {
            int deg = i.getKey();
            double coef = i.getValue();
            r.addMonomial(new Monomial(coef, deg));
        }
        for (Map.Entry<Integer, Double> i : q.polynomial.entrySet()) {
            int deg = i.getKey();
            double coef = i.getValue();
            r.addMonomial(new Monomial(coef, deg));
        }
        for (Map.Entry<Integer, Double> i : r.polynomial.entrySet()) { // trebe sa rotunjesc pt ca altfel nu scade bine double-urile: https://stackoverflow.com/questions/15625556/adding-and-subtracting-doubles-are-giving-strange-results
            int deg = i.getKey();
            double coef = i.getValue();
            BigDecimal bd = new BigDecimal(coef);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            r.polynomial.put(deg, bd.doubleValue()); // inlocuieste valoarea de pe slotu ala cu asta rotunjita
        }
        return r;
    }


    public static Polynomial subtractPolynomials(Polynomial p, Polynomial q) {

        Polynomial r = new Polynomial();
        for (Map.Entry<Integer, Double> i : p.polynomial.entrySet()) {
            int deg = i.getKey();
            double coef = i.getValue();
            r.addMonomial(new Monomial(coef, deg));
        }
        for (Map.Entry<Integer, Double> i : q.polynomial.entrySet()) {
            int deg = i.getKey();
            double coef = i.getValue();
            r.addMonomial(new Monomial(-1 * coef, deg));
        }
        for (Map.Entry<Integer, Double> i : r.polynomial.entrySet()) {
            int deg = i.getKey();
            double coef = i.getValue();

            DecimalFormat df = new DecimalFormat("#.###"); // https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
            df.setRoundingMode(RoundingMode.CEILING);
            double aux = Double.parseDouble(df.format(coef));
            r.polynomial.put(deg, aux);
        }
        return r;
    }

    public static Polynomial multiplyPolynomials(Polynomial p, Polynomial q) {

        Polynomial r = new Polynomial();
        for (Map.Entry<Integer, Double> i : p.polynomial.entrySet()) {
            for (Map.Entry<Integer, Double> j : q.polynomial.entrySet()) {
                int degP = i.getKey();
                int degQ = j.getKey();
                double coefP = i.getValue();
                double coefQ = j.getValue();

                int degR = degP + degQ;
                double coefR = coefP * coefQ;
                BigDecimal bd = new BigDecimal(coefR);
                bd = bd.setScale(3, RoundingMode.HALF_UP);
                coefR = bd.doubleValue();
                r.addMonomial(new Monomial(coefR, degR));
            }
        }
        return r;
    }


    public static String dividePolynomials(Polynomial p, Polynomial q) {

        if(q.isZero())
            return "Nu se poate face impartirea cu zero!";

        if(p.isZero())
            return  ("Quotient = 0\nRemainder = 0");

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        for(Map.Entry<Integer, Double> i : p.polynomial.entrySet()) { // copie la P in remainder pt ca poate fi rest, sa nu se imparta si catu sa ramana 0 si restu tot P-u
            int deg = i.getKey();
            double coef = i.getValue();
            remainder.addMonomial(new Monomial(coef, deg));
        }

        while(remainder.polynomial.firstKey() > 0 && q.polynomial.firstKey() > 0 && remainder.polynomial.firstKey() >= q.polynomial.firstKey()) {
            int degR = remainder.polynomial.firstKey();
            int degQ = q.polynomial.firstKey();
            int deg = degR - degQ;
            double coefR = remainder.polynomial.get(degR);
            double coefQ = q.polynomial.get(degQ);
            double coef = coefR / coefQ;

            Monomial term = new Monomial(coef, deg);
            quotient.addMonomial(term);

            Polynomial aux = new Polynomial();
            aux.addMonomial(term); // nou termen gasit si de pus in cat. numa pe asta il inmultesc de il pun in noul remainder gen sub bara sa scad din remainder
            Polynomial prod = multiplyPolynomials(aux, q);
            Polynomial res = subtractPolynomials(remainder, prod); // new remainder
            remainder = res;
            if(remainder.isZero())
                break;
        }
        return ("Quotient = " + quotient + "\nRemainder = " + remainder);
    }

    public static Polynomial derivePolynomial(Polynomial p) {

        Polynomial r = new Polynomial();
        for (Map.Entry<Integer, Double> i : p.polynomial.entrySet()) {
            int deg = i.getKey();
            double coef = i.getValue();
            if (deg > 0) {
                r.addMonomial(new Monomial(coef * deg, deg - 1));
            }
        }
        for (Map.Entry<Integer, Double> i : r.polynomial.entrySet()) { // trebe sa rotunjesc pt ca altfel nu scade bine double-urile: https://stackoverflow.com/questions/15625556/adding-and-subtracting-doubles-are-giving-strange-results
            int deg = i.getKey();
            double coef = i.getValue();
            BigDecimal bd = new BigDecimal(coef);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            r.polynomial.put(deg, bd.doubleValue());
        }
        return r;
    }

    public static Polynomial integratePolynomial(Polynomial p) {

        Polynomial r = new Polynomial();
        for (Map.Entry<Integer, Double> i : p.polynomial.entrySet()) {
            int deg = i.getKey() + 1;
            double coef = i.getValue() / deg;
            r.addMonomial(new Monomial(coef, deg));
        }
        for (Map.Entry<Integer, Double> i : r.polynomial.entrySet()) { // trebe sa rotunjesc pt ca altfel nu scade bine double-urile: https://stackoverflow.com/questions/15625556/adding-and-subtracting-doubles-are-giving-strange-results
            int deg = i.getKey();
            double coef = i.getValue();
            BigDecimal bd = new BigDecimal(coef);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            r.polynomial.put(deg, bd.doubleValue());
        }
        return r;
    }

    public static boolean validatePolynomial(String input) { // https://stackoverflow.com/questions/36490757/regex-for-polynomial-expression

        //String regex = "^([+-]?(?:\\d*\\.\\d+|\\d+)x\\^(\\d+)|[+-]?(?:\\d*\\.\\d+|\\d+)x|[+-]?(?:\\d*\\.\\d+|\\d+))(?:[+-]([+-]?(?:\\d*\\.\\d+|\\d+)x\\^(\\d+)|[+-]?(?:\\d*\\.\\d+|\\d+)x|[+-]?(?:\\d*\\.\\d+|\\d+)))*$";
        String regex = "^[+-]?(?:(?:\\d*\\.\\d+|\\d+)?x(?:\\^\\d+)?|\\d*\\.\\d+|\\d+)(?:[+-](?:(?:\\d*\\.\\d+|\\d+)?x(?:\\^\\d+)?|\\d*\\.\\d+|\\d+))*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static Polynomial stringToPolynomial(String input) {

        Polynomial r = new Polynomial();
        String[] strings = input.split("(?=[+-])"); // splits the string after the sign (+ or -), deci pe monoame
        for (String i : strings) {
            System.out.println(i);
            if (!i.isEmpty()) { // daca am termen, pentru ca daca nu as avea, it would return ""
                double coef = 1.0;
                int deg = 0;
                if (i.contains("x")) {
                    int xIndex = i.indexOf("x");
                    if (xIndex > 0) { // daca x nu ii primu, deci inseamna ca am coeficient
                        String coefStr = i.substring(0, xIndex); //parse tot de la 0 la xIndex, in cazu in care am float
                        if (coefStr.equals("-")) {
                            coef = -1.0;
                        } else if (!coefStr.equals("+")) { // parse as normal DOuble value
                            coef = Double.parseDouble(coefStr);
                        }
                        deg = 1;
                    } else if (xIndex == 0) { // daca x e primu deci coef ii 1
                        if (i.length() > 1 && i.charAt(1) == '-') { // !! String nu ii char[] .. nu pot i[1] .. urit f urit
                            coef = -1.0;
                        }
                        deg = 1;
                    }
                    if (xIndex < i.length() - 1) { // daca am termen dupa x, i ll parse it
                        deg = Integer.parseInt(i.substring(xIndex + 2));
                    }
                } else {
                    coef = Double.parseDouble(i);
                    deg = 0;
                }
                r.addMonomial(new Monomial(coef, deg));
            }
        }
        return r;
    }

}