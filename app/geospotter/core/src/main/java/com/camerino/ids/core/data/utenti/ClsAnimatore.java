package com.camerino.ids.core.data.utenti;

import jakarta.persistence.Entity;

/**
 * Il suo ruolo è quello di aggiungere contest sulla piattaforma.
 * I contenuti del contest sono moderati da questo ruolo,
 *
 * Per diventare animatore bisogna raggiungere più di 1000 punti.
 */
@Entity
public class ClsAnimatore extends ClsContributorAutorizzato{
}
