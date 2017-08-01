package kr.or.seongjin.reservation.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.seongjin.reservation.dto.ReservationUser;
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

	@GetMapping(path = "/fileManage")
	public String file() {
		return "files";
	}

	@PostMapping
	@ResponseBody
	public List<Integer> create(@RequestParam("file") MultipartFile[] files, HttpSession session,
			HttpServletResponse response) throws IOException {
		ReservationUser user = (ReservationUser) session.getAttribute("user");
		// session없을때 Redirect필요!
		if (user == null) {
			response.setStatus(401);
			return null;
		} else {
			return fileService.addFiles(files, user.getId());
		}
	}

	@GetMapping(path = "/{id}")
	public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
		FileDto fileDto = fileService.selectFileById(id);

		if (fileResponseMapper(fileDto, response)) {
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
				}
			}
		}
		return;

	}

	@PutMapping
	public void registFile() {

	}

	private boolean fileResponseMapper(FileDto fileDto, HttpServletResponse response) throws IOException {
		if (fileDto == null) {
			response.sendRedirect("/resources/img/noimage.png");
			return false;
		} else {
			response.setHeader("Content-Disposition", "inline; filename=\"" + fileDto.getFileName() + "\";");
			// 구브라우저 미구현.
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Type", fileDto.getContentType());
			response.setHeader("Content-Length", "" + fileDto.getFileLength());
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			return true;
		}
	}

}
