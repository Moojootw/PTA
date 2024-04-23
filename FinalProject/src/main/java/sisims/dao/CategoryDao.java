package sisims.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sisims.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
	//Custom
	//This is used for updating items to use a default category after their category is deleted
    Optional<Category> findByCategoryName(String categoryName);
}
