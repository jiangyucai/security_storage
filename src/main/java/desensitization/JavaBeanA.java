package desensitization;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class JavaBeanA
{

  @SensitiveInfo
  private String name;
  @SuppressWarnings("unused")
private JavaBeanB b;
  @SuppressWarnings("unused")
private Date date;
  @SuppressWarnings("unused")
private List<JavaBeanB> list;
  @SuppressWarnings("unused")
private Map<String, JavaBeanB> map;

  public JavaBeanA(String paramString1, String paramString2)
  {
  }

  public String getName()
  {
    throw new Error("Unresolved compilation problem: \n");
  }

  public void setName(String paramString) {
    throw new Error("Unresolved compilation problem: \n");
  }

  public JavaBeanB getB() {
    throw new Error("Unresolved compilation problem: \n");
  }

  public void setB(JavaBeanB paramJavaBeanB) {
    throw new Error("Unresolved compilation problem: \n");
  }

  public List<JavaBeanB> getList() {
    throw new Error("Unresolved compilation problem: \n");
  }

  public void setList(List<JavaBeanB> paramList) {
    throw new Error("Unresolved compilation problem: \n");
  }

  public Map<String, JavaBeanB> getMap() {
    throw new Error("Unresolved compilation problem: \n");
  }

  public void setMap(Map<String, JavaBeanB> paramMap) {
    throw new Error("Unresolved compilation problem: \n");
  }

  public Date getDate() {
    throw new Error("Unresolved compilation problem: \n");
  }

  public void setDate(Date paramDate) {
    throw new Error("Unresolved compilation problem: \n");
  }
}