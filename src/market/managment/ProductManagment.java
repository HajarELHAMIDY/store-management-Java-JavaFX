package market.managment;

import market.dao.ProductDAO;
import market.entities.Category;
import market.entities.Produit;

import java.util.List;

public class ProductManagment {
    private List<Produit> produits;
    private ProductDAO productDAO= ProductDAO.getInstance();
    private static ProductManagment productManagment=null;
    private ProductManagment(){
        produits=productDAO.getAll();
    }
    public static ProductManagment getInstance(){
        if (productManagment==null) productManagment=new ProductManagment();
        return productManagment;
    }
    public boolean add(String designation, double prixAchat,double prixVente,int quantite, Category category){
        if (designation.length()<5 || prixAchat<=0 ||prixVente<=0 ||quantite<=0 || category==null){
            return false;
        }
        Produit produit=new Produit(-1,designation,prixAchat,prixVente,quantite,category);
        productDAO.creat(produit);
        produits.add(produit);
        return true;

    }
    public boolean update(Produit produit,String designation,double prixAchat, double prixVente,int quantite, Category category){
        if (produit==null ||designation.length()<5 || prixAchat<=0 ||prixVente<=0 ||quantite<=0|| category==null){
            return false;
        }
        produit.setDesignation(designation);
        produit.setPrixVente(prixVente);
        produit.setPrixAchat(prixAchat);
        produit.setQuantite(quantite);
        if (produit.getCategory()!=category)
            produit.setCategory(category);
        productDAO.update(produit);
        return true;
    }
    public void update(Produit p){
        productDAO.update(p);

    }
    public boolean delet(long id){
        Produit produit=getById(id);
        if (produit==null)
            return false;
        productDAO.delete(id);
        produits.remove(produit);
        return true;
    }
    public List<Produit> getAll(){
        if (produits==null)
            produits=productDAO.getAll();
        return produits;
    }
    public Produit getById(long id){
        for (Produit p:produits){
            if (p.getId()==id){
                return p;
            }
        }
        return null;
    }
}
