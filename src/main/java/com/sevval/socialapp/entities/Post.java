package com.sevval.socialapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //dbde tabloya map'ler
@Table(name="post")
@Data //getter ve setter'ları otomatik ekler
public class Post {
	
	@Id
	Long id;
	Long userId;
	String title;
	@Lob //text alanının içeriği büyük nesne olarak işaretlenir
	@Column(columnDefinition="text") //hibernate'in mysqlde stringi text olarak algılaması için
	String text;

}
