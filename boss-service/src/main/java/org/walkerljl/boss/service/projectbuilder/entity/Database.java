/*
 * Copyright (c) 2010-2015 www.walkerljl.org All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.boss.service.projectbuilder.entity;

import java.io.Serializable;
import java.util.List;

import org.walkerljl.boss.service.projectbuilder.enums.DatabaseType;
import org.walkerljl.toolkit.lang.ListUtils;

/**
 * Database 
 *
 * @author lijunlin
 */
public class Database implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 数据库类型*/
	private DatabaseType type;
	/** 地址*/
	private String address;
	/** 名称*/
	private String name;
	/** 用户*/
	private String user;
	/** 密码*/
	private String password;
	/** 字符集*/
	private String charset;
	/** 引擎*/
	private String engine;
	private String tablePattern;
	/** 表*/
	private List<Table> tables;

	/**
	 * 默认构造函数
	 */
	public Database() {}

	public DatabaseType getType() {
		return type;
	}

	public void setType(DatabaseType type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}

	public String getTablePattern() {
		return tablePattern;
	}

	public void setTablePattern(String tablePattern) {
		this.tablePattern = tablePattern;
	}

	public void addTable(Table table) {
		if (tables == null) {
			tables = ListUtils.newArrayList();
		}
		tables.add(table);
	}
}