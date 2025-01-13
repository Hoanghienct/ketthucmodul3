package org.example.ketthucm3.controller;


import org.example.ketthucm3.model.Space;
import org.example.ketthucm3.model.SpaceDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/spaces")
public class SpaceController extends HttpServlet {
    private SpaceDAO spaceDAO;

    @Override
    public void init() {
        spaceDAO = new SpaceDAO(); // Khởi tạo DAO
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listSpaces(request, response); // Hiển thị danh sách mặc định
            } else if (action.equals("add")) {
                showAddSpaceForm(request, response); // Điều hướng đến form thêm
            } else if (action.equals("delete")) {
                deleteSpace(request, response); // Xóa mặt bằng
            } else {
                listSpaces(request, response); // Mặc định hiển thị danh sách
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }


    private void showAddSpaceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/add-space.jsp");
        dispatcher.forward(request, response); // Điều hướng nội bộ đến JSP
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                addSpace(request, response);

            }
        } catch (SQLException e) {
            throw new ServletException(e);

        }
    }
    private void listSpaces(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Space> spaces = spaceDAO.getAllSpaces();  // Lấy danh sách từ DAO, đã sắp xếp theo diện tích
        request.setAttribute("spaces", spaces);  // Gắn danh sách vào request
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/space-list.jsp");
        dispatcher.forward(request, response);  // Chuyển đến trang danh sách
    }

    private void addSpace(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        float area = Float.parseFloat(request.getParameter("area"));
        int floor = Integer.parseInt(request.getParameter("floor"));
        String type = request.getParameter("type");
        float price = Float.parseFloat(request.getParameter("price"));
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");

        // Kiểm tra các trường `status` và `type`
        if (status == null || status.isEmpty() || type == null || type.isEmpty()) {
            request.setAttribute("error", "Trạng thái và loại mặt bằng không được để trống.");
            request.getRequestDispatcher("/WEB-INF/view/add-space.jsp").forward(request, response);
            return;
        }

        // Kiểm tra độ dài các trường `status` và `type` (Giả sử độ dài tối đa là 50 ký tự)
        if (status.length() > 50 || type.length() > 50) {
            request.setAttribute("error", "Trạng thái và loại mặt bằng không được quá 50 ký tự.");
            request.getRequestDispatcher("/WEB-INF/view/add-space.jsp").forward(request, response);
            return;
        }

        // In các giá trị để kiểm tra
        System.out.println("Price: " + price);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);

        // Thực hiện kiểm tra các giá trị theo yêu cầu của cơ sở dữ liệu
        if (spaceDAO.isIdExists(id)) {
            response.sendRedirect("spaces");
        } else {
            Space space = new Space(id, status, area, floor, type, price, startDate, endDate);
            spaceDAO.addSpace(space);
            response.sendRedirect("spaces");
        }
    }



    private void deleteSpace(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String id = request.getParameter("id");
        spaceDAO.deleteSpace(id);
        response.sendRedirect("spaces"); // Quay lại danh sách
    }
}


