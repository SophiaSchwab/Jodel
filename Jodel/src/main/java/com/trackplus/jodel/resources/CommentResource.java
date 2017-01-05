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

import com.trackplus.jodel.dao.CommentDao;
import com.trackplus.jodel.model.Comment;

public class CommentResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public CommentResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Comment getComment() {
		Comment comment = CommentDao.getInstance().getComment(id);
		if(comment==null)
			throw new RuntimeException("Get: Comment with " + id +  " not found");
		return comment;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Comment getCommentHTML() {
		Comment comment = CommentDao.getInstance().getComment(id);
		if(comment==null)
			throw new RuntimeException("Get: Comment with " + id +  " not found");
		return comment;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putComment(JAXBElement<Comment> comment) {
		Comment c = comment.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteComment() {
		CommentDao.getInstance().deleteComment(id);
	}

	private Response putAndGetResponse(Comment comment) {
		Response res;
		CommentDao.getInstance().saveComment(comment);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 