1) This is a pure function. Although it depends on the ```max``` function, this is also pure. Also, it changes the first parameter in the code, but since an int is _passed by value_ this doesn't have a side effect.
```
    public int pure_or_not_1(int n1, int n2) {
        if (n1 > 0) {
            n1 -= 4;
            return max(n1, n2);
        }
        return n2;
    }
```

2) This is not a pure function because the field ```x``` is outside the scope of the function.
```
    static class PureOrNot2 {
        private int x = 1;

        public String pure_or_not_2(int n) {
            return x + ", " + n;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
```
3) This is a pure function.
```
    String pure_or_not_3(String s) {
        if (!s.isEmpty())
            return "Hello, " + s;
        else
            return "Hello, World!";
    }
```
4) This is not a pure function since it writes to the System.out
```
    void pure_or_not_4(String s) {
        if (!s.isEmpty())
            System.out.println("Hello, " + s);
        else
            System.out.println("Hello, World!");

    }
```
5) This is not a pure function since it depends on ```x```. Also, it mutates the parameter ```running_total```.
```
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
```
6) This is not a pure function since it mutates the parameter ```running_total```.
```
    static class PureOrNot6{
        public Total pure_or_not_6(Total running_total, int increment) {
            running_total.total += x;
            return running_total;
        }
    }
```
7) This is a pure function since it returns a new copy of ```running_total```
```
    static class PureOrNot7 {
        public Total pure_or_not_6(Total running_total) {
            return new Total(running_total.total + increment)
        }
    }
```
8) This is a pure function
```
    double pure_or_not_7(double n) {
        if (n > 0)
            return sqrt(n);
        else
            return 0.0;
    }
```
9This is not a pure function because it reads from System.in
```
    String pure_or_not_8() {
        Scanner sc= new Scanner(System.in);
        String input =  sc.nextLine();
        return "They said: " + input;
    }

}
```