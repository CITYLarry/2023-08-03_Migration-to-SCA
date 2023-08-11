package larry.ramirez.usecase.generic.gateways.model;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel {

    private String id;
    private String aggregateId;
    private String author;
    private String title;
    private List<CommentViewModel> comments;


    public PostViewModel() {
        this.comments = new ArrayList<>();
    }

    public PostViewModel(String aggregateId, String author, String title, List<CommentViewModel> comments) {
        this.aggregateId = aggregateId;
        this.author = author;
        this.title = title;
        this.comments = comments;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }
}
