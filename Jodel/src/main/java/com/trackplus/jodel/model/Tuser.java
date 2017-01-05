/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package com.trackplus.jodel.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the TUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Tuser.findAll", query="SELECT t FROM Tuser t")
public class Tuser implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String name;
	@JsonIgnore
	private List<Comment> comments;
	@JsonIgnore
	private List<Post> posts;

	public Tuser() {
	}

	public Tuser(String name) {
		this.name = name;
	}

	@Id
	@TableGenerator( name = "userSeq", table = "SEQUENCE", 
						pkColumnName = "SEQ_NAME", pkColumnValue = "TUSER", 
						valueColumnName = "SEQ_COUNT", initialValue = 1, allocationSize = 1 )
	@GeneratedValue( strategy = GenerationType.TABLE, generator = "userSeq" )
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="tuser")
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setTuser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setTuser(null);

		return comment;
	}


	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="tuser")
	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setTuser(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setTuser(null);

		return post;
	}

}