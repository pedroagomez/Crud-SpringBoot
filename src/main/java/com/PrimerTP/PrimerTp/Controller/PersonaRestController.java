package com.PrimerTP.PrimerTp.Controller;

import com.PrimerTP.PrimerTp.Model.Persona;
import com.PrimerTP.PrimerTp.Repositorio.IRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonaRestController {

    @Autowired
    private IRepository repository;

    @GetMapping
    public ResponseEntity<List<Persona>> getPersonas() {
        List<Persona> listaPersonas = repository.findAll();
        return ResponseEntity.ok(listaPersonas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = repository.findById(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Persona> postPersona(@RequestBody Persona persona) {
        Persona person1 = repository.save(persona);
        java.net.URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(persona.getNombre()).toUri();

        return ResponseEntity.created(location).body(person1);
        //return ResponseEntity.status(HttpStatus.CREATED).body(person1);

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?>deletePersona(@PathVariable Long id)
    {
        if(!repository.existsById(id))
        {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @PutMapping()

    public ResponseEntity<?> putPersona(@RequestBody Persona persona)
    {
        if(!repository.existsById(persona.getId()))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(persona);
        }
        persona.setId(persona.getId());
        repository.save(persona);
        return ResponseEntity.ok(persona);

    }


}
