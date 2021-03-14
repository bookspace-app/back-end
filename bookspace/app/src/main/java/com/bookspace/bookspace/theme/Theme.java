package com.bookspace.bookspace.theme;

import java.util.Collection;

import com.bookspace.bookspace.publication.Publication;

public class Theme {

    enum theme {
        ACTION, 
        LOVE, 
        WAR, 
        POTENTIAL
    }

    private theme theme;
    private Collection<Publication> themed_publications;

    public Theme() {
    }

    public Theme(theme theme, Collection<Publication> themed_publications) {
        this.theme = theme;
        this.themed_publications = themed_publications;
    }

    public theme getTheme() {
        return this.theme;
    }

    public theme setTheme(String newTheme) throws Exception {
        throw new Exception("This function is not implemented yet");
    }

    public Collection<Publication> getThemed_publications() {
        return this.themed_publications;
    }

    public void setThemed_publications(Collection<Publication> themed_publications) {
        this.themed_publications = themed_publications;
    }

    
}
