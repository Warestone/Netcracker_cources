package ru.skillbench.tasks.basics.entity;

public class EmployeeImpl implements Employee{
    public EmployeeImpl(){}

    private int Salary=1000;
    private String First_name,Last_name;
    private Employee Manager;



    @Override
    public int getSalary() {
        return Salary;
    }

    @Override
    public void increaseSalary(int value) {
        Salary+=value;
    }

    @Override
    public String getFirstName() {
        return First_name;
    }

    @Override
    public void setFirstName(String firstName) {
        First_name=firstName;
    }

    @Override
    public String getLastName() {
        return Last_name;
    }

    @Override
    public void setLastName(String lastName) {
        Last_name = lastName;
    }

    @Override
    public void setManager(Employee manager) {
        Manager = manager;
    }

    @Override
    public String getManagerName() {
        if (Manager==null) return "No manager";
        else return Manager.getFullName();
    }

    @Override
    public Employee getTopManager() {
        if (Manager==null)return this;
        else return Manager.getTopManager();
    }

    @Override
    public String getFullName() {
        return First_name+" "+Last_name;
    }
}
