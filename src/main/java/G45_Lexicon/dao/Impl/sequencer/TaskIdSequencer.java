package G45_Lexicon.dao.Impl.sequencer;

public class TaskIdSequencer {
    private static int startId;
    public static int nextId() {return ++startId;}
}
