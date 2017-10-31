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
	private String role;
	private String profile;
	private List<MyCard> card;
	private List<TradeCard> tradeCardSend;
	private List<TradeCard> tradeCardReceived;
	
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
	@Column(name = "role")
	public String getRole() {
		return role+"";
	}
	public void setRole(String role) {
		this.role = role;
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
	@Column(name = "profile")
	public String getProfile() {
		return profile;
	}
	public void setProfile(String name) {
		this.profile = name;
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
	
	@OneToMany(mappedBy="user_received", cascade = CascadeType.ALL)
	public List<TradeCard> getTradeCardReceived() {
		return tradeCardReceived;
	}
	public void setTradeCardReceived(List<TradeCard> tradeCardReceived) {
		this.tradeCardReceived = tradeCardReceived;
	}
	
	@OneToMany(mappedBy="user_send", cascade = CascadeType.ALL)
	public List<TradeCard> getTradeCardSend() {
		return tradeCardSend;
	}
	public void setTradeCardSend(List<TradeCard> tradeCardSend) {
		this.tradeCardSend = tradeCardSend;
	}
	
	
	
	
}
