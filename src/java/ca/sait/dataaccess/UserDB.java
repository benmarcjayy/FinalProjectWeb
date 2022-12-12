
package ca.sait.dataaccess;

import ca.sait.models.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Benmarc Agulto
 */
public class UserDB {

    public UserDB() {

    }

    public boolean createUser(User user) {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();

        User checkUser = getUser(user.getEmail());

        if (checkUser != null) {
            return false;
        }

        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();

            return false;
        } finally {
            em.close();
        }

    }

    public void deleteUser(User user) {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        User it = em.find(User.class, user.getEmail());

        try {
            trans.begin();
            em.remove(it);
            trans.commit();

        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }

    }

    public boolean updateUser(User user) {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        User it = em.find(User.class, user.getEmail());

        it.setActive(user.getActive());
        it.setFirstName(user.getFirstName());
        it.setLastName(user.getLastName());
        it.setRole(user.getRole());

        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(it);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updateUser(User user, String email) {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();

        User oldUser = getUser(email);
        deleteUser(oldUser);

        try {
            return createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }

    }

    public List<User> getAll() {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();

        try {
            return em.createNamedQuery("User.findAll", User.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public User getUser(String userEmail) {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        
        Query query = em.createNamedQuery("User.findByEmail");
        query.setParameter("email", userEmail);

        try {
            User userCheck = (User) query.getResultList().get(0);
            em.close();
            return userCheck;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }
}
