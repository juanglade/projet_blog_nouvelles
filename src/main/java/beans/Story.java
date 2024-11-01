package beans;

import dao.DAOFactory;
import interfaces.Identifiable;
import java.time.LocalDate;

/**
 *
 * @author stag
 */
public class Story implements Identifiable {

    private Long id;
    private String title;
    private String content;
    private LocalDate created;
    private long id_person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public long getId_person() {
        return id_person;
    }

    public void setId_person(long id_person) {
        this.id_person = id_person;
    }

    public String getName() {
        return DAOFactory.getDAOPerson().find(id_person).getName();
    }

    @Override
    public String toString() {
        return "Story{" + "id=" + id + ", title=" + title + ", content=" + content + ", created=" + created + ", id_person=" + id_person + '}';
    }

}
