package option.option_name;

import javax.swing.JButton;

/**상위옵션의 이름과 SerialNumber에 저장할 값과, 하위옵션의 이름을
 * 가지는 버튼클래스이다.*/
public class OptionButton extends JButton {
	/** JButton for OptionButton */
	private static final long serialVersionUID = -1181582160979503330L;
	
	String lowerName;
	String upperName;
	int optionNum;
	
	public OptionButton(String name, String upperName, Integer optionNum) {
		this.lowerName = name;
		this.upperName = upperName;
		this.optionNum = optionNum;
	}
	
	/**상위옵션의 이름을 반환하는 메서드*/
	public String getUpperName() {
		return upperName;
	}
	/**해당 하위옵션의 번호(0, 1, 2)를 반환하는 메서드*/
	public int getOptionNum() {
		return optionNum;
	}
	/**해당 하위옵션의 이름을 반환하는 메서드*/
	public String getLowerName() {
		return lowerName;
	}
	
}
