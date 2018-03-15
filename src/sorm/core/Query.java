package sorm.core;

import java.util.List;

/**
 * �����ѯ�������ṩ����ĺ����ࣩ
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public interface Query {
	/**
	 * ֱ��ִ��һ��DML���
	 * @param sql���
	 * @param params����
	 * @return ִ��sql����Ӱ���¼������
	 */
	public int executDML(String sql,Object[]params);
	/**
	 * ��һ������洢�����ݿ���
	 * �Ѷ����в�Ϊnull�����������ݿ��д洢���������Ϊnull�����0
	 * @param objҪ�洢�Ķ���
	 */
	public void insert(Object obj);
	/**
	 * ɾ��clazz����ӡ�ı��еļ�¼��ָ��Id�ļ�¼��
	 * @param clazz�����Ӧ�����class����
	 * @param id������ֵ
	 */
	public void delect(Class clazz,Object id);//delect from User where id=2;
	/**
	 * ɾ�����������ݿ��ж�ӡ�ļ�¼���������ڵ����Ӧ��������������ֵ��Ӧ����¼��
	 * @param obj
	 */
	public void delect(Object obj);
	/**
	 * ���¶����Ӧ�ļ�¼������ֻ����ָ�����ֶε�ֵ
	 * @param obj��Ҫ���µĶ���
	 * @param fieldNames���µ������б�
	 * @return ִ��sql����Ӱ�������
	 */
	public int update(Object obj,String [] fieldNames);//update user set uname=?,pwd=?
	/**
	 * ��ѯ���ض��м�¼������ÿ�м�¼��װ��clazzָ������Ķ�����
	 * @param sql ��ѯ���
	 * @param clazz	��װ���ݵ�javabean���Classd����
	 * @param params  sql�Ĳ���
	 * @return	���ز�ѯ�Ľ��
	 */
	public List queryRows(String sql,Class clazz,Object[]params);
	/**
	 * ��ѯ����һ�м�¼�������ü�¼��װ��clazzָ������Ķ���
	 * @param sql ��ѯ���
	 * @param clazz ��װ���ݵ�javabean���Class����
	 * @param params sql�Ĳ���
	 * @return ��ѯ���Ľ��
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[]params);
	/**
	 * ��ѯ����һ��ֵ��һ��һ�У�������ֵ����
	 * @param sql ��ѯ���
	 * @param params sql����
	 * @return ��ѯ���Ľ��	
	 */
	public Object queryValue(String sql,Object[]params);
	/**
	 * ��ѯ����һ�����֣�һ��һ�У���������ֵ����
	 * @param sql	��ѯ���
	 * @param params	sql���
	 * @return	��ѯ��������
	 */
	public Number queryNumber(String sql,Object[]params);

}
