package larry.ramirez.gamamqlistener;

import larry.ramirez.controller.SocketController;
import larry.ramirez.controller.model.CommentModel;
import larry.ramirez.controller.model.PostModel;
import larry.ramirez.serializer.JSONMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitMQMessageListener {

    private final SocketController controller;
    private final JSONMapper eventSerializer;
    public static final String POST_CREATED_QUEUE = "sca.events.post.created.queue";
    public static final String COMMENT_ADDED_QUEUE = "sca.events.comment.added.queue";


    @RabbitListener(queues = POST_CREATED_QUEUE)
    public void postQueueProcess(String message) {
        System.out.println(message);
        PostModel post = (PostModel) eventSerializer.readFromJson(message, PostModel.class);
        controller.sendPostCreated("mainSpace", post);
    }

    @RabbitListener(queues = COMMENT_ADDED_QUEUE)
    public void commentQueueProcess(String message) {
        System.out.println(message);
        CommentModel comment = (CommentModel) eventSerializer.readFromJson(message, CommentModel.class);
        controller.sendCommentAdded(comment.getPostId(), comment);
    }
}
