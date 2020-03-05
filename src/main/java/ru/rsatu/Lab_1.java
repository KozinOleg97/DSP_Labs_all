package ru.rsatu;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public class Lab_1 {

    public void doLab(){
        Features features = new Features();

        OneDHaar oneDHaar = new OneDHaar();


        Signal signalGen = new Signal();

        double[] sample_1 = signalGen.getSignalMass(64, 0.09956785, 0);

        //List<double[]> signalBlocks = signalGen.getSignalBlocks(sample_1, 4);

        //double[] sample_1 = {6, 4, 13, 5, 9, 11, 13, 12, 10, 9, 4, 6, 13, 10, 8, 9};

        oneDHaar.inPlaceFastHaarWaveletTransform(sample_1);

        System.out.println("============================");

        oneDHaar.inPlaceFastInverseHaarWaveletTransform(oneDHaar.transformResult.get(oneDHaar.transformResult.size() - 1));

        ExcelHandler excelHandler = new ExcelHandler();
        Workbook newWb = excelHandler.createNewWb();

        newWb = excelHandler.addList(newWb, oneDHaar.transformResult, 0);

        newWb = excelHandler.addList(newWb, oneDHaar.reverseTransformApproximation, 1);

        excelHandler.saveWbToFile(newWb, " Отчёт " + features.getCurTimeStr());
    }
}
