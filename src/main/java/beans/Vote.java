/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import interfaces.Identifiable;

/**
 *
 * @author Julien Anglade
 */
public class Vote implements Identifiable {

    private Long id;
    private long id_person;
    private long id_story;
    private int quality;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public long getId_person() {
        return id_person;
    }

    public void setId_person(long id_person) {
        this.id_person = id_person;
    }

    public long getId_story() {
        return id_story;
    }

    public void setId_story(long id_story) {
        this.id_story = id_story;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Vote{" + "id=" + id + ", id_person=" + id_person + ", id_story=" + id_story + ", quality=" + quality + '}';
    }
}
