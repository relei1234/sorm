package sorm.bean;
/**
 * 封装了java属性和get set方法的源代码
 * @author Administrator
 *
 */
public class JavaFieldGetSet {
	/**
	 * 属性的源码信息，如 private int id
	 */
	private String filedInfo;
	/**
	 * get的源码信息，如public int getUserId(){}
	 */
	private String getInfo;
	/**
	 * set的源码信息，如public int setId(){}
	 */
	private String setInfo;
	public String getFiledInfo() {
		return filedInfo;
	}
	public void setFiledInfo(String filedInfo) {
		this.filedInfo = filedInfo;
	}
	public String getGetInfo() {
		return getInfo;
	}
	public void setGetInfo(String getInfo) {
		this.getInfo = getInfo;
	}
	public String getSetInfo() {
		return setInfo;
	}
	public void setSetInfo(String setInfo) {
		this.setInfo = setInfo;
	}
	public JavaFieldGetSet(String filedInfo, String getInfo, String setInfo) {
		super();
		this.filedInfo = filedInfo;
		this.getInfo = getInfo;
		this.setInfo = setInfo;
	}
	public JavaFieldGetSet() {
		super();
	} 
	@Override
	public String toString() {
		System.out.println(filedInfo);
		System.out.println(getInfo);
		System.out.println(setInfo);
		return super.toString();
	}
}
