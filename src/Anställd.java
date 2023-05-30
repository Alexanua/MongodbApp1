class Anställd extends Person {
    private String anställningsnummer;

    public Anställd(String anställningsnummer) {
        this.anställningsnummer = anställningsnummer;
    }

    public Anställd(String name, int age, String address, String anställningsnummer) {
        super(name, age, address);
        this.anställningsnummer = anställningsnummer;
    }

    public String getAnställningsnummer() {
        return anställningsnummer;
    }

    public void setAnställningsnummer(String anställningsnummer) {
        this.anställningsnummer = anställningsnummer;
    }

    @Override
    public String toString() {
        return "Anställd{" +
                "anställningsnummer='" + anställningsnummer + '\'' +
                "} " + super.toString();
    }
}