<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Thêm mới mặt bằng</title>
</head>
<body>
<h1>Thêm mới mặt bằng</h1>

<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
<p style="color: red;"><%= error %></p>
<% } %>

<form action="spaces" method="post">
  <input type="hidden" name="action" value="add">
  Mã: <input type="text" name="id" required><br>
  Trạng thái: <input type="text" name="status" required><br>
  Diện tích: <input type="number" name="area" step="0.1" min="0" required><br>
  Tầng: <input type="number" name="floor" min="1" required><br>
  Loại: <input type="text" name="type" required><br>
  Giá: <input type="number" name="price" step="0.01" min="0" required><br>
  Ngày bắt đầu: <input type="date" name="start_date" required><br>
  Ngày kết thúc: <input type="date" name="end_date" required><br>
  <button type="submit">Thêm</button>
</form>
</body>
</html>
