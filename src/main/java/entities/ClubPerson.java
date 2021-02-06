package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Table(name = "clubperson")
@Entity
@NamedQueries({
    @NamedQuery(name = "ClubPerson.deleteAllRows", query = "DELETE from ClubPerson"),
    @NamedQuery(name = "ClubPerson.findAll", query = "SELECT p FROM ClubPerson p"),
    @NamedQuery(name = "ClubPerson.findByName", query = "SELECT p FROM ClubPerson p WHERE p.firstName = :name"),
    @NamedQuery(name = "ClubPerson.findMaxAge", query = "SELECT MAX(p.age) FROM ClubPerson p")
})
public class ClubPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;
    private String creditCard;

    public ClubPerson() {
    }

    public ClubPerson(String firstName, String lastName, int age, String creditCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.creditCard = creditCard;
    }

    @Override
    public String toString() {
        return "ClubPerson{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", age=" + age +
            ", creditCard='" + creditCard + '\'' +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
