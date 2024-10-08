package com.sma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@Column(name = "Descriptions")
	private String desc;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Users users;

	public Post(String desc) {
		super();
		this.desc = desc;
	}

	public Post() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Users getUser() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}

}
