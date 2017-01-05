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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.trackplus.jodel.dao.CommentDao;
import com.trackplus.jodel.model.Comment;

// Will map the resource to the URL comments
@Path("/comments")
public class CommentsResource {

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of comments to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Comment> getCommentsBrowser() {
    return CommentDao.getInstance().getComments();
  }

  // Return the list of comments for applications
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<Comment> getComments() {
	 return CommentDao.getInstance().getComments();
  }

  // returns the number of comments
  // Use http://localhost:8080/Rest/rest/comments/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = CommentDao.getInstance().getComments().size();
    return String.valueOf(count);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Comment> newComment(Comment comment,
		  @Context HttpServletResponse servletResponse) throws IOException {
    CommentDao.getInstance().saveComment(comment);
    return CommentDao.getInstance().getComments();
  }

  // Defines that the next path parameter after comments is
  // treated as a parameter and passed to the CommentResources
  // Allows to type http://localhost:8080/Rest/rest/comments/1
  // 1 will be treaded as parameter and passed to CommentResource
  @Path("{comment}")
  public CommentResource getComment(@PathParam("comment") Integer id) {
    return new CommentResource(uriInfo, request, id);
  }

} 
