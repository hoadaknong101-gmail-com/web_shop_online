package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Role;
import com.hoadaknong.web_shop_online.services.AuthenticationService;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.RoleService;
import com.hoadaknong.web_shop_online.services.SendMailService;
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
import java.util.Date;
import java.util.List;

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


    @RequestMapping(value="/sign_up")
    public String signUp(Customer c, Model model, RedirectAttributes attributes){
        Customer existCustomer = customerService.getCustomerByEmail(c.getEmail());
        if(existCustomer != null){
            Role roleDefault = roleService.getRoleById(3);
            c.setRole(roleDefault);
            customerService.saveCustomer(c);
            return "redirect:/web_shop/admin/products";
        }
        attributes.addFlashAttribute("message","Email đã được sử dụng");
        return "redirect:/sign_in_sign_up";
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
            session.setAttribute("user",c);
            session.setAttribute("userId",c.getId());
            session.setAttribute("roleId",c.getRole().getId());

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

        return "redirect:/index";
    }

    @RequestMapping(value="/web_shop/admin/customers/details/{id}")
    public String customerDetails(@PathVariable Integer id,
                                  Model model){
        Customer c = customerService.getCustomerById(id);
        List<Role> listRole = roleService.findAllRole();
        model.addAttribute("customer",c);
        model.addAttribute("listRole",listRole);

        return "admin_customer_form";
    }

    @RequestMapping(value="/web_shop/admin/customers/update")
    public String updateRole(@RequestParam("id") Integer cId,
                             @RequestParam("role") Integer rId,
                             RedirectAttributes attributes){
        Customer c = customerService.getCustomerById(cId);
        Role r = roleService.getRoleById(rId);

        c.setRole(r);
        customerService.saveCustomer(c);

        attributes.addFlashAttribute("message","Cập  nhật quyền người dùng thành công!");

        return "redirect:/web_shop/admin/customers";
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
}
