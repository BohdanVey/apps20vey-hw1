package ua.edu.ucu.tempseries;


import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {


    double[] temperatureSeries;
    int length;
    double MINIMUM_TEMPERATURE = -273;
    double[] DEFAULT_ARRAY = {0.0};

    private void checkData(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
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

    public TemperatureSeriesAnalysis() {
        // Create class, without data
        temperatureSeries = DEFAULT_ARRAY;
        length = 1;
    }


    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        // Check it data is correct
        checkData(temperatureSeries);
        this.temperatureSeries = temperatureSeries;
        length = temperatureSeries.length;
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
            ans += (temperatureSeries[i] - average) * (temperatureSeries[i] - average) / length;
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
        double ClosestToValue = temperatureSeries[0] - tempValue;
        for (int i = 1; i < length; i += 1) {
            if (Math.abs(ClosestToValue) > Math.abs(temperatureSeries[i] - tempValue))
                ClosestToValue = temperatureSeries[i] - tempValue;
            else if (Math.abs(ClosestToValue) == temperatureSeries[i] - tempValue)
                ClosestToValue = temperatureSeries[i] - tempValue;
        }
        return ClosestToValue + tempValue;
    }

    private double[] getSomeValue(int multiplier, double tempValue) {
        checkLength();
        int lengthThen = 0;
        for (int i = 0; i < length; i += 1)
            if (multiplier * temperatureSeries[i] < multiplier * tempValue) {
                lengthThen++;
            }
        double[] arrayThen = new double[lengthThen];
        int indexNow = 0;
        for (int i = 0; i < length; i += 1)
            if (multiplier * temperatureSeries[i] < multiplier * tempValue) {
                arrayThen[indexNow] = temperatureSeries[i];
                indexNow += 1;
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
        checkData(temps);
        length += temps.length;
        if (length > temperatureSeries.length) {
            double[] newArray = new double[length * 2];
            if (length - temps.length >= 0) System.arraycopy(temperatureSeries, 0, newArray, 0, length - temps.length);
            temperatureSeries = newArray;
        }
        System.arraycopy(temps, 0, temperatureSeries, length - temps.length, temps.length);
        return length;
    }
}
