package ru.skillbench.tasks.basics.math;

public class ComplexNumberImpl implements ComplexNumber{

    private double re,im;
    public ComplexNumberImpl(){
        re=0;
        im=0;
    }
    public ComplexNumberImpl(double re, double im){
        this.set(re,im);
    }
    public ComplexNumberImpl(String value){
        this.set(value);
    }

    @Override
    public double getRe() {
        return re;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {
        if (im==0)return true;
        else return false;
    }

    @Override
    public void set(double re, double im) {
        re=re;
        im=im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        if (value.length()==1)
        {
            if (value=="i")
            {
                re=0;
                im=1;
                return;
            }
            else re = Double.parseDouble(value); return;
        }
        if (value.toCharArray()[value.length()-1]!='i')throw new NumberFormatException();
        for (int i = value.length()-1; i > 0; i--)
        {
            if (value.toCharArray()[i]=='-'||value.toCharArray()[i]=='+')
            {
                im = Double.parseDouble(value.substring(i,value.length()-1));
                if (i!=0)re = Double.parseDouble(value.substring(0,i));
                else re=0;
                return;
            }
        }
        throw new NumberFormatException();
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumberImpl clone = new ComplexNumberImpl();
        clone.set(re,im);
        return clone;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return copy();
    }

    @Override
    public String toString() {
        String Output_Complex="";
        if (im==0)
        {
            Output_Complex= Double.toString(re);
            return Output_Complex;
        }
        if (re==0 && im !=0)
        {
            Output_Complex= Double.toString(im)+"i";
            return Output_Complex;
        }
        if (re!=0 && im!=0)
        {
            if (im>0)
            {
                Output_Complex= Double.toString(re) +"+"+ Double.toString(im)+"i";
            }
            else Output_Complex= Double.toString(re) + Double.toString(im)+"i";
            return Output_Complex;
        }
        return Output_Complex;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComplexNumber)) {
            return false;
        }
        ComplexNumber another_Complex = (ComplexNumber) obj;
        return this.getRe() == another_Complex.getRe() && this.getIm() == another_Complex.getIm();
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double ThisComplex = Math.pow(re,2)+Math.pow(im,2);
        double OtherComplex = Math.pow(other.getRe(), 2)+Math.pow(other.getIm(),2);
        if (ThisComplex < OtherComplex) return -1;
        if (ThisComplex > OtherComplex) return 1;
        return 0;
    }

    @Override
    public void sort(ComplexNumber[] array) {
        for (int i = 0; i < array.length; i++)
        {
            ComplexNumber min = array[i];
            int min_i = i;
            for (int j = i+1; j < array.length; j++)
            {
                double MIN_D = Math.pow(min.getRe(), 2)+Math.pow(min.getIm(),2);
                double MAX_D = Math.pow(array[j].getRe(), 2)+Math.pow(array[j].getIm(),2);
                if (MAX_D < MIN_D)
                {
                    min = array[j];
                    min_i = j;
                }
            }
            if (i != min_i)
            {
                ComplexNumber tmp = array[i];
                array[i] = array[min_i];
                array[min_i] = tmp;
            }
        }
    }

    @Override
    public ComplexNumber negate() {
        re=-re;
        im=-im;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        re += arg2.getRe();
        im += arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double re_2 = arg2.getRe();
        double im_2 = arg2.getIm();
        double re_1 = re;
        double im_1 = im;
        re = (re_1 * re_2 - im_1 * im_2);
        im = (im_1 * re_2 + re_1 * im_2);
        return this;
    }
}
