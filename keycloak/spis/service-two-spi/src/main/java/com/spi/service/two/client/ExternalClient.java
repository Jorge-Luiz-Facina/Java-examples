package com.spi.service.two.client;

import com.spi.service.two.client.dto.UserLoginResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public interface ExternalClient
{
    @POST
    @Consumes("application/json")
    @Path("/auth")
    Response login(UserLoginResponseDTO user);

    @GET
    @Consumes("application/json")
    @Path("/infos/{login}")
    Response getUserByLogin(@PathParam("login") String login);

    @GET
    @Path("/infos")
    Response getAll();

    @GET
    @Path("/infos/like/{login}")
    Response getAllByLogin(@PathParam("login") String login);
}