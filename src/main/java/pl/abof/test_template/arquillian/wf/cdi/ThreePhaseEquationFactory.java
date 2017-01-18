package pl.abof.test_template.arquillian.wf.cdi;


import java.lang.annotation.Annotation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Qualifier;

import org.apache.log4j.Logger;

import pl.abof.test_template.arquillian.wf.cdi.operation.Operation;

@Stateless
@LocalBean
public class ThreePhaseEquationFactory {

	@Inject
	Instance<Operation> operations;
	
	public ThreePhaseEquation create(EquationType type) {
		
		log.warn("[CDI] ThreePhaseEquation dsoteępne operacje" );
		log.warn("\t ambigious: " + operations.isAmbiguous());
		log.warn("\t unsatisfied: " + operations.isUnsatisfied());
		operations.forEach(o -> log.warn("\t\t+" + o.getProcessName()));
		
		if(EquationType.PEOPLE_IN_GOOD_MOOD_FROM_YEAR.equals(type)) {
			// ADD 4 three times :)
			Instance<Operation> addFour = operations.select(new NamedAnnotationLiteral("AddFour"));
			return new ThreePhaseEquation(addFour.get(), addFour.get(), addFour.get());
		}
		else if(EquationType.BLACK_HOLE_FORCE_AMOUNT_FROM_ITS_SIZE.equals(type)) {
			// ADD 4 DIVIDE BY 2 MINUS 1
			
			Instance<Operation> addFour = operations.select(new NamedAnnotationLiteral("AddFour"));
			Instance<Operation> divideByTwo = operations.select(new NamedAnnotationLiteral("DivideByTwo"));
			Instance<Operation> subtractOne = operations.select(new NamedAnnotationLiteral("SubtractOne"));
			
			return new ThreePhaseEquation(addFour.get(), divideByTwo.get(), subtractOne.get());
		}
		
		throw new IllegalArgumentException("Unsupproted equation type: " + type);
	}
	
	public static enum EquationType{
		BLACK_HOLE_FORCE_AMOUNT_FROM_ITS_SIZE,
		PEOPLE_IN_GOOD_MOOD_FROM_YEAR;
	}
	
	private class NamedAnnotationLiteral extends AnnotationLiteral<Named> implements Named {
		
		String name;
		
		public NamedAnnotationLiteral(String name) {
			super();
			this.name = name;
		}

		@Override
		public String value() {
			return name;
		}}
	
	
	public static Logger log = Logger.getLogger(ThreePhaseEquationFactory.class);
}
