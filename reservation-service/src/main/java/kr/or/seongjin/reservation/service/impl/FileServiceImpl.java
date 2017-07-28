package kr.or.seongjin.reservation.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.seongjin.reservation.dao.FileDao;
import kr.or.seongjin.reservation.service.FileService;
import kr.or.seongjin.util.FileDto;

@Service
public class FileServiceImpl implements FileService {

	
	@Value("${spring.file.upload-locations}")
	private String baseDir;
	
	private FileDao fileDao;
	
	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao=fileDao;
	}
	
	@Override
	public List<Integer> addFiles(MultipartFile[] files,Integer userId) {
		List<Integer> fileIdList = new ArrayList<Integer>();
		FileDto fileDto = new FileDto();
		if (files != null && files.length > 0) {
			String formattedDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			File newFile = new File(baseDir + formattedDate);
			if (!newFile.exists()) {
				newFile.mkdirs();
			}

			for (MultipartFile file : files) {
				fileDto.setContentType(file.getContentType());
				fileDto.setFileName(file.getOriginalFilename());
				fileDto.setFileLength(file.getSize());
				fileDto.setSaveFileName(formattedDate + File.separator + UUID.randomUUID().toString()); // 실제 저장되는 파일의
				fileDto.setUserId(userId);
				fileDto.setDeleteFlag(1);
				fileDto.setCreateDate(new Date());
				fileDto.setModifyDate(new Date());

				// try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
				try (InputStream in = file.getInputStream();
						FileOutputStream fos = new FileOutputStream(baseDir + fileDto.getSaveFileName())) {
					int readCount = 0;
					byte[] buffer = new byte[512];
					while ((readCount = in.read(buffer)) != -1) {
						fos.write(buffer, 0, readCount);
					}
					fileIdList.add(fileDao.insert(fileDto));// 실제 파일을 저장함.
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} // for
		} // if
		
		return fileIdList;
	}

	@Override
	public FileDto selectFileById(Integer fileId) {
		// TODO Auto-generated method stub
		return fileDao.selectById(fileId);
	}

	@Override
	public FileInputStream getFileInputStream(String saveFileName) {
		java.io.File readFile = new java.io.File(baseDir+saveFileName);
		if (!readFile.exists()) { // 파일이 존재하지 않다면
			throw new RuntimeException("file not found");
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(readFile);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} 
		
		return fis;
	}

}
