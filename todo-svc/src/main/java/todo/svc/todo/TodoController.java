package todo.svc.todo;

import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

@Controller("/todos")
public class TodoController {

  @Inject
  TodoRepository repository;

  HttpResponseFactory responseFactory = HttpResponseFactory.INSTANCE;

  @Get(produces = MediaType.APPLICATION_JSON)
  public Iterable<Todo> getAllTodos() {
    return repository.findAll();
  }

  @Post
  public HttpResponse<Todo> createTodo(@Body @Valid Todo input) {
    if (input.getId() == null) {
      return responseFactory.status(HttpStatus.CREATED, repository.save(input));
    }

    return responseFactory.status(HttpStatus.BAD_REQUEST);
  }

  @Get("/{id}")
  public HttpResponse<Todo> getById(Long id) {
    Optional<Todo> result = repository.findById(id);
    if (result.isPresent()) {
      return responseFactory.ok(result.get());
    }

    return responseFactory.status(HttpStatus.NOT_FOUND);
  }

  @Put("/{id}")
  public HttpResponse<Todo> update(Long id, @Body @Valid Todo input) {
    if (!id.equals(input.getId())) {
      return responseFactory.status(HttpStatus.BAD_REQUEST);
    }

    return responseFactory.ok(repository.update(input));
  }
}
