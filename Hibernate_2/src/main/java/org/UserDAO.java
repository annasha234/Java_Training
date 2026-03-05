package org;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAO() {
        this.emf = Persistence.createEntityManagerFactory("taskPU");
        this.em = emf.createEntityManager();
    }

    public UserDTO create(User user) {
        try {
            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();
            System.out.println("User created successfully with id: " + user.getId());

            return convertToDTO(user);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error creating users: " + e.getMessage());
            return null;
        }
    }

    public UserDTO read(Long id) {
        try {
            User user = em.find(User.class, id);

            if (user != null) {
                System.out.println("User found with id: " + id);
                return convertToDTO(user);
            } else {
                System.out.println("User not found with id: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error reading user: " + e.getMessage());
            return null;
        }
        return null;
    }

    public UserDTO update(long id, String name, String email) {
        try {
            //begin transaction
            em.getTransaction().begin();

            //find task
            User user = em.find(User.class, id);

            if (user != null) {
                //update field
                user.setName(name);
                user.setEmail(email);

                //merge(update in database
                User updatedUser = em.merge(user);

                //commit transaction
                em.getTransaction().commit();

                System.out.println("User updated successfully with id: " + id);
                return convertToDTO(updatedUser);
            } else {
                em.getTransaction().rollback();
                System.out.println("User not found with id: " + id);
                return null;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error updating user: " + e.getMessage());
            return null;
        }
    }

    public boolean delete(long id) {
        try {
            //begin transaction
            em.getTransaction().begin();

            //find task
            User user = em.find(User.class, id);

            if (user != null) {
                //remove from database
                em.remove(user);

                //commit transaction
                em.getTransaction().commit();

                System.out.println("User deleted successfullu=y with id: " + id);
                return true;
            } else {
                em.getTransaction().rollback();
                System.out.println("User not found with id: " + id);
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    public List<UserDTO> getAllTask() {
        List<UserDTO> userList = new ArrayList<>();
        try {
            //JPQL query to get all tasks
            String jpql = "SELECT u FROM User u";
            List<User> users = em.createQuery(jpql, User.class).getResultList();

            //convert each entity to DTO
            for (User user : users) {
                userList.add(convertToDTO(user));
            }
            System.out.println("Retrieved " + userList.size() + " user from database");
            return userList;
        } catch (Exception e) {
            System.out.println("Error retrieving user: " + e.getMessage());
            return userList; //Return empty list if error
        }
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (em != null && emf.isOpen()) {
            emf.close();
        }
        System.out.println("Database connection closed");
    }
}

