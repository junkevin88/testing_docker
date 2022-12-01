package com.binar5.apps.chapter3.lamda;

public class ContohLambdaJava8 {
    public static void main(String args[]){
        ContohLambdaJava8 obj = new ContohLambdaJava8();

        //dengan type declaration
        OperasiPerhitungan penjumlahan = (int a, int b) -> a + b;

        //tanpa type declaration
        OperasiPerhitungan pengurangan = (a, b) -> a - b;

        //dengan return statement dan dengan kurung kurawal
        OperasiPerhitungan perkalian = (int a, int b) -> { return a * b; };

        //tanpa return statement and tanpa kurung kurawal
        OperasiPerhitungan pembagian = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + obj.operate(10, 5, penjumlahan));
        System.out.println("10 - 5 = " + obj.operate(10, 5, pengurangan));
        System.out.println("10 x 5 = " + obj.operate(10, 5, perkalian));
        System.out.println("10 / 5 = " + obj.operate(10, 5, pembagian));

        //dengan parenthesis
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        //tanpa parenthesis
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Kitty");
        greetService2.sayMessage("Pikachu");
    }

    private interface OperasiPerhitungan {
        int operation(int a, int b);
    }

    private interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, OperasiPerhitungan op){
        return op.operation(a, b);
    }

}
