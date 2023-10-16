package com.siddharth.OnlineWebIDE;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(IndexServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to index.jsp for displaying the page.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the action parameter from the request
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    // Get the numbers from the request
                    int num1 = Integer.parseInt(request.getParameter("num1"));
                    int num2 = Integer.parseInt(request.getParameter("num2"));

                    // Calculate the sum
                    int sum = num1 + num2;

                    // Send the sum as a response
                    response.setContentType("text/plain");
                    response.getWriter().write(String.valueOf(sum));
                    break;
                // You can add more cases for other actions here
                default:
                    // Handle invalid actions here.
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("Invalid action");
                    break;
            }
        }
    }
}
