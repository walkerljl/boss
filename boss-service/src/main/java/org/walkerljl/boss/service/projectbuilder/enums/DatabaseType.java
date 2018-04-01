package org.walkerljl.boss.service.projectbuilder.enums;

/**
 *
 * DatabaseType
 *
 * @author lijunlin
 */
public enum DatabaseType {

	/**
	 * MySQL
	 */
	MYSQL(1, "MySQL", "com.mysql.jdbc.Driver");
	
	private int value;
	private String name;
	private String driver;
	
	private DatabaseType(int value, String name, String driver) {
		this.value = value;
		this.name = name;
		this.driver = driver;
	}
	
	public static DatabaseType getType(int value) {
		for (DatabaseType type : DatabaseType.values()) {
			if (value == type.getValue()) {
				return type;
			}
		}
		return null;
	}
	
	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getDriver() {
		return driver;
	}
}