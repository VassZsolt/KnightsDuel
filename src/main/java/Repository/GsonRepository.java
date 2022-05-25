package Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents a repository of objects of the same type.
 * The elements can also be loaded from a file and stored in the repository.
 * Furthermore, the elements in the repository can be saved to a file
 * in JSON format.
 * @param <T> The type of objects the repository holds.
 */
public class GsonRepository<T> extends RepositoryModel<T> {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Creates a {@code GsonGepository} object whose elements
     * are {@code elementType} type objects.
     * @param elementType is the type of elements.
     */

    public GsonRepository(Class<T> elementType) {
        super(elementType);
    }

    /**
     * Loads the elements from a JSON file and stores them
     * in the repository.
     * @param file contains the elements should be loaded.
     * @throws IOException if the {@code file} does not exist, or
     * can't be loaded, or it's a directory.
     */
    public void loadFromFile(File file) throws IOException {
        try (var reader = new FileReader(file)) {
            var listType = TypeToken.getParameterized(List.class, elementType).getType();
            elements = GSON.fromJson(reader, listType);
        }
    }

    /**
     * Saves the elements in the repository to the {@code file} in JSON format.
     * @param file contains the elements that should be saved
     * @throws IOException If the {@code file} exists, but it's a directory
     * instead of a regular file, or does not exist.
     */
    public void saveToFile(File file) throws IOException {
        try (var writer = new FileWriter(file)) {
            GSON.toJson(elements, writer);
        }
    }

}