package srudy.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srudy.datajpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
