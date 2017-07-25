package kr.or.seongjin.util;

import java.util.Date;

public class FileDto {
	
	private Integer id;
	private Integer user_id;
	private String file_name;
	private String save_file_name;
	private Long file_length;
	private String content_type;
	private Integer delete_flag;
	private Date create_date;
	private Date modify_date;
	
	
	
	
	
	
	
	public Integer getUser_id() {
		return user_id;
	}


	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


	public Integer getDelete_flag() {
		return delete_flag;
	}


	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Date getModify_date() {
		return modify_date;
	}


	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public Long getFile_length() {
		return file_length;
	}
	public void setFile_length(Long file_length) {
		this.file_length = file_length;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	
	
	

}
