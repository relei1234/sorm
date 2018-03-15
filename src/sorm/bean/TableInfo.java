package sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * 存储表结构的信息
 * @author Administrator
 *
 */
public class TableInfo {
	/**
	 * 表名
	 */
	private String name;
	/**
	 * 所有字段的信息
	 */
	private Map<String ,ColumnInfo>columns;
	/**
	 * 唯一主键（目前我们只能处理表中只有一个主键的情况）
	 */
	private ColumnInfo onlyPrikey;
	/**
	 * 联合主键，在这里存储
	 */
	private List<ColumnInfo>prikeys;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}
	public ColumnInfo getOnlyPrikey() {
		return onlyPrikey;
	}
	public void setOnlyPrikey(ColumnInfo onluPrikey) {
		this.onlyPrikey = onluPrikey;
	}
	public TableInfo(String name, Map<String, ColumnInfo> columns, ColumnInfo onluPrikey) {
		super();
		this.name = name;
		this.columns = columns;
		this.onlyPrikey = onluPrikey;
	}
	public TableInfo() {
		super();
	}
	public TableInfo(String name, Map<String, ColumnInfo> columns, ColumnInfo onluPrikey, List<ColumnInfo> prikeys) {
		super();
		this.name = name;
		this.columns = columns;
		this.onlyPrikey = onluPrikey;
		this.prikeys = prikeys;
	}
	public TableInfo(String name, List<ColumnInfo> prikeys, Map<String, ColumnInfo> columns) {
		super();
		this.name = name;
		this.columns = columns;
		this.prikeys = prikeys;
	}
	public List<ColumnInfo> getPrikeys() {
		return prikeys;
	}
	public void setPrikeys(List<ColumnInfo> prikeys) {
		this.prikeys = prikeys;
	}
	


}
