package osmium.exception;

import java.io.File;
import java.io.IOException;

public class InvalidStructureTypeException extends IOException {

	private static final long serialVersionUID = 630547858883638354L;

	public InvalidStructureTypeException() {
		super("Error: Invalid structure type.");
	}

}
