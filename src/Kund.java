public class Kund extends Person {
    private String kundnummer;

    public Kund(String kundnummer) {
        this.kundnummer = kundnummer;
    }

    public Kund(String name, int age, String address, String kundnummer) {
        super(name, age, address);
        this.kundnummer = kundnummer;
    }

    public String getKundnummer() {
        return kundnummer;
    }

    public void setKundnummer(String kundnummer) {
        this.kundnummer = kundnummer;
    }

    @Override
    public String toString() {
        return "Kund{" +
                "kundnummer='" + kundnummer + '\'' +
                "} " + super.toString();
    }
}