package com.stoneage.microservices.rettiwtservice.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Post entity. Contains a message and is always linked to a user.")
@Entity
@Table(name = "POST")
public class Post 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 140, 
            message = "Message should have a maximum of 140 characters.")
    @ApiModelProperty(
            notes = "Message should have a maximum of 140 characters.")
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
