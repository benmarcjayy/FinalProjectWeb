package ca.sait.servlets;

import ca.sait.models.Category;
import ca.sait.services.CategoryService;
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
public class CategoryServlet extends HttpServlet {

    private Category selectedCategory;

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
        CategoryService categoryService = new CategoryService();
        String action = request.getParameter("action");

        List<Category> categoryList = (List<Category>) categoryService.getAll();

        if (action != null && action.equals("editCategory")) {
            String categoryID = request.getParameter("categoryID");

            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getCategoryId() == Integer.parseInt(categoryID)) {
                    selectedCategory = categoryList.get(i);
                }
            }
            request.setAttribute("selectedCategory", selectedCategory);
        }

        if (action != null && action.equals("editCategory")) {
            selectedCategory = null;
        }

        request.setAttribute("categoryList", categoryList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
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
        CategoryService cs = new CategoryService();

        if (action.equals("addCategory")) {
            String newCategoryName = request.getParameter("newCategoryName");

            cs.addCategory(newCategoryName);
        } else if (action.equals("updateCategory")) {
            String selectedCategoryName = request.getParameter("selectedCategoryName");

            selectedCategory.setCategoryName(selectedCategoryName);

            cs.updateCategory(selectedCategory);
        }
        response.sendRedirect("category");
    }
}
