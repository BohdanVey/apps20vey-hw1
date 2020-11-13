package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    double avgTemp;
    double devTemp;
    double minTemp;
    double maxTemp;

    public TempSummaryStatistics(TemperatureSeriesAnalysis temp) {
        avgTemp = temp.average();
        devTemp = temp.deviation();
        minTemp = temp.min();
        maxTemp = temp.max();
    }
}
