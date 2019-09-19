package mvc.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mvc.model.*;


@Controller
public class Update {
	
	@RequestMapping(value = "Update", method = RequestMethod.GET)
	public String form(Cadastros cadastro, HttpServletRequest request) throws IOException {
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("tarefas", dao.showTarefa(Integer.valueOf(request.getParameter("id"))));
		return "Update";
	}
	
	
	@RequestMapping(value = "Update", method = RequestMethod.POST)
	public String formTwo(Tarefas tarefas, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		Cadastros user = (Cadastros) session.getAttribute("user");
		DAO dao = null;
		try {
			dao = new DAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShowTarefas tarefa = new ShowTarefas();
		
		tarefa.setNome(request.getParameter("nome"));
		tarefa.setTarefa(request.getParameter("descTarefa"));
		dao.updateTarefa(tarefa, Integer.valueOf(request.getParameter("id")));
		request.setAttribute("user", user);
		request.setAttribute("dados", dao.getTarefas(user.getLogin()));
		dao.close();
		return "home";
	}
	
	
	
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		DAO dao = null;
//		try {
//			dao = new DAO();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		request.setAttribute("tarefas", dao.showTarefa(Integer.valueOf(request.getParameter("id"))));
//		RequestDispatcher rd = request.getRequestDispatcher("./Update.jsp");
//		rd.forward(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		HttpSession session = request.getSession();
//		Cadastros user = (Cadastros) session.getAttribute("user");
//		DAO dao = null;
//		try {
//			dao = new DAO();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ShowTarefas tarefa = new ShowTarefas();
//		
//		tarefa.setNome(request.getParameter("nome"));
//		tarefa.setTarefa(request.getParameter("desc"));
//		dao.updateTarefa(tarefa, Integer.valueOf(request.getParameter("id")));
//		request.setAttribute("user", user);
//		request.setAttribute("dados", dao.getTarefas(user.getLogin()));
//		RequestDispatcher rd = request.getRequestDispatcher("./home.jsp");
//		rd.forward(request, response);
//		dao.close();
//	}

}
