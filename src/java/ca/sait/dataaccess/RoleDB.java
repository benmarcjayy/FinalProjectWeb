
package ca.sait.dataaccess;

import ca.sait.models.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Benmarc Agulto
 */
public class RoleDB {
    public RoleDB() {
        
    }
    
    public List<Role> getAll() {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        
        EntityManager em = emFactory.createEntityManager();
        
        try {
           return em.createNamedQuery("Role.findAll", Role.class).getResultList();

        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
       
    }
}
