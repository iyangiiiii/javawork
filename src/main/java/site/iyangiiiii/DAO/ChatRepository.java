package site.iyangiiiii.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import site.iyangiiiii.Entities.Chat;
import site.iyangiiiii.Entities.User;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Chat findChatByCid(int cid);
    List<Chat> findChatsByDest(User dest);
    List<Chat> findChatsByOrigin(User origin);
    List<Chat> findChatsByOriginAndDest(User origin, User dest);
}
