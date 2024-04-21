package sisims.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sisims.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
