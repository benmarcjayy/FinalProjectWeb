package ca.sait.servlets;

import ca.sait.models.Item;
import ca.sait.models.User;
import ca.sait.services.ItemService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Benmarc Agulto
 */
public class HomeServlet extends HttpServlet {

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
        ItemService is = new ItemService();

        if (action != null && action.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("home");
            return;
        }

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        List<Item> usersItems = is.getAll(user);
        request.setAttribute("usersItems", usersItems);

        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }
}
