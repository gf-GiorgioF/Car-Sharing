import java.time.Duration;
import java.time.LocalDateTime;

public class Utente {
    private String nome, cognome, dataNascita, codiceFiscale, idUtente;
    private boolean casco;
    private double credito;
    private PatentiGuida patente;
    private Veicolo veicolo;

    // Costruttore
    public Utente(String nome, String cognome, String dataNascita, String codiceFiscale, PatentiGuida patente) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.patente = patente;
        idUtente = Database.count + " ";
        Database.count++;
        this.veicolo = null;
    }

    //SETTER
    public void setPatenteGuida(PatentiGuida patente) {
        this.patente = patente;
    }

    public void setCasco(boolean casco) {
        this.casco = casco;
    }

    public void setCredito(double credito) {
        this.credito += credito;
        if(this.credito < 0)
        System.out.println("Credito non sufficiente per proseguire");
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    //GETTER
    public PatentiGuida getPatenteGuida() {
        return patente;
    }
    public double getCredito() {
        return credito;
    }
    public String getIdUtente() {

        return idUtente;
    }
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public boolean isCasco() {
        return casco;
    }
    public Veicolo getVeicolo() {
        return veicolo;
    }

    // Metodi
    public void restituzioneVeicolo() {
        LocalDateTime ora = LocalDateTime.now();
        LocalDateTime fiveMinutesLater = LocalDateTime.now();
        long time[] = getTime(ora, fiveMinutesLater);
        long minuti = time[0] * 60 + time[1];
        if (minuti < 5) {
            setCredito(0 - (veicolo.getTariffa() * 5));
        } else {
            setCredito(0 - (veicolo.getTariffa()) * minuti);
        }
        veicolo.setDisponibilita(true);
        veicolo = null;
    }
    // Calcola il tempo del noleggio ore e minuti
    private long[] getTime(LocalDateTime dob, LocalDateTime now) {
        final int MINUTES_PER_HOUR = 60;
        final int SECONDS_PER_MINUTE = 60;
        final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
        LocalDateTime today = LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), dob.getHour(), dob.getMinute(), dob.getSecond());
        Duration duration = Duration.between(today, now);

        long seconds = duration.getSeconds();

        long hours = seconds / SECONDS_PER_HOUR;
        long minutes = ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        return new long[]{hours, minutes};
    }
    @Override
    public String toString() {
        return " Utente{" +
                " patenteGuida='" + patente + '\'' +
                ", idUtente='" + idUtente + '\'' +
                ", casco=" + casco +
                ", credito=" + credito +
                ", veicolo=" + veicolo +
                '}';
    }
}
