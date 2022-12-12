
package ca.sait.servlets;

import ca.sait.models.Role;
import ca.sait.models.User;
import ca.sait.services.RoleService;
import ca.sait.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benmarc Agulto
 */
public class UserServlet extends HttpServlet {
    List<Role> roles;
    List<User> users;
    UserService us;

    
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
        RoleService rs = new RoleService();
        roles = rs.getAll();
        us = new UserService();
        users = us.getAll();

        if (users == null) {
            users = new Vector<User>();
        }

        request.getSession().setAttribute("users", users);
        request.getSession().setAttribute("roles", roles);

        String action = request.getParameter("action");

        if (action != null && action.equals("edit")) {
            request.getSession().setAttribute("message", null);
            String userEmail = request.getParameter("user").replaceAll("\\s+", "+");

            User user = null;

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(userEmail)) {
                    user = users.get(i);
                }
            }
            request.getSession().setAttribute("selectedUser", user);
        } else if (action != null && action.equals("delete")) {

            String userEmail = request.getParameter("user").replaceAll("\\s+", "+");
            User user = null;

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(userEmail)) {
                    user = users.get(i);
                }
            }

            us.deleteUser(user);

            response.sendRedirect("user");
            request.getSession().invalidate();
            return;
        } else if (action != null && action.equals("cancel")) {
            request.getSession().invalidate();
            response.sendRedirect("user");
            return;
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
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
        String action = request.getParameter("action");

        if (action.equals("addUser")) {
            boolean validInputs = true;
            
            String inputEmail = request.getParameter("inputEmail");
            String inputPassword = request.getParameter("inputPassword");
            String inputFirstName = request.getParameter("inputFirstName");
            String inputLastName = request.getParameter("inputLastName");
            String inputRole = request.getParameter("inputRole");
            String inputActive = request.getParameter("inputActive");


            boolean booleanInputActive = inputActive.equals("Yes");

            Role newRole = null;
            for (int i = 0; i < roles.size(); i++) {
                if (roles.get(i).getRoleName().equals(inputRole)) {
                    newRole = roles.get(i);
                }
            }

            boolean userAlreadyExists = false;

            for (User user : users) {
                if (inputEmail.equals(user.getEmail())) {
                    userAlreadyExists = true;
                }
            }

            String message = null;

            //email, password, firstName, lstName, Role, Active
            if (inputEmail.length() == 0) {
                validInputs = false;
                message = "Invalid email";
            } else if (inputPassword.length() == 0) {
                validInputs = false;
                message = "Invalid password";
            } else if (inputFirstName.length() == 0) {
                validInputs = false;
                message = "Invalid first name";
            } else if (inputLastName.length() == 0) {
                validInputs = false;
                message = "Invalid last name";
            } else if (newRole == null) {
                validInputs = false;
                message = "Selectrole";
            } else if (inputRole.equals("Select a Role")) {
                validInputs = false;
                message = "Role field not selected";
            } else if (inputActive.equals("Yes or No")) {
                validInputs = false;
                message = "Active field not selected";
            } else if (userAlreadyExists) {
                validInputs = false;
                message = "Email already taken";
            } else if (validInputs) {
                User user = new User(inputEmail, booleanInputActive, inputFirstName, inputLastName, inputPassword);
                user.setRole(newRole);
 
                Boolean created = us.createUser(user);

                if (created) {
                    message = "User Created";
                } else {
                    message = "Try again";
                }
            }

            request.getSession().setAttribute("message", message);
            response.sendRedirect("user");
            return;
        } else if (action.equals("update")) {
            String selectedEmail = request.getParameter("selectedEmail");
            User selectedUser = null;

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getEmail().equals(selectedEmail)) {
                    selectedUser = users.get(i);
                }
            }

            String selectedFirstName = request.getParameter("selectedFirstName");
            String selectedLastName = request.getParameter("selectedLastName");
            String selectedRole = request.getParameter("selectedRole");
            String selectedActive = request.getParameter("selectedActive");

            Role newRole = null;

            if (selectedRole.startsWith("Current:")) {
                newRole = selectedUser.getRole();
            } else {
                for (int i = 0; i < roles.size(); i++) {
                    if (roles.get(i).getRoleName().equals(selectedRole)) {
                        newRole = roles.get(i);
                    }
                }
            }

            boolean newActive = selectedActive.equals("Yes");

            if (selectedActive.startsWith("Current:")) {
                newActive = selectedUser.getActive();
            }
            selectedUser.setFirstName(selectedFirstName);
            selectedUser.setLastName(selectedLastName);
            selectedUser.setRole(newRole);
            selectedUser.setActive(newActive);

            us.updateUser(selectedUser);

            request.getSession().invalidate();
            response.sendRedirect("user");
            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}