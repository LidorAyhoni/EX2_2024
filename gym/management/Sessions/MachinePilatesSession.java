package gym.management.Sessions;

import gym.customers.ForumType;
import gym.management.Instructor;

class MachinePilatesSession extends Session {
    public MachinePilatesSession(String sessionDate, ForumType forumType, Instructor instructor) {
        super(sessionDate, forumType, instructor,80,10);
    }
    public String getSessionType() {
        return "MachinePilates";
    }
    @Override
    public String toString() {
        return "Session Type: MachinePilates"+super.toString();
    }
}
