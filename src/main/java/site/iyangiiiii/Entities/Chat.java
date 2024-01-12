package site.iyangiiiii.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cid;

    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "uid")
    User origin;

    @ManyToOne
    @JoinColumn(name = "dest", referencedColumnName = "uid")
    User dest;

    @Column(columnDefinition = "TEXT")
    String content;

    public Chat(User origin, User dest, String content) {
        this.origin = origin;
        this.dest = dest;
        this.content = content;
    }

    public Chat() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public User getOrigin() {
        return origin;
    }

    public void setOrigin(User origin) {
        this.origin = origin;
    }

    public User getDest() {
        return dest;
    }

    public void setDest(User dest) {
        this.dest = dest;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
