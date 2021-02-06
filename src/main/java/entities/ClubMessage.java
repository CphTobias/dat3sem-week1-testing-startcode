package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "clubmessage")
@NamedQueries({
    @NamedQuery(name = "ClubMessage.deleteAllRows", query = "DELETE FROM ClubMessage"),
    @NamedQuery(name = "ClubMessage.findAll", query = "SELECT cm FROM ClubMessage cm"),
    @NamedQuery(name = "ClubMessage.findByPerson", query = "SELECT cm FROM ClubMessage cm WHERE cm.person.id = :person")
})
public class ClubMessage implements Serializable {

    private static final long serialVersionUID = 1127928236982432232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @ManyToOne()
    private ClubPerson person;

    public ClubMessage(String message) {
        this.message = message;
    }

    public ClubMessage(String message, ClubPerson clubPerson) {
        this.message = message;
        this.person = clubPerson;
    }

    @Override
    public String toString() {
        return "ClubMessage{" +
            "id=" + id +
            ", message='" + message + '\'' +
            ", clubPerson=" + person +
            '}';
    }

    public ClubMessage() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClubPerson getPerson() {
        return person;
    }

    public void setPerson(ClubPerson person) {
        this.person = person;
    }
}
