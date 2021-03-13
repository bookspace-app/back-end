public class Publication {

    private final String title;
    private final String content;
    private final Date date; 


    public Publication() {
    }

    public Publication(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
