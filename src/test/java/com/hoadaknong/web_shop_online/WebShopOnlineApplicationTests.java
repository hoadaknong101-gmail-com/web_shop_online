package com.hoadaknong.web_shop_online;

import com.hoadaknong.web_shop_online.entities.*;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;
import com.hoadaknong.web_shop_online.repositories.*;
import com.hoadaknong.web_shop_online.services.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class WebShopOnlineApplicationTests {
	@Autowired
	ProductRepository repo;

	@Autowired
	UploadFileService uploadFileService;

	@Autowired
	SendMailService mailService;

	@Autowired
	CustomerService cusService;

	@Autowired
	OrderService orderService;


	@Autowired
	OrderRepository orderRepository;

	@Autowired
	StatsService statsService;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	CustomerRepository rp;

	@Autowired
	ProductCategoryRepository categoryRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	FeedbackService feedbackService;

	@Autowired
	ProductService productService;

	@Autowired
	RoleRepository roleRepository;

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	void addData() {
		//Thêm loại sản phẩm
		ProductCategory cat1 = new ProductCategory("Áo thun",new Date());
		ProductCategory cat2 = new ProductCategory("Quần Short",new Date());
		ProductCategory cat3 = new ProductCategory("Quần Thun",new Date());
		ProductCategory cat4 = new ProductCategory("Quần Jean",new Date());
		ProductCategory cat5 = new ProductCategory("Dành cho nam",new Date());
		ProductCategory cat6 = new ProductCategory("Dành cho nữ",new Date());
		ProductCategory cat7 = new ProductCategory("Dành cho trẻ",new Date());
		ProductCategory cat8 = new ProductCategory("Phụ kiện thời trang",new Date());
		productService.saveCategory(cat1);
		productService.saveCategory(cat2);
		productService.saveCategory(cat3);
		productService.saveCategory(cat4);
		productService.saveCategory(cat5);
		productService.saveCategory(cat6);
		productService.saveCategory(cat7);
		productService.saveCategory(cat8);

		//Thêm thương hiệu sản phẩm
		ProductBrand brand1 = new ProductBrand("Dirty Coins","Local Brand Việt Nam");
		ProductBrand brand2 = new ProductBrand("Yame","Local Brand Việt Nam");
		ProductBrand brand3 = new ProductBrand("SLY","Local Brand Việt Nam");
		ProductBrand brand4 = new ProductBrand("Nirvana","Local Brand Việt Nam");
		ProductBrand brand5 = new ProductBrand("GODSON","Local Brand Việt Nam");
		ProductBrand brand6 = new ProductBrand("Ssstutter","Local Brand Việt Nam");
		ProductBrand brand7 = new ProductBrand("ONTOP","Local Brand Việt Nam");
		ProductBrand brand8 = new ProductBrand("TSUN","Local Brand Việt Nam");
		ProductBrand brand9 = new ProductBrand("NOWSAIGON","Local Brand Việt Nam");
		ProductBrand brand10 = new ProductBrand("ClownZ","Local Brand Việt Nam");
		ProductBrand brand11 = new ProductBrand("DEGREY","Local Brand Việt Nam");
		ProductBrand brand12 = new ProductBrand("T-REDX","Local Brand Việt Nam");
		ProductBrand brand13 = new ProductBrand("No brand","Không thương hiệu");
		productService.saveBrand(brand1);
		productService.saveBrand(brand2);
		productService.saveBrand(brand3);
		productService.saveBrand(brand4);
		productService.saveBrand(brand5);
		productService.saveBrand(brand6);
		productService.saveBrand(brand7);
		productService.saveBrand(brand8);
		productService.saveBrand(brand9);
		productService.saveBrand(brand10);
		productService.saveBrand(brand11);
		productService.saveBrand(brand12);
		productService.saveBrand(brand13);

		//Thêm sản phẩm
		productService.saveProduct(new Product("Áo thun đen",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"c6b799db947547f59d8b47282e567e1b.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));

		productService.saveProduct(new Product("Áo thun thời trang",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"d0064e335aef476f96834fd3e68cf05a.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));

		productService.saveProduct(new Product("Áo thun mùa hè",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"9820c67d0d6a45c6a21befd923e51aed.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo thun Adidas",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"0a88831727e04a66a410db2dac09cecc.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo thun POLO",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"3fe21eede89d49c68aabf6b0cbf44905.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo Thun",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"3b0fb348fed545609d78618c869edf4d.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo thun mùa hè",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"0991a56747644308b0b399d1437a6c82.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo Phông",
				130000.0,
				180000.0,
				cat1,
				brand1,
				"d1db7197684a4e93938a6b8b51370ac4.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo Thun Adidas",
				130000.0,
				180000.0,
				cat5,
				brand5,
				"2acf533024d645b590a46c02f37903b5.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Quần Jean Nam",
				130000.0,
				180000.0,
				cat5,
				brand1,
				"00f4442f6a544d8f8618b8f43b15eb49.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Vest Nam",
				130000.0,
				180000.0,
				cat5,
				brand1,
				"c4cfe85027034980a414c03b7be1b2a3.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo trễ vai",
				130000.0,
				180000.0,
				cat6,
				brand1,
				"68a97b43bc104f8bb503a47b8cbeb4b9.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo trễ vai ",
				130000.0,
				180000.0,
				cat6,
				brand1,
				"8b5d88b032df4ceebf77f77bcb27dcf0.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo trễ vai ",
				130000.0,
				180000.0,
				cat6,
				brand1,
				"c6b799db947547f59d8b47282e567e1b.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Áo trễ vai ",
				130000.0,
				180000.0,
				cat6,
				brand1,
				"c6b799db947547f59d8b47282e567e1b.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));

		productService.saveProduct(new Product("Set đồ short ",
				130000.0,
				180000.0,
				cat7,
				brand1,
				"02a132b69623472fbd05cc42e3344355.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));
		productService.saveProduct(new Product("Set đồ short ",
				130000.0,
				180000.0,
				cat7,
				brand1,
				"de8d5b617af741ce9c9c541c31af964d.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));

		productService.saveProduct(new Product("Set đồ short cho trẻ ",
				130000.0,
				180000.0,
				cat7,
				brand1,
				"334d58a6b2c2470ebc850b07373cfc80.jpg",
				5.0,new Date(),
				"Áo thun tay lỡ unisex Nhật Bản đẹp độc lạ vải dày mịn TEEVN1613.Chất liệu thun mềm mại, thoáng mát, thấm hút tốt, không lo hầm bí khi mặc. Thiết kế cổ tròn, tay ngắn mang lại cho phái mạnh phong cách nam tính và lịch lãm khi mặc hàng ngày.Áo thun nam đang là một xu hướng được giới trẻ săn đón."));


		//Thêm quyền
		Role admin = new Role("Admin", "Vai trò Quản trị viên", new Date());
		Role staff = new Role("Nhân viên", "Vai trò nhân viên", new Date());
		Role customer = new Role("Khách hàng", "Vai trò khách hàng", new Date());
		roleRepository.save(admin);
		roleRepository.save(staff);
		roleRepository.save(customer);

		//Thêm người dùng
		cusService.saveCustomer(new Customer("Phạm Đinh Quốc", "Hòa","Sinh viên", "hoadaknong101@gmail.com","0388891635","123",admin,new Date()));
		cusService.saveCustomer(new Customer("Nguyễn Duy", "Dương","Sinh viên", "duyduong@gmail.com","0388891635","123",staff,new Date()));
		cusService.saveCustomer(new Customer("Nguyễn Đức", "Mạnh","Sinh viên", "ducmanh@gmail.com","0388891635","123",staff,new Date()));
		cusService.saveCustomer(new Customer("Nguyễn Quang", "Sang","Sinh viên", "quangxang@gmail.com","0388891635","123",customer,new Date()));
		cusService.saveCustomer(new Customer("Võ Nhật", "Phi","Sinh viên", "phivo@gmail.com","0388891635","123",customer,new Date()));
	}

}
