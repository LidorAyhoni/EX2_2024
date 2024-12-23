package gym.management.Sessions;

import gym.customers.ForumType;
import gym.Employees.Instructor;

class MachinePilatesSession extends Session {
    public MachinePilatesSession(String sessionDate, ForumType forumType, Instructor instructor) {
        super(sessionDate, forumType, instructor,80,10);
    }
    @Override
    public String toString() {
        return "Session Type: MachinePilates"+super.toString();
    }
}
