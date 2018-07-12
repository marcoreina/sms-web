package com.reina.marco.sms.web;

import com.reina.marco.sms.domain.Person;
import com.reina.marco.sms.service.PersonService;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/ServletController", "/ListPersons"})
public class ServletController extends HttpServlet {

    @EJB
    private PersonService personService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if(action != null && action.equals("edit")){
            //1: Retrieve the idPerson selected
            String idPersonString = req.getParameter("idPerson");
            int idPerson = 0;
            
            if(idPersonString != null){
                //2: Create person object to be retrieved
                idPerson = Integer.parseInt(idPersonString);
                Person person = new Person(idPerson);
                person = personService.findPersonById(person);
                
                //3: Sharing the person object in request
                req.setAttribute("person", person);
                
                //4: Redirecting the page to edit the person object
                req.getRequestDispatcher("editPerson.jsp").forward(req, resp);
            }
        }
        else {
            //The default action is to list persons
            this.listPersons(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if(action != null && action.equals("add")){
            //1: Retrieve parameters
            String name = req.getParameter("name");
            String fatherName = req.getParameter("fatherName");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            
            //2: Create the Person object
            Person person = new Person();
            person.setName(name);
            person.setFatherName(fatherName);
            person.setEmail(email);
            person.setPhone(phone);
            
            try {
                //3: Using service layer to persist a person
                personService.registerPerson(person);
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
            
            //4: List persons
            this.listPersons(req, resp);
        }
        else if(action != null && action.equals("modify")){
            //During modification, it's possible to modify or to delete depending what was selected
            String saveButton = req.getParameter("save");
            String deleteButton = req.getParameter("delete");
            
            if(saveButton != null){
                //1: Retrieve parameters
                String idPersonString = req.getParameter("idPerson");
                String name = req.getParameter("name");
                String fatherName = req.getParameter("fatherName");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                
                //2: Create the Person object
                Person person = new Person();
                int idPerson = Integer.parseInt(idPersonString);
                person.setIdPerson(idPerson);
                person.setName(name);
                person.setFatherName(fatherName);
                person.setEmail(email);
                person.setPhone(phone);
                
                try{
                    //3: Using service layer to modify the person
                    personService.modifyPerson(person);
                }catch(Exception e){
                    e.printStackTrace(System.out);
                }
                
                //4: List persons
                this.listPersons(req, resp);
            }
            else if(deleteButton != null){
                //1: Retrieve parameters
                String idPersonString = req.getParameter("idPerson");
                
                //2: Create the Person object
                int idPerson = Integer.parseInt(idPersonString);
                Person person = new Person(idPerson);
                
                try{
                    //3: Delete person
                    personService.deletePerson(person);
                }
                catch(Exception e){
                    e.printStackTrace(System.out);
                }
                
                //4: List persons
                this.listPersons(req, resp);
            }
        }
        else {
            //The default action is to list persons
            this.listPersons(req, resp);
        }
    }
    
    private void listPersons(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<Person> persons = personService.listPersons();
        req.setAttribute("persons", persons);
        req.getRequestDispatcher("listPersons.jsp").forward(req, resp);
    }
}
