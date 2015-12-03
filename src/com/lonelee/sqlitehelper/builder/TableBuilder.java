package com.lonelee.sqlitehelper.builder;

import com.lonelee.sqlitehelper.table.column.Column;
import com.lonelee.sqlitehelper.table.column.ColumnType;
import com.lonelee.sqlitehelper.table.strategy.ConflictStrategy;
import com.lonelee.sqlitehelper.util.StringUtil;

import java.util.ArrayList;

/**
 * Table构建类
 */
public class TableBuilder {
	
	private String mTableName;
	private ArrayList<Column> mColumns = new ArrayList<>();

	private String mUniqueClause;
	
	public TableBuilder(String tableName) {
		mTableName = tableName;
	}

    /**
     * 添加SQLite默认主键
     * @return
     */
    public TableBuilder addDefaultPrimaryKey() {
        mColumns.add(new Column("_id", ColumnType.INTEGER).primaryKey());
        return this;
    }

	public TableBuilder column(String name, ColumnType type) {
		mColumns.add(new Column(name, type));
		return this;
	}
	
	public TableBuilder column(String name, ColumnType type, boolean primaryKey) {
		if (primaryKey && !ColumnType.INTEGER.equals(type)) {
			throw new UnsupportedOperationException("PrimaryKey support ColumnType.INTEGER currently.");
		}
		mColumns.add(new Column(name, type).primaryKey());
		return this;
	}

	public TableBuilder column(String name, ColumnType type, boolean notNull, String defaultValue) {
		mColumns.add(new Column(name, type).notNull().defaultValue(defaultValue));
		return this;
	}

    /**
     *  生成表字段的方法
     * @param name 字段名
     * @param type 字段类型
     * @param refTable 引用表名
     * @param refColName 引用表中的字段
     * @return
     */
    public TableBuilder column(String name, ColumnType type, String refTable, String refColName) {
        mColumns.add(new Column(name, type).references(refTable, refColName));
        return this;
    }

	public TableBuilder unique(String column, ConflictStrategy strategy) {
		mUniqueClause = "UNIQUE (" + column + ") " + strategy.mValue;
		return this;
	}

    private void buildColumns(StringBuffer sql){
        final int count = mColumns.size();
        for (int i = 0; i < count; i++) {
            Column column = mColumns.get(i);
            column.buildSQL(sql);
            if (i != count - 1) {
                sql.append(", ");
            }
        }
    }
	
	public String buildSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE TABLE IF NOT EXISTS [")
			.append(mTableName)
			.append("] (");
        buildColumns(sql);
		if (!StringUtil.isEmpty(mUniqueClause)) {
			sql.append(" ").append(mUniqueClause);
		}
		sql.append(")");
		return sql.toString();
	}



	@Override
	public String toString() {
		return buildSQL().toString();
	}
}
