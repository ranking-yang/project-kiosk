package project;

/** 
 * 고객정보 암호화용 클래스
 */
public class Encryption {
	
	static final int id = 1;
	static final String charSet = "RlLzAvNGQ1k0u82I9J4@P&3mTVfyFS^HjagMDUnZ6XhKxtwB7W%*cpdbeoE5#iO!qCr$Ys";
	static final int key = 5;
	
	/**
	 * 암호화 메서드
	 * @param target  암호화대상
	 * @param charSet 암호화용 문자열
	 * @param key	  키값
	 */
	public static String encryption(String target) {
		StringBuilder sb = new StringBuilder(target); 

		int setLen = charSet.length();
		
		int tempKey = key % setLen;
		
		for(int i = 0; i < sb.length(); ++i) {
			int index = charSet.indexOf(sb.charAt(i));
			
			if(index != -1) {
				sb.setCharAt(i,
						charSet.charAt((index + tempKey) % setLen));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 복호화메서드
	 * @param target  복호화대상
	 * @param charSet 복호화용 문자열
	 * @param key	  키값
	 */
	public static String decryption(String target) {
		StringBuilder sb = new StringBuilder(target);
		
		int setLen = charSet.length();
		
		int tempKey = key % setLen;
		
		for(int i = 0; i < sb.length(); ++i) {
			int index = charSet.indexOf(sb.charAt(i));
			
			if(index != -1) {
				sb.setCharAt(i,
						charSet.charAt((index - tempKey + setLen) % setLen));
			}
		}
		return sb.toString();
	}
	
	/**
	 * charSet을 섞어주는 메서드. 결과는 같은 길이의 랜덤한 문자열.
	 * @param charSet
	 */
	public static String makeCharSet(String charSet) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < charSet.length(); ++i) {
			int ran = (int)(Math.random() * charSet.length());
			
			String target = String.valueOf(charSet.charAt(ran));
			if(sb.indexOf(target) == -1) {
				sb.append(target);
			} else {
				--i;
			}
		}
		return sb.toString();
	}
	
	
}
