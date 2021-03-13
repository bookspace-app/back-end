public class Theme {

    enum theme {
        ACTION, 
        LOVE, 
        WAR, 
        POTENTIAL
    }

    private final theme theme;
    private final Collection<Publication> themed_publications;

    public Theme() {
    }

    public Theme(theme theme, Collection<Publication> themed_publications) {
        this.theme = theme;
        this.themed_publications = themed_publications;
    }

    public theme getTheme() {
        return this.theme;
    }

    public theme setTheme(String newTheme) {
        throw new Exception("This function is not implemented yet");
    }

    public Collection<Publication> getThemed_publications() {
        return this.themed_publications;
    }

    public void setThemed_publications(Collection<Publication> themed_publications) {
        this.themed_publications = themed_publications;
    }

    
}
