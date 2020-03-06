package com.skynet.javafx.views.def;

import java.util.List;

public interface FrameGridDef {
	
	public List<String> getColumnNames();

	public List<String> getColumnDataName();

	public List<Integer> getColumnSizes();
	
	public Class<?> getCreateView();

	public String getTitlePopups();
	
}
