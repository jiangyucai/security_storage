package desensitization;

import java.util.Map;
import java.util.Set;

public class JavaBeanB {

	@SensitiveInfo
	private String name;
	@SuppressWarnings("unused")
	private JavaBeanA a;
	@SuppressWarnings("unused")
	private Set<JavaBeanA> list;
	@SuppressWarnings("unused")
	private Map<String, JavaBeanA> map;

	public String getName() {
		throw new Error("Unresolved compilation problem: \n");
	}

	public void setName(String paramString) {
		throw new Error("Unresolved compilation problem: \n");
	}

	public JavaBeanA getA() {
		throw new Error("Unresolved compilation problem: \n");
	}

	public void setA(JavaBeanA paramJavaBeanA) {
		throw new Error("Unresolved compilation problem: \n");
	}

	public Set<JavaBeanA> getList() {
		throw new Error("Unresolved compilation problem: \n");
	}

	public void setList(Set<JavaBeanA> paramSet) {
		throw new Error("Unresolved compilation problem: \n");
	}

	public Map<String, JavaBeanA> getMap() {
		throw new Error("Unresolved compilation problem: \n");
	}

	public void setMap(Map<String, JavaBeanA> paramMap) {
		throw new Error("Unresolved compilation problem: \n");
	}
}