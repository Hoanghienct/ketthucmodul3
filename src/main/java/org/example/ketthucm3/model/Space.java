package org.example.ketthucm3.model;

public class Space {

        private String id;
        private String status;
        private float area;
        private int floor;
        private String type;
        private float price;
        private String startDate;
        private String endDate;

        public Space(String id, String status, float area, int floor, String type, float price, String startDate, String endDate) {
            this.id = id;
            this.status = status;
            this.area = area;
            this.floor = floor;
            this.type = type;
            this.price = price;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        // Getter và Setter
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public float getArea() { return area; }
        public void setArea(float area) { this.area = area; }

        public int getFloor() { return floor; }
        public void setFloor(int floor) { this.floor = floor; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public float getPrice() { return price; }
        public void setPrice(float price) { this.price = price; }

        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }

        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }
    }


