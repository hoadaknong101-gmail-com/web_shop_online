# Business Website
## _Website bán quần áo online_

[![Spring](https://www.vectorlogo.zone/logos/springio/springio-ar21.png)](https://spring.io/projects/spring-framework)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://spring.io/projects/spring-framework)

SPK01 SHOP là một website bán quần áo online do đơn vị SPK đầu tư,
Danh sách thành viên thực hiện.
- Phạm Đinh Quốc Hòa
- Võ Nhật Phi
- Nguyễn Duy Dương
- Nguyễn Đức Mạnh
- Nguyễn Quang Sang

## Chức năng

- Quản lý đơn hàng
- Quản lý sản phẩm
- Quản lý người dùng
- Quản lý phản hồi về sản phẩm
- Đặt hàng online
- Thống kê doanh thu 7 ngày gần nhất
- 
## Công nghệ sử dụng

- [Spring Framework] - Spring boot
- [MySQL] - Hệ quản trị cơ sở dữ liệu
- [Bootstrap] - CSS framework
- [Thymeleaf] - công cụ hỗ trợ xây dựng page html trong spring framework
- [jQuery] - JavaScript framework


## Hướng dẫn sử dụng

- Sử dụng Inteliji IDEA
- Cài đặt hệ quản trị cơ sở dữ liệu MySQL
- Sau đó tạo schema tên là "web_shop_online"
- Sau đó tiến hành chỉnh sửa file **application.properties** trong project.
```sh
spring.datasource.url=jdbc:mysql://localhost:3306/web_shop_online?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
spring.datasource.username=root
spring.datasource.password=Nhien@02122019
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=phamdinhquochoa101@gmail.com
spring.mail.password=vwksoinwgrevpknf
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
auto-ddl=create-drop
```
- Sau đó tiến hành chạy chương trình và trải nghiệm


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>
   [Bootstrap]: <https://getbootstrap.com/>
   [MySQL]: <https://www.mysql.com/>
   [Spring Framework]: <https://spring.io/projects/spring-framework>
   [Thymeleaf]: <https://thymeleaf.org>
   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
