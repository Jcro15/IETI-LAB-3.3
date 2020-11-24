package co.edu.eci.ieti.android.repository;

import android.content.Context;

import java.util.List;

import co.edu.eci.ieti.android.network.RetrofitNetwork;
import co.edu.eci.ieti.android.network.service.TaskService;
import co.edu.eci.ieti.android.persistence.AppDatabase;
import co.edu.eci.ieti.android.persistence.dao.TaskDao;
import co.edu.eci.ieti.android.persistence.entities.Task;

public class TaskRepository {

    private TaskDao taskDao;
    private TaskService taskService;

    public TaskRepository(String token, Context context){
        taskDao = AppDatabase.getDatabase(context).taskDao();
        taskService = new RetrofitNetwork(token).getTaskService();
    }

    public List<Task> getTasks(){
        try{
            List<Task> tasks = taskService.getTasks().execute().body();
            if(tasks!=null){
                taskDao.deleteAll();
                taskDao.insertAll(tasks);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return taskDao.getAll();
    }
}
