import fr.esgi.taskmanager.application.EntryPointControllerTest;
import fr.esgi.taskmanager.infra.JsonTaskRepositoryTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ EntryPointControllerTest.class, JsonTaskRepositoryTest.class })
public class MainTest {
    @Test
    public void run() {

    }

}
