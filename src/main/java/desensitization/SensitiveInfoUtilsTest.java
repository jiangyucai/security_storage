package desensitization;

public class SensitiveInfoUtilsTest {

	public void testChineseNameString() {
		System.out.println(SensitiveInfoUtils.chineseName("李先生", 1, "*"));
	}

	public void testChineseNameStringString() {
		System.out.println(SensitiveInfoUtils.chineseName("李", "雷"));
	}

	public void testIdCardNum() {
		System.out.println(SensitiveInfoUtils.idCardNum("1103541983073188711", 4, "*"));
	}

	public void testFixedPhone() {
		System.out.println(SensitiveInfoUtils.fixedPhone("01077482277"));
	}

	public void testMobilePhone() {
		System.out.println(SensitiveInfoUtils.mobilePhone("13777446578"));
	}

	public void testAddress() {
		System.out.println(SensitiveInfoUtils.address("北京朝阳区酒仙桥中路26号院4号楼人人大厦", 8, "*"));
	}

	public void testEmail() {
		System.out.println(SensitiveInfoUtils.email("66374777@qq.com", 1, "*"));
	}

	public void testBankCard() {
		System.out.println(SensitiveInfoUtils.bankCard("6228480402565890018"));
	}

	public void testCnapsCode() {
		System.out.println(SensitiveInfoUtils.cnapsCode("102100029679"));
	}
}