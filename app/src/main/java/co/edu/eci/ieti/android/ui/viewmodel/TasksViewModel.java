package co.edu.eci.ieti.android.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.edu.eci.ieti.android.persistence.entities.Task;
import co.edu.eci.ieti.android.persistence.entities.User;
import co.edu.eci.ieti.android.repository.TaskRepository;
import co.edu.eci.ieti.android.storage.Storage;

public class TasksViewModel extends ViewModel {
    private MutableLiveData<List<Task>> tasks;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );
    private TaskRepository taskRepository;


    public LiveData<List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<List<Task>>();
            loadTasks();
        }
        return tasks;
    }
    public void setTaskRepository(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    private void loadTasks() {
        executorService.execute(()->tasks.postValue(taskRepository.getTasks()));

        // Do an asynchronous operation to fetch users.
    }
}

