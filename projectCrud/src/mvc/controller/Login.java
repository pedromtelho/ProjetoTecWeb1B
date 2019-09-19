package mvc.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mvc.model.*;


@Controller
public class Login {
	
	@RequestMapping(value = "Login", method = RequestMethod.POST)
	public String form(Cadastros cadastro, HttpServletRequest request) throws IOException {

		String user = cadastro.getLogin();
		String senha = cadastro.getSenha();
		String invalidLogin = "Verifique se digitou corretamente o nome de usuário ou senha.";
		System.out.println(user);
		System.out.println(senha);
		
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Cadastros id = dao.verifica(user, senha);
		if(id.getId() != 0 && id != null) {
			HttpSession session = request.getSession(true);
		    session.setAttribute("user",id);
		    request.setAttribute("dados", dao.getTarefas(id.getLogin()));
			dao.close();
			return "home";
		}
		else {
			request.setAttribute("name", invalidLogin);
			return "index";
		}
	}
}
