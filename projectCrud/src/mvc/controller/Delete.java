package mvc.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.*;


@Controller
public class Delete {

	@RequestMapping(value = "Delete", method = RequestMethod.POST)
	public String form(Cadastros cadastro, HttpServletRequest request) throws IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		Cadastros user = (Cadastros) session.getAttribute("user");
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ShowTarefas i : dao.getTarefas(user.getLogin())) {
			if (Integer.valueOf(id) == i.getId()) {
				dao.delTarefa(Integer.valueOf(id));
			}
		}
		request.setAttribute("user", user);
		request.setAttribute("dados", dao.getTarefas(user.getLogin()));
		dao.close();
		return "home";
	}

}
