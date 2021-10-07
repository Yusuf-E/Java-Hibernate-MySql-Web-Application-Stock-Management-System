package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PayIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pay_id;

    private String payment_amount;

    private String payment_detail;
    @OneToOne
    private Customer customer ;
    @OneToOne
    private Receipt receipt;
    @Column(name = "date")
    private Date date = new Date();

}

