package pojo;

import java.util.List;

import org.junit.Before;

import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.PojoValidator;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.NoStaticExceptFinalRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public abstract class AbstractPojoTest {

	private PojoValidator pojoValidator;

	@Before
	public void init() {
		pojoValidator = new PojoValidator();
		pojoValidator.addRule(new NoPublicFieldsRule());
		pojoValidator.addRule(new NoStaticExceptFinalRule());
		pojoValidator.addRule(new NoFieldShadowingRule());
		pojoValidator.addRule(new GetterMustExistRule());
		pojoValidator.addRule(new SetterMustExistRule());

		pojoValidator.addTester(new SetterTester());
		pojoValidator.addTester(new GetterTester());
		pojoValidator.addTester(new Tester() {

			@Override
			public void run(PojoClass pojoClass) {
				List<PojoMethod> list = pojoClass.getPojoConstructors();
				for (PojoMethod method : list) {
					Class<?>[] types = method.getParameterTypes();
					int count = types.length;
					Object[] values = new Object[count];
					for (int i = 0; i < count; i++) {
						values[i] = RandomFactory.getRandomValue(types[i]);
					}
					method.invoke(null, values);
				}
			}
		});

	}

	protected void testClass(Class<?> clazz) {
		testSetterGetters(clazz);
	}

	private void testSetterGetters(Class<?> clazz) {
		PojoClass pojoClass = PojoClassFactory.getPojoClass(clazz);
		pojoValidator.runValidation(pojoClass);
	}

	abstract public void testPojo();

}
