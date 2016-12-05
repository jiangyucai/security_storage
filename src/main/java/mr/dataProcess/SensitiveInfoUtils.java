package mr.dataProcess;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public final class SensitiveInfoUtils {
	public static String chineseName(String fullName) {
		if (StringUtils.isBlank(fullName)) {
			return "";
		}
		String name = StringUtils.left(fullName, 1);
		return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
	}

	public static String chineseName(String familyName, String givenName) {
		if ((StringUtils.isBlank(familyName)) || (StringUtils.isBlank(givenName))) {
			return "";
		}
		return chineseName(familyName + givenName);
	}

	public static String idCardNum(String id) {
		if (StringUtils.isBlank(id)) {
			return "";
		}
		String num = StringUtils.right(id, 4);
		return StringUtils.leftPad(num, StringUtils.length(id), "*");
	}

	public static String fixedPhone(String num) {
		if (StringUtils.isBlank(num)) {
			return "";
		}
		return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
	}

	public static String mobilePhone(String num, int start, int end, String givenName) {
		if (StringUtils.isBlank(num)) {
			return "";
		}
		return StringUtils.left(num, start).concat(StringUtils
				.removeStart(StringUtils.leftPad(StringUtils.right(num, end), StringUtils.length(num), "*"), "***"));
	}

	public static String address(String address, int sensitiveSize) {
		if (StringUtils.isBlank(address)) {
			return "";
		}
		int length = StringUtils.length(address);
		return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
	}

	public static String email(String email) {
		if (StringUtils.isBlank(email)) {
			return "";
		}
		int index = StringUtils.indexOf(email, "@");
		if (index <= 1) {
			return email;
		}
		return StringUtils.rightPad(StringUtils.left(email, 1), index, "*")
				.concat(StringUtils.mid(email, index, StringUtils.length(email)));
	}

	public static String bankCard(String cardNum) {
		if (StringUtils.isBlank(cardNum)) {
			return "";
		}
		return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(
				StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
	}

	public static String cnapsCode(String code) {
		if (StringUtils.isBlank(code)) {
			return "";
		}
		return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
	}

	private static Field[] findAllField(Class<?> clazz) {
		Field[] fileds = clazz.getDeclaredFields();
		while ((clazz.getSuperclass() != null) && (!Object.class.equals(clazz.getSuperclass()))) {
			fileds = (Field[]) ArrayUtils.addAll(fileds, clazz.getSuperclass().getDeclaredFields());
			clazz = clazz.getSuperclass();
		}
		return fileds;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private static void replace(Field[] fields, Object javaBean, Set<Integer> referenceCounter)
			throws IllegalArgumentException, IllegalAccessException {
		if ((fields != null) && (fields.length > 0)) {
			Field[] arrayOfField = fields;
			int j = fields.length;
			for (int i = 0; i < j; i++) {
				Field field = arrayOfField[i];
				field.setAccessible(true);
				if ((field != null) && (javaBean != null)) {
					Object value = field.get(javaBean);
					if (value != null) {
						Class type = value.getClass();

						if (type.isArray()) {
							int len = Array.getLength(value);
							for (int i1 = 0; i1 < len; i1++) {
								Object arrayObject = Array.get(value, i1);
								replace(findAllField(arrayObject.getClass()), arrayObject, referenceCounter);
							}
						} else if ((value instanceof Collection)) {
							Collection c = (Collection) value;
							Iterator it = c.iterator();
							while (it.hasNext()) {
								Object collectionObj = it.next();
								replace(findAllField(collectionObj.getClass()), collectionObj, referenceCounter);
							}
						} else if ((value instanceof Map)) {
							Map m = (Map) value;
							Set set = m.entrySet();
							for (Iterator localIterator1 = set.iterator(); localIterator1.hasNext();) {
								Object o = localIterator1.next();
								Map.Entry entry = (Map.Entry) o;
								Object mapVal = entry.getValue();
								replace(findAllField(mapVal.getClass()), mapVal, referenceCounter);
							}
						} else if ((!type.isPrimitive())
								&& (!StringUtils.startsWith(type.getPackage().getName(), "javax."))
								&& (!StringUtils.startsWith(type.getPackage().getName(), "java."))
								&& (!StringUtils.startsWith(field.getType().getName(), "javax."))
								&& (!StringUtils.startsWith(field.getName(), "java."))
								&& (referenceCounter.add(Integer.valueOf(value.hashCode())))) {
							replace(findAllField(type), value, referenceCounter);
						}
					}

					SensitiveInfo annotation = (SensitiveInfo) field.getAnnotation(SensitiveInfo.class);
					if ((field.getType().equals(String.class)) && (annotation != null)) {
						String valueStr = (String) value;
						if (StringUtils.isNotBlank(valueStr))
							switch ($SWITCH_TABLE$storageSecurity$SensitiveInfoUtils$SensitiveType()[annotation.type()
									.ordinal()]) {
							case 1:
								field.set(javaBean, chineseName(valueStr));
								break;
							case 2:
								field.set(javaBean, idCardNum(valueStr));
								break;
							case 3:
								field.set(javaBean, fixedPhone(valueStr));
								break;
							case 4:
								field.set(javaBean, mobilePhone(valueStr, 5, 4, "*"));
								break;
							case 5:
								field.set(javaBean, address(valueStr, 4));
								break;
							case 6:
								field.set(javaBean, email(valueStr));
								break;
							case 7:
								field.set(javaBean, bankCard(valueStr));
								break;
							case 8:
								field.set(javaBean, cnapsCode(valueStr));
							}
					}
				}
			}
		}
	}

	private static int[] $SWITCH_TABLE$storageSecurity$SensitiveInfoUtils$SensitiveType() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Method[] findAllMethod(Class<?> clazz) {
		Method[] methods = clazz.getMethods();
		return methods;
	}

	public static enum SensitiveType {
		CHINESE_NAME,

		ID_CARD,

		FIXED_PHONE,

		MOBILE_PHONE,

		ADDRESS,

		EMAIL,

		BANK_CARD,

		CNAPS_CODE;
	}
}