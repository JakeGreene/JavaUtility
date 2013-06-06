package ca.jakegreene.util.graph;

public class EdgeLoopException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5607075080910938330L;
	public EdgeLoopException() {
	}
	
	public EdgeLoopException(String msg) {
		super(msg);
	}
}
