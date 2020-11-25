package ru.skillbench.tasks.basics.math;

public class Main_ComplexNumber {
    public static void main(String args[])
    {
        double Re = 1;
        double Im = 0.5;
        double Re_2 = 2;
        double Im_2 = 1.5;
        ComplexNumberImpl Complex_1 = new ComplexNumberImpl();
        ComplexNumberImpl Complex_2 = new ComplexNumberImpl();

        Complex_1.set(Re,Im);
        //System.out.println("\n\nSET double, test:\nRE (1) = "+Complex_1.getRe()+"\nIM (0.5) = "+Complex_1.getIm());

        //System.out.println("\n\nIsReal test (1, 0.5) - true: "+Complex_1.isReal());

        String Complex_String = "5+4.0i";
        Complex_1.set(Complex_String);
        System.out.println("\n\nSET, string, test:\nRE = "+Complex_1.getRe()+"\nIM  = "+Complex_1.getIm());
        Complex_String = "1+1.0i";
        Complex_1.set(Complex_String);
        System.out.println("\n\nSET, string, test:\nRE = "+Complex_1.getRe()+"\nIM  = "+Complex_1.getIm());
        Complex_String = "1+1.0i";
        Complex_1.set(Complex_String);
        System.out.println("\n\nSET, string, test:\nRE = "+Complex_1.getRe()+"\nIM  = "+Complex_1.getIm());
        Complex_1.set(Complex_String);
        System.out.println("\n\nGet string test\nOrig string = "+Complex_String);
        System.out.println("Outp string = "+Complex_1.toString());

        ComplexNumber Complex_1_Copy = Complex_1.copy();
        System.out.println("\n\nCopy test. Complex_1 values. Re = "+Complex_1.getRe()+" Im = "+Complex_1.getIm());
        Complex_1_Copy.set(1,1);
        System.out.println("Copy test. Complex_1 values after copy. Re = "+Complex_1.getRe()+" Im = "+Complex_1.getIm());


        Complex_1.set(Complex_String);
        System.out.println("\n\nGet string test\nOrig string = "+Complex_String);
        System.out.println("Outp string = "+Complex_1.toString());

        System.out.println("\n\nEquals test (obj) Complex_1 == His copy == "+Complex_1.equals(Complex_1_Copy));

        Complex_1.set(Re, Im);
        Complex_2.set(Re, Im);
        System.out.println("\n\nCompareTo test. Data is same, Complex_1 & Complex_2 == "+Complex_1.compareTo(Complex_2));


    }
}
