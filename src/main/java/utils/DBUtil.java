package utils;

import com.google.gson.Gson;
import entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import props.Box;
import props.Payin;
import props.Report;
import props.SaveBox;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;

public class DBUtil {

    SessionFactory sf = HibernateUtil.getSessionFactory();

    public boolean login(String email, String password, String remember, HttpServletRequest request , HttpServletResponse response){
        boolean status = false;
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {

            List<Admin> admin = session.createQuery("from Admin  where email= :email and password= :password")
                    .setParameter("email",email)
                    .setParameter("password",password).getResultList();
            System.out.println(admin);

            if (admin.size() != 0){
                // session create
                status = true;
                admin.forEach(item->{
                    int aid = item.getAid();
                    String name = item.getName();

                    request.getSession().setAttribute("aid",aid);
                    request.getSession().setAttribute("name",name);
                    request.getSession().setAttribute("email",email);

                    // cookie create

                    if(remember != null && remember.equals("on")){
                        name = name.replaceAll(" ", "_");
                        String val = aid+"_"+name;
                        Cookie cookie = new Cookie("user", val);
                        cookie.setMaxAge( 60*60 );
                        response.addCookie(cookie);
                    }
                });
                }


        }catch (Exception e){
            System.err.println("Login Error : "+e);
        }finally {
            session.close();
        }
        return status;
    }

