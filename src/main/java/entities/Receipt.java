package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Receipt {

    @Id
    private String re_rid;

    private String amount;

    private String repayment;

    private boolean active;

    @OneToOne
    private Customer customer;


}
