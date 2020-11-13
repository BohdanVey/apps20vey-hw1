package ua.edu.ucu.tempseries;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.*;

public class TemperatureSeriesAnalysisTest {
    public void testAverage(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {-1.0, -2.0, 1.0, 2.0};
        testAverage(temperatureSeries, 0);
        double[] temperatureSeries2 = {-1.0, -2.0, 1.0, 2.0, 100.0};
        testAverage(temperatureSeries2, 20);
    }

    public void testDeviation(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {-1.0, -2.0, 1.0, 2.0};
        testDeviation(temperatureSeries, 2.5);
        double[] temperatureSeries2 = {-1.0, -2.0, 1.0, 2.0, 100.0};
        testDeviation(temperatureSeries2, 1602);
    }

    public void testMin(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {-1.0, -2.0, 1.0, 2.0};
        testMin(temperatureSeries, -2.0);
        double[] temperatureSeries2 = {-100.0, -2.0, 1.0, 2.0, 100.0};
        testMin(temperatureSeries2, -100.0);
    }

    public void testMax(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {-1.0, -2.0, 1.0, 2.0};
        testMax(temperatureSeries, 2);
        double[] temperatureSeries2 = {-110.0, -2.0, 1.0, 2.0, 100.0};
        testMax(temperatureSeries2, 100);

    }

    public void testClosest0(double[] temperatureSeries, double expResult) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosest0() {
        double[] temperatureSeries = {-1.0, -2.0, 0, 1.0, 2.0};
        testClosest0(temperatureSeries, 0);
        double[] temperatureSeries2 = {-110.0, -2.0, 1.0, 2.0, 100.0};
        testClosest0(temperatureSeries2, 1.0);
        double[] temperatureSeries3 = {-110.0, -2.0, -0.5, 1.0, 2.0, 100.0};
        testClosest0(temperatureSeries3, -0.5);
        double[] temperatureSeries4 = {-110.0, -2.0, -0.5, 0.5, 2.0, 100.0};
        testClosest0(temperatureSeries4, 0.5);
    }

    public void testClosestToValue(double[] temperatureSeries, double expResult, double value) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualResult = seriesAnalysis.findTempClosestToValue(value);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testClosestToValue() {
        double[] temperatureSeries = {-1.0, -2.0, 0, 1.0, 2.0};
        testClosestToValue(temperatureSeries, 2, 100);

        double[] temperatureSeries2 = {-110.0, -2.0, 1.0, 2.0, 100.0};
        testClosestToValue(temperatureSeries2, 2, 1.5);

        double[] temperatureSeries3 = {-110.0, -2.0, -0.5, 1.0, 2.0, 100.0};
        TemperatureSeriesAnalysis seriesAnalysis3 = new TemperatureSeriesAnalysis(temperatureSeries3);
        double expResult3 = 100;
        double actualResult3 = seriesAnalysis3.findTempClosestToValue(51);
        assertEquals(expResult3, actualResult3, 0.00001);
        testClosestToValue(temperatureSeries3, 100, 52);

        double[] temperatureSeries4 = {-110.0, -2.0, -0.5, 0.5, 2.0, 100.0};
        testClosestToValue(temperatureSeries4, 0.5, 0);
    }

    public void testFindTempsLessThen(double[] temperatureSeries, double[] expResult, double value) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsLessThen(value);
        for (int i = 0; i < expResult.length; i += 1) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }
        assertEquals(actualResult.length, expResult.length);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {-1.0, -2.0, 0, 1.0, 2.0};
        double[] expResult = {-1.0, -2.0};
        testFindTempsLessThen(temperatureSeries, expResult, 0);
        double[] temperatureSeries2 = {-1.0, -2.0, 0, 1.0, 2.0, 100.0};
        double[] expResult2 = {};
        testFindTempsLessThen(temperatureSeries2, expResult2, -5);
    }

    public void testFindTempsGreaterThen(double[] temperatureSeries, double[] expResult, double value) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualResult = seriesAnalysis.findTempsGreaterThen(value);
        for (int i = 0; i < expResult.length; i += 1) {
            assertEquals(expResult[i], actualResult[i], 0.00001);
        }
        assertEquals(actualResult.length, expResult.length);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {-1.0, -2.0, 0, 1.0, 2.0};
        double[] expResult = {1.0, 2.0};
        testFindTempsGreaterThen(temperatureSeries, expResult, 0);
        double[] temperatureSeries2 = {-1.0, -2.0, 0, 1.0, 2.0, 100.0};
        double[] expResult2 = {-1.0, -2.0, 0, 1.0, 2.0, 100.0};
        testFindTempsGreaterThen(temperatureSeries2, expResult2, -5);
    }

    public void testAddElement(ArrayList<Double> arr, TemperatureSeriesAnalysis seriesAnalysis) {
        for (int i = 0; i < seriesAnalysis.length; i += 1)
            assertEquals(arr.get(i), seriesAnalysis.temperatureSeries[i], 0.00001);
    }

    @Test
    public void testAddElement() {
        Double[] lst = {0.0};
        ArrayList<Double> series = new ArrayList<>(Arrays.asList(lst));
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis();
        testAddElement(series, seriesAnalysis2);
        Double[] temperatureSeries = {-1.0, -2.0, 0.0, 1.0, 2.0};
        double[] temperatureSeriesCopy = {-1.0, -2.0, 0.0, 1.0, 2.0};
        ArrayList<Double> list = new ArrayList<>(Arrays.asList(temperatureSeries));
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeriesCopy);
        seriesAnalysis.addTemps(temperatureSeriesCopy);
        list.addAll(Arrays.asList(temperatureSeries));
        Double[] temperatureSeries2 = {-1.0, -2.0, 0.0, 1.0, 2.0, 200.0, 51.0, 32.0};
        double[] temperatureSeriesCopy2 = {-1.0, -2.0, 0.0, 1.0, 2.0, 200.0, 51.0, 32.0};

        seriesAnalysis.addTemps(temperatureSeriesCopy2);
        Collections.addAll(list, temperatureSeries2);
        testAddElement(list, seriesAnalysis);

    }

    public void testTempSummaryStatistics(double[] temperatureSeries) {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics summaryStatistics = seriesAnalysis.summaryStatistics();
        assertEquals(summaryStatistics.avgTemp, seriesAnalysis.average(), 0.000001);
        assertEquals(summaryStatistics.devTemp, seriesAnalysis.deviation(), 0.000001);
        assertEquals(summaryStatistics.maxTemp, seriesAnalysis.max(), 0.000001);
        assertEquals(summaryStatistics.minTemp, seriesAnalysis.min(), 0.000001);

    }

    @Test
    public void testTempSummaryStatistics() {
        double[] temperatureSeries = {-1.0, -2.0, 0, 1.0, 2.0};
        testTempSummaryStatistics(temperatureSeries);
    }

    @Test(expected = InputMismatchException.class)
    public void IllegalTest(){
        double arr[] = {-275.0,5.0,10.0,5.0,62.0,53.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(arr);

    }
    @Test(expected = IllegalArgumentException.class)
    public void IllegalLengthTest(){
        double arr[] = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(arr);
        seriesAnalysis.average();
    }
}
