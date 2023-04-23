package springCore.depend2;

public class AnotherStudent {
    private Cheat cheat;

    public void setCheat(Cheat cheat) {
        this.cheat = cheat;
    }

    public void startCheating() {
        cheat.cheat();
    }

}
