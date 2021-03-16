package com.bookspace.bookspace.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.bookspace.bookspace.enums.Mode;

@Entity
@Table (name = "configuration")
public class Configuration {

    @Id
    @SequenceGenerator(
        name = "user_sequence", 
        sequenceName = "user_sequence", 
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, 
        generator = "user_sequence"
    )
    private Long id;

    @Column(name = "state")
    private Boolean available = false;

    @Column(name = "mode")
    private Mode mode = Mode.LIGHT;

    /*Estado del perfil, 0 -> privado, 1-> publico*/
    @Column(name = "profile_state")
    private Boolean profile_state = false; 

    public Configuration() {
    }

    public Configuration(Boolean available, Mode mode, Boolean profile_state) {
        this.available = available;
        this.mode = mode;
        this.profile_state = profile_state;
    }


    public Boolean isAvailable() {
        return this.available;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Boolean isProfile_state() {
        return this.profile_state;
    }

    public Boolean getProfile_state() {
        return this.profile_state;
    }

    public void setProfile_state(Boolean profile_state) {
        this.profile_state = profile_state;
    }
    

}
