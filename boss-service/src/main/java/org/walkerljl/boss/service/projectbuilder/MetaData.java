package org.walkerljl.boss.service.projectbuilder;

import org.walkerljl.boss.service.projectbuilder.entity.AppInfo;
import org.walkerljl.boss.service.projectbuilder.entity.Column;
import org.walkerljl.boss.service.projectbuilder.entity.Database;
import org.walkerljl.boss.service.projectbuilder.entity.Table;

import java.sql.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijunlin
 */
public class MetaData {

    public static void buildAppInfo(AppInfo appInfo) {
        // 应用配置
        Connection conn = null;
        try {
            Database database = appInfo.getDatabase();
            Class.forName(database.getType().getDriver());
            conn = DriverManager.getConnection(database.getAddress(), database.getUser(), database.getPassword());
            // 收集数据库信息
            DatabaseMetaData dm = conn.getMetaData();
            ResultSet rs = dm.getTables(database.getName(), null, null, new String[]{"TABLE"});
            while (rs.next()) {
                Table table = new Table();
                table.setName(rs.getString("TABLE_NAME"));
                table.setRemark(rs.getString("REMARKS"));
                database.addTable(table);
            }
            rs.close();

            for (Table table : database.getTables()) {
                rs = dm.getPrimaryKeys(database.getName(), null, table.getName());
                Set<String> primarySet = new HashSet<String>();
                while (rs.next()) {
                    primarySet.add(rs.getString("COLUMN_NAME"));
                }
                rs.close();

                rs = dm.getColumns(database.getName(), null, table.getName(), null);
                while (rs.next()) {
                    Column column = new Column();
                    column.setName(rs.getString("COLUMN_NAME"));
                    column.setLength(rs.getInt("COLUMN_SIZE"));
                    column.setNullAble(rs.getBoolean("NULLABLE"));
                    column.setRemark(rs.getString("REMARKS"));
                    column.setPrimaryKey(primarySet.contains(column.getFieldName()));
                    column.setJavaType(getType(rs.getInt("DATA_TYPE")));
                    column.setJdbcType(rs.getString("TYPE_NAME"));
                    table.addColumn(column);
                }
                rs.close();

            }
        } catch (Exception e) {
            System.err.println("获取数据库信息异常:" + e.getMessage());
            System.exit(1);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e2) {

            }
        }
    }

    /**
     * 数据库类型映射
     *
     * @param type
     * @return
     */
    private static Class<?> getType(int type) {
        Class<?> clazz = null;
        switch (type) {
            // 字符类型
            case Types.CLOB:
            case Types.NCLOB:
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.NVARCHAR:
            case Types.LONGNVARCHAR:
            case Types.LONGVARCHAR:
                clazz = String.class;
                break;
            // 数字类型
            case Types.NUMERIC:
            case Types.DECIMAL:
                clazz = Number.class;
                break;
            case Types.BIT:
            case Types.BOOLEAN:
                clazz = Boolean.class;
                break;
            case Types.TINYINT:
            case Types.SMALLINT:
            case Types.INTEGER:
                clazz = Integer.class;
                break;
            case Types.VARBINARY:
            case Types.BIGINT:
                clazz = Long.class;
                break;
            case Types.REAL:
                clazz = Float.class;
                break;
            case Types.FLOAT:
            case Types.DOUBLE:
                clazz = Double.class;
                break;
            case Types.DATE:
            case Types.TIME:
            case Types.TIMESTAMP:
                clazz = Date.class;
                break;
            // 其他
            default:
                clazz = Object.class;
        }
        return clazz;
    }

    public static Table getTestTable() {
        Table table = new Table();
        table.setName("test");
        table.setRemark("测试表");
        Column column1 = new Column();
        column1.setName("id");
        column1.setRemark("编号");
        column1.setPrimaryKey(true);
        column1.setJavaType(Long.class);

        Column column2 = new Column();
        column2.setName("name");
        column2.setRemark("名称");
        column2.setJavaType(String.class);

        Column column3 = new Column();
        column3.setName("created");
        column3.setRemark("创建时间");
        column3.setJavaType(Date.class);
        table.addColumn(column1);
        table.addColumn(column2);
        table.addColumn(column3);
        return table;
    }
}
