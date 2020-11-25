package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector {
    private double[]Array_Vector;

    public ArrayVectorImpl() { }

    @Override
    public void set(double... elements) {
        if (elements.length==0)
        {
            System.out.println("No elements for setting");
        }
        else
            {
                Array_Vector = new double[elements.length];
                int Index = 0;
                for (double Element: elements)
                {
                    Array_Vector[Index]=Element;
                    Index++;
                }
        }

    }

    @Override
    public double[] get() {
        return Array_Vector;
    }

    @Override
    public ArrayVector clone() {
        ArrayVectorImpl Clone_Vector = new ArrayVectorImpl();
        Clone_Vector.set(Arrays.stream(Array_Vector).toArray());
        return Clone_Vector;
    }

    @Override
    public int getSize() {
        return Array_Vector.length;
    }

    @Override
    public void set(int index, double value){
        if (index < 0) return;
        if (index > Array_Vector.length)
        {
            double Temp[] = new double[index+1];
            for (int i = 0; i < Array_Vector.length; i++)
            {
                Temp[i]= Array_Vector[i];
            }
            Array_Vector = Temp;
        }
        Array_Vector[index]=value;
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        return Array_Vector[index];
    }

    @Override
    public double getMax() {
        return Arrays.stream(Array_Vector).max().orElse(-1);
    }

    @Override
    public double getMin() {
        return Arrays.stream(Array_Vector).min().orElse(-1);
    }

    @Override
    public void sortAscending() {
        for (int i = 0; i < Array_Vector.length; i++)
        {
            double min = Array_Vector[i];
            int min_i = i;
            for (int j = i+1; j < Array_Vector.length; j++)
            {
                if (Array_Vector[j] < min)
                {
                    min = Array_Vector[j];
                    min_i = j;
                }
            }
            if (i != min_i)
            {
                double tmp = Array_Vector[i];
                Array_Vector[i] = Array_Vector[min_i];
                Array_Vector[min_i] = tmp;
            }
        }
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < Array_Vector.length; i++)
        {
            Array_Vector[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        double[] Second_Vector = anotherVector.get();
        if (Array_Vector.length > Second_Vector.length || Array_Vector.length == Second_Vector.length)
        {
            for (int i = 0; i < Second_Vector.length; i++)
            {
                Array_Vector[i]+=Second_Vector[i];
            }
        }
        if (Array_Vector.length < Second_Vector.length)
        {
            for (int i = 0; i < Array_Vector.length; i++)
            {
                Array_Vector[i]+=Second_Vector[i];
            }
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double[] Second_Vector = anotherVector.get();
        double Result = 0;
        if (Array_Vector.length > Second_Vector.length || Array_Vector.length == Second_Vector.length)
        {
            for (int i = 0; i < Second_Vector.length; i++)
            {
                Result += (Array_Vector[i]* Second_Vector[i]);
            }
        }
        if (Array_Vector.length < Second_Vector.length)
        {
            for (int i = 0; i < Array_Vector.length; i++)
            {
                Result += (Array_Vector[i]* Second_Vector[i]);
            }
        }
        return Result;
    }

    @Override
    public double getNorm() {
        ArrayVector Clone_Vector = clone();
        return Math.sqrt(scalarMult(Clone_Vector));
    }
}
