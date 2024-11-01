package interfaces;
/**
 *
 * @author stag
 * @param <T>
 */
public interface Crudable<T> {
    
    public abstract T find(Long id);

    public abstract void persist(T obj);

    public abstract void delete(T obj);
}
