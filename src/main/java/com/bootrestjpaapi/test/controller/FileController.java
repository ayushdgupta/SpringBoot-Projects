package com.bootrestjpaapi.test.controller;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bootrestjpaapi.test.helper.FileUploadHelper;

@RestController
public class FileController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;

	// here "file" is matching with the name that written in postman api in
	// key column remember
	@PostMapping("/file-upload")
	public ResponseEntity<String> fileHandler(@RequestParam("file") MultipartFile file )
	{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		
		try {
			
			// checking user khi khali data to nhi bhej rha
			if(file.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CHUTIYE IMAGE TO BHEJ PHLE");
			}
			
			// only jpeg accepted
			if(!file.getContentType().equals("image/jpeg"))
			{
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("CHUTIYE JPEG IMAGE BHEJ BS");
			}
			
			// now uploading part
			// for uploading we will take help from an helper
			boolean b = this.fileUploadHelper.uploadFile(file);
			if(b==true)
			{
			//	return ResponseEntity.ok("Ha bhaiya file uploaded successfully");
				
				// returning path of our image from target folder
				//     <start from here ----------------till here we will get localhost:8080><now then image folder then image name>
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
			}
			else
			{
				return ResponseEntity.ok("Something went wrong plz try again");
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("ha bhaiya aa gyi image");
	}
}
