package org.walkerljl.boss.service.projectbuilder.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.walkerljl.toolkit.lang.ListUtils;
import org.walkerljl.toolkit.lang.StringUtils;

/**
 * 表
 *
 * @author lijunlin
 */
public class Table {

	private static final String KEY_WRAPPER = "`";

	/** 表名*/
	private String name;
	/** 主键*/
	private Column primaryKey;
	/** 字段列表*/
	private List<Column> columns;
	/** 备注*/
	private String remark;
	
	//扩展字段
	/** 字段、属性映射Map*/
	private Map<String, String> columnToPropertyMap = null;
	private String columnNameListString = "";
	
	/**
	 * 默认构造函数
	 */
	public Table() {}
	
	//============自定义方法
	public String getClassName() {
		return StringUtils.toCamelCase(name, true, '_');
	}

	public String getVarName() {
		return StringUtils.fromCamelCase(name, '-');
	}

	public String getFields() {
		StringBuffer str = new StringBuffer();
		for(Column column : columns) {
			str.append("\t@ColumnName");
			if(column.isPrimaryKey()) {
				str.append("(key=true");
				if(!column.getName().equalsIgnoreCase(column.getFieldName())) {
					str.append(",value=\"").append(column.getName()).append("\"");
				}
				str.append(")");
			} else if(!column.getName().equalsIgnoreCase(column.getFieldName())) {
				str.append("(value=\"").append(column.getName()).append("\")");
			}
			str.append(" private ").append(column.getJavaType().getSimpleName()).append(" ");
			str.append(column.getFieldName()).append(";\n");
		}

		return str.toString();
	}

	public String getMethods() {
		StringBuffer str = new StringBuffer();
		for(Column column : columns) {
			//GET方法
			str.append("\t/**\n\t * 获取 ").append(column.getRemark());
			str.append("\n\t * @return ").append("\n\t */\n");
			str.append("\tpublic ").append(column.getJavaType().getSimpleName()).append(" ");
			str.append(column.getMethodPrefix()).append(column.getMethodName()).append("() {\n");
			str.append("\t\treturn ").append(column.getFieldName()).append(";\n\t}\n\n");

			//SET方法
			str.append("\t/**\n\t * 设置 ").append(column.getRemark());
			str.append("\n\t * @param ").append(column.getFieldName()).append("\n\t */\n");
			str.append("\tpublic void set").append(column.getMethodName()).append("(");
			str.append(column.getJavaType().getSimpleName()).append(" ").append(column.getFieldName()).append(") {\n");
			str.append("\t\tthis.").append(column.getFieldName());
			str.append(" = ").append(column.getFieldName()).append(";\n\t}\n\n");
		}
		return str.toString();
	}

//	/**
//	 * 获取表主键,默认第一个字段或者第一个主键.暂不支持复合主键
//	 * @return
//	 */
//	public Column getPrimaryField() {
//		Field field = null;
//		List<Field> list = fields;
//		for (Field f : list) {
//			if (f.isPrimaryKey()) {
//				field = f;
//				break;
//			}
//		}
//		if (field == null && list.size() > 0) {
//			field = list.get(0);// 默认第一个为主键
//		}
//		return field;
//	}

	/**
	 * 获取导入列表
	 *
	 * @return
	 */
	public String getImports() {
		Set<Class<?>> ts = new HashSet<Class<?>>();
		StringBuffer str = new StringBuffer();
		for (Column column : columns) {
			Class<?> type = column.getJavaType();
			if (ts.contains(type) || ("java.lang." + type.getSimpleName()).equals(type.getName())) {
				continue;
			}

			ts.add(type);
			str.append("import ").append(type.getName()).append(";\n");
		}
		return str.toString();
	}

	public void addColumn(Column column) {
		if (columns == null) {
			columns = ListUtils.newArrayList();
		}
		columns.add(column);
	}

	//============getter and setter
	/**
	 * 获取表名
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置表名
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取主键
	 * @return
	 */
	public Column getPrimaryKey() {
		return primaryKey;
	}

	/**
	 * 设置主键
	 * @param primaryKey
	 */
	public void setPrimaryKey(Column primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * 获取字段
	 * @return
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * 设置字段
	 * @param columns
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	/**
	 * 获取备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Table{" +
				"name='" + name + '\'' +
				", primaryKey=" + primaryKey +
				", columns=" + columns +
				", remark='" + remark + '\'' +
				", columnToPropertyMap=" + columnToPropertyMap +
				", columnNameListString='" + columnNameListString + '\'' +
				'}';
	}
}