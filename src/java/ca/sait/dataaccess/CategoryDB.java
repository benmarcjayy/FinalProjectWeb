/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.dataaccess;

import ca.sait.models.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Benmarc Agulto
 */
public class CategoryDB {
    public List<Category> getAll() {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();

        EntityManager em = emFactory.createEntityManager();

        return em.createNamedQuery("Category.findAll", Category.class).getResultList();
    }

    public void addCategory(Category newCategory) {
         EntityManagerFactory emFactory = DBUtil.getEmFactory();

        EntityManager em = emFactory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(newCategory);
            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }
    }

    public void updateCategory(Category selectedCategory) {
        EntityManagerFactory emFactory = DBUtil.getEmFactory();
        EntityManager em = emFactory.createEntityManager();
        Category ref = em.find(Category.class, selectedCategory.getCategoryId());
        
        ref.setCategoryName(selectedCategory.getCategoryName());
        
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.persist(ref);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
}
