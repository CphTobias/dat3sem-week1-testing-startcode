package dtos;

import entities.ClubMessage;
import entities.ClubPerson;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDTO {

    private long id;
    private String message;
    private ClubPerson clubPerson;

    public MessageDTO(ClubMessage message) {
        this.id = message.getId();
        this.message = message.getMessage();
        this.clubPerson = message.getPerson();
    }

    public static List<MessageDTO> getFromList(List<ClubMessage> messages) {
        return messages.stream()
            .map(MessageDTO::new)
            .collect(Collectors.toList());
    }

    public MessageDTO(String message, ClubPerson clubPerson) {
        this.message = message;
        this.clubPerson = clubPerson;
    }

    public MessageDTO(long id, String message, ClubPerson clubPerson) {
        this.id = id;
        this.message = message;
        this.clubPerson = clubPerson;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
            "id=" + id +
            ", message='" + message + '\'' +
            ", clubPerson=" + clubPerson +
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

    public ClubPerson getClubPerson() {
        return clubPerson;
    }

    public void setClubPerson(ClubPerson clubPerson) {
        this.clubPerson = clubPerson;
    }
}
