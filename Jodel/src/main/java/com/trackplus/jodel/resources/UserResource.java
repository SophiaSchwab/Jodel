
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

import com.trackplus.jodel.dao.UserDao;
import com.trackplus.jodel.model.Tuser;

public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	Integer id;

	public UserResource(UriInfo uriInfo, Request request, Integer id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration     
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Tuser getTuser() {
		Tuser user = UserDao.getInstance().getTuser(id);
		if(user==null)
			throw new RuntimeException("Get: Tuser with " + id +  " not found");
		return user;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Tuser getTuserHTML() {
		Tuser user = UserDao.getInstance().getTuser(id);
		if(user==null)
			throw new RuntimeException("Get: Tuser with " + id +  " not found");
		return user;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTuser(JAXBElement<Tuser> user) {
		Tuser c = user.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteTuser() {
		UserDao.getInstance().deleteTuser(id);
	}

	private Response putAndGetResponse(Tuser user) {
		Response res;
		UserDao.getInstance().saveTuser(user);
		res = Response.created(uriInfo.getAbsolutePath()).build();
		return res;
	}
} 