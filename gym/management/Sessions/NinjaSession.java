package gym.management.Sessions;

import gym.customers.ForumType;
import gym.management.Instructor;

class NinjaSession extends Session {
    public NinjaSession(String sessionDate, ForumType forumType, Instructor instructor) {
        super(sessionDate, forumType, instructor,150,5);
    }
    public String getSessionType() {
        return "Ninja";
    }
    @Override
    public String toString() {
        return "Session Type: Ninja"+super.toString();
    }
}
