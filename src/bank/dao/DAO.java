package bank.dao;

import java.util.List;

public interface DAO<T> {

    public boolean update(T objet);

    public T getByid(long id);

    public List<T> getAll();
}
