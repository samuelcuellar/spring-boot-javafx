package com.skynet.javafx.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import com.skynet.javafx.constants.CommonConstants;

@MappedSuperclass
public abstract class SimpleEntity {

	protected static final long serialVersionUID = CommonConstants.SERIAL_VERSION_UID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	protected SimpleEntity() {
		super();
	}

	public SimpleEntity(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}
}
