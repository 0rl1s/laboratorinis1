package com.example.laboratorinis1.rest;

import com.example.laboratorinis1.entities.Mokytojas;
import com.example.laboratorinis1.persistence.MokytojaiDAO;
import com.example.laboratorinis1.rest.contracts.MokytojasDTO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/mokytojai")
public class MokytojaiController {

    @Inject
    @Setter @Getter
    private MokytojaiDAO mokytojaiDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final long id) {
        Mokytojas mokytojas = mokytojaiDAO.findbyId(id);
        if (mokytojas == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        MokytojasDTO mokytojasDTO = new MokytojasDTO();
        mokytojasDTO.setVardas(mokytojas.getVardas());
        mokytojasDTO.setPavarde(mokytojas.getPavarde());
        mokytojasDTO.setSlapyvardis(mokytojas.getSlapyvardis());

        return Response.ok(mokytojasDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(MokytojasDTO mokytojasDTO) {
        Mokytojas mokytojas = new Mokytojas();
        mokytojas.setVardas(mokytojasDTO.getVardas());
        mokytojas.setPavarde(mokytojasDTO.getPavarde());
        mokytojas.setSlapyvardis(mokytojasDTO.getSlapyvardis());
        mokytojaiDAO.persist(mokytojas);
        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final long id, MokytojasDTO mokytojasDTO) {
        try {
            Mokytojas existingMokytojas = mokytojaiDAO.findbyId(id);
            if (existingMokytojas == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingMokytojas.setVardas(mokytojasDTO.getVardas());
            existingMokytojas.setPavarde(mokytojasDTO.getPavarde());
            existingMokytojas.setSlapyvardis(mokytojasDTO.getSlapyvardis());
            mokytojaiDAO.update(existingMokytojas);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
