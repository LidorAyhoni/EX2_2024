package gym.customers;

import gym.Bank;
import gym.MyDateFunc;

public class Person {
    protected String name;
    protected double age;
    protected Gender gender;
    protected String birthday;
    protected Bank bankAccount;
    protected int ID;
    private static int numberID=1111;
    protected ForumType forumType;
    public Person(String name, int balance, Gender gender, String birthday) {
        this.name = name;
        this.bankAccount =new Bank(balance);
        this.gender = gender;
        this.birthday = birthday;
        this.ID=numberID++;
        this.age= MyDateFunc.Age(birthday);
        setForumType();
    }
    public Person(Person p) {
        this.name = p.name;
        this.bankAccount = p.bankAccount;
        this.gender = p.gender;
        this.birthday = p.birthday;
        this.ID= p.getID();
        this.age=p.getAge();
        this.forumType=getForumType();
    }
    public String getName() {return this.name;}
    public int getAge() {return (int)this.age;}
    public Bank getBankAccount() {return this.bankAccount;}
    public Gender getGender() {return this.gender;}
    public String getBirthday() {return this.birthday;}
    public ForumType getForumType() {return this.forumType;}
    public int getID() {return this.ID;}
    public static void decreaseNumberID() {
        numberID--;
    }
    public void setForumType() {
        if (getAge()<65){
            if (getGender()==Gender.Male)this.forumType=ForumType.Male;
            else this.forumType=ForumType.Female;
        }
        else this.forumType=ForumType.Seniors;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return this.ID==((Person) obj).ID;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: "+this.ID+" | "+
                "Name: "+this.name+" | "+
                "Gender: "+this.gender+" | "+
                "Birthday: "+this.birthday+" | "+
                "Age: "+getAge()+" | "+
                "Balance: "+bankAccount.getBalance();
    }
}
