package data.models;

import java.util.*;

public class Polynomial {
    public TreeMap<Integer, Double> polynomial; // = new TreeMap<>();
                // grad,    coef

    public Polynomial() {
        this.polynomial = new TreeMap<>(Comparator.reverseOrder());
    }

    public void addMonomial(Monomial m) {
        int deg = m.getDeg();
        double coef = m.getCoef();
        if(this.polynomial.containsKey(deg)) { // grad existent
            double currentCoefficient = polynomial.get(deg);
            coef += currentCoefficient;
        }
        if(coef != 0) {
            polynomial.put(deg, coef);
        }
        else polynomial.remove(deg);
    }

    public boolean isZero() { // pentru impartire
        for(Double i : this.polynomial.values()) { // coeficientii
            if(i != 0.0) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        boolean first = true;
        for (Map.Entry<Integer, Double> i : this.polynomial.entrySet()) {
            double coef = i.getValue();
            int deg = i.getKey();
            if (coef != 0) {
                if (!first && coef > 0) {
                    res.append("+");
                }
                if (coef < 0) {
                    res.append("-");
                    coef = -coef;
                }
                if ((coef != 1 && coef != -1) || deg == 0) {
                    int intCoef = (int) coef;
                    if (intCoef == coef) {
                        res.append(intCoef);
                    } else {
                        res.append(coef);
                    }
                }
                if (deg > 0) {
                    res.append("x");
                    if (deg > 1) {
                        res.append("^").append(deg);
                    }
                }
                first = false;
            }
        }
        if(res.length() == 0) {
            res.append("0");
        }
        return res.toString();
    }

}