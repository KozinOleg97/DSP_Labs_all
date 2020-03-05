package ru.rsatu;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class Lab_2 {

    public void doLab(){
        Features features = new Features();

        OneDHaar oneDHaar = new OneDHaar();


        Signal signalGen = new Signal();

        double[] sample_1 = signalGen.getSignalMass(64, 0.09956785, 0);

        List<double[]> signalBlocks = signalGen.getSignalBlocks(sample_1, 16);

        double[] average = features.calcAverage(signalBlocks);

        double[] dispersion = features.calcDispersion(signalBlocks, average);

        double[] inversion = features.calcInversion(signalBlocks);
    }

}
