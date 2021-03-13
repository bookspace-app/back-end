public class Message {

    private final String content;
    private final Date date; 

    private final Chat chat;
    private final User owner;

    public Message() {
    }

    public Message(String content, Date date, Chat chat, User owner) {
        this.content = content;
        this.date = date;
        this.chat = chat;
        this.owner = owner;
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

    public Chat getChat() {
        return this.chat;
    }


    public User getOwner() {
        return this.owner;
    }
}
