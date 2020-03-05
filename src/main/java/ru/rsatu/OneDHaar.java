package ru.rsatu;

import java.util.ArrayList;
import java.util.List;

public class OneDHaar {

    public List<double[]> transformResult = new ArrayList<>();
    public List<double[]> reverseTransformResult = new ArrayList<>();
    public List<double[]> reverseTransformApproximation = new ArrayList<>();


    public boolean isPowerOf2(int n) {
        if (n < 1) {
            return false;
        } else {
            double p_of_2 = (Math.log(n) / Math.log(2));
            return Math.abs(p_of_2 - (int) p_of_2) == 0;
        }
    }

    public void displaySample(double[] sample) {
        System.out.print("Sample: ");
        for (double v : sample) {
            System.out.print(v + " ");
        }
        System.out.println();
    }


    // compute in-place fast haar wavelet transform.
    public void inPlaceFastHaarWaveletTransform(double[] sample) {
        if (sample.length == 0 || sample.length == 1) {
            return;
        }
        if (!isPowerOf2(sample.length)) {
            return;
        }
        final int num_sweeps = (int) (Math.log(sample.length) / Math.log(2));
        inPlaceFastHaarWaveletTransformForNumIters(sample, num_sweeps);
    }

    // apply in-place fast haar wavelet transform for num_sweeps sweeps.
    public void inPlaceFastHaarWaveletTransformForNumIters(double[] sample, int num_iters) {


        if (sample.length == 0 || sample.length == 1) {
            return;
        }
        if (!isPowerOf2(sample.length)) {
            return;
        }


        displaySample(sample);
        transformResult.add(sample.clone());

        int I = 1; // index increment
        int GAP_SIZE = 2; // number of elements b/w averages
        int NUM_SAMPLE_VALS = sample.length; // number of values in the sample
        final int n = (int) (Math.log(NUM_SAMPLE_VALS) / Math.log(2));
        if (num_iters < 1 || num_iters > n) {
            return;
        }
        double a;
        double c;
        for (int ITER_NUM = 1; ITER_NUM <= num_iters; ITER_NUM++) {
            NUM_SAMPLE_VALS /= 2;
            for (int K = 0; K < NUM_SAMPLE_VALS; K++) {
                a = (sample[GAP_SIZE * K] + sample[GAP_SIZE * K + I]) / 2;
                c = (sample[GAP_SIZE * K] - sample[GAP_SIZE * K + I]) / 2;
                sample[GAP_SIZE * K] = a;
                sample[GAP_SIZE * K + I] = c;
            }
            I = GAP_SIZE;
            GAP_SIZE *= 2;

            displaySample(sample);
            transformResult.add(sample.clone());
        }
    }

    // do the n-th sweep of In-Place Fast Haar Wavelet Transform
    public  void doNthSweepOfInPlaceFastHaarWaveletTransform(double[] sample, int sweep_number) {
        if (sample.length % 2 != 0 || sample.length == 0) {
            return;
        }
        int I = (int) (Math.pow(2.0, sweep_number - 1));
        int GAP_SIZE = (int) (Math.pow(2.0, sweep_number));
        int NUM_SAMPLE_VALS = sample.length;
        final int n = (int) (Math.log(NUM_SAMPLE_VALS) / Math.log(2));
        if (sweep_number < 1 || sweep_number > n) {
            return;
        }
        double a;
        double c;
        NUM_SAMPLE_VALS /= (int) (Math.pow(2.0, sweep_number));
        for (int K = 0; K < NUM_SAMPLE_VALS; K++) {
            a = (sample[GAP_SIZE * K] + sample[GAP_SIZE * K + I]) / 2;
            c = (sample[GAP_SIZE * K] - sample[GAP_SIZE * K + I]) / 2;
            sample[GAP_SIZE * K] = a;
            sample[GAP_SIZE * K + I] = c;
        }
    }




    public  void inPlaceFastInverseHaarWaveletTransform(double[] sample) {
        displaySample(sample);
        reverseTransformResult.add(sample.clone());

        int len = sample.length;

        int n = sample.length;
        n = (int) (Math.log(n) / Math.log(2.0));
        int GAP_SIZE = (int) (Math.pow(2.0, n - 1));
        int JUMP = 2 * GAP_SIZE;
        int NUM_FREQS = 1;
        for (int SWEEP_NUM = n; SWEEP_NUM >= 1; SWEEP_NUM--) {

            double[] approximationMass = new double[len];
            int aproxIndex = 0;

            for (int K = 0; K < NUM_FREQS; K++) {
                double aPlus = sample[JUMP * K] + sample[JUMP * K + GAP_SIZE];
                double aMinus = sample[JUMP * K] - sample[JUMP * K + GAP_SIZE];
                sample[JUMP * K] = aPlus;
                sample[JUMP * K + GAP_SIZE] = aMinus;


                for (int ind=K * (GAP_SIZE*2); ind<GAP_SIZE + K * (GAP_SIZE*2); ind++){
                    approximationMass[ind] = aPlus;
                }
                for (int ind=GAP_SIZE + K * (GAP_SIZE*2); ind<(GAP_SIZE*2) + K * (GAP_SIZE*2); ind++){
                    approximationMass[ind] = aMinus;
                }

            }
            JUMP = GAP_SIZE;
            GAP_SIZE /= 2;
            NUM_FREQS *= 2;

            displaySample(sample);
            reverseTransformResult.add(sample.clone());
            reverseTransformApproximation.add(approximationMass);
        }
    }

}
