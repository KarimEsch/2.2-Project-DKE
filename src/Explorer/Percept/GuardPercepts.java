package Explorer.Percept;
import Explorer.Percept.Scenario.ScenarioGuardPercepts;
import Explorer.Percept.Smell.SmellPercepts;
import Explorer.Percept.Sound.SoundPercepts;
import Explorer.Percept.Vision.VisionPrecepts;
import Explorer.Utils.Require;

/**
 * Represents percepts of an agent, including percepts specific to a guard agent.
 *
 * Please, make sure that you review the documentation of the parent class!
 *
 * @see Percepts
 */
public final class GuardPercepts extends Percepts {

    private ScenarioGuardPercepts scenarioGuardPercepts;

    public GuardPercepts(
        VisionPrecepts vision,
        SoundPercepts sounds,
        SmellPercepts smells,
        AreaPercepts areaPercepts,
        ScenarioGuardPercepts scenarioGuardPercepts,
        boolean wasLastActionExecuted
    ) {
        super(vision, sounds, smells, areaPercepts, wasLastActionExecuted);
        Require.notNull(scenarioGuardPercepts);
        this.scenarioGuardPercepts = scenarioGuardPercepts;
    }

    public ScenarioGuardPercepts getScenarioGuardPercepts() {
        return scenarioGuardPercepts;
    }

}
