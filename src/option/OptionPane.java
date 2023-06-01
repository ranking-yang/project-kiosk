package option;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import option.option_name.OptionButton;
import option.option_name.factory.LowerOptionFactory;
import project.ImageMaker;
import project.PosFrameProperties;

/**상위옵션 카테고리와 하위옵션버튼들을 담는 JLayeredPane*/
public class OptionPane extends JLayeredPane {
	/** JLayeredPane for OptionPane */
	private static final long serialVersionUID = 7895759003520454414L;

	JLabel label;
	String name;
	
	LowerOptionFactory lowerOptionFactory;
	
	private int width = 150;
	private int height = 135;
	
	public OptionPane(LowerOptionFactory lowerOptionFactory) {
		this.lowerOptionFactory = lowerOptionFactory;
		this.name = lowerOptionFactory.getName();
		
		label = new JLabel(name);
		label.setBackground(new Color(230, 230, 230));
		label.setOpaque(true);
		label.setBounds(10, 0, 510, 25);
		label.setFont(PosFrameProperties.HEAD_FONT);
		add(label);
		
		List<OptionButton> btnList = lowerOptionFactory.optionList();
		List<String> optionImage = lowerOptionFactory.optionImage();
		List<String> optionImageBLur = lowerOptionFactory.optionImageBlur();
		
		int x = 10;
		List<OptionButton> opList = new ArrayList<>();
		
		for (int i = 0; i < btnList.size(); ++i) {
			OptionButton btn = btnList.get(i);
			btn.setBounds(x, 27, width, height);
			opList.add(btn);
			
			String image = optionImage.get(i);
			String imageBlur = optionImageBLur.get(i);
			
			btn.addActionListener((e) -> {
				for (int j = 0; j < opList.size(); ++j) {
					OptionButton btn2 = opList.get(j);
					String image2 = optionImage.get(j);
					
					setSerialNumber(btn);
					ImageMaker.setImage(btn2, image2, width, height);
				}
				ImageMaker.setImage(btn, imageBlur, width, height);
			});
			
			x += 170;
			add(btn);
			btn.setBorderPainted(false);
			ImageMaker.setImage(btn, image, width, height);
		}
	}
	
	/** 해당 제품의 옵션일련번호를 구하는 메서드*/
	private void setSerialNumber(OptionButton optionButton) {
		String name = optionButton.getUpperName();
		Integer eachNum = optionButton.getOptionNum();
		String option = optionButton.getLowerName();
		
		
		if (name.equals("샷")) {
			SerialNumber.serialNumber[0] = eachNum;
			SerialNumber.options[0] = option;
		} else if (name.equals("우유")) {
			SerialNumber.serialNumber[1] = eachNum;
			SerialNumber.options[1] = option;
		} else if (name.equals("얼음")) {
			SerialNumber.serialNumber[2] = eachNum;
			SerialNumber.options[2] = option;
		} else if (name.equals("스테비아")) {
			SerialNumber.serialNumber[3] = eachNum;
			SerialNumber.options[3] = option;
		} else {
			SerialNumber.serialNumber[4] = eachNum;
			SerialNumber.options[4] = option;
		}
	}
	
}
