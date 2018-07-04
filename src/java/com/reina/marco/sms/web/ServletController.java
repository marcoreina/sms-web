package com.reina.marco.sms.web;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.service.PersonService;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListPersons")
public class ServletController extends HttpServlet {

    @Inject
    private PersonService personService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> persons = personService.listPersons();
        req.setAttribute("persons", persons);
        req.getRequestDispatcher("listPersons.jsp").forward(req, resp);
    }
}
