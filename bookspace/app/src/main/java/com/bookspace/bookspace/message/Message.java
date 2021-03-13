public class Message {

    private final String content;
    private final Date date; 

    public Message() {
    }

    public Message(String content, Date date) {
        this.content = content;
        this.date = date;
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
}
