package pl.abof.test_template.arquillian.wf.cdi;

public class Value {
	
	private int value = 0;

	public Value(int value) {
		super();
		this.value = value;
	}

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}

}
