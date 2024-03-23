package data.models;

public class Monomial {

    private double coef; // coefficient

    private int deg; // degree

    public Monomial(double coef, int deg) {
        this.coef = coef;
        this.deg = deg;
    }

    public double getCoef() {
        return coef;
    }

    public void setCoef(double coef) {
        this.coef = coef;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public String toString() {
        if (coef == 0) {
            return "0";
        } else if (deg == 0) {
            return String.format(Double.toString(coef));
        } else if (deg == 1) {
            return coef + "x";
        } else {
            return coef + "x^" + deg;
        }
    }


}
