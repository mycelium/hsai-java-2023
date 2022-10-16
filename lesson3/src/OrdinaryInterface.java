import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface OrdinaryInterface {

    void setSearchTechnique(SearchTechniques technique);

    Point get(@NotNull String k);

    Optional<Point> getSafe(@NotNull String k);

    Point getOrElse(@NotNull String k, Point val);

    boolean contains(@NotNull String k);

    Optional<Point> remove(@NotNull String k);

    void put(@NotNull String k, Point val);
}
