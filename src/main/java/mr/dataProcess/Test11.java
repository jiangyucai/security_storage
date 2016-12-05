package mr.dataProcess;

public class Test11 {
	public static void main(String[] args) {
		String line = "59046620256a3c07903c384c290386a2\001\001\001\001\001\001\001U003002004003004000,U017002006000000000\001S003521000001\001苹果官网\001\001\001未知\001\001\001{'site':{'ruleID':'479976'},'url':{'ruleID:'141072'}}\001\001";

		String uniqueness = line.split("\001")[0];

		String aaaa = uniqueness;

		System.out.println("uniqueness.length()=" + uniqueness.length());

		uniqueness = uniqueness.replace(uniqueness, SensitiveInfoUtils.mobilePhone(uniqueness, 5, 4, "*"));

		System.out.println("uniqueness=" + uniqueness);

		line = line.replace(aaaa, uniqueness);

		System.out.println(line);
	}
}
