package com.library.publications.models;


import javax.persistence.*;
import javax.validation.OverridesAttribute;

/**
 * @author Chaklader on 2019-11-03
 */
@MappedSuperclass
public class Publication {

    @Column
    private String title;

    public Publication() {

    }

    public Publication(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String toString(Publication p) {

        if (p instanceof Book) {
            return ((Book) p).toString();
        }

        //
        else if (p instanceof Magazine) {
            return ((Magazine) p).toString();
        }

        return null;
    }
}