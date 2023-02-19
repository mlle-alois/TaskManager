import fr.esgi.taskmanager.infra.input.ScriptChoice;
import org.junit.jupiter.api.Test;

public class ScriptChoiceTest {
    @Test
    public void read_script_with_add_c_hello_world() {
        // Given
        String[] args = {"add"};
        // When
        ScriptChoice scriptChoice = new ScriptChoice(args);
        // Then
        assert scriptChoice.choose().equals("add");

    }

}
