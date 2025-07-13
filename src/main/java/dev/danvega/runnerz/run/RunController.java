package dev.danvega.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController //man kiregt angeblich json zurück
@RequestMapping("/api/runs")
class RunController { //anegblich ein spring rest cotroller was das heitst ist mir ien rätsel weil ich drepostry macht ei alles was der controller beim anderen ding gemacht hat yk
//angeblich eine http schnittstelle

    private final JdbcRunRepository runRepository;

    RunController(JdbcRunRepository runRepository) {
        this.runRepository = runRepository;
    } //was für konstruktor damit kann man auf datean bank zugerifen

    @GetMapping
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        runRepository.update(run,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

    List<Run> findByLocation(@RequestParam String location) {
        return runRepository.findByLocation(location);
    }
}
//FAZIT WARUM HAB ICH GEFÜHLT 100 KLASSEN DEI DAS SLEB MACHEN ALLE HABEN CREATE DELETE BLABLA RECIHT DOCH