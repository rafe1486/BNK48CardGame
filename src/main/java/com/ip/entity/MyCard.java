package com.ip.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "mycard")
public class MyCard {

	private int id;
	private User user;
	private Card card;
	private Date date;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	@JoinColumn(name = "card_id", insertable = false, updatable = false)
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}

	@Basic
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
