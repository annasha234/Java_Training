package org;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAO(){
        this.emf= Persistence.createEntityManagerFactory("taskPU");
        this.em=emf.createEntityManager();
    }
    public UserDTO create(User user){
        try{
            em.getTransaction().begin();

            em.persist(user);

            em.getTransaction().commit();

            System.out.println("User created successfully with ID: "+user.getId());

            return convertToDTO(user);
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error creating users: "+e.getMessage());
            return null;
        }

    }
    public UserDTO read(Long id){
        try{
            User user = em.find(User.class,id);

            if(user!=null){
                System.out.println("User found with ID: "+id);
                return convertToDTO(user);
            }
            else{
                System.out.println("Task not found with ID: "+id);
                return null;
            }
        }catch (Exception e){
            System.out.println("Error reading users: "+ e.getMessage());
            return null;
        }
    }
    public UserDTO update(Long id, String name, String email){
        try{
            em.getTransaction().begin();

            User user = em.find(User.class,id);

            if(user!=null){
                user.setName(name);
                user.setEmail(name);

                User updatedUser = em.merge(user);

                em.getTransaction().commit();

                System.out.println("User updated successfully with ID: "+id);
                return convertToDTO(user);
            }
            else{
                em.getTransaction().rollback();
                System.out.println("User not found with ID: "+id);
                return null;
            }
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error updating user: "+e.getMessage());
            return null;
        }
    }
    public boolean delete(Long id){
        try{
            em.getTransaction().begin();
            User user = em.find(User.class,id);

            if(user!=null){
                em.remove(user);

                em.getTransaction().commit();

                System.out.println("User deleted successfully with ID: "+id);
                return true;
            }
            else{
                em.getTransaction().rollback();
                System.out.println("User not found with ID: "+id);
                return false;
            }
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error deleting user: "+e.getMessage());
            return false;
        }
    }
    public List<UserDTO> getAllTasks(){
        List<UserDTO> userList = new ArrayList<>();
        try{
            //JPQL query to get all tasks
            String jpql = "SELECT u from User u";
            List<User> users = em.createQuery(jpql,User.class).getResultList();

            //Convert each entity to DTO
            for(User user: users){
                userList.add(convertToDTO(user));
            }

            System.out.println("Retrieved: "+ userList.size() + "tasks from database");
            return userList;
        }catch(Exception e){
            System.out.println("Error retrieving users : "+e.getMessage());
            return userList; //Returns empty list if error
        }
    }
    private UserDTO convertToDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }
    public void close(){
        if(em!=null && em.isOpen()){
            em.close();
        }
        if(emf!=null && emf.isOpen()){
            emf.close();
        }
        System.out.println("Database Connection closed");
    }
}