    public int insert_updateCustomer(String obj){
        int cid = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            Gson gson = new Gson();
            Customer customer = gson.fromJson(obj, Customer.class);
            sesi.saveOrUpdate(customer);
            tr.commit();
            sesi.close();
            cid = 1;
        }catch ( Exception ex) {
            System.err.println("Save OR Update Error : " + ex);
        }finally {
            sesi.close();
            return cid;
        }
    }
    public String customerList(){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        List<Customer> ls = sesi.createQuery("from Customer").getResultList();
        sesi.close();

        String stJson = gson.toJson(ls);

        return stJson;
    }

    public int deleteCustomer( int cu_id ){
        int return_id = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            Customer customer = sesi.load(Customer.class, cu_id);
            System.out.println(customer.getCu_name());
            sesi.delete(customer);
            tr.commit();
            return_id = customer.getCu_id();
        }catch (Exception ex) {
            System.err.println("Delete Error : " + ex);
        }finally {
            sesi.close();
            return  return_id;
        }
    }
    public int insert_updateProduct( String obj ){
        int sid=0;
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Gson gson = new Gson();
            Stock product = gson.fromJson(obj,Stock.class);
            session.saveOrUpdate(product);
            transaction.commit();
            session.close();
            sid = 1;

        }catch (Exception e){
            System.err.println("Ürün Ekleme Sırasında Bir Hata Oluştu! : "+e);
            sid = -1;
            return sid;
        }finally {
            session.close();
            return sid;
        }
    }

    public String productList(){
        List<Stock> ls;
        Gson gson = new Gson();
        Session session = sf.openSession();
        ls = session.createQuery("from Stock ").getResultList();
        session.close();

        String stJson = gson.toJson(ls);

        return stJson;
    }
    public int deleteProduct( int st_id ){
        int return_id = 0;

            Session session = sf.openSession();
            Transaction tr = session.beginTransaction();

            try {
                Stock stock = session.load(Stock.class,st_id);
                session.delete(stock);
                tr.commit();
                return_id = stock.getSt_id();
            }catch (Exception e){
                System.out.println("productDelete Error : "+ e);

            }finally {
                session.close();
                return return_id;
            }
    }

    public String getOrders( int id){
        Gson gson = new Gson();
        Session session = sf.openSession();
        List<BoxAction> ls = session.createQuery("from BoxAction")
            .getResultList();


        session.close();
        String stJson = gson.toJson(ls);

        return stJson;

    }
    public int addBox( Box box ){
        int return_id = 0;
       Session sesi = sf.openSession();
       Session sesi2 = sf.openSession();
        Session sesi3 = sf.openSession();
       Transaction transaction = sesi.beginTransaction();
       Transaction transaction1 = sesi2.beginTransaction();
        Transaction transaction2 = sesi3.beginTransaction();
        try{

            System.out.println("BoxProid"+box.getPro_id());
            int cu_id = box.getPro_id()+1;
            Stock stock = (Stock) sesi.load(Stock.class, cu_id);
            Customer customer = sesi.load(Customer.class, box.getCu_id());
            System.out.println(stock.getSt_title());
            System.out.println(customer.getCu_name());
            transaction.commit();

            List<Stock> stocks = new ArrayList<>();
            stocks.add(stock);
            Quantity quantity = new Quantity();
            quantity.setStocks(stocks);
            quantity.setQuantity(box.getBox_quantity());

            sesi2.save(quantity);
            transaction1.commit();

            BoxAction boxAction = new BoxAction();

            boxAction.setBox_rid(box.getBox_rid());

            boxAction.setCustomer(customer);

            boxAction.setQuantity_id(quantity);

            sesi3.save(boxAction);
            transaction2.commit();

            return_id = 1;
         }catch (Exception err){
            System.err.println("addBox Error : "+ err);
        }finally {
           sesi.close();
            sesi2.close();
            sesi3.close();
        }

        return return_id;
    }
 public int deleteProductList( int box_bid ){
     int return_id = 0;

     Session session = sf.openSession();
     Transaction tr = session.beginTransaction();

     try {
         BoxAction boxAction = session.load(BoxAction.class,box_bid);
         session.delete(boxAction);
         tr.commit();
         return_id = boxAction.getBox_bid();
     }catch (Exception e){
         System.out.println("productDelete Error : "+ e);

     }finally {
         session.close();
         return return_id;
     }
 }
 public int saveBox( SaveBox saveBox ){
        int return_id =0;

        Session sesi = sf.openSession();

        Transaction transaction = sesi.beginTransaction();
        try{
            for (int i = 0; i<saveBox.getBox_ids().size();i++){
                Session sesi1 = sf.openSession();
                Session sesi4 = sf.openSession();
                Transaction transacrion4 = sesi4.beginTransaction();
                Transaction transaction1 = sesi1.beginTransaction();
                BoxAction boxAction = sesi1.load(BoxAction.class,saveBox.getBox_ids().get(i));
                int quantity = boxAction.getQuantity_id().getQuantity();
                String st_quantity = boxAction.getQuantity_id().getStocks().get(0).getSt_quantity();
                int newquantity = Integer.parseInt(st_quantity)-quantity;
                boxAction.getQuantity_id().getStocks().get(0).setSt_quantity( Integer.toString(newquantity));
                sesi4.saveOrUpdate(boxAction.getQuantity_id().getStocks().get(0));
                transacrion4.commit();
                sesi4.close();
                // delete
                sesi1.delete(boxAction);
                transaction1.commit();
                sesi1.close();
            }
            Customer customer = sesi.load(Customer.class,saveBox.getCu_id());
            transaction.commit();

            Receipt receipt = new Receipt();
            receipt.setRe_rid(saveBox.getRid());
            receipt.setAmount(String.valueOf(saveBox.getTotal()));
            receipt.setRepayment("0");
            receipt.setCustomer(customer);
            receipt.setActive(true);

            Session sesi3 = sf.openSession();
            Transaction transaction2 = sesi3.beginTransaction();

            sesi3.save(receipt);
            transaction2.commit();
            sesi3.close();
            return_id = 1;
        }catch (Exception e){
            System.err.println("saveBox Error : " + e);
            return_id = -1;
        }finally {
            sesi.close();
            return return_id;
        }
 }
 public String billList( String cu_id ){

         Gson gson = new Gson();
         Session sesi = sf.openSession();
         int it_cu_id = Integer.parseInt(cu_id);
         List<Customer> ls = sesi.createQuery("from Receipt where customer.cu_id= :cu_id").setParameter("cu_id",it_cu_id).getResultList();
         sesi.close();

         String stJson = gson.toJson(ls);

         return stJson;

 }
    public int addPayin(Payin obj){
        int return_id = 0;
        int newCheckOutAmount =0;
        Session sesi = sf.openSession();
        Session sesi2 = sf.openSession();
        Session sesi3 = sf.openSession();
        Session sesi4 = sf.openSession();
        Transaction transaction = sesi.beginTransaction();
        Transaction transaction1 = sesi2.beginTransaction();
        Transaction transaction2 = sesi3.beginTransaction();
        Transaction transaction3 = sesi4.beginTransaction();
        try{
            System.out.println(obj.getBname());
            Customer customer = sesi.load(Customer.class, obj.getCu_id());
            System.out.println(customer.getCu_name());
            transaction.commit();
            Receipt receipt = sesi2.load(Receipt.class,obj.getBname());
            int repayment = (Integer.parseInt(receipt.getRepayment())+Integer.parseInt(obj.getPayment_amount()));
            receipt.setRepayment(String.valueOf(repayment));
            if (String.valueOf(repayment).equals(receipt.getAmount())){
                receipt.setActive(false);
            }
            sesi2.saveOrUpdate(receipt);
            transaction1.commit();
            PayIn payIn = new PayIn();
            payIn.setReceipt(receipt);
            payIn.setCustomer(customer);
            payIn.setPayment_amount(obj.getPayment_amount());
            payIn.setPayment_detail(obj.getPayment_detail());

            sesi3.save(payIn);
            transaction2.commit();
            CheckOut checkOut = sesi4.load(CheckOut.class, 1);

             newCheckOutAmount = checkOut.getCheckout_amount()+Integer.parseInt(obj.getPayment_amount());
            checkOut.setCheckout_amount(newCheckOutAmount);
             sesi4.saveOrUpdate(checkOut);
             transaction3.commit();

            return_id = 1;
        }catch (Exception err){
            System.err.println("addBox Error : "+ err);
        }finally {
            sesi.close();
            sesi2.close();
            sesi3.close();
            sesi4.close();
        }

        return return_id;
    }
    public String payinList(){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        Date date = new Date();
        System.out.println(date);
        List<PayIn> ls = sesi.createQuery("from PayIn order by pay_id desc").getResultList();
        sesi.close();
        String stJson = gson.toJson(ls);

        return stJson;
    }
    public int insert_payOut(String obj){
        int return_id = 0;
        int newCheckOutAmount = 0;
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        Session sesi2 = sf.openSession();
        Transaction tr2 = sesi2.beginTransaction();
        try {
            Gson gson = new Gson();
            Payout payout = gson.fromJson(obj, Payout.class);
            sesi.saveOrUpdate(payout);
            tr.commit();
            sesi.close();
            CheckOut checkOut = sesi2.load(CheckOut.class, 1);

            newCheckOutAmount = checkOut.getCheckout_amount()-Integer.parseInt(payout.getPayOutTotal());
            checkOut.setCheckout_amount(newCheckOutAmount);
            sesi2.saveOrUpdate(checkOut);


            tr2.commit();
            sesi2.close();
            return_id = 1;
        }catch ( Exception ex) {
            System.err.println("insertPayout Error : " + ex);
        }finally {
            sesi.close();
            sesi2.close();
            return return_id;
        }
    }
    public String payoutList(){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        Date date = new Date();
        System.out.println(date);
        List<Payout> ls = sesi.createQuery("from Payout order by id desc").getResultList();
        sesi.close();
        ls.forEach(item->{
            System.out.println(item);
        });
        String stJson = gson.toJson(ls);

        return stJson;
    }
    public String checkOutPayIn(){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        Date st_date = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
        Date end_date = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
        System.out.println(st_date + " ------" + end_date);
        String HQL_QUERY =
                "select payment_amount from PayIn p where p.date between :start and :end ";
       List result = sesi.createQuery(HQL_QUERY)
                .setParameter("start", st_date)
        .setParameter("end",end_date).list();
        sesi.close();
        System.out.println(result);
        String stJson = gson.toJson(result);
        return  stJson;
    }
    public String checkOutPayOut(){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);
        Date st_date = Date.from(startDate.atStartOfDay(defaultZoneId).toInstant());
        Date end_date = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());
        System.out.println(st_date + " ------" + end_date);
        String HQL_QUERY =
                "select payOutTotal from Payout p where p.date between :start and :end ";
        List result = sesi.createQuery(HQL_QUERY)
                .setParameter("start", st_date)
                .setParameter("end",end_date).list();
        sesi.close();
        System.out.println(result);
        String stJson = gson.toJson(result);
        return  stJson;
    }
    public String checkOutAmount(){
        String stJson="";
        Session sesi = sf.openSession();
        Transaction tr = sesi.beginTransaction();
        try {
            Gson gson = new Gson();
       List<CheckOut> checkOut = sesi.createQuery("from CheckOut ").getResultList();
            tr.commit();
            sesi.close();
             stJson = gson.toJson(checkOut);
            System.out.println("Kasa : " + stJson);
            return  stJson;
        }catch ( Exception ex) {
            System.err.println("checkOut Error : " + ex);
        }finally {
            sesi.close();
            return stJson;
        }
    }
    public String filteredPayoutList(Report report){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        Date startDate = report.getStartDate();
        Date endDate = report.getEndDate();
        String HQL_QUERY =
                "from Payout p where p.date between :start and :end ";
        List result = sesi.createQuery(HQL_QUERY)
                .setParameter("start", startDate)
                .setParameter("end",endDate).list();
        sesi.close();
        System.out.println(result);
        String stJson = gson.toJson(result);
        return  stJson;
    }
    public String filteredPayinList(Report report){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        Date startDate = report.getStartDate();
        Date endDate = report.getEndDate();
        String HQL_QUERY =
                "from PayIn p where p.date between :start and :end ";
        List result = sesi.createQuery(HQL_QUERY)
                .setParameter("start", startDate)
                .setParameter("end",endDate).list();
        sesi.close();
        System.out.println(result);
        String stJson = gson.toJson(result);
        return  stJson;
    }
    public String filteredCustomerPayin( Report report ){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        Date startDate = report.getStartDate();
        Date endDate = report.getEndDate();
        String HQL_QUERY =
                "from PayIn p where p.customer.cu_id = :cu_id and p.date between :start and :end ";
        List result = sesi.createQuery(HQL_QUERY)
                .setParameter("cu_id",report.getCu_id())
                .setParameter("start", startDate)
                .setParameter("end",endDate).list();
        sesi.close();
        System.out.println(result);
        String stJson = gson.toJson(result);
        return  stJson;
    }
    public String allBills(){
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        List<Receipt> ls = sesi.createQuery("from Receipt ").getResultList();
        sesi.close();

        String stJson = gson.toJson(ls);

        return stJson;
    }
    public String rememberMe(String email){
        String return_password="" ;
        Gson gson = new Gson();
        Session sesi = sf.openSession();
        Transaction transaction = sesi.beginTransaction();
        try {

            List<Admin> admin = sesi.createQuery("from Admin  where email= :email")
                    .setParameter("email",email).getResultList();
            transaction.commit();
            if (admin.size()!=0){
                return_password = gson.toJson(admin.get(0).getPassword());

            }
            else {
                return_password = gson.toJson("");
            }
            System.out.println(return_password);
    }catch (Exception e){
            System.err.println("RememberMe Error : " + e);
        }finally {
            sesi.close();
        }
        return return_password;
}
}

