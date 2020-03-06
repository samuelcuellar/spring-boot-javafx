package com.skynet.javafx.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
    
	public static LocalDate toLocalDate(Date date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		return localDate;
	}

	public static LocalDate toLocalDate(java.sql.Date sqlDate) {
	    return (sqlDate == null ? null : sqlDate.toLocalDate());
	}

	public static java.sql.Date toSqlDate(LocalDate localDate) {
		java.sql.Date date = java.sql.Date.valueOf(localDate);
		return date;
	}
}
