package G45_Lexicon.dao;

import G45_Lexicon.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDAO extends BaseDAO<Task> {
    List<Task> findByDoneStatus(boolean doneStatus);
    List<Task> findByTitle(String title);

    List<Task> findByUsername(String username);
    List<Task> findByRole(String role);
    List<Task> findByPersonId(Integer personId);
}
