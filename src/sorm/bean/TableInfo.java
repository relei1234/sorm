package sorm.bean;

import java.util.List;
import java.util.Map;

/**
 * �洢��ṹ����Ϣ
 * @author Administrator
 *
 */
public class TableInfo {
	/**
	 * ����
	 */
	private String name;
	/**
	 * �����ֶε���Ϣ
	 */
	private Map<String ,ColumnInfo>columns;
	/**
	 * Ψһ������Ŀǰ����ֻ�ܴ������ֻ��һ�������������
	 */
	private ColumnInfo onlyPrikey;
	/**
	 * ����������������洢
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
