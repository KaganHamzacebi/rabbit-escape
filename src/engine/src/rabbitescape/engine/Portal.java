package rabbitescape.engine;

import java.util.HashMap;
import java.util.Map;

public class Portal extends Thing {

    public Portal(int x, int y, ChangeDescription.State state) {
        super(x, y, state);
    }

    public Portal getOtherPortal(){return null;}

    @Override
    public void calcNewState(World world) {
    }

    @Override
    public void step(World world) {
    }

    @Override
    public Map<String, String> saveState(boolean runtimeMeta) {
        return new HashMap<String, String>();
    }

    @Override
    public void restoreFromState(Map<String, String> state)
    {
    }

    @Override
    public String overlayText() {
        return "Portal";
    }
}