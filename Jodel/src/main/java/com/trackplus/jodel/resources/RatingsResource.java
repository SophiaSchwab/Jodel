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

import com.trackplus.jodel.dao.*;
import com.trackplus.jodel.model.*;

// Will map the resource to the URL users
@Path("/ratings")
public class RatingsResource {

	
  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of ratings to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Rating> getRatingsBrowser() {
    return RatingDao.getInstance().getRatings();
  }

  // Return the list of ratings in JSON format
  @GET
  @Produces({MediaType.APPLICATION_JSON })
  public List<Rating> getRatings() {
	   return RatingDao.getInstance().getRatings();
  }

  // returns the number of ratings
  // Use http://localhost:8080/Jodel/rest/ratings/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = RatingDao.getInstance().getRatings().size();
    return String.valueOf(count);
  }

  // This is the workhorse
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Rating> newRating(Rating rating,
		  @Context HttpServletResponse servletResponse) throws IOException {
    RatingDao.getInstance().saveRating(rating);
    return RatingDao.getInstance().getRatings();
  }

  // Defines that the next path parameter after ratings is
  // treated as a parameter and passed to the RatingResources
  // Allows to type http://localhost:8080/Rest/rest/ratings/1
  // 1 will be treaded as parameter and passed to RatingResource
  @Path("{rating}")
  public RatingResource getRating(@PathParam("rating") Integer id) {
    return new RatingResource(uriInfo, request, id);
  }

} 
