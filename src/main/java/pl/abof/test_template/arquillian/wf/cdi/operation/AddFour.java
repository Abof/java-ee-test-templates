package pl.abof.test_template.arquillian.wf.cdi.operation;

import javax.enterprise.inject.Default;
import javax.inject.Named;

import pl.abof.test_template.arquillian.wf.cdi.Value;

@Default
@Named("AddFour")
public class AddFour implements Operation {

	public AddFour() {
		super();
		System.out.println("NEW AddFour " + super.toString());
	}

	@Override
	public void perform(Value v) {
		v.set(v.get() + 4);
	}
}
