package pl.abof.test_template.arquillian.wf.cdi.operation;

import javax.ejb.Stateless;
import javax.inject.Named;

import pl.abof.test_template.arquillian.wf.cdi.Value;

@Named("AddFour")
@Stateless
public class AddFour implements Operation {

	@Override
	public void perform(Value v) {
		v.set(v.get() + 4);
	}

	@Override
	public String getName() {
		return "AddFour";
	}
}
