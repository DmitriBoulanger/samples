package de.dbo.samples.mockito;

// TODO: Auto-generated Javadoc
/**
 * The Class TestService.
 */
public class TestService {

	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public int getUniqueId() {
		return 43;
	}
	
	public int testing(int num) {
		someMethod("");
		return num;
	}
	
	public void someMethod(String someMethod) {
		testing(1);
	}
}
