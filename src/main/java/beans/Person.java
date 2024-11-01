package beans;

import interfaces.Identifiable;

/**
 *
 * @author stag
 */
public class Person implements Identifiable{
    private Long id;
    private String login;
    private String name;
    private String pwd;
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" + "id_person=" + id + ", login=" + login + ", name=" + name + ", pwd=" + pwd + ", status=" + status + '}';
    }    
}
