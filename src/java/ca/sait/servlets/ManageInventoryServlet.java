package ca.sait.servlets;

import ca.sait.models.Category;
import ca.sait.models.Item;
import ca.sait.models.User;
import ca.sait.services.CategoryService;
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
public class ManageInventoryServlet extends HttpServlet {

    private List<Item> usersItems;
    private Item selectedItem;
    private User user;

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
        user = (User) request.getSession().getAttribute("user");
        String action = request.getParameter("action");
        ItemService is = new ItemService();
        CategoryService cs = new CategoryService();

        if (action != null && action.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("home");
            return;
        }

        if (action != null) {
            selectedItem = null;
            int selectedItemId = Integer.parseInt(request.getParameter("itemId"));

            for (int i = 0; i < usersItems.size(); i++) {
                if (usersItems.get(i).getItemId() == selectedItemId) {
                    selectedItem = usersItems.get(i);
                }
            }

            if (action.equals("edit")) {
                request.setAttribute("selectedItem", selectedItem);
            } else if (action.equals("delete")) {
                is.deleteItem(selectedItem);
            }
        }

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        usersItems = (List<Item>) is.getAll(user);
        List<Category> categoryList = (List<Category>) cs.getAll();
        request.setAttribute("usersItems", usersItems);
        request.setAttribute("categoryList", categoryList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/manageinventory.jsp").forward(request, response);
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
        ItemService is = new ItemService();
        CategoryService cs = new CategoryService();
        List<Category> categoryList = (List) cs.getAll();

        if (action != null) {
            String message = null;

            if (action.equals("update")) {

                String updatedItemName = request.getParameter("updatedItemName");
                double updatedItemPrice = Double.parseDouble(request.getParameter("updatedItemPrice"));
                String updatedItemCategory = request.getParameter("updatedItemCategory");

                Category newSelectedCategory = null;

                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getCategoryName().equals(updatedItemCategory)) {
                        newSelectedCategory = categoryList.get(i);
                    }
                }

                if (updatedItemName.length() > 0 && (updatedItemPrice + "").length() > 0) {
                    selectedItem.setItemName(updatedItemName);
                    selectedItem.setPrice(updatedItemPrice);
                    selectedItem.setCategory(newSelectedCategory);

                    if (!is.updateItem(selectedItem)) {
                        message = "An error occured when updating";
                    } else {
                        response.sendRedirect("manageinventory");
                        return;
                    }

                }

            } else if (action.equals("create")) {
                String newItemName = request.getParameter("newItemName");
                double newItemPrice = Double.parseDouble(request.getParameter("newItemPrice"));
                String newItemCategory = request.getParameter("newItemCategory");

                Category newSelectedCategory = null;

                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getCategoryName().equals(newItemCategory)) {
                        newSelectedCategory = categoryList.get(i);
                    }
                }

                if (newItemName.length() > 0 && (newItemPrice + "").length() > 0) {
                    Item newItem = new Item(newItemName, newItemPrice, newSelectedCategory, user);

                    if (!is.createItem(newItem)) {
                        message = "An error occured when creating";
                    } else {
                        response.sendRedirect("manageinventory");
                        return;
                    }
                }
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/manageinventory.jsp").forward(request, response);
        }
    }
}
