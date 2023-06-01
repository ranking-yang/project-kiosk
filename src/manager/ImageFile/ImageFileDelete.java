package manager.ImageFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/** 이미지 파일의 경로를 문자열로 받으면 해당 경로의 파일을 삭제하는 클래스
 *  @author HONG */
public class ImageFileDelete {

	JPanel parent;
	
	Path filePath;
	
	/** 삭제할 파일의 경로는 인스턴스 생성시가 아니라 getFilePath() 메서드를 통해 받습니다.
	 *  @param parent	삭제 실패시 경고창이 뜰 부모 컨테이너 */
	public ImageFileDelete(JPanel parent) {
		this.parent = parent;
	}
	
	/** 파일 삭제 실행 메서드 */
	public void fileDeleteExecute() {
        try {
            Files.delete(filePath);
        } catch (NoSuchFileException e) {
        	JOptionPane.showMessageDialog(parent, "삭제하려는 이미지파일이 존재하지 않습니다", "파일삭제 오류", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void getFilePath(String filePath) {
	    this.filePath = Paths.get(filePath); 
	}
	
}
