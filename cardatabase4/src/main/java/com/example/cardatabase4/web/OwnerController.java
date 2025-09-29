package com.example.cardatabase4.web;

import com.example.cardatabase4.domain.Owner;
import com.example.cardatabase4.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public List<Owner> getOwner(){
        return ownerService.getOwner();
    }

    // 2.
    @GetMapping("/owners/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id){
        return ownerService.getOwnerById(id)
                .map(owner -> ResponseEntity.ok().body(owner))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3.
    @PostMapping("/owners")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner){
        Owner saveOwner = ownerService.addOwner(owner);

        return new ResponseEntity<>(saveOwner, HttpStatus.CREATED);
    }

    // 4
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id){
        if(ownerService.deleteOwner(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5
    @PutMapping("/owners/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable long id, @RequestBody Owner owerDetails){
        return ownerService.updateOwner(id, owerDetails)
                .map(updateOwner -> ResponseEntity.ok().body(updateOwner))
                .orElse(ResponseEntity.notFound().build());
    }
}
