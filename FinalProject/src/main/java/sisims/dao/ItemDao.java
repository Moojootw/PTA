package sisims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import sisims.entity.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
