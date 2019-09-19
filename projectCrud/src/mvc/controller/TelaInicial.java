package mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.Cadastros;
import mvc.model.DAO;

@Controller
public class TelaInicial {
	@RequestMapping("/")
	public String execute() {
		return "index";
	}

	@RequestMapping(value = "TelaInicial", method = RequestMethod.POST)
	public String form(Cadastros cadastro, HttpServletRequest request) throws IOException {
		String senhaIncor = "As senhas estão diferentes. Digite novamente.";
		String senhaNull = "Você deve digitar os campos em branco.";
		String newUser = "Esse nome de usuário já existe. Digite outro nome.";

		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String cad = cadastro.getLogin();
		String senha1 = cadastro.getSenha();
		String senha2 = cadastro.getSenhaCheck();


		if (!senha1.equals(null) && !senha2.equals(null) && !cad.equals("")) {
			if (!senha1.equals(senha2)) {
				request.setAttribute("senhaIncorr", senhaIncor);
			}
			else {
				int user = dao.adiciona(cadastro);
				if (user == 1) {
					System.out.println("USUÁRIO EXISTENTE");
					request.setAttribute("newUser", newUser);
				} else if (user == 0) {
					dao.close();
				}
			}
		}
		else {
			request.setAttribute("senhaNull", senhaNull);
		}
		return "index";
	}
}