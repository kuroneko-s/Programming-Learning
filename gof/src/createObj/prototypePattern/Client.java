package createObj.prototypePattern;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void run() throws CloneNotSupportedException {
        GitHubRepository repository = new GitHubRepository();
        repository.setUser("kuroneko");
        repository.setName("java-gof");

        GitHubIssue gitHubIssue = new GitHubIssue(repository);
        gitHubIssue.setId(1);
        gitHubIssue.setTitle("Test~");

        System.out.println(gitHubIssue.getUrl());

        GitHubIssue gitHubIssue2 = (GitHubIssue) gitHubIssue.clone();
        gitHubIssue2.setId(2);
        gitHubIssue2.setTitle("Test~222");

        System.out.println(gitHubIssue2.getUrl());
        System.out.println(gitHubIssue.getUrl());

        System.out.println(gitHubIssue2 != gitHubIssue);
        System.out.println(gitHubIssue2.equals(gitHubIssue));
        System.out.println(gitHubIssue2.getClass() == gitHubIssue.getClass());

        repository.setUser("mod_kuroneko");

        System.out.println(gitHubIssue2.getUrl());
        System.out.println(gitHubIssue.getUrl());

        ArrayList<String> list = new ArrayList<>();
        list.add("test");

        List<ArrayList<String>> list1 = List.of(list);
        ArrayList<String> strings = new ArrayList<>(list);

    }
}
