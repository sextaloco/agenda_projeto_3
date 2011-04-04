package agenda.validadores.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<Login, String> {

	private Pattern pattern =
		Pattern.compile("[A-Za-z0-9]{1,}");

	@Override
	public void initialize(Login constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value,
			ConstraintValidatorContext context) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
