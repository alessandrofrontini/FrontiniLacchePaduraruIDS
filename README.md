# Presentazione
**Che cos'è GeoSpotter?**
<br/>GeoSpotter, in soldoni, è una  _piattaforma di digitalizzazione e valorizzazione del territorio Italiano_, sviluppata in Java e Springboot. <br/> <br/> 
Questa applicazione è figlia di una specifica di un progetto relativa al corso di "Ingegneria del Software" erogato dall'Università degli studi di Camerino.

![image](/assets/home_image.png)

GeoSpotter è stata pensata, documentata e implementata dal team - _**SPAFAMAT**_ - composto da:
- [Frontini Alessandro](https://github.com/alessandrofrontini) : alessandro.frontini@studenti.unicam.it (118 532)
- [Paduraru Danut Razvan](https://github.com/PettingStrings) : danutrazvan.paduraru@studenti.unicam.it (118 308)
- [Francesco Saverio Lacchè](https://github.com/Mieti3bbia) : francescosave.lacche@studenti.unicam.it (119 067)

Per qualsiasi cosa abbiate bisogno potete contattarci a una delle tre email riportate sopra. <br/>Vi risponderemo appena possibile.
## Come è strutturato il GeoSpotter?
```console
├───app
│   └───geospotter
│       │
│       ├───cli      # Applicazione locale console in java puro
│       │   └───...
│       ├───client   # Client in JavaFX che usa il server in SpringBoot
│       │   └───...
│       ├───CLIsave  # Cartella dove la CLI salva i dati su file
│       │   └───...
│       ├───core     # Libreria comune che contiene le classi comuni implementate
│       │   └───...
│       └───server   # Server scritto in SpringBoot
│           └───...
│
└───Documentazione
    ├───UML
    │   └───GeoSpotter.vpp
    └───README - Sistema a Punteggi.pdf
```
# Documentazione
Possiamo sommariamente dire che, all'interno della cartella Documentazione, è possibile trovare tutti quei documenti e informazioni utili alla comprensione di come GeoSpotter è stato strutturato e quali componenti esso si serve per il suo funzionamento e delle loro interazioni. In particolar modo possiamo trovare due file:
- _**GeoSpotter.vpp**_<br/>E' da intendersi come la **documentazione effettiva**. <br/>Dentro questo documento troverete tutti i vari diagrammi (o viste, espressi in UML) e documenti che descrivono GeoSpotter.<br/><br/>
- _**README - Sistema a Punteggi.pdf**_<br/>E' da intendersi come un **documento di affiancamento alla documentazione (.vpp)**.<br/>Dentro questo documento troverete delucidadazioni di alcuni punti relativi ad una delle tante funzionalità messa a disposizione dal software: **la gestione dei ruoli tramite punteggi**. <br/><br/>Questa funzionalità viene ampiamente illustrata e discussa in **GeoSpotter.vpp** ma, noi di **_SPAFAMAT_**, abbiamo voluto creare un ulteriore documento di ausilio, utile a chiarire ancor meglio questo punto.

  
# Codice
E' possibile trovare all' interno del progetto **DUE** versioni di GeoSpotter:

- GeoSpotter Console (v0): **CLI** 
- GeoSpotter (v1): **Server**/**GUI**
  
Consigliamo fortemente di utilizzare e testare entrambe le versioni di GeoSpotter per comprenderne al meglio l'essenza :)
## V1 - Geospotter Console - CLI (Java)
La CLI, la prima versione di GeoSpotter, rappresenta una **demo** delle funzionalità cardine di cui GeoSpotter si serve per il suo funzionamento. <br/>
Questa prima versione utilizza un interfaccia a linea di comando (Command Line Interface) per interagire con l'utente che la utilizza e essa implementa i casi d'uso cruciali di cui GeoSpotter si serve; per un'esperienza d'uso migliore la CLI sfrutta il salvataggio su file dei dati. <br/>
Per eseguire la CLI è necessario posizionarsi nella cartella "src/main/java/com/camerino/cli/" ed eseguire il Main. <br/>
Attraverso la CLI sarà possibile effettuare il login e, in base al ruolo dell'utente, compariranno le azioni effettuabili dall'utente. Le scelte nei menu vengono effettuate inserendo il numero corrispondente all'elemento da selezionare, rispondendo a domande "Y/N" e inserendo dati apertamente. <br/>
Effettuando login e logout con diversi utenti sarà possibile provare il funzionamento di Geospotter; non è necessario chiudere l'applicazione prima di cambiare ruolo, è sufficiente effettuare il logout dall'interno di Geospotter. <br/>
All'interno del progetto è presente la cartella "CLISave": come suggerisce il nome, è una cartella contenente le informazioni salvate dalla CLI, sottoforma di file CSV.

### Come eseguire la CLI?
## V2 - SERVER(SpringBoot) - GUI(JavaFX) 
La versione Server di GeoSpotter rappresenta un'evoluzione rispetto alla prima versione, che era una demo delle funzionalità fondamentali del software. <br/> Questa versione di GeoSpotter è da considerarsi **finale e completa**.
### Come eseguire il Server?



## Codice, alcuni chiarimenti
- Versione Java: 19.0.2 Amazon Corretto
- Versione JavaFX 19.0.2
- Versione Springboot 3.2.5

### Librerie Utilizzate:
- Sprinboot Starter Web
- Springboot Data JPA
- Jackson    
  


