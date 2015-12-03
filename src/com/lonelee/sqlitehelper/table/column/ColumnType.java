package com.lonelee.sqlitehelper.table.column;

public enum ColumnType {

	INTEGER("INTEGER"), TEXT("TEXT"), REAL("REAL"), BLOB("BLOB");
	
	String mValue;
	
	private ColumnType(String value) {
		mValue = value;
	}
}
