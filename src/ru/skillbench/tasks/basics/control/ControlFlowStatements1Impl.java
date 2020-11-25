package ru.skillbench.tasks.basics.control;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
    @Override
    public float getFunctionValue(float x) {
        if (x>0)
        {
            double result = 2*Math.sin(x);
            return (float) result;
        }
        else if(x<=0)return 6-x;
        return 0;
    }

    @Override
    public String decodeWeekday(int weekday) {
        switch (weekday)
        {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
        }
        return null;
    }

    @Override
    public int[][] initArray() {
        int[][]array = new int[5][8];
        for (int i=0; i<5; i++)
        {
            for (int j=0; j<8; j++)array[i][j]=i*j;
        }
        return array;
    }

    @Override
    public int getMinValue(int[][] array) {
        int min=0;
        boolean exit= false;
        for (int i=0; i<array.length; i++)
        {
            if (exit)break;
            for (int j=0; j<array[0].length; j++)
            {
                if (array[i][j]>0)
                {
                    min=array[i][j];
                    exit = true;
                }
            }
        }
        for (int i=0; i<array.length; i++)
        {
            for (int j=0; j<array[0].length; j++) if (array[i][j]<min)min=array[i][j];
        }
        return min;
    }

    @Override
    public BankDeposit calculateBankDeposit(double P) {
        BankDeposit bd = new BankDeposit();
        double amount = 1000;
        int years = 0;
        while (amount<=5000)
        {
            double add = (amount / 100)*P;
            amount+=add;
            years++;
        }
        bd.amount = amount;
        bd.years = years;
        return bd;
    }
}
