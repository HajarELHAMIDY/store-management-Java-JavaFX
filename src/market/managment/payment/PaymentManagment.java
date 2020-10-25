package market.managment.payment;



import market.dao.payement.PaymentDAO;
import market.entities.Order;
import market.entities.OrderType;
import market.entities.payement.PayementMode;
import market.entities.payement.Payment;
import market.managment.OrderManagment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class PaymentManagment {
    private List<Payment> payments;
    private PaymentDAO paymentDAO= PaymentDAO.getInstance();
    private OrderManagment orderManagment=OrderManagment.getInstance();
    private static PaymentManagment paymentManagment=null;
    private PaymentManagment(){
        payments=paymentDAO.getAll();
    }
    public static PaymentManagment getInstance(){
        if (paymentManagment==null) paymentManagment=new PaymentManagment();
        return paymentManagment;
    }
    public boolean add(LocalDate datePayement, Order order, PayementMode payementItem){
//        if (name.length()<5 || description.length()<10){
//            return false;
//        }
        Payment payment=new Payment(-1,datePayement,order,payementItem);
        orderManagment.updateType(order, OrderType.Valid);
        paymentDAO.creat(payment);
        payments.add(payment);
        return true;

    }
    public boolean update(Payment payment,LocalDate datePayement, Order order, PayementMode payementItem){
//        if (Payment==null ||name.length()<5 || description.length()<10){
//            return false;
//        }
        payment.setOrder(order);
        payment.setPayementItem(payementItem);
        payment.setDatePayement(datePayement);
        paymentDAO.update(payment);
        return true;
    }
    public boolean delet(long id){
        Payment payment=getById(id);
        if (payment==null)
            return false;
        paymentDAO.delete(id);
        payments.remove(payment);
        return true;
    }
    public List<Payment> getAll(){
        return payments;
    }
    public Payment getById(long id){
        for (Payment p:payments){
            if (p.getId()==id){
                return p;
            }
        }
        return null;
    }

}
