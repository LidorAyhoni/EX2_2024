package gym.management;

import gym.customers.Person;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;

public class Instructor extends Employees {
    protected final ArrayList<SessionType>  certifiedClasses =new ArrayList<>();
    protected int numHoursOfTraining;
    protected Instructor(Person person,int salary,ArrayList<SessionType> certifiedClasses){
        super(person,"Instructor",salary);
        this.certifiedClasses.addAll(certifiedClasses);
        this.numHoursOfTraining =0;
    }
    public void setNumHoursOfTraining(int numHoursOfTraining){
        this.numHoursOfTraining += numHoursOfTraining;
    }

    public boolean isCertified(SessionType type){
        return certifiedClasses.contains(type);
    }
    @Override
    public String toString() {
        String str ="";
        for(int i = 0; i < certifiedClasses.size(); i++){
            if (i<=certifiedClasses.size()-2){
                str += certifiedClasses.get(i) + ", ";
            }
            else{str += certifiedClasses.get(i);}
        }
        return super.toString()+" | Salary per Hour: "+this.salary+" | Certified Classes: "+str;
    }
}
