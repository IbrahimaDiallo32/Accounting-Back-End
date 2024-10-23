package superioraccountingsoftware.com.Accounting;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UserUpdateRequest {
    // Getters and setters

    @Setter
    private String email;
    @Setter
    private String password;
    private String accountType;
    private String address;
    private boolean isActive;


    // Constructor
    public UserUpdateRequest() {}

private UserUpdateRequest(String email, String password, String accountType, String address, boolean isActive) {
        this.email = email;
        this.password = password;
        this.accountType = accountType;
        this.address = address;
        this.isActive = isActive;

}
}
