package market.managment;

import market.dao.CategoryDAO;
import market.entities.Category;
import java.util.List;

public class CategoryManagment {
    private List<Category> categories;
    private CategoryDAO categoryDAO= CategoryDAO.getInstance();
    private static CategoryManagment categoryManagment=null;
    private CategoryManagment(){
        categories=categoryDAO.getAll();
    }
    public static CategoryManagment getInstance(){
        if (categoryManagment==null) categoryManagment=new CategoryManagment();
        return categoryManagment;
    }
    public boolean add(String name,String description){
        if (name.length()<5 || description.length()<10){
            return false;
        }
        Category category=new Category(-1,name,description);
        categoryDAO.creat(category);
        categories.add(category);
        return true;

    }
    public boolean update(Category category,String name,String description){
        if (category==null ||name.length()<5 || description.length()<10){
            return false;
        }
        category.setNom(name);
        category.setDescription(description);
        categoryDAO.update(category);
        return true;
    }
    public boolean delet(long id){
        Category category=getById(id);
        if (category==null || category.getProduits().size()>0)
            return false;
        categoryDAO.delete(id);
        categories.remove(category);
        return true;
    }
    public List<Category> getAll(){
        return categories;
    }
    public Category getById(long id){
        for (Category c:categories){
            if (c.getId()==id){
                return c;
            }
        }
        return null;
    }

}
