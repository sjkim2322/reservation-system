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
import kr.or.seongjin.reservation.service.FileService;
import kr.or.seongjin.util.FileDto;

@Controller
@RequestMapping("/files")
public class FileController {
	

	private FileService fileService;

	@Autowired
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping
	public String create(@RequestParam(required=false) Integer type, @RequestParam("file") MultipartFile[] files) {
		
		System.out.println(files.length);
		fileService.addFile(type, files);
		
		

		return "redirect:/fileManage";
	}


	@GetMapping(path = "/{id}")
	public void downloadReservationUserCommentImage(@PathVariable Integer id,
			HttpServletResponse response) throws IOException {
		FileDto fileDto = fileService.selectFileById(id);
		if(fileDto==null) { 
			response.sendRedirect("/resources/img/noimage.png");
			return ;
		}

		response.setHeader("Content-Disposition", "inline; filename=\"" + fileDto.getFileName() + "\";");
		//구브라우저 미구현.
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", fileDto.getContentType());
		response.setHeader("Content-Length", "" + fileDto.getFileLength());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		FileInputStream fis;
		fis = fileService.getFileInputStream(fileDto.getSaveFileName());
		
		try {
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
