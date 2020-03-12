package pageobject.POJO;

import lombok.Data;

@Data
public class Children extends Traveler {
    private String dateOfBirth;

    public Children(Traveler traveler) {
        this(traveler, "");
    }

    public Children(Traveler traveler, String dateOfBirth) {
        super(traveler);
        this.dateOfBirth = dateOfBirth;
    }

    public Children(String title, String fullName, String dateOfBirth) {
        super(title, fullName);
        this.dateOfBirth = dateOfBirth;
    }
}
