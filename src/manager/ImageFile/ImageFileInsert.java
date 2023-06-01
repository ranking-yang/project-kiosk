package manager.ImageFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import manager.menu_management.NewMenu;
import project.PosFrame;
import project.PosFrameProperties;

/** 이미지 파일을 선택시 지정한 경로로 복사해주는 클래스
 *  @author HONG */
public class ImageFileInsert extends JFileChooser {
	/** JFileChooser for ImageFileChooser */
	private static final long serialVersionUID = -6807081627563091086L;

	private static String directoryPath;
	
	private String filePath;
	private boolean copyPossible;
	
	private File selectedFile;
	private File newFile;
	
	private FileFilter filter;
	private Path newFilePath;
	
	private PosFrame frame;
	private NewMenu parent;
	
	/** 생성시, 기본 경로와 확장자 필터를 설정함
	 *  @param frame FileChooser가 팝업될 상위 container */
	public ImageFileInsert(NewMenu parent) {
		this.frame = PosFrameProperties.frame;
		this.parent = parent;
		directoryPath = PosFrameProperties.PRODUCT_IMAGE_DIR; 
		
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

	/** 파일 선택 다이얼로그를 호출 */ 
	public void showChooseFileDialog() {
		int result = showDialog(frame, "선택");
	    
		if (result == JFileChooser.APPROVE_OPTION) {
		    selectedFile = getSelectedFile();
		    
		    if(!filter.accept(selectedFile)) {
		    	this.copyPossible = false;
		    	JOptionPane.showMessageDialog(frame, "파일 확장자 확인", "파일 선택 오류", JOptionPane.ERROR_MESSAGE);
		    	return;
		    }
		    
	    	newFile = new File(directoryPath + selectedFile.getName());
	    	newFilePath = newFile.toPath();

	    	if(Files.exists(newFilePath, LinkOption.NOFOLLOW_LINKS)) {
	    		this.copyPossible = false;
	    		JOptionPane.showMessageDialog(frame, "같은 이름의 파일이 있습니다.", "파일 중복 오류", JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}
	    	
    		this.filePath = newFilePath.toString(); 
    		this.copyPossible = true;
    		
    		parent.getProductThumbnail().setText(filePath);
		}
	}
	
	/** 선택된 파일의 경로를 String으로 반환
	 *  선택과정이 제대로 이루어지지 않았다면 null 반환 */
	public String getFilePath() {
		return filePath;
	}	
	
	/** 복사를 수행함 */
	public void fileCopyExecute() {
		
		if(!copyPossible)
			return;
		
    	try {
			Files.copy(selectedFile.toPath(), newFilePath);
    	} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
