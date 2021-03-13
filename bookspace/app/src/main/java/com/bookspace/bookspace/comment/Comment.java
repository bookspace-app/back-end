public class Comment {
    
    private final String content;
    private final Date date;

    private final User user;
    private final Collection<User> votedBy;
    private final Publication publication;
    private final Comment parent;
    private final Collection<Comment> answers;


    public Comment() {
    }

    public Comment(String content, Date date, User user, Collection<User> votedBy, Publication publication, Comment parent, Collection<Comment> answers) {
        this.content = content;
        this.date = date;
        this.user = user;
        this.votedBy = votedBy;
        this.publication = publication;
        this.parent = parent;
        this.answers = answers;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return this.user;
    }


    public Collection<User> getVotedBy() {
        return this.votedBy;
    }


    public Publication getPublication() {
        return this.publication;
    }


    public Comment getParent() {
        return this.parent;
    }


    public Collection<Comment> getAnswers() {
        return this.answers;
    }


    
}
