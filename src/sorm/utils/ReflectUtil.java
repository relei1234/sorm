package sorm.utils;

import java.lang.reflect.Method;

public class ReflectUtil {
	/**
	 * ����obj�����Ӧ����fieldName��get����
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName,Object obj){
		//ͨ��������Ƶ������Զ�Ӧ��get��set����
				try {
					Class c=obj.getClass();
					Method m = c.getDeclaredMethod("get"+	StringUtils.firstChar2UpperCase(fieldName), null);
					return m.invoke(obj, null);
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} 
	}
	public static void invokeSet(Object obj,String columnName,Object columnValue){
		Method m;
		try {
			m = obj.getClass().getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(columnName),
					columnValue.getClass());
			m.invoke(obj, columnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
