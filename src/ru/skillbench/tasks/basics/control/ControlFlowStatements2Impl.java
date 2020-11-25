package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements2Impl implements ControlFlowStatements2{
    @Override
    public int getFunctionValue(int x) {
        if (x<-2 || x>2) return 2*x;
        else return -3*x;
    }

    @Override
    public String decodeMark(int mark) {
        switch (mark)
        {
            case 1:
                return "Fail";
            case 2:
                return "Poor";
            case 3:
                return "Satisfactory";
            case 4:
                return "Good";
            case 5:
                return "Excellent";
            default:
                return "Error";
        }
    }

    @Override
    public double[][] initArray() {
        double[][]array = new double[5][8];
        for (int i=0; i<5; i++)
        {
            for (int j=0; j<8; j++)array[i][j]= Math.pow(i,4)-Math.sqrt(j);
        }
        return array;
    }

    @Override
    public double getMaxValue(double[][] array) {
        double max=0;
        boolean exit= false;
        for (int i=0; i<array.length; i++)
        {
            if (exit)break;
            for (int j=0; j<array[0].length; j++)
            {
                if (array[i][j]>0)
                {
                    max=array[i][j];
                    exit = true;
                }
            }
        }
        for (int i=0; i<array.length; i++)
        {
            for (int j=0; j<array[0].length; j++) if (array[i][j]>max)max=array[i][j];
        }
        return max;
    }

    @Override
    public Sportsman calculateSportsman(float P) {
        Sportsman sportsman = new Sportsman();
        sportsman.addDay(10);
        float distance = 10;
        while (sportsman.getTotalDistance()<=200)
        {
            float add = (distance/100)*P;
            distance = add + distance;
            sportsman.addDay(distance);
        }
        return sportsman;
    }
}
