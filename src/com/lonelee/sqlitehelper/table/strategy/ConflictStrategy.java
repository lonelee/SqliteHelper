package com.lonelee.sqlitehelper.table.strategy;

/**
 * 
 * <li>类描述:
 * <li>功能详细描述: ON CONFLICT子句定义了解决约束冲突的算法
 * <p>语法：</p>
 * <p>conflict-clause ::= 	ON CONFLICT conflict-algorithm</p>
 * <p>conflict-algorithm ::= 	ROLLBACK | ABORT | FAIL | IGNORE | REPLACE</p>
 * 
 * @author  lishen
 * @date  [2015-1-1]
 */
public enum ConflictStrategy {
	
	/**
	 * 当发生约束冲突，立即ROLLBACK，即结束当前事务处理，命令中止并返回SQLITE_CONSTRAINT代码。
	 * 若当前无活动事务（除了每一条命令创建的默认事务以外），则该算法与ABORT相同。
	 */
	ROLLBACK("ON CONFLICT ROLLBACK"),
	/**
	 * 当发生约束冲突，命令收回已经引起的改变并中止返回SQLITE_CONSTRAINT。
	 * 但由于不执行ROLLBACK，所以前面的命令产生的改变将予以保留。
	 * 缺省采用这一行为。
	 */
	ABORT("ON CONFLICT ABORT"),
	/**
	 * 当发生约束冲突，命令中止返回SQLITE_CONSTRAINT。
	 * 但遇到冲突之前的所有改变将被保留。
	 */
	FAIL("ON CONFLICT FAIL"),
	/**
	 * 当发生约束冲突，发生冲突的行将不会被插入或改变。但命令将照常执行。
	 * 在冲突行之前或之后的行将被正常的插入和改变，且不返回错误信息。
	 */
	IGNORE("ON CONFLICT IGNORE"),
	/**
	 * 当发生UNIQUE约束冲突，先存在的，导致冲突的行在更改或插入发生冲突的行之前被删除。
	 * 这样，更改和插入总是被执行。命令照常执行且不返回错误信息。
	 * 当发生NOT NULL约束冲突，导致冲突的NULL值会被字段缺省值取代。
	 * 若字段无缺省值，执行ABORT算法。
	 */
	REPLACE("ON CONFLICT REPLACE");
	
	public String mValue;

	private ConflictStrategy(String value) {
		mValue = value;
	}
}
