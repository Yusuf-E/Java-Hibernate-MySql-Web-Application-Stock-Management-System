package props;


import lombok.Data;

import java.util.Date;

@Data
public class Report {
    private int cu_id;
    private int ctype;
    private Date startDate;
    private  Date endDate;
}
