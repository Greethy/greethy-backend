# Gorse Recommender System Essential
Hệ thống gợi ý (Recommender System) là một dạng hệ thống hỗ trợ ra quyết định, cung cấp giải pháp mang tính cá nhân hóa. Hệ thống dựa vào các tương tác của người dùng đến các vật phẩm, để dự đoán
"sở thích" người dùng trong tương lai và thực hiện đề xuất các ngội dung tương tự. Để một hệ thống hoạt động tốt, điều tiên quyết là phụ thuộc vào sự tương tác của người dùng, chẳng hạn như đánh
giá sao, bình luận tích cực, số lần click chuột vào sản phẩm, thời gian quan sát sản phẩm, v.v. 

Gorse là một hệ thống đề xuất mã nguồn mở được viết bằng Go. Gorse đặt mục tiêu trở thành một hệ thống mã nguồn mở phổ biến có thể dễ dàng đưa vào nhiều dịch vụ trực tuyến. Bằng cách nhập các item,
user và các dữ liệu tương tác được đưa vào Gorse, hệ thống sẽ tự động đào tạo các model để tạo đề xuất cho từng người dùng. Các tính năng của hệ thống như sau:
- **Đa nguồn** (Multi-source): Các item được đề xuất từ 
- **Tự động ML** (AutoML): Tự động tìm kiếm mô hình đề xuất thích hợp hợp nhất trong Background.
- **Dự đoán phân phối** (Distributed Prediction): Hỗ trợ mở rộng quy mô trong giai đoạn đề xuất sau khi các node được training.
- **RESTful API**: Cung cấp các api cho việc CRUD dữ liệu và các request đề xuất.
- **Đánh giá trực tuyến** (Online Evaluation): Phân tích hiệu năng đề xuất trực tuyến từ các Feedback được cung cấp.
- **Bảng điều khiển** (Dashboard): Cung cấp GUI cho việc quản lý dữ liệu, giám sát hệ thống và kiểm tra trạng thái các cụm.
<img src="../assets/gorse_recommender_system_workflow.png" alt=""/>

## Các đối tượng dữ liệu (Data Objects)

### User
User là đối tượng người dùng, chứa 2 field chính:
- `UserId` (string): Mã định danh duy nhất của người dùng, không thể chứa ký tự "/" vì gây xung đột với định nghĩa URL của RESTful API.
- `Labels` ([]string): Nhãn được sử dụng để mô tả người dùng, có thể null, giúp cho việc cải thiện đề xuất.

### Item
Item là đối tượng sản phẩm, bao gồm 6 field chính:
- `ItemId` (string): Mã định danh cho sản phẩm, không thể chứa dấu "/" vì sẽ gây xung đột định nghĩa với các URL của RESTful API.
- `IsHidden` (bool): Xác định xem một item có đang bị ẩn đi hay không, nếu là true thì item đó sẽ không được đưa vào kết quả đề xuất.
- `Categories` ([]string): Các danh mục mà item đó thuộc về. Các mặt hàng được đề xuất dựa vào các danh mục này.
- `Timestamp` (time.Time): Mốc thời gian của item đó, được xác định độ tươi của item.
- `Labels` ([]string): Thông tin nhãn của item, được sử dụng để mô tả các đặc điểm của item đó cho hệ thống đề xuất.
- `Comment` (string): Thông tin chú thích của item, giúp duyệt qua các item và kết quả đề xuất trong Dashboard.

#### Ẩn item
Trong một số trường hợp, các item không có sẵn để đề xuất cho người dùng, chẳng hạn:
- Nếu item đó được bán hết trong ngữ cảnh của kinh doanh thì nó không nên được đề xuất cho người dùng.
- Nếu item đó có rủi ro về mặt pháp lý thì nó cũng nên được tiếp tục đề xuất cho người dùng.
Vậy nên trong Gorse, một item có thể được ẩn đi bằng cách đặt `IsHidden` là `true` thông qua RESTful API. Thuật toán đề xuất vẫn sẽ sử dụng item trong quá trình training, nhưng item sẽ 
không thể đề xuất cho người dùng.