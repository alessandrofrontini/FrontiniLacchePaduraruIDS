package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SNodi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CNodi {
    
    private final SNodi sNodi;
    private final String mapping = BaseUrl.baseUrl+"/nodi";

    @Autowired
    public CNodi(SNodi sNodi) {
        this.sNodi = sNodi;
    }

    /**
     * Questo metodo filtra tutti i nodi in base ai parametri passati.
     * Se non vengono passati parametri, saranno restituiti tutti i nodi.
     * //TODO: Ruoli ammessi:
     * @param idNodo (Opzionale)
     * @param idComune (Opzionale)
     * @return
     */
    @GetMapping(value = mapping, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ClsNodo>> getNodi(
            @RequestParam(value = "idNodo", required = false) String idNodo,
            @RequestParam(value = "idComune", required = false) String idComune
    ){
        ArrayList<ClsNodo> body;
        if(idNodo != null)
           body = sNodi.findNodoById(idNodo);
        else if(idComune != null)
            body = sNodi.findNodiByComune(idComune);
        else body = sNodi.getAllNodi();

        return ResponseEntity.ok(body);
    }

    /**
     * Questo metodo aggiunge un nodo.
     * //TODO: Ruoli ammessi:
     * @param nodo Dati del nodo da aggiungere. L'id è ignorato.
     * @return
     */
    @PostMapping(mapping)
    public ResponseEntity<String> postNodi(
            @RequestBody ClsNodo nodo
            ){
        if(!sNodi.postNodo(nodo))
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(null);
    }

    /**
     * Elimina un nodo dato l'ID.
     * //TODO: Ruoli Ammessi:
     * @param idNodo L'id del nodo da eliminare
     * @return
     */
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteNodi(
            @RequestParam(value = "idNodo") String idNodo
    ){
        if(!sNodi.deleteNodoById(idNodo))
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(null);
    }

    /**
     * Modifica un nodo.
     * //TODO: Ruoli Ammessi:
     * @param nodo Il nodo con i dati modificati.
     * @return
     */
    @PutMapping(mapping)
    public ResponseEntity<String> putNodi(
            @RequestBody ClsNodo nodo
    ){
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(nodo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(!sNodi.putNodo(nodo))
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(null);
    }
}
