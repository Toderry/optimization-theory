package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main extends JFrame {
    public static void main(String[] args) {

        final double a0 = -1.3;
        double b0 = 1;
        double E = 0.001;
        double[] e = new double[] {0.1,0.01,0.001, 0.0001,0.00001};
        double[] eo = new double[] {-Math.log(0.1),-Math.log(0.01), -Math.log(0.001), -Math.log(0.0001), -Math.log(0.00001)};
        double[] cnt = new double[e.length];
        double x , y;

        EventQueue.invokeLater(() -> {
            int i = 0;
            double[] buffer;
            double n;
            for (;i < e.length;i++){
                buffer = Functions.dihtomia1(a0, b0, e[i]);
                n = buffer[2];
                cnt[i] = (int) n;
            }
            DrawGraphs ex = new DrawGraphs("График");

            ex.updateDataset("Дихтомия1", eo,cnt);

            for (i = 0; i < e.length;i++){
                buffer = Functions.binsearch(a0, b0, e[i]);
                n = buffer[2];
                cnt[i] = (int)n;
            }
            System.out.println(Arrays.toString(cnt));
            ex.updateDataset("Поразрядный поиск", eo,cnt);
            ex.initUI();

            for (i = 0; i < e.length;i++){
                buffer = Functions.dihtomia2(a0, b0, e[i]);
                n = buffer[2];
                cnt[i] = (int)n;
            }
            System.out.println(Arrays.toString(cnt));
            ex.updateDataset("Дихтомия2", eo,cnt);
            ex.initUI();

            for (i = 0; i < e.length;i++){
                buffer = Functions.auratio(a0, b0, e[i]);
                n = buffer[2];
                cnt[i] = (int)n;
            }
            System.out.println(Arrays.toString(cnt));
            ex.updateDataset("Золотое сечение", eo,cnt);
            ex.initUI();


            for (i = 0; i < e.length;i++){
                buffer = Functions.fibon(a0, b0, e[i]);
                n = buffer[2];
                cnt[i] = (int)n;
            }
            System.out.println(Arrays.toString(cnt));
            ex.updateDataset("Фибоначчи", eo,cnt);
            ex.initUI();

            for (i = 0; i < e.length;i++){
                buffer = Functions.parable(a0, b0, e[i]);
                n = buffer[2];
                cnt[i] = (int)n;
            }
            System.out.println(Arrays.toString(cnt));
            ex.updateDataset("Метод парабол", eo,cnt);
            ex.initUI();



            ex.setVisible(true);
        });
        double[] buffer;

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