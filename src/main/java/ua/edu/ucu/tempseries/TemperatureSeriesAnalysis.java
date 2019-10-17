package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatures;
    public TemperatureSeriesAnalysis() {
    }
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        final double MINTEMP = -273.0;
        this.temperatures = Arrays.copyOf(temperatureSeries,
                temperatureSeries.length);
        for (double t: temperatures) {
            if (t < MINTEMP) {
                throw new InputMismatchException();
            }
        }
    }
    //public double[] grtArr()
    private void check() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        check();
        int sum = 0;
        int avg;
        for (double t: temperatures) {
            sum += t;
        }
        avg = sum / temperatures.length;
        return avg;
    }

    public double deviation() {
        check();
        double averageTemp = this.average();
        double dev = 0;
        for (double t: temperatures) {
            dev += (t - averageTemp) * (t - averageTemp);
        }
        return Math.sqrt(dev / temperatures.length);
    }

    public double min() {
        check();
        double curMin = temperatures[0];
        for (double t: temperatures) {
            if (t < curMin) {
                curMin = t;
            }
        }
        return curMin;
    }

    public double max() {
        check();
        double curMax = temperatures[0];
        for (double t: temperatures) {
            if (t > curMax) {
                curMax = t;
            }
        }
        return curMax;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        check();
        double closest = temperatures[0] - tempValue;
        for (double t: temperatures) {
            if (Math.abs(closest) > Math.abs(t - tempValue)) {
                closest = t - tempValue;
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        check();
        int newLength = 0;
        for (double t: temperatures) {
            if (t < tempValue) {
                newLength += 1;
            }
        }

        double[] result = new double[newLength];
        int i = 0;
        for (double t: temperatures) {
            if (t < tempValue) {
                result[i] = t;
                i += 1;
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        check();
        int newLength = 0;
        for (double t: temperatures) {
            if (t >= tempValue) {
                newLength += 1;
            }
        }
        double[] result = new double[newLength];
        int i = 0;
        for (double t: temperatures) {
            if (t >= tempValue) {
                result[i] = t;
                i += 1;
            }
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() {
        check();
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        check();
        double[] result;
        result = new double[temperatures.length * 2];
        int i = 0;
        int totalSum = 0;
        for (double t: temperatures) {
            result[i] = t;
            totalSum += t;
            i++;
        }
        for (double t: temps) {
            result[i] = t;
            totalSum += t;
            i++;
        }
        this.temperatures = result;
        return totalSum;
    }
}
