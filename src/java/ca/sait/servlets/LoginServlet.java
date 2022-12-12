
package ca.sait.servlets;

import ca.sait.models.User;
import ca.sait.services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benmarc Agulto
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");

        if (user != null && user.getActive()) {
            request.getSession().setAttribute("user", user);

            if (user.getRole().getRoleId() == 2) {
                response.sendRedirect("home");
                return;
            } else {
                response.sendRedirect("user");
                return;
            }
        } else if (action != null && action.equals("register")) {
            response.sendRedirect("register");
            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        UserService us = new UserService();

        User user = us.getUser(userEmail, userPassword);

        if (user != null && user.getActive()) {
            request.getSession().setAttribute("user", user);
            String role = null;
            
            if (user.getRole().getRoleId() == 2) {
                role = "regular user";
            } else {
                role = "admin";
            }
            
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("role", role);

            if (user.getRole().getRoleId() == 2) {
                response.sendRedirect("home");
                return;
            } else {
                response.sendRedirect("user");
                return;
            }
        }

        String message;

        if (user != null && !user.getActive()) {
            message = "Account not active, contact admin to reactivate";
        } else {
            message = "Incorrect Email/Password.";
        }

        request.setAttribute("message", message);

        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
}
