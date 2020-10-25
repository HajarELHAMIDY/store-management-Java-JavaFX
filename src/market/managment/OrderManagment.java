package market.managment;

import market.dao.OrderDAO;
import market.entities.Client;
import market.entities.LineOrder;
import market.entities.Order;
import market.entities.OrderType;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrderManagment {
    private List<Order> orders;
    private OrderDAO orderDAO= OrderDAO.getInstance();
    private static OrderManagment orderManagment=null;
    private ProductManagment productManagment=ProductManagment.getInstance();
    private OrderManagment(){
        orders=orderDAO.getAll();

    }
    public static OrderManagment getInstance(){
        if (orderManagment==null) orderManagment=new OrderManagment();
        return orderManagment;
    }
    public boolean add(LocalDate date,Client client){
        if ( client==null){
            return false;
        }
        Order order=new Order(-1,date,client, OrderType.New);
        orderDAO.creat(order);
        orders.add(order);
        return true;

    }
    public boolean update(Order order, LocalDate date, Client client){
        if (client==null || date==null){
            return false;
        }
        order.setClient(client);
        order.setDate(date);
        orderDAO.update(order);
        return true;
    }
    public boolean update(Order order){
        orderDAO.update(order);
        return true;
    }
    public boolean updateType(Order order, short orderType){
        order.setOrderType(orderType);
        orderDAO.update(order);
        return true;
    }
    public boolean delet(long id){
        Order order=getById(id);
        if (order==null || order.getLineOrders().size()>0)
            return false;
        orderDAO.delete(id);
        orders.remove(order);
        return true;
    }
    public List<Order> getAll(){
        return orders;
    }

    public Order getById(long id){
        for (Order o:orders){
            if (o.getId()==id){
                return o;
            }
        }
        return null;
    }
    public boolean cancelOrder(Order order){
        if (order.getPayement()!=null)
            return false;
        else {
            for (LineOrder l:order.getLineOrders()){
                l.getProduit().setQuantite(l.getProduit().getQuantite()+l.getQuantite());
                productManagment.update(l.getProduit());
            }
            updateType(order,OrderType.Canceled);
        }
        return true;
    }
}
