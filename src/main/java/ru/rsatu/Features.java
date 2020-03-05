package ru.rsatu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Features {

    public String getCurTimeStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH.mm");

        LocalDateTime now = LocalDateTime.now();

        return now.format(formatter);
    }


    public double[] calcAverage(List<double[]> list) {

        double[] resMas = new double[list.size()];
        int resMassIndex = 0;

        for (double[] curMass : list) {

            double sum = 0;
            double avg = 0;
            for (int i = 0; i < curMass.length; i++) {
                sum += curMass[i];
            }
            avg = sum / curMass.length;

            resMas[resMassIndex] = avg;
            resMassIndex++;
        }

        return resMas;
    }


    public double[] calcDispersion(List<double[]> signalBlocks, double[] avgs) {

        double[] resDisp = new double[avgs.length];

        int blockIndex = 0;

        for (double[] curBlock : signalBlocks) {

            double sum = 0;
            for (int i = 0; i < curBlock.length; i++) {
                sum += Math.pow((curBlock[i] - avgs[blockIndex]), 2);
            }
            resDisp[blockIndex] = sum / curBlock.length;
            blockIndex++;
        }

        return resDisp;
    }

}
