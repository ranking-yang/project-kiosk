package phone_button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

/** PhoneNumberScreen에서 NumButton을 눌렀을 때 해당하는 숫자를 반환하는 클래스.
 * 	PhoneNumberScreen 클래스와 같은 패키지 내에 있어야 합니다.
 */
public class NumberActionListener implements ActionListener {

	StringBuilder phoneNum;
	PhoneNumberScreen parent;
	List<JButton> list; 
	
	/** 인스턴스의 TotalNumber를 받기 위해서 필요합니다. */
	public NumberActionListener(PhoneNumberScreen parent) {
		phoneNum = new StringBuilder(parent.getTotalNumber());
		this.parent = parent;
		list = Arrays.asList(parent.btns);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int btnNow = list.indexOf(e.getSource());
		int len = phoneNum.length();
		
		if(btnNow == 11) {
			if(len - 1 < 0) 
				return;
			
			phoneNum.deleteCharAt(len - 1);
		}
		
		if(len >= 13) {
		} else if(btnNow == 9) {
			if(len >= 11) 
				return;
			
			phoneNum.append(parent.btnText[btnNow]);
		} else if(btnNow != 11) {
			phoneNum.append(parent.btnText[btnNow]);
		}
		
		updateSB();
		
		parent.phoneNum.setText(numSecurity(phoneNum.toString()));
	}
	
	/** 하이푼(-) 추가와 관련된 동작을 수행. 버튼을 누를 때마다 sb의 길이를 측정해서
	 *  4, 9 index 이상이면 -를 추가. */
	private void updateSB() {
		if(phoneNum.length() < 4) 
			return;
		
		if(phoneNum.charAt(3) != '-')
			phoneNum.insert(3, '-');
		
		if(phoneNum.length() < 9) 
			return;
		
		if(phoneNum.charAt(8) != '-')
			phoneNum.insert(8, '-');
	}
	
	/** 
	 * 	전화번호 가운데자리를 '*'로 전환해주는 메서드.
	 *  @param number 변환하고자 하는 전화번호. 
	 */
	private String numSecurity(String number) {
		StringBuilder sb = new StringBuilder(number);
		
		for(int i = 4; i < sb.length() - 1 && i < 8; ++i) {
			sb.setCharAt(i, '*');
		}
		
		return sb.toString();
	}

	public String getPhoneNumStr() {
		return phoneNum.toString();
	}
	
}
