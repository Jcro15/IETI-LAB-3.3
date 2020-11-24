package co.edu.eci.ieti.android.persistence.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.edu.eci.ieti.android.persistence.entities.Task;
import co.edu.eci.ieti.android.persistence.entities.User;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();
    @Insert
    void insertAll(List<Task> tasks);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM task")
    void deleteAll();
}
