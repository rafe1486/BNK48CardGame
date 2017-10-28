package com.ip.entity;

import java.sql.Date;

import javax.persistence.*;


import lombok.Data;

@Entity
@Data
@Table(name = "tradecard")
public class TradeCard {

	private int id;
	private User user_send;
	private User user_recieved;
	private Card card;
	private Date date;
	private int status;

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
	@JoinColumn(name = "user_send", insertable = false, updatable = false)
	public User getUser_send() {
		return user_send;
	}

	public void setUser_send(User user_send) {
		this.user_send = user_send;
	}

	@ManyToOne
	@JoinColumn(name = "user_recieved", insertable = false, updatable = false)
	public User getUser_recieved() {
		return user_recieved;
	}

	public void setUser_recieved(User user_recieved) {
		this.user_recieved = user_recieved;
	}

	@Basic
	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Basic
	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name = "card_id", insertable = false, updatable = false)
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	

}
