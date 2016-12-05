package desensitization;

import org.apache.commons.lang.StringUtils;

public final class SensitiveInfoUtils
{
  public static String chineseName(String fullName, int i, String symbol)
  {
    if (StringUtils.isBlank(fullName)) {
      return "";
    }
    String name = StringUtils.left(fullName, i);
    return StringUtils.rightPad(name, StringUtils.length(fullName), symbol);
  }

  public static String chineseName(String familyName, String givenName)
  {
    if ((StringUtils.isBlank(familyName)) || (StringUtils.isBlank(givenName))) {
      return "";
    }
    return chineseName(familyName + givenName, 1, "*");
  }

  public static String idCardNum(String id, int i, String symbol)
  {
    if (StringUtils.isBlank(id)) {
      return "";
    }
    String num = StringUtils.right(id, i);
    return StringUtils.leftPad(num, StringUtils.length(id), symbol);
  }

  public static String fixedPhone(String num)
  {
    if (StringUtils.isBlank(num)) {
      return "";
    }
    return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
  }

  public static String mobilePhone(String num)
  {
    if (StringUtils.isBlank(num)) {
      return "";
    }
    return StringUtils.left(num, 3).concat(
      StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));
  }

  public static String address(String address, int sensitiveSize, String symbol)
  {
    if (StringUtils.isBlank(address)) {
      return "";
    }
    int length = StringUtils.length(address);
    return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
  }

  public static String email(String email, int i, String symbol)
  {
    if (StringUtils.isBlank(email)) {
      return "";
    }
    int index = StringUtils.indexOf(email, "@");
    if (index <= 1) {
      return email;
    }
    return StringUtils.rightPad(StringUtils.left(email, i), index, symbol)
      .concat(StringUtils.mid(email, index, StringUtils.length(email)));
  }

  public static String bankCard(String cardNum)
  {
    if (StringUtils.isBlank(cardNum)) {
      return "";
    }
    return StringUtils.left(cardNum, 6).concat(StringUtils.removeStart(
      StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
  }

  public static String cnapsCode(String code)
  {
    if (StringUtils.isBlank(code)) {
      return "";
    }
    return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
  }
}