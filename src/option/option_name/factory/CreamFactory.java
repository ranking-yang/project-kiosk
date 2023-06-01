package option.option_name.factory;

import java.util.ArrayList;
import java.util.List;

import option.option_name.OptionButton;
import option.option_name.UpperOption;

/**크림옵션의 하위옵션들을 만들어주는 팩토리클래스*/
public class CreamFactory extends LowerOptionFactory {

	public CreamFactory(UpperOption upperOption) {
		super(upperOption);
	}

	@Override
	protected String[] setLowerOptions() {
		return new String[] {"없음", "기본", "많이"};
	}
	
	@Override
	public List<OptionButton> optionList() {
		List<OptionButton> list = new ArrayList<>();
		String[] strArry = super.getLowerOptions();
		String upperName = super.getName();
		
		for (int i = 0; i < strArry.length; ++i) {
			list.add(new OptionButton(strArry[i], upperName, i));
		}
		return list;
	}
	
	@Override
	public List<String> optionImage() {
		List<String> optionImage = new ArrayList<>();
		optionImage.add("images/options/not choice.jpg");
		optionImage.add("images/options/cream.jpg");
		optionImage.add("images/options/many cream.jpg");
		return optionImage;
	}
	
	@Override
	public List<String> optionImageBlur() {
		List<String> optionImageBLur = new ArrayList<>();
		optionImageBLur.add("images/options/blur_not choice.jpg");
		optionImageBLur.add("images/options/blur_cream.jpg");
		optionImageBLur.add("images/options/blur_many cream.jpg");
		return optionImageBLur;
	}


}
