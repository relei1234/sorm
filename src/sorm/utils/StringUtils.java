package sorm.utils;
/**
 * ��װ���ַ������õĲ���
 * @author Administrator
 *
 */
public class StringUtils {
	/**
	 * ��Ŀ���ַ�������ĸ��д
	 * @param str	Ŀ���ַ���
	 * @return	����ĸ��Ϊ��д���ַ���
	 */
	public static String firstChar2UpperCase(String str){
		//abcd-->Abcd
		//abcd-->ABCD--��Abcd
		
		return str.toUpperCase().substring(0, 1)+str.substring(1);
	}
}
