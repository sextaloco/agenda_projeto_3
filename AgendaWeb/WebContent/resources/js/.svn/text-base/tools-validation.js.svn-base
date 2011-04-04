jQuery(document).ready(function(){
	/* $("input[name='txtPass']").keyup(function(){
		switch ( passwordStrength($("input[name='txtPass']").val())) {
			case 'Péssima':
				$('.password-strength').css('border-color','#F00');
				break;
			case 'Ruim':
				$('.password-strength').css('border-color','#F28C02');
				break;
			case 'Bom':
				$('.password-strength').css('border-color','#C3F202');
				break;
			case 'Forte':
				$('.password-strength').css('border-color','#4FF202');
				break;
		}
		$('.password-strength').html(passwordStrength($("input[name='txtPass']").val()))
	}); */

	$("input[name='loginField']").keyup(function() {
		$(".preview").text("www.agendadahora.com.br/" + $(this).val());
	});
	
	
	$("form").submit(function() {		
		validaLogin($("input[name='loginField']").val());
		validaEmail($("input[name='emailField']").val());
		validaConfirm($("input[name='confSenhaField']").val());
		validaSenha($("input[name='senhaField']").val());
		//validaTermos($("input[value='i_agree']"));
		return false;
	});
	
	function validaEmail(email) {
		er = /^[a-zA-Z0-9][a-zA-Z0-9\._-]+@([a-zA-Z0-9\._-]+\.)[a-zA-Z-0-9]{2}/;
		if(er.exec(email)) {
			$("label[for='email'] span.error-message").text("");
			$("label[for='email']").removeClass("error");
			return true;
		} else {
			$("label[for='email'] span.error-message").text("Email inválido!");
			$("label[for='email']").addClass("error");
			return false;
		}
	}
	
	function validaConfirm(confirm) {
		if(confirm != $("input[name='pass']").val()) {
			$("label[for='password'] span.error-message").text("Senhas não conferem!");
			$("label[for='password']").addClass("error");
		} else {
			$("label[for='password'] span.error-message").text("");
			$("label[for='password']").removeClass("error");
		}
	}
	
	function validaSenha(senha) {
			if( senha.length < 6){
				$("label[for='password'] span.error-message").text("Senha inválida!");
				$("label[for='password']").addClass("error");
			} else {
				$("label[for='password'] span.error-message").text("");
				$("label[for='password']").removeClass("error");
			}

	}	
	
	function validaLogin(login) {
		if( login.length < 6 || login.length > 36){
			$("label[for='login'] span.error-message").text("Login inválido!");
			$("label[for='login']").addClass("error");
		} else {
			$("label[for='login'] span.error-message").text("");
			$("label[for='login']").removeClass("error");
		}
		
	}

});