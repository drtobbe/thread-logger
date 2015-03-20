package se.util.eif.logging;

/**
 * @author drtobbe
 */
public abstract class LogConstants {

    public enum LocalState {
        UNDEFINED("Unknown"),
        //Currently working
        WORKING("Working"),
        //Waiting for something, e.g., a response.
        WAITING("Waiting"),
        //Continuing in the flow.
        CONTINUING("Continuing"),
        //Beginning a major work task.
        BEGIN("Begin"),
        //Ending a major work task.
        END("End"),
        //Sending something.
        SENDING("Sending"),
        //Received something.
        RECEIVED("Received"),
        //Starting a transaction.
        BEGIN_TRANSACTION("BeginTransaction"),
        //Ending a transaction.
        END_TRANSACTION("EndTransaction"),
        //Starting a sub flow.
        START_SUB_FLOW("StartSubFlow"),
        //Ending a sub flow.
        END_SUB_FLOW("EndSubFlow"),
        //Exit flow.
        EXIT("Exit"),
        //Exit flow with error.
        EXIT_WITH_ERROR("Error");

        private String enumValue;
        LocalState(String value) {
            enumValue = value;
        }

        @Override
        public String toString() {
            return enumValue;
        }
    }

    public enum GlobalState {
        //Undefined global state.
        UNDEFINED("Undefined"),
        //Initiating flow.
        INITIATE_FLOW("Initiate"),
        //Analyzing something.
        ANALYZE("Analyze"),
        //In some kind of process.
        PROCESS("Process"),
        //Continuing in the flow.
        CONTINUE("Continue"),
        //Finalizing flow.
        FINALIZE_FLOW("Finalize");

        private final String enumValue;
        private GlobalState(String value) {
            enumValue = value;
        }

        @Override
        public String toString() {
            return enumValue;
        }
    }

}
