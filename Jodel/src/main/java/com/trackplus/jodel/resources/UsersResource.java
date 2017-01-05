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

import com.trackplus.jodel.dao.UserDao;
import com.trackplus.jodel.model.Tuser;

// Will map the resource to the URL users
@Path("/users")
public class UsersResource {

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of users to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Tuser> getTusersBrowser() {
    return UserDao.getInstance().getTusers();
  }

  // Return the list of users in JSON format
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<Tuser> getTusers() {
	   return UserDao.getInstance().getTusers();
  }

  // returns the number of users
  // Use http://localhost:8080/Jodel/rest/users/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = UserDao.getInstance().getTusers().size();
    return String.valueOf(count);
  }

  // This is the workhorse
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Tuser> newTuser(Tuser user,
		  @Context HttpServletResponse servletResponse) throws IOException {
    UserDao.getInstance().saveTuser(user);
    return UserDao.getInstance().getTusers();
  }

  // Defines that the next path parameter after users is
  // treated as a parameter and passed to the UserResources
  // Allows to type http://localhost:8080/Rest/rest/users/1
  // 1 will be treaded as parameter and passed to UserResource
  @Path("{user}")
  public UserResource getTuser(@PathParam("user") Integer id) {
    return new UserResource(uriInfo, request, id);
  }

} 
