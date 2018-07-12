package eng.tz.ms.la.model;
/**
 * @author salvatore mariniello
 */
public enum States {
	PENDING("0"), INVIATE("1"), CLOSE("2");

	private String state;

	States(String state) {
		this.state = state;
	}

	public String state() {
		return state;
	}

}
