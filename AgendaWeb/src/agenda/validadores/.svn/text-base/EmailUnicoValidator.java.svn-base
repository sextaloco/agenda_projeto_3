package agenda.validadores;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import agenda.ejb.EstabelecimentoEJBRemote;

@FacesValidator(value="emailUnicoValidator")
public class EmailUnicoValidator implements Validator {
	private EstabelecimentoEJBRemote estabelecimentoEJB;

	public EmailUnicoValidator() throws NamingException{
		InitialContext ctx = new InitialContext();
		estabelecimentoEJB = (EstabelecimentoEJBRemote) ctx.lookup("java:global/AgendaEAR/AgendaServer/EstabelecimentoEJB");
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
	throws ValidatorException {
		String email = value.toString();

		if(estabelecimentoEJB.verificarSeEmailExiste(email)){
			String mensagem = MessageFormat.format("{0} já está cadastrado no sistema", email);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
			throw new ValidatorException(facesMessage);
		}
	}

}
