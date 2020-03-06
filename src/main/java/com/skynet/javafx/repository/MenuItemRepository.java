package com.skynet.javafx.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.skynet.javafx.model.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
 
	public List<MenuItem> findByParent(Long parent);

}
