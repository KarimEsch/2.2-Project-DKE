package Explorer.Percept;


import Explorer.Geometry.Direction;
import Explorer.Percept.Scenario.ScenarioIntruderPercepts;
import Explorer.Percept.Smell.SmellPercepts;
import Explorer.Percept.Sound.SoundPercepts;
import Explorer.Percept.Vision.VisionPrecepts;
import Explorer.Utils.Require;

/**
 * Represents percepts of an agent, including percepts specific to a intruder agent.
 *
 * Please, make sure that you review the documentation of the parent class!
 *
 * @see Percepts
 */
public final class IntruderPercepts extends Percepts {

    private Direction targetDirection;
    private ScenarioIntruderPercepts scenarioIntruderPercepts;

    public IntruderPercepts(
        Direction targetDirection,
        VisionPrecepts vision,
        SoundPercepts sounds,
        SmellPercepts smells,
        AreaPercepts areaPercepts,
        ScenarioIntruderPercepts scenarioIntruderPercepts,
        boolean wasLastActionExecuted
    ) {
        super(vision, sounds, smells, areaPercepts, wasLastActionExecuted);
        Require.notNull(targetDirection);
        Require.notNull(scenarioIntruderPercepts);
        this.targetDirection = targetDirection;
        this.scenarioIntruderPercepts = scenarioIntruderPercepts;
    }

    /**
     * @return The direction of the target relative to an agent.
     */
    public Direction getTargetDirection() {
        return targetDirection;
    }

    public ScenarioIntruderPercepts getScenarioIntruderPercepts() {
        return scenarioIntruderPercepts;
    }

}
