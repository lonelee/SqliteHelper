package com.lonelee.test;

import com.lonelee.sqlitehelper.builder.TableBuilder;
import com.lonelee.sqlitehelper.builder.TriggerBuilder;
import com.lonelee.sqlitehelper.table.column.ColumnType;
import com.lonelee.sqlitehelper.table.event.DatabaseEvent;
import com.lonelee.sqlitehelper.table.strategy.ConflictStrategy;

/**
 * Created by Administrator on 2015/11/24.
 */
public class Test {

    public static void main(String[] args) {

        TableBuilder schoolClass_table = new TableBuilder("school_class")
                .addDefaultPrimaryKey()
                .column("class_name", ColumnType.TEXT)
                .unique("class_name", ConflictStrategy.IGNORE);
        System.out.println(schoolClass_table.buildSQL());

        TableBuilder student_table = new TableBuilder("student")
                .addDefaultPrimaryKey()
                .column("student_name", ColumnType.TEXT)
                .column("student_age", ColumnType.INTEGER)
                .column("belong_class_name", ColumnType.TEXT, "school_class", "class_name");
        System.out.println(student_table.buildSQL());

        TriggerBuilder cls_stu_trigger = new TriggerBuilder("cls_stu_trigger")
                .before(DatabaseEvent.DELETE, "school_class")
                .action(DatabaseEvent.DELETE, "student", "student.belong_class_name = old.class_name");
        System.out.println(cls_stu_trigger.buildSQL());
    }

}
