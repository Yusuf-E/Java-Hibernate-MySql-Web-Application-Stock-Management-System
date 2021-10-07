package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Payout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String payOutTitle;
    private int payOutType;
    private String payOutTotal;
    private String payOutDetail;

    @Column(name = "date")
    private Date date = new Date();
}
