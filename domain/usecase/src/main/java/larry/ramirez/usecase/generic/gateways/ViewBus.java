package larry.ramirez.usecase.generic.gateways;

import larry.ramirez.usecase.generic.gateways.model.CommentViewModel;
import larry.ramirez.usecase.generic.gateways.model.PostViewModel;

public interface ViewBus {

    void publishPost(PostViewModel view);
    void publishComment(CommentViewModel view);
}
