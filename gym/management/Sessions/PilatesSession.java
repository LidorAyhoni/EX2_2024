package gym.management.Sessions;

import gym.customers.ForumType;
import gym.management.Instructor;

class PilatesSession extends Session {
    public PilatesSession(String sessionDate, ForumType forumType, Instructor instructor) {
        super(sessionDate,forumType,instructor,60,30);
    }
    public String getSessionType() {
        return "Pilates";
    }
    @Override
    public String toString() {
        return "Session Type: Pilates"+super.toString();
    }
}
