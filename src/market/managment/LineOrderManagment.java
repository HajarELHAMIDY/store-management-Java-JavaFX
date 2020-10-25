package market.managment;

import market.dao.LineOrderDAO;
import market.entities.LineOrder;
import market.entities.Order;
import market.entities.Produit;

import java.util.List;

public class LineOrderManagment {
    private List<LineOrder> lineOrders;
    private LineOrderDAO lineOrderDAO= LineOrderDAO.getInstance();
    private static LineOrderManagment lineOrderManagment=null;
    private OrderManagment orderManagment=OrderManagment.getInstance();
    private ProductManagment productManagment=ProductManagment.getInstance();
    private LineOrderManagment(){
        lineOrders=lineOrderDAO.getAll();
    }
    public static LineOrderManagment getInstance(){
        if (lineOrderManagment==null) lineOrderManagment=new LineOrderManagment();
        return lineOrderManagment;
    }
    public boolean add(Produit produit, int quantite, Order order){
        if (order==null || quantite<=0 || produit==null||produit.getQuantite()<quantite){
            return false;
        }
        LineOrder existingInLings=exist(produit,order);
        if (existingInLings!=null){
            existingInLings.setQuantite(existingInLings.getQuantite()+quantite);
            produit.setQuantite(produit.getQuantite()-quantite);
            lineOrderDAO.update(existingInLings);
            orderManagment.update(order);
            productManagment.update(produit);
            return true;
        }
        LineOrder lineOrder=new LineOrder(-1,produit,quantite,order);
        lineOrderDAO.creat(lineOrder);
        lineOrders.add(lineOrder);
        orderManagment.update(order);
        productManagment.update(produit);
        return true;

    }
    public LineOrder exist(Produit p,Order o){
        for (LineOrder l:o.getLineOrders()){
            if (l.getProduit()==p){
                return l;
            }
        }
        return null;
    }
    public boolean update(LineOrder lineOrder,Produit produit, int quantite, Order order){
        if (lineOrder==null||order==null || quantite<=0 || produit==null){
            return false;
        }
        LineOrder l=order.getLineByProduct(produit);
        if (l!=null){
            l.setQuantite(l.getQuantite()+quantite);
            lineOrder.getProduit().setQuantite(lineOrder.getProduit().getQuantite()+lineOrder.getQuantite());
            produit.setQuantite(produit.getQuantite()-quantite);
            order.removeLineOrder(lineOrder);
            delet(lineOrder.getId());
            productManagment.update(produit);
            productManagment.update(l.getProduit());
            orderManagment.update(order);
            return true;
        }else {
            if (produit != lineOrder.getProduit()) {
                lineOrder.getProduit().setQuantite(lineOrder.getProduit().getQuantite() + lineOrder.getQuantite());
                produit.setQuantite(produit.getQuantite() - quantite);
            } else {
                if (quantite > lineOrder.getQuantite()) {
                    int q = quantite - lineOrder.getQuantite();
                    lineOrder.getProduit().setQuantite(lineOrder.getProduit().getQuantite() - q);
                } else {
                    int q = lineOrder.getQuantite() - quantite;

                    lineOrder.getProduit().setQuantite(lineOrder.getProduit().getQuantite() + q);
                }
            }
            productManagment.update(lineOrder.getProduit());
            lineOrder.setProduit(produit);
            lineOrder.setQuantite(quantite);
            if (lineOrder.getOrder() != order)
                lineOrder.setOrder(order);
            lineOrderDAO.update(lineOrder);
            orderManagment.update(lineOrder.getOrder());
            productManagment.update(lineOrder.getProduit());
            return true;
        }

    }
    public boolean delet(long id){
        LineOrder lineOrder=getById(id);
        lineOrder.getProduit().setQuantite(lineOrder.getQuantite()+lineOrder.getProduit().getQuantite());
        if (lineOrder==null)
            return false;
        lineOrder.getOrder().removeLineOrder(lineOrder);
        productManagment.update(lineOrder.getProduit());
        lineOrderDAO.delete(id);
        lineOrders.remove(lineOrder);
        return true;
    }
    public List<LineOrder> getAll(){
        if (lineOrders==null)
            lineOrders=lineOrderDAO.getAll();
        return lineOrders;
    }
    public LineOrder getById(long id){
        for (LineOrder l:lineOrders){
            if (l.getId()==id){
                return l;
            }
        }
        return null;
    }
}
