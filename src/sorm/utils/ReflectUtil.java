package sorm.utils;

import java.lang.reflect.Method;

public class ReflectUtil {
	/**
	 * 调用obj对象对应属性fieldName的get犯法
	 * @param fieldName
	 * @param obj
	 * @return
	 */
	public static Object invokeGet(String fieldName,Object obj){
		//通过反射机制调用属性对应的get或set方法
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
