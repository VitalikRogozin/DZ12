package com.Geekbrains;

import java.util.Arrays;

public class ArrCalculation {
    static final int SIZE = 10_000_000;
    static final int HALF = SIZE / 2;
    float[] arr = new float[SIZE];

    public void doCalculation (){
        Arrays.fill(arr, 1);

        long start = System.currentTimeMillis();
        float [] a1 = new float[HALF];
        float [] a2 = new float[HALF];

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);


        Thread thread1 = new Thread (() -> {
            calculation(a1);
        });

        Thread thread2 = new Thread (() -> {
            calculation(a2);
        });



        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch(Exception e){
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);



        long finish = System.currentTimeMillis() - start;
        System.out.println(finish);
    }

    private void calculation(float[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

        }

    }
}