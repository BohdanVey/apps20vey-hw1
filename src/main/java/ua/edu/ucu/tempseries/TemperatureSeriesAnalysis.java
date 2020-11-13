package ua.edu.ucu.tempseries;


import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    static final double MINIMUM_TEMPERATURE = -273.15;
    static final double[] DEFAULT_ARRAY = {0.0};

    private double[] temperatureSeries;
    private int length;


    public TemperatureSeriesAnalysis() {
        length = 1;
        temperatureSeries = DEFAULT_ARRAY;
    }


    public TemperatureSeriesAnalysis(double[] series) {
        // Check it data is correct
        checkEmptyData(series);
        length = series.length;
        this.temperatureSeries = series;
    }

    public int getLength() {
        return length;
    }

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }

    private void checkEmptyData(double[] series) {
        for (double temperature : series) {
            if (temperature < MINIMUM_TEMPERATURE) {
                throw new InputMismatchException();
            }
        }
    }

    private void checkLength() {
        if (length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        checkLength();
        double average = 0;
        for (int i = 0; i < length; i += 1) {
            average += temperatureSeries[i] / length;
        }
        return average;
    }

    public double deviation() {
        checkLength();
        double average = average();
        double ans = 0;
        for (int i = 0; i < length; i += 1) {
            double value = temperatureSeries[i] - average;
            ans += value * value / length;
        }
        return ans;
    }

    private double findMinMax(int multiplier) {
        checkLength();
        double now = multiplier * temperatureSeries[0];
        for (double temperature : temperatureSeries) {
            if (multiplier * temperature > now) {
                now = multiplier * temperature;
            }
        }
        return multiplier * now;
    }

    public double min() {
        return findMinMax(-1);
    }

    public double max() {
        return findMinMax(1);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkLength();
        double closestToValue = temperatureSeries[0] - tempValue;
        for (int i = 1; i < length; i += 1) {
            double now = temperatureSeries[i] - tempValue;
            if (Math.abs(closestToValue) > Math.abs(now)) {
                closestToValue = now;
            } else if (Math.abs(closestToValue) == now) {
                closestToValue = now;
            }
        }
        return closestToValue + tempValue;
    }

    private double[] getSomeValue(int multiplier, double tempValue) {
        checkLength();
        int lengthThen = 0;
        for (int i = 0; i < length; i += 1) {
            if (multiplier * temperatureSeries[i] < multiplier * tempValue) {
                lengthThen++;
            }
        }
        double[] arrayThen = new double[lengthThen];
        int indexNow = 0;
        for (int i = 0; i < length; i += 1) {
            if (multiplier * temperatureSeries[i] < multiplier * tempValue) {
                arrayThen[indexNow] = temperatureSeries[i];
                indexNow += 1;
            }
        }
        return arrayThen;
    }

    public double[] findTempsLessThen(double tempValue) {
        return getSomeValue(1, tempValue);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return getSomeValue(-1, tempValue);
    }

    public TempSummaryStatistics summaryStatistics() {
        checkLength();
        return new TempSummaryStatistics(this);
    }

    public int addTemps(double[] temps) {
        checkEmptyData(temps);
        length += temps.length;
        int prevLen = length - temps.length;
        if (length > temperatureSeries.length) {
            double[] newArray = new double[length * 2];
            if (length - temps.length >= 0) {
                System.arraycopy(temperatureSeries, 0, newArray, 0, prevLen);
            }
            temperatureSeries = newArray;
        }
        System.arraycopy(temps, 0, temperatureSeries, prevLen, temps.length);
        return length;
    }
}
