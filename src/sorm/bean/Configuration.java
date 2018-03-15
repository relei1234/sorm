package sorm.bean;
/**
 * �۲�������Ϣ
 * @author Administrator
 *
 */
public class Configuration {
	/**
	 * ������
	 */
	private String driver;
	/**
	 * JDBC��Url
	 */
	private String Url;
	/**
	 * ���ݿ���û���
	 */
	private String user;
	/**
	 * ���ݿ������
	 */
	private String pwd;
	/**
	 * ����ʹ���ĸ����ݿ�
	 */
	private String usingDB;
	/**
	 * ��Ŀ��Դ��·��
	 */
	private String srcPath;
	/**
	 * 	ɨ������java��İ���po����˼��presistence object�־û�����
	 */
	private String poPackage;
	public Configuration(String driver, String Url, String user, String pwd, String usingDB, String srcPath,
			String poPackage) {
		super();
		this.driver = driver;
		this.Url = Url;
		this.user = user;
		this.pwd = pwd;
		this.usingDB = usingDB;
		this.srcPath = srcPath;
		this.poPackage = poPackage;
	}
	public Configuration() {
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String Url) {
		this.Url = Url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsingDB() {
		return usingDB;
	}
	public void setUsingDB(String usingDB) {
		this.usingDB = usingDB;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getPoPackage() {
		return poPackage;
	}
	public void setPoPackage(String poPackage) {
		this.poPackage = poPackage;
	}
	
}
