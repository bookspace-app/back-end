public class Chat {

    enum language {
        INGLES, 
        ESPAÑOL, 
        CATALAN
    }
    private final Date date;

    public Chat() {
    }


    public Chat(Date date) {
        this.date = date;
    }
    

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
