package io.nirahtech.virtualization.manager.interfaces.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Interface that defines a CREATE, READ, UPDATE and DELETE API.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public interface Crud<T> {
    
    /**
     * Create the entity.
     * 
     * @param entity Entity to create.
     * @return The created entity.
     */
    @PostMapping
    @ResponseBody
    public T create(@RequestBody T entity);
    
    /**
     * Read the entity identified by the given identifier.
     * 
     * @param identifier Identifier of the entity to retrieve.
     * @return The entity found identified by the identifier, else {@code null}.
     */
    @GetMapping("/{identifier}")
    @ResponseBody
    public T read(@PathVariable("identifier") String identifier);
    
    /**
     * Update the entity identifier by the given identifier.
     * 
     * @param identifier Identifier of the entity to update.
     * @param entity Entity to create.
     * @return The updated entity identified by the identifier, else {@code null}.
     */
    @PutMapping("/{identifier}")
    @ResponseBody
    public T update(@PathVariable("identifier") String identifier, @RequestBody T entity);
    
    /**
     * Delete the entity identifier by the given identifier.
     * 
     * @param identifier Identifier of the entity to delete.
     */
    @DeleteMapping("/{identifier}")
    public void delete(@PathVariable("identifier") String identifier);
}
