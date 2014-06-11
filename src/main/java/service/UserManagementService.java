package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import studentsqueue.Quser;

//import com.nabisoft.json.JsonResponse;
//import com.nabisoft.model.usermanagement.Group;
//import com.nabisoft.model.usermanagement.User;
//import com.nabisoft.model.usermanagement.UserBean;
//import com.nabisoft.model.usermanagement.dto.UserDTO;
@Path("studentsqueue.auth")
@Produces(MediaType.TEXT_PLAIN)
@Stateless
public class UserManagementService {
    @PersistenceContext(unitName = "com.mycompany_studentsqueue_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @GET
    @Path("ping")
    public String ping() {
        return "alive";
    }

    @GET
    @Path("currentuser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUser(@Context HttpServletRequest req) {

        String username = req.getUserPrincipal().getName();
// 
//        List<Quser> users = em.createNamedQuery("findByUsername")
//            .setParameter("username", username)
//            .getResultList();

        Quser user = (Quser) em.createNamedQuery("Quser.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        return Response.ok().entity(user).build();
    }
    
        @GET
    @Path("currentuserid")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentUserId(@Context HttpServletRequest req) {

        String username = req.getUserPrincipal().getName();
// 
//        List<Quser> users = em.createNamedQuery("findByUsername")
//            .setParameter("username", username)
//            .getResultList();

        Quser user = (Quser) em.createNamedQuery("Quser.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
        return Response.ok().entity(user.getId()).build();
    }

    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest req)
            throws ServletException {
        req.logout();
        req.getSession().invalidate();
        return Response.ok().build();
    }
}
