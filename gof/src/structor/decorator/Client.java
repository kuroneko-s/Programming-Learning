package structor.decorator;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        CommentService commentService = new DefaultCommentService();

        commentService = new TrimmingDecorator(commentService);
        commentService = new SpamFilteringCommentDecorator(commentService);

        commentService.addComment("test");
        commentService.addComment("tes...t");
        commentService.addComment("http://test.com");

        ArrayList list = new ArrayList();
        list.add(1);
        list.add("123");

        List list1 = Collections.checkedList(list, String.class);

        list.add(123);
        list1.add(123);

        List list2 = Collections.unmodifiableList(list);
    }
}
