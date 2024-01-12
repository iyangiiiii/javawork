package site.iyangiiiii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.iyangiiiii.DAO.ChatRepository;
import site.iyangiiiii.DAO.GoodsRepository;
import site.iyangiiiii.DAO.OrderGoodsRepository;
import site.iyangiiiii.DAO.UserRepository;
import site.iyangiiiii.Entities.Chat;
import site.iyangiiiii.Entities.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ChatService {
    public static Logger logger = Logger.getLogger("ChatService");
    protected UserRepository userRepository;
    protected ChatRepository chatRepository;
    protected static ChatService chatService;

    @Autowired
    protected ChatService(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;

        chatService = this;
    }

    /**
     * 查找两方的聊天记录
     * @param lhs 一方
     * @param rhs 另一方
     * @return 成功返回双方的聊天记录, 失败返回null
     */
    public static List<Chat> getHistory(int lhs, int rhs) {
        try {
            User l = new User(), r = new User();
            l.setUid(lhs);
            r.setUid(rhs);
            List<Chat> ret = new ArrayList<>();
            ret = chatService.chatRepository.findChatsByOriginAndDest(l, r);
            ret.addAll(chatService.chatRepository.findChatsByOriginAndDest(r, l));
            ret.sort(Comparator.comparingInt(Chat::getCid));

            return ret;
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "ChatService: ", e);
            return null;
        }
    }
}
