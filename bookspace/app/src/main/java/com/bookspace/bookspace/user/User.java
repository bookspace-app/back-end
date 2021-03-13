public class User {

     enum Rank {
            QUEEN,
            HAREM, 
            SOLDIER, 
            WORKER

    }

    private final String email; 
    private final String name;
    private final Date dov;
    private final Rank rank; 
    private final Date register; 

    private final Collection<Publication> publications;
    private final Collection<Publication> voted_publications;
    private final Collection<Comment> comments;
    private final Collection<Comment> voted_comments;
    private final Collection<Message> messages;
    private final Collection<Chat> chats;


    public User() {
    }
    

    public User(String email, String name, Date dov, Rank rank, Date register) {
        this.email = email;
        this.name = name;
        this.dov = dov;
        this.rank = rank;
        this.register = register;
    }

    public String getEmail() {
        return this.email;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDov() {
        return this.dov;
    }

    public void setDov(Date dov) {
        this.dov = dov;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Date getRegister() {
        return this.register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }
    

}
