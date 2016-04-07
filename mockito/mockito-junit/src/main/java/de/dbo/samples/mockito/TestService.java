package de.dbo.samples.mockito;

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
	voidMethod("");
	return num;
    }

    public void voidMethod(String someData) {
	testing(1);
    }
}
