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
import studentsqueue.Student;
import studentsqueue.StudentInQueue;

/**
 *
 * @author teamdevelopment
 */
@Stateless
@Path("studentsqueue.student")
public class StudentFacadeREST extends AbstractFacade<Student> {
    @PersistenceContext(unitName = "com.mycompany_studentsqueue_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public StudentFacadeREST() {
        super(Student.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Student entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(Student entity) {
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
    public Student find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Student> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<Student> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("{studentId}/queues")
    @Produces({"application/json"})
    public List<Queue> getQueuesByStudnet(@PathParam("studentId") BigDecimal studentId) {
        List<Queue> queues = em
                .createNamedQuery("Student.getQueuesByStudent")
                .setParameter("studentId", studentId)
                .getResultList();
        return queues;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
