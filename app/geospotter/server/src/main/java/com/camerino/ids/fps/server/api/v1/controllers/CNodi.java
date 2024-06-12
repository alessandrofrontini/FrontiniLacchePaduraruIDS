package com.camerino.ids.fps.server.api.v1.controllers;

import com.camerino.ids.core.data.contenuti.ClsNodo;
import com.camerino.ids.fps.server.api.v1.BaseUrl;
import com.camerino.ids.fps.server.api.v1.services.SNodi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CNodi {

    private final SNodi sNodi;
    private final String mapping = BaseUrl.baseUrl + "/nodi";

    @Autowired
    public CNodi(SNodi sNodi) {
        this.sNodi = sNodi;
    }

    /**
     * Questo metodo filtra tutti i nodi in base ai parametri passati.
     * Se non vengono passati parametri, saranno restituiti tutti i nodi.
     * //TODO: Ruoli ammessi:
     *
     * @param idNodo   (Opzionale)
     * @param idComune (Opzionale)
     * @return
     */
    @GetMapping(value = mapping, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClsNodo>> getNodi(
            @RequestParam(value = "idNodo", required = false) Long idNodo,
            @RequestParam(value = "idComune", required = false) Long idComune,
            @RequestParam(value = "owner", required = false) Long owner
    ) {
        if (owner != null)
            return ResponseEntity.ok(sNodi.getNodiPosessore());
        if (idNodo != null)
            return ResponseEntity.ok(sNodi.findNodoById(idNodo));
        else if (idComune != null)
            return ResponseEntity.ok(sNodi.findNodiByComune(idComune));
        return ResponseEntity.ok(sNodi.getAllNodi());
    }

    /**
     * Questo metodo aggiunge un nodo.
     * //TODO: Ruoli ammessi:
     *
     * @param nodo Dati del nodo da aggiungere. L'id è ignorato.
     * @return
     */
    @PostMapping(mapping)
    public ResponseEntity<Boolean> postNodi(
            @RequestBody ClsNodo nodo
    ) {
        return ResponseEntity.ok(sNodi.postNodo(nodo));
    }

    /**
     * Elimina un nodo dato l'ID.
     * //TODO: Ruoli Ammessi:
     *
     * @param idNodo L'id del nodo da eliminare
     * @return
     */
    @DeleteMapping(mapping)
    public ResponseEntity<Boolean> deleteNodi(
            @RequestParam(value = "idNodo") Long idNodo
    ) {
        if (!sNodi.deleteNodoById(idNodo))
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(null);
    }

    /**
     * Modifica un nodo.
     * //TODO: Ruoli Ammessi:
     *
     * @param nodo Il nodo con i dati modificati.
     * @return
     */
    @PutMapping(mapping)
    public ResponseEntity<Boolean> putNodi(
            @RequestBody ClsNodo nodo
    ) {
        return ResponseEntity.ok(sNodi.putNodo(nodo));
    }
}
