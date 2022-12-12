package ca.sait.servlets;

import ca.sait.models.Role;
import ca.sait.models.User;
import ca.sait.services.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benamrc Agulto
 */
public class ManageAccountServlet extends HttpServlet {

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
        User user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");

        if (action != null && action.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("home");
            return;
        } else if (action != null && action.equals("deactivate")) {
            UserService us = new UserService();
            user.setActive(false);
            us.updateUser(user);

            request.getSession().invalidate();
            response.sendRedirect("home");
            return;
        }

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/manageaccount.jsp").forward(request, response);
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
        User user = (User) request.getSession().getAttribute("user");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordTwo = request.getParameter("retypeNewPassword");

        String message = null;

        if (newPassword != null && newPasswordTwo != null && !newPassword.equals(newPasswordTwo)) {
            message = "New passwords do not match, please try again";
        } else if (currentPassword != null && !currentPassword.equals(user.getPassword())) {
            message = "Current Password is incorrect";
        } else if (firstName.length() > 0 && lastName.length() > 0 && email.length() > 0) {
            UserService userService = new UserService();

            User updatedUser = new User(email, true, firstName, lastName, newPassword);
            updatedUser.setRole(new Role(2, "regular user"));

            if (userService.updateUserInfo(updatedUser, user)) {
                request.getSession().setAttribute("user", updatedUser);
                message = "Successful";
            } else {
                message = "Email already taken";
            }
        }

        request.setAttribute("message", message);

        this.getServletContext().getRequestDispatcher("/WEB-INF/manageaccount.jsp").forward(request, response);
    }
}
