package com.github.redline;

import java.math.BigInteger;

public class BigIntegerSolution {
    private String forParse;
    private String result = "";
    private BigInteger maxDoubleSquare = BigInteger.ZERO;
    private BigInteger currentDoubleSquare;

    private void setForParse(String forParse) {
        this.forParse = forParse;
    }

    public void nextIteration(String forParse) {
        if (forParse == null) return;
        setForParse(forParse);
        tryParse();
    }

    private void tryParse() {
        String[] coordinates = forParse.trim().split(" ");
        if (coordinates.length != 6) return;
        BigInteger x1, y1, x2, y2, x3, y3;
        try {
            x1 = new BigInteger(coordinates[0]);
            y1 = new BigInteger(coordinates[1]);
            x2 = new BigInteger(coordinates[2]);
            y2 = new BigInteger(coordinates[3]);
            x3 = new BigInteger(coordinates[4]);
            y3 = new BigInteger(coordinates[5]);
        } catch (Exception e) {
            return;
        }

        if (!isIsosceles(x1, y1, x2, y2, x3, y3)) return;
        if (currentDoubleSquare.compareTo(maxDoubleSquare) > 0) {
            maxDoubleSquare = currentDoubleSquare;
            result = forParse;
        }
    }

    private boolean isIsosceles(BigInteger... params) {
        if (params.length != 6) return false;
        BigInteger dx12 = params[2].subtract(params[0]);
        BigInteger dx13 = params[4].subtract(params[0]);
        BigInteger dx23 = params[4].subtract(params[2]);
        BigInteger dy12 = params[3].subtract(params[1]);
        BigInteger dy13 = params[5].subtract(params[1]);
        BigInteger dy23 = params[5].subtract(params[3]);

        BigInteger length1 = dx12.pow(2).add(dy12.pow(2));
        BigInteger length2 = dx13.pow(2).add(dy13.pow(2));
        BigInteger length3 = dx23.pow(2).add(dy23.pow(2));

        if (length1.equals(length2) || length1.equals(length3) || length2.equals(length3)) {
            currentDoubleSquare = dx12.multiply(dy13).subtract(dx13.multiply(dy12)).abs();
            return true;
        }
        return false;
    }

    public String getResult() {
        return result;
    }

    public BigInteger getMaxDoubleSquare() {
        return maxDoubleSquare;
    }
}
