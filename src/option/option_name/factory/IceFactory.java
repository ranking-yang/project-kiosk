package option.option_name.factory;

import java.util.ArrayList;
import java.util.List;

import option.option_name.OptionButton;
import option.option_name.UpperOption;

/**얼음옵션의 하위옵션들을 만들어주는 팩토리클래스*/
public class IceFactory extends LowerOptionFactory {

	public IceFactory(UpperOption upperOption) {
		super(upperOption);
	}

	@Override
	protected String[] setLowerOptions() {
		return new String[] {"적게", "기본", "많이"};
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
		optionImage.add("images/options/ice.jpg");
		optionImage.add("images/options/default ice.jpg");
		optionImage.add("images/options/many ice.jpg");
		return optionImage;
	}
	
	@Override
	public List<String> optionImageBlur() {
		List<String> optionImageBLur = new ArrayList<>();
		optionImageBLur.add("images/options/blur_ice.jpg");
		optionImageBLur.add("images/options/blur_default ice.jpg");
		optionImageBLur.add("images/options/blur_many ice.jpg");
		return optionImageBLur;
	}


}
