package pageobject.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Traveler {
    protected String title;
    protected String fullName;

    public Traveler(Traveler traveler) {
        this.title = traveler.title;
        this.fullName = traveler.fullName;
    }
}
