package com.taskmanager.action.upload;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.taskmanager.entity.User;

public class UploadAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5772569168498055431L;
	private File portrait;  
    private String portraitContentType;  
    private String portraitFileName;
    private String message;
    private String fName;
    
    public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public File getPortrait() {
		return portrait;
	}
	public void setPortrait(File portrait) {
		this.portrait = portrait;
	}
	public String getPortraitContentType() {
		return portraitContentType;
	}
	public void setPortraitContentType(String portraitContentType) {
		this.portraitContentType = portraitContentType;
	}
	public String getPortraitFileName() {
		return portraitFileName;
	}
	public void setPortraitFileName(String portraitFileName) {
		this.portraitFileName = portraitFileName;
	}
	public String upload() throws Exception{  
		HttpServletRequest request = ServletActionContext.getRequest();
		InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/classes/upload.properties");  
		Properties p = new Properties();  
		p.load(in);    
        String savePath=p.getProperty("upload_dir");
        HttpServletResponse response = ServletActionContext.getResponse();    
        response.setCharacterEncoding("utf-8");    
        String name = new Date().getTime()+"__"+portraitFileName;  
        if(savefile(portrait,savePath,name,10485760))
        {
        	portrait.delete();
        	message="上传成功！";
        }
        else
        	message="上传失败！";
        	return SUCCESS;
   }  
   public boolean savefile(File file, String path, String filename, int size) { 
	    HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String username=((User)session.getAttribute("user")).getNo();
		File f =new File(path +"\\" +username+"\\");      
		if  (!f .exists()  && !f .isDirectory())      
		{       
		    f.mkdir();    
		} 
        boolean bool = true;  
        try {  
            InputStream is = new FileInputStream(file);  
            OutputStream os = new FileOutputStream(path +"\\" +username+"\\" + filename);  
            byte[] bytefer = new byte[size];  
            int length = 0;  
            while ((length = is.read(bytefer)) != -1) {  
                os.write(bytefer, 0, length);  
            }  
            os.close();  
            is.close();  
        } catch (Exception e) {  
            bool = false;  
        } 
        setfName("\\upload\\" +username+"\\" + filename);
        System.out.println("上传文件到"+getfName());
        return bool;  
    }  
}
