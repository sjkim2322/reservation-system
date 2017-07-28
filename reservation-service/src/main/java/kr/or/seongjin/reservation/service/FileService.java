package kr.or.seongjin.reservation.service;

import java.io.FileInputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.seongjin.util.FileDto;

public interface FileService {
	
	public List<Integer> addFiles(MultipartFile[] files,Integer userId);
	public FileDto selectFileById(Integer fileId);
	public FileInputStream getFileInputStream(String saveFileName);
}