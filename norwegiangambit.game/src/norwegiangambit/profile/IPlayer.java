package norwegiangambit.profile;

import java.util.ArrayList;

public interface IPlayer {

    public enum RunState{READY,RUNNING,PAUSE,PAUSED,STOP,STOPPED,TIMEOUT}

    public enum Players {
        MANUAL(Manual.class),
        MANUALHELP(ManualWithHelp.class),
        EASY(Easy.class),
        MEDIUM(Medium.class);

        public static String[] NAMES = getPlayers();

        public Class<?> profile;

        <X extends Player> Players(Class<X> profile) {
            this.profile = profile;
        }

        public Player getInstance() {
            try {
                return (Player) profile.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static String[] getPlayers() {
            ArrayList<String> list = new ArrayList<String>();
            for (Players player : Players.values())
                list.add(player.name());
            return list.toArray(new String[list.size()]);
        }
    }

    void run();

    String clickSquare(int i);

    ArrayList<Marking> getMarkers();
    
    String getDescription();
}
