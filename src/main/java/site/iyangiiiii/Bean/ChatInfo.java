package site.iyangiiiii.Bean;

import site.iyangiiiii.Entities.User;

import java.util.Vector;

public class ChatInfo {
    User lhs, rhs;
    int direction;
    String content;

    public ChatInfo(User lhs, User rhs, int direction, String content) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.direction = direction;
        this.content = content;
    }

    public User getLhs() {
        return lhs;
    }

    public void setLhs(User lhs) {
        this.lhs = lhs;
    }

    public User getRhs() {
        return rhs;
    }

    public void setRhs(User rhs) {
        this.rhs = rhs;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Vector<String> toVector() {
        Vector<String> ret = new Vector<>();
        if(direction == 1) {
            ret.add("");
            ret.add(content);
            ret.add(lhs.getUsername());
        }
        else {
            ret.add(lhs.getUsername());
            ret.add(content);
            ret.add("");
        }
        return ret;
    }
}
