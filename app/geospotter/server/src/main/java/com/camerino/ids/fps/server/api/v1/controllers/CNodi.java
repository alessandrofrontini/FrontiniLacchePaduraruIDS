package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SNodi;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.status(501).build();
    }

    /**
     * Elimina un nodo dato l'ID.
     * //TODO: Ruoli Ammessi:
     * @param idNodo L'id del nodo da eliminare
     * @return
     */
    @DeleteMapping(mapping)
    public ResponseEntity<String> deleteNodi(
            @RequestParam(value = "id") String idNodo
    ){
        return ResponseEntity.status(501).build();
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
        return ResponseEntity.status(501).build();
    }

}
