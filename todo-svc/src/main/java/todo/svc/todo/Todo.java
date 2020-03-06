package todo.svc.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Todo {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  @NotEmpty
  private String task;

  private boolean complete;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTask() {
    return this.task;
  }

  public void setTask(String task) {
    this.task = task;
  }

  public boolean getComplete() {
    return this.complete;
  }

  public void setComplete(boolean complete) {
    this.complete = complete;
  }
}
