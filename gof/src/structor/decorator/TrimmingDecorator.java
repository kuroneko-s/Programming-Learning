package structor.decorator;

public class TrimmingDecorator extends CommentDecorator {
    public TrimmingDecorator(CommentService commentService) {
        super(commentService);
    }

    @Override
    public void addComment(String comment) {
        super.addComment(trim(comment));
    }

    private String trim(String comment) {
        return comment.replace("...", "");
    }
}
