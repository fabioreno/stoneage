package com.stoneage.microservices.rettiwtservice.user;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
		
	@OneToMany(mappedBy = "user")
	@OrderBy("id desc")
	private List<Post> posts;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "USER_FOLLOWERS", 
		joinColumns = {
				@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
		inverseJoinColumns = {
				@JoinColumn(name = "FOLLOWED_ID", referencedColumnName = "ID")})
	@JsonIgnore
	private Set<User> followedByMe = new HashSet<User>();
	
	@ManyToMany(mappedBy = "followedByMe")
	@JsonIgnore
	private Set<User> followers = new HashSet<User>();
		
	public User() 
	{
	}
	
	public User(Long id, String name) 
	{
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public List<Post> getPosts()
	{
		return posts;
	}

	public void setPosts(List<Post> posts) 
	{
		this.posts = posts;
	}

	public Set<User> getFollowedByMe() 
	{
		return followedByMe;
	}

	public void setFollowedByMe(Set<User> followedByMe) 
	{
		this.followedByMe = followedByMe;
	}

	public Set<User> getFollowers() 
	{
		return followers;
	}

	public void setFollowers(Set<User> followers) 
	{
		this.followers = followers;
	}

	@Override
	public String toString() 
	{
		return new ToStringCreator(this)
				.append("id", id)
				.append("name", name)
				.toString();
	}
}
