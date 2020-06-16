import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.model.InitializationError;
import org.junit.runners.parameterized.BlockJUnit4ClassRunnerWithParameters;
import org.junit.runners.parameterized.ParametersRunnerFactory;
import org.junit.runners.parameterized.TestWithParameters;

import java.util.Arrays;

/**
 * example about custom parameterize test
 * from: https://stackoverflow.com/questions/27745691/how-to-combine-runwith-with-runwithparameterized-class
 * */

@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(CustomParameterizedTest.RunnerFactory.class)
public class CustomParameterizedTest {

    @Parameterized.Parameters
    public static Iterable<Integer> data() {
        return Arrays.asList(new Integer[]{1, 2, 3});
    }

    private int i;

    public CustomParameterizedTest(int i) {
        this.i = i;
    }

    @Test
    public void test() {
        System.out.println(i);
    }

    public static class RunnerFactory implements ParametersRunnerFactory {
        @Override
        public org.junit.runner.Runner createRunnerForTestWithParameters(TestWithParameters test) throws InitializationError {
            return new A(test);
        }
    }

    public static class A extends BlockJUnit4ClassRunnerWithParameters {
        private final Object[] parameters;

        public A(TestWithParameters test) throws InitializationError {
            super(test);
            parameters = test.getParameters().toArray(new Object[test.getParameters().size()]);
        }

        @Override
        public Object createTest() throws Exception {
            return getTestClass().getOnlyConstructor().newInstance(parameters);
        }
    }
}
