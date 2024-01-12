/**
 Attribution: Includes material from [Emily Bache](https://sammancoaching.org/society/contributors/emilybache.html) - ProAgile - [sammancoaching.org](https://sammancoaching.org/)
 */
package com.shawnbutton.learningHours.pureFunctions;

import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.sqrt;

public class PureOrNot {

    public int pure_or_not_1(int n1, int n2) {
        if (n1 > 0) {
            n1 -= 4;
            return max(n1, n2);
        }
        return n2;
    }

    static class PureOrNot2 {
        private int x = 1;

        public String pure_or_not_2(int n) {
            return x + ", " + n;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    String pure_or_not_3(String s) {
        if (!s.isEmpty())
            return "Hello, " + s;
        else
            return "Hello, World!";
    }

    void pure_or_not_4(String s) {
        if (!s.isEmpty())
            System.out.println("Hello, " + s);
        else
            System.out.println("Hello, World!");

    }

    static class Total {
        public int total;

        Total(int total) {
            this.total = total;
        }
    }

    static class PureOrNot5 {
        private int x = 1;
        public Total pure_or_not_5(Total running_total) {
            running_total.total += x;
            return running_total;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    static class PureOrNot6{
        public Total pure_or_not_6(Total running_total, int increment) {
            running_total.total += increment;
            return running_total;
        }
    }

    static class PureOrNot7 {
        public Total pure_or_not_7(Total running_total, int increment) {
            return new Total(running_total.total + increment);
        }
    }

    double pure_or_not_8(double n) {
        if (n > 0)
            return sqrt(n);
        else
            return 0.0;
    }

    String pure_or_not_9() {
        Scanner sc= new Scanner(System.in);
        String input =  sc.nextLine();
        return "They said: " + input;
    }

}