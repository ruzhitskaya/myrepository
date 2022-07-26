package by.test.newidea.controller;

import by.test.newidea.repository.UserRerository;
import by.test.newidea.configuration.DatabaseProperties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

//   public FrontController() {
//       super();
//   }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");
            req.setAttribute("user","Slava");

            UserRerository userRerository = new UserRerository(new DatabaseProperties());
            req.setAttribute("users", userRerository.findAll());

            dispatcher.forward(req, resp);
        }
    }
}
