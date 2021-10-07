package props;

import lombok.Data;

@Data
public class Payin {
    private int cu_id;
    private String bname;
    private String payment_amount;
    private String payment_detail;
}
