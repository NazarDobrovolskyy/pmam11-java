package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.User;
import dao.UserDao;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            // Display all users
            List<User> users = userDao.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/views/user-list.jsp").forward(request, response);
        } else {
            // Display a specific user by ID
            int userId = Integer.parseInt(pathInfo.substring(1));
            User user = userDao.getUserById(userId);

            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/views/user-details.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Create a new user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User newUser = new User(0, username, password);
        userDao.addUser(newUser);

        // Redirect to the user list page
        response.sendRedirect(request.getContextPath() + "/users");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Update an existing user
        int userId = Integer.parseInt(request.getPathInfo().substring(1));
        User existingUser = userDao.getUserById(userId);

        if (existingUser != null) {
            String newUsername = request.getParameter("username");
            String newPassword = request.getParameter("password");

            existingUser.setUsername(newUsername);
            existingUser.setPassword(newPassword);

            userDao.updateUser(existingUser);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Delete an existing user
        int userId = Integer.parseInt(request.getPathInfo().substring(1));
        User existingUser = userDao.getUserById(userId);

        if (existingUser != null) {
            userDao.deleteUser(userId);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void destroy() {
        // Clean up resources
    }
}