package com.lonelee.sqlitehelper.table.column;


/**
 * 
 * <li>类描述: 
 * <li>功能详细描述:
 * 
 * @author lishen
 * @date [2015-1-1]
 */
public class Column {
	/** 列名 */
	private String mName;
	/** 类型 */
	private ColumnType mColumnType;
	/** 是否为主键(只考虑Integer类型，自增) */
	private boolean mPrimaryKey;
	/** 是否不能为空 */
	private boolean mNotNull;
	/** 默认值 */
	private String mDefaultClause;
	/** 字段关联 */
	private String mReferencesClause;
	
	public Column(String name, ColumnType type) {
		mName = name;
		mColumnType = type;
	}

	public Column primaryKey(){
		mPrimaryKey = true;
		return this;
	}
	
	public Column notNull(){
		mNotNull = true;
		return this;
	}
	
	public Column defaultValue(String defaultVal){
		mDefaultClause = "DEFAULT " + defaultVal;
		return this;
	}
	
	public Column references(String tableName, String columnName) {
		mReferencesClause = "REFERENCES " + tableName + "(" + columnName + ")";
		return this;
	}
	
	public StringBuffer buildSQL(StringBuffer sql) {
		sql.append(mName);
		if (mPrimaryKey) {
			sql.append(" INTEGER PRIMARY KEY AUTOINCREMENT");
		} else {
			sql.append(" ").append(mColumnType.mValue);
			if (mNotNull) {
				sql.append(" NOT NULL");
			}
			if (mDefaultClause != null) {
				sql.append(" ").append(mDefaultClause);
			}
			if (mReferencesClause != null) {
				sql.append(" ").append(mReferencesClause);
			}
		}
		return sql;
	}
	
}
