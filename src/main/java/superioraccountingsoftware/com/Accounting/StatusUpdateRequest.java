package superioraccountingsoftware.com.Accounting;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatusUpdateRequest {
    private String status;
    private String dateRejected;
    private String reasonForRejection;
    private String rejectedBy;

}