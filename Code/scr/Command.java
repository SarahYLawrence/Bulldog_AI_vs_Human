/**
 * The {@code Command} interface represents an action or operation 
 * that can be executed and later undone.
 * <p>
 * This interface follows the Command design pattern, commonly used 
 * to encapsulate a request as an object, thereby allowing for 
 * parameterization of clients with queues, requests, and operations.
 * </p>
 */
public interface Command {

    /**
     * Executes the command.
     * This method should contain the logic to perform the action.
     */
    void execute();

    /**
     * Undoes the command.
     * This method should revert the effects of the {@code execute()} method.
     */
    void undo();
}
