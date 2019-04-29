package com.example.test_thymeleaf.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class IUpliadFileServiceImpl implements IUploadFileService{

	private static final String UPLOAD_FOLDER = "uploads";
	private final Logger log= LoggerFactory.getLogger(getClass());
	
	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path photoPath = getPath(filename);
		Resource resource =null;
		resource = new UrlResource(photoPath.toUri());
		if(!resource.exists() || !resource.isReadable())
		{
			throw new RuntimeException("Runtime Exception: Unable to upload resource: "+filename);
		}

		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException
	{
		String uniqueFileName=UUID.randomUUID().toString()+file.getOriginalFilename().replaceAll("\\s", "");
		Path rootPath = getPath(uniqueFileName);
		log.info("rootpath: "+rootPath);
		
		Files.copy(file.getInputStream(),rootPath);

		return uniqueFileName;
	}

	@Override
	public boolean delete(String filename) {
		Path photoPath = getPath(filename);
		File file = photoPath.toFile();
		if(file.exists() || file.canRead())
		{
			if(file.delete())
			{
				return true;
			}
		}
		return false;
	}

	public Path getPath(String filename)
	{
		return Paths.get(UPLOAD_FOLDER).resolve(filename).toAbsolutePath();
	}
}
