package option.option_name.factory;

import java.util.List;

import option.option_name.OptionButton;
import option.option_name.UpperOption;

/**각 상위 옵션을 받으면 하위 옵션을 자동 생성해주는 팩토리클래스*/
public abstract class LowerOptionFactory {
	
	String name;
	String[] lowerOptions;
	
	public LowerOptionFactory(UpperOption upperOption) {
		name = upperOption.name();
		lowerOptions = setLowerOptions();
	}
	
	/**하위옵션의 이름들을 배정하는 메서드*/
	protected abstract String[] setLowerOptions();
	
	/**하위옵션의 이미지들을 담는 리스트*/
	public abstract List<String> optionImage();
	
	/**하위옵션 위에 마우스를 가져갔을 때 나오는 이미지를 담는 리스트*/
	public abstract List<String> optionImageBlur();
	
	/**해당 상위옵션의 하위옵션버튼들을 담는 리스트*/
	public abstract List<OptionButton> optionList();
	
	/**상위옵션의 이름을 반환하는 메서드*/
	public String getName() {
		return name;
	}
	
	/**해당 상위옵션의 하위옵션들을 String[]로 반환하는 메서드*/
	public String[] getLowerOptions() {
		return lowerOptions;
	}
	
	
}
