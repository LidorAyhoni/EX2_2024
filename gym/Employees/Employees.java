package gym.Employees;

import gym.customers.Gender;
import gym.customers.Person;

abstract class Employees extends Person {
    protected String role;
    protected int salary;
    public Employees(String name, int balance, Gender gender, String birthday,String role,int salary) {
        super(name, balance, gender, birthday);
        this.role = role;
        this.salary = salary;
    }
    public String getRole() {
        return role;
    }
    @Override
    public String toString() {
        return super.toString()+ " | Role: "+getRole();
    }
}
