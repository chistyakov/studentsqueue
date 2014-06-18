/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import studentsqueue.Queue;
import studentsqueue.Teacher;

/**
 *
 * @author teamdevelopment
 */
@Stateless
@Path("studentsqueue.teacher")
public class TeacherFacadeREST extends AbstractFacade<Teacher> {
    @PersistenceContext(unitName = "com.mycompany_studentsqueue_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public TeacherFacadeREST() {
        super(Teacher.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Teacher entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(Teacher entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Teacher find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Teacher> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Teacher> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
        
    @GET
    @Path("{teacherId}/queues")
    @Produces({"application/json"})
    public List<Queue> getQueuesByStudnet(@PathParam("teacherId") BigDecimal teacherId) {
        List<Queue> queues = em
                .createNamedQuery("Teacher.getQueuesByTeacher")
                .setParameter("teacherId", teacherId)
                .getResultList();
        return queues;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
