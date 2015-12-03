package com.lonelee.sqlitehelper.builder;

import com.lonelee.sqlitehelper.table.event.DatabaseEvent;

import java.util.ArrayList;

/**
 * 触发器构建类
 */
public class TriggerBuilder {

    private String mTriggerName;

    private boolean mIsActionBefore;

    private DatabaseEvent mTriggerEvent;
    private String mTriggerTable;

    private ArrayList<String> mActionClauses = new ArrayList<>();

    public TriggerBuilder(String triggerName) {
        mTriggerName = triggerName;
    }

    public TriggerBuilder before(DatabaseEvent event, String tableName) {
        mIsActionBefore = true;
        mTriggerEvent = event;
        mTriggerTable = tableName;
        return this;
    }

    public TriggerBuilder after(DatabaseEvent event, String tableName) {
        mIsActionBefore = false;
        mTriggerEvent = event;
        mTriggerTable = tableName;
        return this;
    }

    /**
     *
     * @param event
     * @param tableName
     * @param whereClause
     * @return
     */
    public TriggerBuilder action(DatabaseEvent event, String tableName, String whereClause) {
        if (event == null || tableName == null || whereClause == null) {
            throw new IllegalArgumentException("Parameters can not be null!");
        }
        mActionClauses.add(buildActionClause(event, tableName, whereClause));
        return this;
    }

    private String buildActionClause(DatabaseEvent event, String tableName, String whereClause) {
        StringBuffer sql = new StringBuffer();
        sql.append(event.mValue).append(" FROM ").append(tableName);
        sql.append(" WHERE ").append(whereClause).append(";");
        return sql.toString();
    }

    public String buildSQL() {
        StringBuffer sql = new StringBuffer();
        sql.append("CREATE TRIGGER [").append(mTriggerName).append("]");
        if (mTriggerEvent != null && mTriggerTable != null) {
            if (mIsActionBefore) {
                sql.append(" BEFORE ");
            } else {
                sql.append(" AFTER ");
            }
            sql.append(mTriggerEvent.mValue)
                    .append(" ON ")
                    .append(mTriggerTable)
                    .append(" FOR EACH ROW BEGIN ");
        } else {
            throw new IllegalArgumentException("Need call before() or after().");
        }
        for (String action : mActionClauses) {
            sql.append(action);
        }
        sql.append(" END;");
        return sql.toString();
    }

}