/*========================================================================*
 *                                                                        *
 * This software is governed by the GPL version 2.                        *
 *                                                                        *
 * Copyright: Joerg Friedrich, University of Applied Sciences Esslingen   *
 *                                                                        *
 * $Id:$
 *                                                                        *
 *========================================================================*/
package com.trackplus.jodel.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.trackplus.jodel.dao.PostDao;
import com.trackplus.jodel.model.Post;

public class PostResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public PostResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Post getPost() {
		Post post = PostDao.getInstance().getPost(id);
		if(post==null)
			throw new RuntimeException("Get: Post with " + id +  " not found");
		return post;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Post getPostHTML() {
		Post post = PostDao.getInstance().getPost(id);
		if(post==null)
			throw new RuntimeException("Get: Post with " + id +  " not found");
		return post;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPost(JAXBElement<Post> post) {
		Post c = post.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deletePost() {
		PostDao.getInstance().deletePost(id);
	}

	private Response putAndGetResponse(Post post) {
		Response res;
		PostDao.getInstance().savePost(post);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 