package pageobject.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Contact {
    private String fullName;
    private String countryCode;
    private String mobilePhone;
    private String email;
}
