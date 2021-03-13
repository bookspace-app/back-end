public class Chat {

    enum language {
        INGLES, 
        ESPAÑOL, 
        CATALAN
    }
    private final Date date;
    private final Publication publication;
    private final Collecion<User> participants;
    private final Collection<Message> record;

    public Chat() {
    }

    public Chat(Date date, Publication publication, Collecion<User> participants, Collection<Message> record) {
        this.date = date;
        this.publication = publication;
        this.participants = participants;
        this.record = record;
    }  
    

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Publication getPublication() {
        return this.publication;
    }


    public Collecion<User> getParticipants() {
        return this.participants;
    }


    public Collection<Message> getRecord() {
        return this.record;
    }

}
