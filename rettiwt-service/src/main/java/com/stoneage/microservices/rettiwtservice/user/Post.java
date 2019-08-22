package com.stoneage.microservices.rettiwtservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "POST")
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Post() 
	{
	}
	
	public Post(Long id, String message, User user) 
	{
		super();
		this.id = id;
		this.message = message;
		this.user = user;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}

	@Override
	public String toString() 
	{
		return new ToStringCreator(this)
				.append("id", id)
				.append("message", message)
				.toString();
	}
}
