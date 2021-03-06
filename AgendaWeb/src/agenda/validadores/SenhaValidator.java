package agenda.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="senhaValidator")
public class SenhaValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		String senhaId = (String) component.getAttributes().get("senhaId");
		
		UIInput senhaInput = (UIInput) context.getViewRoot().findComponent(senhaId);
		
		String senha = "";
		
		if(senhaInput.getValue()!=null)
			senha = (String) senhaInput.getValue();
		
		String confirmacao = (String) value;
		
		if(!senha.equals(confirmacao)){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"senhas n�o conferem","senhas n�o conferem"));
		}		
	}

}
