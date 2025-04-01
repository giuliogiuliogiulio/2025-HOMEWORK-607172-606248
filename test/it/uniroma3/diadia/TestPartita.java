import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class TestPartita {

    private Partita partita;

    @BeforeEach
    void setUp() throws Exception {
	this.partita = new Partita();
    }

    @Test
    void testNuovaPartitaNON_FINITA() {
	assertFalse(this.partita.isFinita());
    }

    
}
