package option.option_name.factory;

import java.util.ArrayList;
import java.util.List;

import option.option_name.OptionButton;
import option.option_name.UpperOption;

/**샷옵션의 하위옵션들을 만들어주는 팩토리클래스*/
public class ShotFactory extends LowerOptionFactory {

	public ShotFactory(UpperOption upperOption) {
		super(upperOption);
	}

	@Override
	protected String[] setLowerOptions() {
		return new String[] {"연하게", "기본", "진하게"};
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
		optionImage.add("images/options/half shot.jpg");
		optionImage.add("images/options/one shot.jpg");
		optionImage.add("images/options/two shot.jpg");
		return optionImage;
	}
	
	@Override
	public List<String> optionImageBlur() {
		List<String> optionImageBLur = new ArrayList<>();
		optionImageBLur.add("images/options/blur_half shot.jpg");
		optionImageBLur.add("images/options/blur_one shot.jpg");
		optionImageBLur.add("images/options/blur_two shot.jpg");
		return optionImageBLur;
	}

}
