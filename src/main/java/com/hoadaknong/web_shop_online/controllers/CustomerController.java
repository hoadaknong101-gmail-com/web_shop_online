package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Address;
import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.CustomerAddress;
import com.hoadaknong.web_shop_online.entities.Role;
import com.hoadaknong.web_shop_online.services.*;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    SendMailService mailService;

    @Autowired
    OrderService orderService;

    @Autowired
    StatsService statsService;

    @Autowired
    AddressService addressService;


    Integer page = 4;

    NumberFormat formatter = new DecimalFormat("#0,000");

    @RequestMapping(value="/sign_up")
    public String signUp(Customer c, Model model, RedirectAttributes attributes){
        Customer existCustomer = customerService.getCustomerByEmail(c.getEmail());
        if(existCustomer == null){
            Role roleDefault = roleService.getRoleById(3);
            c.setRole(roleDefault);
            customerService.saveCustomer(c);
            attributes.addFlashAttribute("messageSuccess","Đăng ký tài khoản thành công");
            return "redirect:/sign_in_sign_up";
        } else {
            attributes.addFlashAttribute("message","Email đã được sử dụng");
            return "redirect:/sign_in_sign_up";
        }
    }
    @RequestMapping(value="/sign_in")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         RedirectAttributes attributes,
                         HttpServletRequest request){
        Boolean flag = authenticationService.isRightInformation(email, password);
        if(flag){
            attributes.addFlashAttribute("message","Đăng nhập thành công!");
            // Lấy ra tài khoản và cập nhật thời gian truy cập
            Customer c = customerService.getCustomerByEmailAndPassword(email,password);
            c.setModifiedDate(new Date());
            customerService.saveCustomer(c);
            HttpSession session = request.getSession();
            double profitValue = statsService.getProfitUpToNow();
            session.setAttribute("user",c);
            session.setAttribute("userId",c.getId());
            session.setAttribute("fullNameUser", c.getFirstName() +" "+ c.getLastName());
            session.setAttribute("roleId",c.getRole().getId());
            session.setAttribute("profitValue", String.format("%,.0f", profitValue));
            return "redirect:/web_shop/admin/";
        } else {
            attributes.addFlashAttribute("message","Sai thông tin tài khoản hoặc mật khẩu");
            return "redirect:/sign_in_sign_up";
        }
    }

    @RequestMapping(value = "/sign_out")
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("userId");
        session.removeAttribute("roleId");

        return "redirect:/index";
    }

    @RequestMapping(value="/web_shop/admin/customers/details/{id}")
    public String customerDetails(@PathVariable Integer id,
                                  Model model){
        Customer c = customerService.getCustomerById(id);
        List<Role> listRole = roleService.findAllRole();
        List<CustomerAddress> listAddressCustomer = addressService.findAllAddressByCustomerId(c);
        Address address = new Address();
        model.addAttribute("customer",c);
        model.addAttribute("listRole",listRole);
        model.addAttribute("page",page);
        model.addAttribute("listAddressCustomer",listAddressCustomer);
        model.addAttribute("address",address);
        return "admin_page/admin_customer_form";
    }

    @RequestMapping(value="/web_shop/admin/customers/update")
    public String updateRole(@RequestParam("id") Integer cId,
                             @NonNull @RequestParam("role") Integer rId,
                             RedirectAttributes attributes,
                             Customer customer,
                             HttpServletRequest request){
        HttpSession session = request.getSession();
        int roleId = (int)session.getAttribute("roleId");
        int userId = (int)session.getAttribute("userId");
        if(rId != null){
            if(roleId == 1){
                Role r = roleService.getRoleById(rId);
                customer.setRole(r);
                customerService.saveCustomer(customer);
                attributes.addFlashAttribute("message","Cập  nhật quyền người dùng thành công!");
                return "redirect:/web_shop/admin/customers";
            } else {
                attributes.addFlashAttribute("message","Bạn không thể thay đổi thông tin");
                return "redirect:/web_shop/admin/customers/details/" + cId;
            }
        }else{
            attributes.addFlashAttribute("message","Bạn chưa chọn quyền cho người dùng");
            return "redirect:/web_shop/admin/customers/details/" + cId;
        }
    }

    @RequestMapping(value="/forgot_password")
    public String getPassword(@RequestParam("email") String email,
                              RedirectAttributes attributes,
                              HttpServletRequest request){
        Customer customer = customerService.getCustomerByEmail(email);
        if(customer != null){
            System.out.println(customer.toString());
            //Send Mail to reset password
            String mailTo = customer.getEmail();
            String subject ="Reset password, " +
                    "Hello " + customer.getFirstName() + " " +
                    customer.getLastName();
            String linkReset = request.getContextPath().toString() +
                    "/reset_password/" +
                    customer.getEmail();
            String body = "Here is your link to reset your password: \n" + linkReset;
            mailService.sendMail(mailTo,subject,body,"Reset password");
            return "redirect:/check_mail";
        }else{
            System.out.println("Account not found");
            attributes.addFlashAttribute("message","Account not found!");
            return "redirect:/input_forgot_password";
        }
    }

    @RequestMapping(value ="/reset_password/{gmail}")
    public String resetPassword(@PathVariable String gmail, Model model){
        Customer c = customerService.getCustomerByEmail(gmail);
        model.addAttribute("customer",c);

        return "reset_password";
    }

    @RequestMapping(value="/save_password")
    public String savePassword(@RequestParam("id") Integer id,
                               @RequestParam("password") String password){
        Customer c = customerService.getCustomerById(id);
        c.setPassword(password);
        customerService.saveCustomer(c);
        return "redirect:/sign_in_sign_up";
    }

    @RequestMapping(value ="/customer/{id}")
    public String customerPage(@PathVariable("id") Integer id,
                               Model model,
                               HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer currentUserId = (Integer) session.getAttribute("userId");
        Customer customer = customerService.getCustomerById(id);
        if(currentUserId != customer.getId()){
            return "redirect:/index";
        }else{
            model.addAttribute("customer", customer);
            return "client_page/customer";
        }
    }

    @RequestMapping(value="/web_shop/admin/address/delete/{aId}/customer/{cId}")
    public String deleteAddress(@PathVariable Integer aId,
                                @PathVariable Integer cId,
                                RedirectAttributes attributes){

        Customer c = customerService.getCustomerById(cId);
        Optional<Address> getAddress = addressService.findAddressById(aId);
        Address a = getAddress.get();
        addressService.deleteCustomerAddress(a,c);

        attributes.addFlashAttribute("message", "Xóa địa chỉ thành công!");

        return "redirect:/web_shop/admin/customers/details/" + cId;
    }

    @RequestMapping(value="/web_shop/admin/address/insert/{addressLine}/{district}/{province}/{postalCode}/{typeAddress}/{customerId}")
    public String insertAddress(@PathVariable String addressLine,
                                @PathVariable String district,
                                @PathVariable String province,
                                @PathVariable String postalCode,
                                @PathVariable String typeAddress,
                                @PathVariable Integer customerId){
        Address address = new Address();
        address.setAddressLine(addressLine);
        address.setDistrict(district);
        address.setProvince(province);
        address.setPostalCode(postalCode);
        address.setModifiedDate(new Date());
        addressService.saveAddress(address);

        Customer customer = customerService.getCustomerById(customerId);
        CustomerAddress customerAddress = new CustomerAddress(customer,address,typeAddress,new Date());

        addressService.saveAddressOfCustomer(customerAddress);

        return "redirect:/web_shop/admin/customers/details/" + customerId;
    }
}
