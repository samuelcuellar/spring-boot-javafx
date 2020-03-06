package com.skynet.javafx.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "menuitem")
public class MenuItem extends SimpleEntity {

	@Column(nullable = false)
	private Long parent;

	@Column(nullable = false)
	private String key;

	@Column(nullable = false)
	private String value;

	@Column
	private String target;

	@Column
	private String service;

	@Column
	private String gridDef;

	@Column
	private String tooltip;

	@Column
	private String image;

	@Column
	private Boolean expanded;

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getGridDef() {
		return gridDef;
	}

	public void setGridDef(String gridDef) {
		this.gridDef = gridDef;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public String toString() {
		return this.value;
	}
	
}
