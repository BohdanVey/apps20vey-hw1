package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    public double avgTemp;
    public double devTemp;
    public double minTemp;
    public double maxTemp;

    public TempSummaryStatistics(TemperatureSeriesAnalysis temp) {
        avgTemp = temp.average();
        devTemp = temp.deviation();
        minTemp = temp.min();
        maxTemp = temp.max();
    }
}
