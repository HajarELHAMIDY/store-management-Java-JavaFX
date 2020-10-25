package market.dao;

import java.util.List;

public interface DAO<T> {
    public boolean creat(T p);

    public boolean delete(long id);

    public boolean update(T objet);

    public T getByid(long id);

    public List<T> getAll();

    public List<T> getAll(String keyword);
}
