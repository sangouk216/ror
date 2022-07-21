package kr.ror.common.util;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
    public static String getAbsolutePath(String path) throws Exception {
        File dir = new File(path);
        FileUtils.forceMkdir(dir);
        return dir.getAbsolutePath() + File.separator;
    }
    
    public static void moveFile(String srcFile, String destFile) throws Exception {
        FileUtils.moveFile(new File(srcFile), new File(destFile));
    }
}
