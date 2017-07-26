package kr.or.seongjin.reservation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.seongjin.reservation.dao.FileDao;
import kr.or.seongjin.util.FileDto;

@Controller
@RequestMapping("/files")
public class FileController {

	@Value("${spring.file.upload-locations}")
	private String baseDir;

	private FileDao fileDao;

	@Autowired
	public void setFileDao(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	@PostMapping
	public String create(@RequestParam("title") String title, @RequestParam("file") MultipartFile[] files) {
		
		FileDto fileDto = new FileDto();
		if (files != null && files.length > 0) {

			// windows 사용자라면 "c:\temp\년도\월\일" 형태의 문자열을 구한다.
			String formattedDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			File f = new File(baseDir + formattedDate);
			if (!f.exists()) { // 파일이 존재하지 않는다면
				f.mkdirs(); // 해당 디렉토리를 만든다. 하위폴더까지 한꺼번에 만든다.
			}

			for (MultipartFile file : files) {
				fileDto.setContent_type(file.getContentType());
				fileDto.setFile_name(file.getOriginalFilename());
				fileDto.setFile_length(file.getSize());
				fileDto.setSave_file_name(formattedDate + File.separator + UUID.randomUUID().toString()); // 실제 저장되는 파일의
																											// 상대
																											// 경로(basedir제외)

				fileDto.setUser_id(1);
				fileDto.setDelete_flag(0);
				fileDto.setCreate_date(new Date());
				fileDto.setModify_date(new Date());
				fileDao.insert(fileDto);// 실제 파일을 저장함.

				// try-with-resource 구문. close()를 할 필요가 없다. java 7 이상에서 가능
				try (InputStream in = file.getInputStream();
						FileOutputStream fos = new FileOutputStream(baseDir + fileDto.getSave_file_name())) {
					int readCount = 0;
					byte[] buffer = new byte[512];
					while ((readCount = in.read(buffer)) != -1) {
						fos.write(buffer, 0, readCount);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} // for
		} // if

		return "redirect:/fileManage";
	}


	@GetMapping(path = "/{id}")
	public void downloadReservationUserCommentImage(@PathVariable(name = "id") Integer id,
			HttpServletResponse response) throws IOException {
		FileDto fileDto;
		fileDto = fileDao.selectById(id);
		if(fileDto==null) { 
			response.sendRedirect("/resources/img/noimage.png");
			return ;
		}


		response.setHeader("Content-Disposition", "inline; filename=\"" + fileDto.getFile_name() + "\";");
		//구브라우저 미구현.
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", fileDto.getContent_type());
		response.setHeader("Content-Length", "" + fileDto.getFile_length());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		

		java.io.File readFile = new java.io.File(baseDir+fileDto.getSave_file_name());
		if (!readFile.exists()) { // 파일이 존재하지 않다면
			throw new RuntimeException("file not found");
		}

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(readFile);
			FileCopyUtils.copy(fis, response.getOutputStream()); // 파일을 저장할때도 사용할 수 있다.
			response.getOutputStream().flush();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			try {
				fis.close();
			} catch (Exception ex) {
				// 아무것도 하지 않음 (필요한 로그를 남기는 정도의 작업만 함.)
			}
		}

	}

}
