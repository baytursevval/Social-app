package com.sevval.socialapp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //dbde tabloya map'ler
@Table(name="comment")
@Data //getter ve setter'ları otomatik ekler
public class Comment {
	
	@Id
	Long id;
	
	//Long postId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="post_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE) //user silindiğinde postları da silinsin
	@JsonIgnore
	Post post;
	
	//Long userId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE) //user silindiğinde postları da silinsin
	@JsonIgnore
	User user;
	
	@Lob //text alanının içeriği büyük nesne olarak işaretlenir
	@Column(columnDefinition="text") //hibernate'in mysqlde stringi text olarak algılaması için(yoksa varchar255 olarak alır)
	String text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
