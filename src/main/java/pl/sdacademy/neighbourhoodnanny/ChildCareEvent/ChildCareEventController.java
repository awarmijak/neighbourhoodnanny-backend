package pl.sdacademy.neighbourhoodnanny.ChildCareEvent;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/event")
public class ChildCareEventController {
    ChildCareEventRepository childCareEventRepository;

    public ChildCareEventController(ChildCareEventRepository childCareEventRepository) {
        this.childCareEventRepository = childCareEventRepository;
    }

    @GetMapping
    public List<ChildCareEvent> getAll() {
        return childCareEventRepository.findAll();
    }

    @PostMapping
    public ChildCareEvent add(@RequestBody ChildCareEvent childCareEvent) {
        return childCareEventRepository.save(childCareEvent);
    }

    @GetMapping("/{id}")
    public ChildCareEvent getById(@PathVariable long id) {
        return childCareEventRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable long id) {
        childCareEventRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateChildCareEvent(@PathVariable long id, @RequestBody ChildCareEvent childCareEvent) {
        Optional<ChildCareEvent> childCareEventOptional = childCareEventRepository.findById(id);
        if (!childCareEventOptional.isPresent()) {
            System.out.println("Event with id: " + id + " not found");
        }
        childCareEvent.setId(id);
        childCareEventRepository.save(childCareEvent);

    }
}
