package com.skynet.javafx.service;

import java.util.List;
import com.skynet.javafx.model.SimpleEntity;

public interface FrameService {

	public List<? extends SimpleEntity> getData();
	
	public void delete(Long id);	
	
}
