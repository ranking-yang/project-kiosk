package manager.ImageFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import manager.menu_management.EditMenuDialog;
import project.PosFrame;
import project.PosFrameProperties;

/** 메뉴 업데이트시 image파일을 설정하는 클래스
 *  ImageFileInsert 클래스와 다르게 DB통신 과정보다 먼저
 *  이 클래스 작업이 이루어져야함. */
public class ImageFileUpdate extends JFileChooser {
	/** JFileChooser for ImageFileUpdate */
	private static final long serialVersionUID = -8657511652760025775L;
	
	private String directoryPath;
	
	private String existingPathStr; 
	
	private File selectedFile;		
	private File newFile;			
	private Path newFilePath; 		
	private String newPathStr; 		
							
	private FileFilter filter;
	
	private PosFrame frame;
	private EditMenuDialog parent;
	
	// 0 = 파일 선택한 적 없음. 1 = 기존파일 이름과 동일함. 
	// 2 = 새로운 파일 이름과 중복되는 파일이 존재함. 3 = 파일에 관한 예외사항 없음.
	private int executeType = 0;
	
	/** 생성시, 기본 경로와 확장자 필터를 설정함
	 *  @param parent FileChooser가 팝업될 상위 container */
	public ImageFileUpdate(EditMenuDialog parent) {
		this.frame = PosFrameProperties.frame;
		this.parent = parent;
		this.directoryPath = PosFrameProperties.PRODUCT_IMAGE_DIR; 
		
		this.existingPathStr = parent.getProductThumbnail().getText();
		
		setDialogTitle("파일 선택");
		
        setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));
        
        String filePattern = ".*[.]jpg|.*[.]png";
        
        filter = new FileFilter() {
			@Override
			public String getDescription() {
				return "jpg and png images";
			}
			
			@Override
			public boolean accept(File f) {
				return Pattern.matches(filePattern, f.getName());
			}
		};
        
		addChoosableFileFilter(filter);
	}

	/** 다이얼로그를 호출 */ 
	public void showFileDialog() {
		int result = showDialog(frame, "선택");
	    
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = getSelectedFile();
		    
		    if(filter.accept(selectedFile)) {
		    	newFile = new File(directoryPath + selectedFile.getName());
		    	
		    	newFilePath = newFile.toPath();
		    	
	    		this.newPathStr = newFilePath.toString(); 		  
	    		parent.getProductThumbnail().setText(newPathStr);
		    } else {
		    	JOptionPane.showMessageDialog(frame, "파일 확장자 확인", "파일 선택 오류", JOptionPane.ERROR_MESSAGE);
		    	return;
		    }
		    
		    if(newPathStr.equals(existingPathStr)) {
		    	executeType = 1;
		    	return;
		    }
		    
		    if(Files.exists(newFilePath, LinkOption.NOFOLLOW_LINKS)) {
				String modifyName = fileNameConvert(selectedFile.getName()); 
				File modiFile = new File(directoryPath + modifyName);
		    	
				this.newFilePath = modiFile.toPath();
				
		    	this.newPathStr = newFilePath.toString(); 
		    	parent.getProductThumbnail().setText(newPathStr);
		    	
		    	executeType = 2;
		    	return;
		    }
		    
		    executeType = 3; 
		}
	}

	/** DB통신보다 이 메서드가 선행되어야함.
	 *  경우에 따라 productThumbnail TextField의 값을 변경시킬 수 있기 때문에 */
	public void fileUpdateExecute() {
		
		if(executeType == 0)
			return;
		
		try {
			File existingPath;
			switch(executeType) {
			case 1: 
				Files.copy(selectedFile.toPath(), newFilePath, StandardCopyOption.REPLACE_EXISTING);
				break;
			case 2: 
				existingPath = new File(existingPathStr);
				Files.delete(existingPath.toPath());
				
				Files.copy(selectedFile.toPath(), newFilePath);
				break;
			default: 
				existingPath = new File(existingPathStr);
				Files.delete(existingPath.toPath());
				
				Files.copy(selectedFile.toPath(), newFilePath);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 문자열 맨 뒤가 숫자면 + 1을 해준다. 아니면 2를 붙여준다
	 *  @param fileName 파일명을 넣어준다 */
	private String fileNameConvert(String fileName) {
		StringBuilder sb = new StringBuilder(fileName);
		
		int len = sb.length();
		
		int pathLast = len - 5;
		
		int dotIndex = len - 4;
		
		if(!Character.isDigit(sb.charAt(pathLast))) {
			sb.insert(dotIndex , 2);
		} else {
			int index = 0; 
			for(int i = pathLast; i >= 0; --i) {
				if(!Character.isDigit(sb.charAt(i))) {
					index = ++i;
					break;
				}
			}
			
			sb.replace(index, dotIndex, String.valueOf(Integer.parseInt(sb.substring(index, dotIndex)) + 1));
		}
		
		return sb.toString();
	}
	
}
