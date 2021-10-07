package entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int st_id;
    @Column(unique = true)
    private String st_title;

    private String st_purchase_price;
    private String st_sell_price;
    private long st_code;
    private int st_tax_value;
    private int st_unit;
    private String st_quantity;
    @Column(length = 500)
    private String st_detail;

}
