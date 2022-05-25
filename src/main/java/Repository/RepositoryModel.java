package Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a collection or repository of objects with the same type.
 * @param <T> The type of the objects in the repository.
 */

public abstract class RepositoryModel<T> {

    protected Class<T> elementType;

    protected List<T> elements;

    protected RepositoryModel(Class<T> elementType) {
        this.elementType = elementType;
        elements = new ArrayList<>();
    }

    /**
     * @return The number of elements the repository contains.
     */
    public int size() {
        return elements.size();
    }

    /**
     * Adds the {@code element} to the repository.
     */
    public void add(T element) {
        elements.add(element);
    }

    /**
     * Removes the {@code element} from the repository.
     */
    public void remove(T element) {
        elements.remove(element);
    }

    /**
     * Removes all the elements from the repository.
     */
    public void clear() {
        elements.clear();
    }

    /**
     * Filters the elements in the repository and returns only those
     * that match the given predicate.
     * @param predicate The predicate that the elements in the repository
     * should match.
     * @return A list of elements from the repository that match the given predicate.
     */
    public List<T> find(Predicate<T> predicate) {
        return elements.stream().filter(predicate).toList();
    }

    /**
     * @return An unmodifiable view of the elements that are contained
     * in the repository.
     */
    public List<T> findAll() {
        return Collections.unmodifiableList(elements);
    }
}