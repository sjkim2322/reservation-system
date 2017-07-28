package kr.or.seongjin.reservation.service;

import java.io.FileInputStream;

import org.springframework.web.multipart.MultipartFile;

import kr.or.seongjin.util.FileDto;

public interface FileService {
	
	public boolean addFile(Integer type, MultipartFile[] files);
	public FileDto selectFileById(Integer fileId);
	public FileInputStream getFileInputStream(String saveFileName);
}