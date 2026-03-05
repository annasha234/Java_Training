package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TaskDAO(){
        this.emf= Persistence.createEntityManagerFactory("taskPU");
        this.em=emf.createEntityManager();
    }

    public TaskDTO create(Task task){
        try{
            //Begin transaction
            em.getTransaction().begin();

            //Persist(save) the task
            em.persist(task);

            //write to database
            em.getTransaction().commit();
            System.out.println("Task created successfully with id: "+task.getId());

            //convert to DTO and return
            return convertToDTO(task);
        } catch (Exception e){
            //RollBack if error
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error creating task:" +e.getMessage());
            return null;
        }
    }

    public TaskDTO read(Long id){
        try{
            Task task=em.find(Task.class,id);

            if(task !=null){
                System.out.println("Task found with id:"+id);
                return convertToDTO(task);
            } else {
                System.out.println("Task not found with id: "+id);
                return null;
            }
        } catch(Exception e){
            System.out.println("Error reading task: "+e.getMessage());
            return null;
        }
    }

    public TaskDTO update(long id, String taskName, String description){
        try{
            //begin transaction
            em.getTransaction().begin();

            //find task
            Task task=em.find(Task.class,id);

            if(task !=null){
                //update field
                task.setTask(taskName);
                task.setDescription(description);

                //merge(update in database
                Task updatedTask=em.merge(task);

                //commit transaction
                em.getTransaction().commit();

                System.out.println("Task updated successfully with id: "+id);
                return convertToDTO(updatedTask);
            } else {
                em.getTransaction().rollback();
                System.out.println("Task not found with id: "+id);
                return null;
            }
        } catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error updating task: "+e.getMessage());
            return null;
        }
    }

    public boolean delete(long id){
        try{
            //begin transaction
            em.getTransaction().begin();

            //find task
            Task task=em.find(Task.class,id);

            if(task != null){
                //remove from database
                em.remove(task);

                //commit transaction
                em.getTransaction().commit();

                System.out.println("Task deleted successfullu=y with id: "+id);
                return  true;
            } else {
                em.getTransaction().rollback();
                System.out.println("Task not found with id: "+id);
                return false;
            }
        } catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            System.out.println("Error deleting task: "+e.getMessage());
            return false;
        }
    }

    public List<TaskDTO> getAllTask(){
        List<TaskDTO> taskList=new ArrayList<>();
        try{
            //JPQL query to get all tasks
            String jpql="SELECT t FROM Task t";
            List<Task> tasks=em.createQuery(jpql,Task.class).getResultList();

            //convert each entity to DTO
            for(Task task:tasks){
                taskList.add(convertToDTO(task));
            }
            System.out.println("Retrieved "+ taskList.size()+" task from database");
            return taskList;
        } catch (Exception e){
            System.out.println("Error retrieving task: "+e.getMessage());
            return taskList; //Return empty list if error
        }
    }

    private TaskDTO convertToDTO(Task task){
        return  new TaskDTO(
                task.getId(),
                task.getTask(),
                task.getDescription()
        );
    }

    public void close(){
        if(em != null && em.isOpen()){
            em.close();
        }
        if(em !=null && emf.isOpen()){
            emf.close();
        }
        System.out.println("Database connection closed");
    }
}
