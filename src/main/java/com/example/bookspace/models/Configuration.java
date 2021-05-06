package com.example.bookspace.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.bookspace.enums.Mode;

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
    @Column(name = "profileState")
    private Boolean profileState = false; 

    public Configuration() {
    }

    public Configuration(Boolean available, Mode mode, Boolean profileState) {
        this.available = available;
        this.mode = mode;
        this.profileState = profileState;
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


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isProfileState() {
        return this.profileState;
    }

    public Boolean getProfileState() {
        return this.profileState;
    }

    public void setProfileState(Boolean profileState) {
        this.profileState = profileState;
    }
    
    

}
