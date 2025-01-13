<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Danh sách mặt bằng</title>
</head>
<body>
<h1>Danh sách mặt bằng</h1>
<table border="1">
  <tr>
    <th>Mã</th>
    <th>Trạng thái</th>
    <th>Diện tích</th>
    <th>Tầng</th>
    <th>Loại</th>
    <th>Giá</th>
    <th>Ngày bắt đầu</th>
    <th>Ngày kết thúc</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="space" items="${spaces}">
    <tr>
      <td>${space.id}</td>
      <td>${space.status}</td>
      <td>${space.area}</td>
      <td>${space.floor}</td>
      <td>${space.type}</td>
      <td>${space.price}</td>
      <td>${space.startDate}</td>
      <td>${space.endDate}</td>
      <td>
        <a href="spaces?action=delete&id=${space.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a href="spaces?action=add">Thêm mới</a>
</body>
</html>
