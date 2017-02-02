package test.bu.dorohovich.site;

import by.dorohovich.site.exception.ValidationException;
import by.dorohovich.site.validator.Validator;
import org.junit.Test;

/**
 * Created by User on 02.02.2017.
 */
public class ValidationTester {

    @Test
    public void testValidateLogin1() throws ValidationException{
        Validator validator = new Validator();
        validator.validateLogin("gotse");
    }

    @Test
    public void testValidateLogin2() throws ValidationException{
        Validator validator = new Validator();
        validator.validateLogin("gotse");
    }

    @Test
    public void testValidateLogin3() throws ValidationException{
        Validator validator = new Validator();
        validator.validateLogin("gots_q21e");
    }

    @Test( expected = ValidationException.class)
    public void testValidateLogin4() throws ValidationException{
        Validator validator = new Validator();
        validator.validateLogin("got");
    }

    @Test (expected = ValidationException.class)
    public void testValidateLogin5() throws ValidationException{
        Validator validator = new Validator();
        validator.validateLogin("gots_q21easdsdfddgfgddfdfdfsdfdfgdgdgdgddgfddgdddgdgd");
    }

    @Test
    public void testValidateEmail1() throws ValidationException{
        Validator validator = new Validator();
        validator.validateEmail("dorohovichandrey@gmail.com");
    }

    @Test(expected = ValidationException.class)
    public void testValidateEmail2() throws ValidationException{
        Validator validator = new Validator();
        validator.validateEmail("@gmail.com");
    }

    @Test
    public void testValidatePass1() throws ValidationException{
        Validator validator = new Validator();
        validator.validatePassword("1234Qwe");
    }

    @Test
    public void testValidatePass2() throws ValidationException{

        Validator validator = new Validator();
        validator.validatePassword("1234Qw");
    }

    @Test( expected = ValidationException.class)
    public void testValidatePass3() throws ValidationException{
        Validator validator = new Validator();
        validator.validatePassword("1234qwe");
    }

    @Test(expected = ValidationException.class)
    public void testValidatePass4() throws ValidationException{

        Validator validator = new Validator();
        validator.validatePassword("asdsdfgsQw");
    }

    @Test( expected = ValidationException.class)
    public void testValidatePass5() throws ValidationException{
        Validator validator = new Validator();
        validator.validatePassword("1Qe");
    }

    @Test(expected = ValidationException.class)
    public void testValidatePass6() throws ValidationException{

        Validator validator = new Validator();
        validator.validatePassword("asdsdasdffsdklfvdfvkjdfvlkvkldfdfvklmdklvmldfkmldfvkmkldfvmdfvkfgsQw");
    }

}
