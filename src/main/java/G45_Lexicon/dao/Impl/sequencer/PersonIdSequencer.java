package G45_Lexicon.dao.Impl.sequencer;

public class PersonIdSequencer {
    private static int startId;
    public static int nextId() {return ++startId;}
}
