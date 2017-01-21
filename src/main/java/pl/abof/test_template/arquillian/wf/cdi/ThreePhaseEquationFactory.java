package pl.abof.test_template.arquillian.wf.cdi;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import pl.abof.test_template.arquillian.wf.cdi.operation.Operation;

@Stateless
@LocalBean
public class ThreePhaseEquationFactory {

	@Inject @Any Instance<Operation> operations;
	
	public static enum EquationType{
		BLACK_HOLE_FORCE_AMOUNT_FROM_ITS_SIZE,
		PEOPLE_IN_GOOD_MOOD_FROM_YEAR;
	}
	
	public ThreePhaseEquation create(EquationType type) {
		// START: For testing purposes
		log.info("[CDI] ThreePhaseEquation; create invocation for " + type + " :" );
		log.info(" + is injected 'Instance' ambigious? : " + operations.isAmbiguous());
		log.info(" + is injected 'Instance' unsatisfied? : " + operations.isUnsatisfied());
		log.info(" + all available instances: ");
		operations.forEach(o -> log.info("  + " + o.getName()));
		
		// END: For testing purposes
		
		// Main factory stuff...
		if(EquationType.PEOPLE_IN_GOOD_MOOD_FROM_YEAR.equals(type)) {
			// eq(x) = x + 4 + 4 + 4 
			Instance<Operation> addFour = operations.select(prepareNamedQualifier("AddFour"));
			return new ThreePhaseEquation(addFour.get(), addFour.get(), addFour.get());
		}
		else if(EquationType.BLACK_HOLE_FORCE_AMOUNT_FROM_ITS_SIZE.equals(type)) {
			// eq(x) = (x + 4)/2 - 1
			Instance<Operation> addFour = operations.select(prepareNamedQualifier("AddFour"));
			Instance<Operation> divideByTwo = operations.select(prepareNamedQualifier("DivideByTwo"));
			Instance<Operation> subtractOne = operations.select(prepareNamedQualifier("SubtractOne"));
			return new ThreePhaseEquation(addFour.get(), divideByTwo.get(), subtractOne.get());
		}
		
		throw new IllegalArgumentException("Unsupproted equation type: " + type);
	}
	
	private AnnotationLiteral <Named> prepareNamedQualifier(String value) {
		return new NamedAnnotationLiteral(value);
	}
	
	@SuppressWarnings("serial")
	private class NamedAnnotationLiteral extends AnnotationLiteral<Named> implements Named {
		String name;
		
		public NamedAnnotationLiteral(String name) {
			super();
			this.name = name;
		}

		@Override
		public String value() {
			return name;
		}
	}

	public static Logger log = Logger.getLogger(ThreePhaseEquationFactory.class);
}
