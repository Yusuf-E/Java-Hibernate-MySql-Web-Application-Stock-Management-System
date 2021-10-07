package props;

import lombok.Data;

import java.util.List;
@Data
public class SaveBox {
    private String rid ;
    private int cu_id;
    private List<Integer> products;
    private List<Integer> box_ids;
    private int total;
}
