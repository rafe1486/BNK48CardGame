package com.ip.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "card")
public class Card {

	private int id;
	private String name;
	private String path;
	
	@Basic
	@Column(name = "path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
