package gym.Employees;


import gym.customers.Person;

abstract class Employees extends Person {
    protected String role;
    protected int salary;
    public Employees(Person person,String role,int salary){
        super(person);
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
