package sorm.bean;
/**
 * ��װ��java���Ժ�get set������Դ����
 * @author Administrator
 *
 */
public class JavaFieldGetSet {
	/**
	 * ���Ե�Դ����Ϣ���� private int id
	 */
	private String filedInfo;
	/**
	 * get��Դ����Ϣ����public int getUserId(){}
	 */
	private String getInfo;
	/**
	 * set��Դ����Ϣ����public int setId(){}
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
