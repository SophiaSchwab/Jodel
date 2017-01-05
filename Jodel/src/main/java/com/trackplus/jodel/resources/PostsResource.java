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

import com.trackplus.jodel.dao.PostDao;
import com.trackplus.jodel.model.Post;

// Will map the resource to the URL posts
@Path("/posts")
public class PostsResource {

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of posts to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Post> getPostsBrowser() {
    return PostDao.getInstance().getPosts();
  }

  // Return the list of posts for applications
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<Post> getPosts() {
    return PostDao.getInstance().getPosts();
  }

  // returns the number of posts
  // Use http://localhost:8080/Jodel/rest/posts/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = PostDao.getInstance().getPosts().size();
    return String.valueOf(count);
  }

  // This is the workhorse
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Post> newPost(Post post,
		  @Context HttpServletResponse servletResponse) throws IOException {
    PostDao.getInstance().savePost(post);
    return PostDao.getInstance().getPosts();
  }

  // Defines that the next path parameter after posts is
  // treated as a parameter and passed to the PostResources
  // Allows to type http://localhost:8080/Jodel/rest/posts/1
  // 1 will be treaded as parameter and passed to PostResource
  @Path("{post}")
  public PostResource getPost(@PathParam("post") Integer id) {
    return new PostResource(uriInfo, request, id);
  }

} 
