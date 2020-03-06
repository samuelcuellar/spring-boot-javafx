package com.skynet.javafx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skynet.javafx.model.MenuItem;
import com.skynet.javafx.repository.MenuItemRepository;

@Service
public class MenuItemService {

	private static final Logger logger = LoggerFactory.getLogger(MenuItemService.class);

	@Autowired
	MenuItemRepository menuItemRepository;

	public MenuItem getMenuItemRoot() {
		logger.info("Getting menu item root");
		Optional<MenuItem> root = menuItemRepository.findById(0l);
		return root.get();
	}

	public List<MenuItem> getAllMenuItems() {
		logger.info("Getting all menu items");
		Iterable<MenuItem> it = menuItemRepository.findAll();
		List<MenuItem> result = new ArrayList<>();
		it.forEach(result::add);
		return result;
	}

	public List<MenuItem> getMenuItemsByParent(Long parent) {
		logger.info("Getting menu items by parent {}", parent);
		Iterable<MenuItem> it = menuItemRepository.findByParent(parent);
		List<MenuItem> result = new ArrayList<>();
		it.forEach(result::add);
		return result;
	}
}
