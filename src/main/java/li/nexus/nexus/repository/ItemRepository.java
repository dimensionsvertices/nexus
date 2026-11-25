package li.nexus.nexus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import li.nexus.nexus.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
