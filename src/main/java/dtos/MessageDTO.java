package dtos;

import entities.ClubMessage;
import entities.ClubPerson;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDTO {

    private long id;
    private String message;
    private PersonDTO personDTO;

    public MessageDTO(ClubMessage message) {
        this.id = message.getId();
        this.message = message.getMessage();
        this.personDTO = new PersonDTO(message.getPerson());
    }

    public static List<MessageDTO> getFromList(List<ClubMessage> messages) {
        return messages.stream()
            .map(MessageDTO::new)
            .collect(Collectors.toList());
    }

    public MessageDTO(String message, PersonDTO personDTO) {
        this.message = message;
        this.personDTO = personDTO;
    }

    public MessageDTO(long id, String message, PersonDTO personDTO) {
        this.id = id;
        this.message = message;
        this.personDTO = personDTO;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + id +
            ", message='" + message + '\'' +
            ", clubPerson=" + personDTO+
            '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    public void setPersonDTO(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }
}
