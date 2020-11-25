package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class Main_ArrayVector {
    public static void main (String[] args){
        ArrayVectorImpl Vector = new ArrayVectorImpl();

        Vector.set(1,2,3,4,5.2);

        ArrayVector CloneVector = Vector.clone();

        CloneVector.set(0, 10.2);

        System.out.println("Size is: "+ Vector.getSize());

        System.out.println("2 element is (2) "+ Vector.get(1));

        System.out.println("Max is : "+Vector.getMax());

        System.out.println("Min is : "+Vector.getMin());

        CloneVector.sortAscending();

        ArrayVector Summ = CloneVector.sum(Vector);

        Vector.mult(1.5);

        PrintLN(CloneVector.get());

        System.out.println("Scalar: "+CloneVector.scalarMult(Vector));

        System.out.println("Norm: "+Vector.getNorm());
    }

    private static void PrintLN(double Vector[])
    {
        for (int i = 0; i < Vector.length; i++)
        {
            System.out.print(Vector[i]+" ");
        }
        System.out.println("");
    }
}
