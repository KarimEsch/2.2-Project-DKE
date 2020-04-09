package Explorer.Action;
import Explorer.Geometry.Distance;

/**
 * This class represents intention to make a move.
 *
 * The agent is allowed to specify the move distance in range limited by scenario parameters.
 */
public final class  AMove implements Action, IntruderAction, GuardAction {

    private Distance distance;

    public AMove(Distance distance) {
        this.distance = distance;
    }

    public Distance getDistance() {
        return distance;
    }

}