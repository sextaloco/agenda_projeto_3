package agenda.validadores.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {

	private Pattern pattern =
		Pattern.compile(".+@.+\\.[a-z]+");

	@Override
	public void initialize(Email constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value,
			ConstraintValidatorContext context) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
