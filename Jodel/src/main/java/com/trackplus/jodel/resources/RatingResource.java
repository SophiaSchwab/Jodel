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

import com.trackplus.jodel.dao.*;
import com.trackplus.jodel.model.*;


public class RatingResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public RatingResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Rating getRating() {
		Rating rating = RatingDao.getInstance().getRating(id);
		if (rating == null)
			throw new RuntimeException("Get: Rating with " + id + " not found");
		return rating;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Rating getRatingHTML() {
		Rating rating = RatingDao.getInstance().getRating(id);
		if (rating == null)
			throw new RuntimeException("Get: Rating with " + id + " not found");
		return rating;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putRating(JAXBElement<Rating> rating) {
		Rating c = rating.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteRating() {
		RatingDao.getInstance().deleteRating(id);
	}

	private Response putAndGetResponse(Rating c) {
		Response res;
		RatingDao.getInstance().saveRating(c);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}

}
