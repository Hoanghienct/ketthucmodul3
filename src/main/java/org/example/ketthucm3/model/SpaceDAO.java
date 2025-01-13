package org.example.ketthucm3.model;

import org.example.ketthucm3.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SpaceDAO {

        private Connection connection;

        public SpaceDAO() {
            connection = DBConnection.getConnection();
        }

    public List<Space> getAllSpaces() throws SQLException {
        List<Space> spaces = new ArrayList<>();
        String sql = "SELECT * FROM spaces ORDER BY area ASC";  // Sắp xếp theo diện tích tăng dần
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            Space space = new Space(
                    rs.getString("id"),
                    rs.getString("status"),
                    rs.getFloat("area"),
                    rs.getInt("floor"),
                    rs.getString("type"),
                    rs.getFloat("price"),
                    rs.getString("start_date"),
                    rs.getString("end_date")
            );
            spaces.add(space);
        }

        return spaces;
    }


    public boolean addSpace(Space space) throws SQLException {
        String sql = "INSERT INTO spaces (id, status, area, floor, type, price, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Cắt chuỗi type nếu nó vượt quá 50 ký tự
            String type = space.getType();
            if (type.length() > 50) {
                type = type.substring(0, 50);
            }

            ps.setString(1, space.getId());
            ps.setString(2, space.getStatus());
            ps.setFloat(3, space.getArea());
            ps.setInt(4, space.getFloor());
            ps.setString(5, type);  // Đảm bảo type có độ dài hợp lệ
            ps.setFloat(6, space.getPrice());
            ps.setString(7, space.getStartDate());
            ps.setString(8, space.getEndDate());

            return ps.executeUpdate() > 0;
        }
    }



    public boolean deleteSpace(String id) throws SQLException {
            String sql = "DELETE FROM spaces WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);

            return ps.executeUpdate() > 0;
        }

    public boolean isIdExists(String id) throws SQLException {
        String query = "SELECT COUNT(*) FROM spaces WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
    public List<Space> getSpacesByType(String type) throws SQLException {
        List<Space> spaces = new ArrayList<>();
        String sql = "SELECT * FROM spaces WHERE type LIKE ? ORDER BY area ASC";  // Thêm điều kiện tìm kiếm theo loại và sắp xếp theo diện tích
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + type + "%");  // Dùng LIKE để tìm kiếm theo loại mặt bằng
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Space space = new Space(
                        rs.getString("id"),
                        rs.getString("status"),
                        rs.getFloat("area"),
                        rs.getInt("floor"),
                        rs.getString("type"),
                        rs.getFloat("price"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                );
                spaces.add(space);
            }
        }
        return spaces;
    }
    }


