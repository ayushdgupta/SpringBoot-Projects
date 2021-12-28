package com.bootrestjpaapi.test.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	// static file uploading directory path
//	public final String UPLOAD_DIR = "C:\\Users\\ayush\\Documents\\workspace-spring-tool-suite-4-4.11.0.RELEASE\\bootrestjpaapiexample\\src\\main\\resources\\static\\image";
	
	// now we are fetching path to save images dynamically
	public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	// we add default constructor bcz ClassPathResource throwing an IOException
	public FileUploadHelper() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isFileUploaded = false;
	
	public boolean uploadFile(MultipartFile file)
	{
		try {
			
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			isFileUploaded = true;
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return isFileUploaded;
	}

}
