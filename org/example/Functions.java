package org.example;

public class Functions {



// метод перебора

    public static double fun(double x) {
        return 5*Math.sin(2*x)+x*x;
    }

    //метод перебора
    public static double[] perebor(double a, double b, double E) {
        double n = (b - a) / E;
        double ymin = fun(a);
        double xi = a, xmin = a;
        for (int i = 0; i < n + 2; i++) {
            if (ymin > fun(xi)) {
                ymin = fun(xi);
                xmin = xi;
            }
            xi = a + i * ((b - a) / n);
        }
        //System.out.printf("Перебор: X* = %f, Ymin = %f, n = %f\n", xmin, ymin,n);
        double[] result = new double[]{xmin,ymin,n};
        return result;

    }

    //Золотое сечение
    public static double[] auratio(double a, double b, double E) {
        double Q = (Math.sqrt(5) - 1) / 2, q = 1-Q, x1, x2;
        x1 = a + (b - a) * q;
        x2 = b - (x1 - a);
        int n = (int) (Math.log((2 * E) /(b - a) ) / (Math.log(Q)));

        for (int i = 0; i < n; i++) {

            if (fun(x1) <= fun(x2)) {
                b = x2;
                x2 = x1;
                x1 = a + b - x1;
            } else {
                a = x1;
                x1 = x2;
                x2 = a + b - x2;
            }
        }

        //System.out.printf("Золотое сечение: X* = %f, Ymin = %f, n = %d\n", (a + b) / 2, fun((a + b) / 2),n);
        double[] result = new double[]{(a + b) / 2,fun((a + b) / 2), n};
        return result;

    }

    //метод дихотомии(2)
    public static double[] dihtomia2(double a, double b, double E) {
        double x0=0,x1=0,x2=0;
        int n=0;
        while (Math.abs((b-a)/2)>E) {
            x0 = a + ((b - a) / 4);
            x1 = a + 2 * ((b - a) / 4);
            x2 = a + 3 * ((b - a) / 4);

            if (fun(x0) <= fun(x1)) {
                b = x1;
            } else if (fun(x1) <= fun(x2)) {
                a = x0;
                b = x2;
            } else {
                a = x1;
            }
            n++;
        }
        //System.out.printf("Дихтомия2: X* = %f, Ymin = %f, n = %d\n", ((a+b)/2), fun((a+b)/2),n);
        double[] result = new double[]{(a + b) / 2,fun((a+b)/2), n};
        return result;

    }

    //метод дихотомии(1)
    public static double[] dihtomia1(double a, double b, double E) {
        double delta = E,x1,x2;
        int n = 0;
        while (Math.abs((b-a)/2)>E){
            x1 = (a+b-delta)/2;
            x2 = (a+b+delta)/2;
            if (fun(x1)<=fun(x2)) {
                b = x2;
            }
            else{
                a = x1;
            }
            n++;
        }
        //System.out.printf("Дихтомия1: X* = %f, Ymin = %f, n = %d\n", (a+b)/2, fun((a+b)/2),n);
        double[] result = new double[]{(a+b)/2, fun((a+b)/2), n};
        return result;

    }

    public static double[] binsearch(double a, double b, double E) {
        double delta = (b-a)/4;
        double x0 = a;
        double x1=0;
        int n = 0;
        while (Math.abs(delta)>E){
            x1 = x0 + delta;
            if (fun(x0)>fun(x1)){
                x0 = x1;
                if (a<x0 && x0<b){
                    x1 = x0 +delta;
                }
            }
            else {
                x0 = x1;
                delta = (-delta)/4;
            }
            n++;
        }
        //System.out.printf("Поразрядный поиск: X* = %f, Ymin = %f, n = %d\n", x0, fun(x0),n);

        double[] result = new double[]{x0,fun(x0), n};
        return result;

    }


    //метод парабол
    public static double[] parable(double a, double b, double E) {

            double x1 = a;
            double x2 = (a + b) / 2;
            double x3 = b;
            double x;
            int N = 0;
            double[] result;

            while (fun(x1) <= fun(x2) && fun(x2) >= fun(x3)) {
                x2 = (a + x2) / 2;
                N += 3;
            }

            x = (x1 + x2) / 2 - (x3 - x2) / 2 * (
                    (fun(x3) - fun(x1)) * (x2 - x1) / (x3 - x1) / Math.pow ((fun(x2) - fun(x1)),-1));

            while (Math.abs(x3 - x1) > E) {
                x = (x1 + x2) / 2 - (x3 - x2) / 2 * (
                        Math.pow(((fun(x3) - fun(x1)) * (x2 - x1) / (x3 - x1) / (fun(x2) - fun(x1)) - 1) ,-1));
                N += 3;
                if (fun(x) < fun(x2)) {
                    if (x > x2) {
                        x1 = x2;
                        x2 = x;
                    }
                    else{
                        x3 = x2;
                        x2 = x;
                    }
                }
                else{
                    if (x < x2) {
                        x1 = x;
                    }
                    else {
                        x3 = x;
                    }
                }
            }
        //System.out.printf("Парабол: X* = %f, Ymin = %f, n = %d\n", x, fun(x),N);
            result = new double[] {x,fun(x),N};
            return result;
        }



    //метод Фибоначчи
    public static double[] fibon(double a, double b, double E) {
        double fi = ((Math.pow(5,(0.5))-1)/2);
        double F = ((Math.pow(5,(0.5))+1)/2);
        double n1 = ((Math.log((b-a)*Math.pow(5,(0.5))/E)/Math.log(F))-3);
        int n = (int) Math.ceil(n1);
        double x1 = a+(1-fi)*(b-a);
        double x2 = a+fi*(b-a);
        for (int i = 0; i < n; i++){
            if (fun(x1)<=fun(x2)){
                b=x2;
                x2=x1;
                x1=a+b-x1;
            }
            else {
                a=x1;
                x1=x2;
                x2=a+b-x1;
            }
        }
        //System.out.printf("Фибоначчи: X* = %f, Ymin = %f, n = %d\n", (a+b)/2, fun((a+b)/2),n);

        double[] result = new double[]{(a+b)/2,fun((a+b)/2), n};
        return result;
    }
}