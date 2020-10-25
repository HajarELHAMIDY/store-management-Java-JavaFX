package market.entities.payement;

import market.entities.Order;
import market.managment.payment.DraftManagment;

import java.time.LocalDate;
import java.util.Date;

public class Payment {
    private long id;
    private Order order;
    private PayementMode payementItem;
    private LocalDate datePayement;
    private DraftManagment draftManagment=DraftManagment.getInstance();
    public Payment(long id,LocalDate datePayement, Order order, PayementMode payementItem) {
        this.id = id;
        this.payementItem=payementItem;
        this.datePayement = datePayement;
        this.setOrder(order);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrderPayement(Order order) {
        this.order = order;
    }
    public void setOrder(Order order) {
        if (this.order!=null)
            this.order.setPayement(null);
        this.order = order;
        if (this.order!=null)
            this.order.setPayement(this);
    }
    public PayementMode getPayementItem() {
        return payementItem;
    }

    public void setPayementItem(PayementMode payementItem) {
        this.payementItem = payementItem;
    }

    public LocalDate getDatePayement() {
        return datePayement;
    }

    public void setDatePayement(LocalDate datePayement) {
        this.datePayement = datePayement;
    }
}
