package dtos;

import entities.ClubPerson;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDTO {
    private long id;
    private String name;
    private int age;

    public PersonDTO(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static List<PersonDTO> getFromList(List<ClubPerson> people){
        return people.stream().
            map(PersonDTO::new).
            collect(Collectors.toList());
    }

    public PersonDTO(ClubPerson p) {
        this.id = p.getId();
        this.name = p.getFirstName() + " " + p.getLastName();
        this.age = p.getAge();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
