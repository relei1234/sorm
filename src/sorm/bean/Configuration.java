package sorm.bean;
/**
 * 观察配置信息
 * @author Administrator
 *
 */
public class Configuration {
	/**
	 * 驱动类
	 */
	private String driver;
	/**
	 * JDBC的Url
	 */
	private String Url;
	/**
	 * 数据库的用户名
	 */
	private String user;
	/**
	 * 数据库的密码
	 */
	private String pwd;
	/**
	 * 正在使用哪个数据库
	 */
	private String usingDB;
	/**
	 * 项目的源码路径
	 */
	private String srcPath;
	/**
	 * 	扫描生成java类的包（po的意思是presistence object持久化对象）
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
