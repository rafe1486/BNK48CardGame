package com.ip.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

	private int id;
	private String name;
	private String email;
	private String password;
	private List<MyCard> card;
	private List<TradeCard> tradeCard;
	
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
	
	@Basic
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Basic
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToMany(mappedBy="card", cascade = CascadeType.ALL)
	public List<MyCard> getCard() {
		return card;
	}
	public void setCard(List<MyCard> card) {
		this.card = card;
	}
	
	@OneToMany(mappedBy="user_recieved", cascade = CascadeType.ALL)
	public List<TradeCard> getTradeCard() {
		return tradeCard;
	}
	public void setTradeCard(List<TradeCard> tradeCard) {
		this.tradeCard = tradeCard;
	}
	
	
}
