package createObj.prototypePattern;

import java.util.Objects;

public class GitHubIssue implements Cloneable {
    private int id;
    private String title;

    private GitHubRepository repository;

    public GitHubIssue(GitHubRepository repository) {
        this.repository = repository;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return "https://github.com" +
                "/" + this.repository.getUser() +
                "/" + this.repository.getName() +
                "/issues" +
                "/" + this.id;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitHubIssue that = (GitHubIssue) o;
        return Objects.equals(repository, that.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(repository);
    }
}
