package com.sma.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostsDTO {
	private int id;

	@NotNull
	@Size(min = 3, max = 256, message = "Post should be between 3 to 256 characters long")
	private String desc;

	private String usersname;

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public String getUsersname() {
		return usersname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setUsersname(String usersname) {
		this.usersname = usersname;
	}

	public PostsDTO(String desc) {
		super();
		this.desc = desc;
	}

	public PostsDTO(int id, String desc, String usersname) {
		super();
		this.id = id;
		this.desc = desc;
		this.usersname = usersname;
	}

	public PostsDTO() {
		super();
	}

}
