package ru.rsatu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Signal {



    public double[] getSignalMass(int numb, double step, double startValue){

        double[] signalMass = new double[numb];

        double x = startValue;

        for (int i = 0; i<numb; i++){
            signalMass[i] = signal(x);
            x+=step;
        }

        return signalMass;
    }

    public List<double[]> getSignalBlocks(double[] signal, int blockCnt){

        int step = signal.length/blockCnt;

        List<double[]> signalBlocks = new ArrayList<>();


        for (int i = 0; i<signal.length; i += step ){
            signalBlocks.add(Arrays.copyOfRange(signal, i, i+step));
        }
        return signalBlocks;
    }



    private double signal(double x){
        return Math.sin(x);
    }

}
