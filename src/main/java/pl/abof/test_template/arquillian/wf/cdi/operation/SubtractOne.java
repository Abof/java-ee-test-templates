package pl.abof.test_template.arquillian.wf.cdi.operation;

import javax.ejb.Stateless;
import javax.inject.Named;

import pl.abof.test_template.arquillian.wf.cdi.Value;

@Named("SubtractOne")
@Stateless
public class SubtractOne implements Operation {

	@Override
	public void perform(Value v) {
		v.set(v.get() - 1);
	}
	
	@Override
	public String getName() {
		return "SubtractOne";
	}
}
