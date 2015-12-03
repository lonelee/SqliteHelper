package com.lonelee.sqlitehelper.table.event;

/**
 * Description:
 * Author: lishen
 * Date: 2015.01.04 15:13
 */
public enum DatabaseEvent {

    INSERT("INSERT"), DELETE("DELETE"), UPDATE("UPDATE"), QUERY("QUERY");

    public String mValue;

    private DatabaseEvent(String value) {
        mValue = value;
    }

}
