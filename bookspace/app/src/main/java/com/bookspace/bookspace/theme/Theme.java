public class Theme {

    enum theme {
        ACTION, 
        LOVE, 
        WAR, 
        POTENTIAL
    }

    private final theme theme;

    public Theme() {
    }

    public Theme(theme theme) {
        this.theme = theme;
    }

    public theme getTheme() {
        return this.theme;
    }

    public theme setTheme(String newTheme) {
        throw new Exception("This function is not implemented yet");
    }



    
}
