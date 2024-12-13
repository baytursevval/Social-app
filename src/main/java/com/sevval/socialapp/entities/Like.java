package com.sevval.socialapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity //dbde tabloya map'ler
@Table(name="p_like")
@Data //getter ve setter'ları otomatik ekler
public class Like {
	
	@Id
	Long id;
	Long postId;
	Long userId;

}
