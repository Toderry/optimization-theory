package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main extends JFrame {
    public static void main(String[] args) {

        double a0 = -1.3;
        double b0 = 1;
        double E = 0.001;
        double[] e = new double[] {0.1,0.01,0.001, 0.0001,0.00001};
        double[] eo = new double[] {-Math.log(0.1),-Math.log(0.01), -Math.log(0.001), -Math.log(0.0001), -Math.log(0.00001)};
        double[] cnt = new double[e.length];
        int i = 0;
        double x , y, n;

        double[] buffer;
        for (;i < e.length;i++){
            buffer = Functions.perebor(a0, b0, e[i]);
            n = buffer[2];
            cnt[i] = (int) n;
        }
        EventQueue.invokeLater(() -> {

            DrawGraphs ex = new DrawGraphs("Перебор",eo,cnt);
            ex.setVisible(true);
        });


        i=0;
        for (;i < e.length;i++){
            buffer = Functions.binsearch(a0, b0, e[i]);
            n = buffer[2];
            cnt[i] = (int)n;
        }

        EventQueue.invokeLater(() -> {

            DrawGraphs eq = new DrawGraphs("Поразрядный поиск",eo,cnt);
            eq.setVisible(true);
        });



//        System.out.println("Перебор:" + Arrays.toString(Functions.perebor(a0, b0, E)));
//        System.out.println("Поразрядный поиск:" + Arrays.toString(Functions.binsearch(a0, b0, E)));
//        System.out.println("Дихтомия1:" + Arrays.toString(Functions.dihtomia1(a0, b0, E)));
//        System.out.println("Дихтомия2:" + Arrays.toString(Functions.dihtomia2(a0, b0, E)));
//        System.out.println("Золотое сечение:" + Arrays.toString(Functions.auratio(a0, b0, E)));
//        System.out.println("Фибоначчи:" + Arrays.toString(Functions.fibon(a0, b0, E)));
//        System.out.println("Парабол:" + Arrays.toString(Functions.parable(a0, b0, E)));

        buffer = Functions.perebor(a0, b0, E);
        System.out.printf("Перебор: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);

        buffer = Functions.binsearch(a0, b0, E);
        System.out.printf("Поразрядный поиск: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);

        buffer = Functions.dihtomia1(a0, b0, E);
        System.out.printf("Дихтомия1: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);

        buffer = Functions.dihtomia2(a0, b0, E);
        System.out.printf("Дихтомия2: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);

        buffer = Functions.auratio(a0, b0, E);
        System.out.printf("Золотое сечение: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);

        buffer = Functions.fibon(a0, b0, E);
        System.out.printf("Фибоначчи: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);

        buffer = Functions.parable(a0, b0, E);
        System.out.printf("Парабол: X* = %f, Ymin = %f, n = %d\n", buffer[0], buffer[1],(int)buffer[2]);


    }
}