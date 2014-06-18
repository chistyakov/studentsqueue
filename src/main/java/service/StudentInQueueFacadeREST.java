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

@Stateless
@Path("studentsqueue.studentinqueue")
public class StudentInQueueFacadeREST extends AbstractFacade<StudentInQueue> {

    @PersistenceContext(unitName = "com.mycompany_studentsqueue_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public StudentInQueueFacadeREST() {
        super(StudentInQueue.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(StudentInQueue entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json"})
    public void edit(StudentInQueue entity) {
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
    public StudentInQueue find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<StudentInQueue> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<StudentInQueue> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @PUT
    @Path("queue/{queueId}/student/{studentId}")
    @Consumes({"text/plain"})
    public void addStudentToQueue(String description, @PathParam("queueId") BigDecimal queueId,
            @PathParam("studentId") BigDecimal studentId) {

        Queue queue = getQueueById(queueId);
        Student student = getStudentById(studentId);
        StudentInQueue studentInQueue = new StudentInQueue();
        studentInQueue.setQueue(queue);
        studentInQueue.setStudent(student);
        studentInQueue.setDescription(description);
        queue.getStudentInQueueList().add(studentInQueue);
        em.persist(queue);
    }

    private Student getStudentById(BigDecimal studentId) {
        Student student = (Student) em.createNamedQuery("Student.findById")
                .setParameter("id", studentId)
                .getSingleResult();
        return student;
    }

    @DELETE
    @Path("queue/{queueId}/student/{studentId}")
    public void deleteStudentFromQueueByStudentId(
            @PathParam("queueId") BigDecimal queueId,
            @PathParam("studentId") BigDecimal studentId) {

        Queue queue = getQueueById(queueId);

        StudentInQueue studentInQueue = getStudentInQueueByStudentIdAndQueueId(queueId,
                studentId);
        queue.getStudentInQueueList().remove(studentInQueue);
        em.remove(studentInQueue);
    }

    private StudentInQueue getStudentInQueueByStudentIdAndQueueId(BigDecimal queueId,
            BigDecimal studentId) {
        StudentInQueue studentInQueue = (StudentInQueue) em
                .createNamedQuery("StudentInQueue.findByStudentIdAndQueueId")
                .setParameter("queueId", queueId)
                .setParameter("studentId", studentId)
                .getSingleResult();
        return studentInQueue;
    }

    @DELETE
    @Path("queue/{queueId}/rank/{rank}")
    public void deleteStudentFromQueueByRank(
            @PathParam("queueId") BigDecimal queueId,
            @PathParam("rank") BigDecimal rank) {

        Queue queue = getQueueById(queueId);
        StudentInQueue studentInQueue = getStudentInQueueByStudentIdAndRank(
                queueId, rank);
        queue.getStudentInQueueList().remove(studentInQueue);
        em.remove(studentInQueue);
    }

    private Queue getQueueById(BigDecimal queueId) {
        Queue queue = (Queue) em.createNamedQuery("Queue.findById")
                .setParameter("id", queueId)
                .getSingleResult();
        return queue;
    }

    private StudentInQueue getStudentInQueueByStudentIdAndRank(BigDecimal queueId,
            BigDecimal rank) {
        StudentInQueue studentInQueue = (StudentInQueue) em
                .createNamedQuery("StudentInQueue.findByStudentIdAndRank")
                .setParameter("queueId", queueId)
                .setParameter("rank", rank)
                .getSingleResult();
        return studentInQueue;
    }

    @GET
    @Path("queue/{queueId}/student/{studentId}")
    @Produces("text/plain")
    public String getStudentRankInQueue(
            @PathParam("queueId") BigDecimal queueId,
            @PathParam("studentId") BigDecimal studentId) {
        BigDecimal rank = (BigDecimal) em
                .createNamedQuery("StudentInQueue.getStudentRankInQueue")
                .setParameter("queueId", queueId)
                .setParameter("studentId", studentId)
                .getSingleResult();
        return String.valueOf(rank);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
