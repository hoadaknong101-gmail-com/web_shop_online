package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Role;
import com.hoadaknong.web_shop_online.services.AuthenticationService;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value="/sign_up")
    public String signUp(Customer c, Model model){
        Role roleDefault = roleService.getRoleById(3);
        c.setRole(roleDefault);
        customerService.saveCustomer(c);

        return "redirect:/web_shop/admin/products";
    }

    @RequestMapping(value="/web_shop/admin/customers/details/{id}")
    public String customerDetails(@PathVariable Integer id, Model model){
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

    @RequestMapping(value="/sign_in")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         RedirectAttributes attributes){
        Boolean flag = authenticationService.isRightInformation(email, password);
        if(flag){
            attributes.addFlashAttribute("message","Đăng nhập thành công!");
            // Lấy ra tài khoản và cập nhật thời gian truy cập
            Customer c = customerService.getCustomerByEmailAndPassword(email,password);
            c.setModifiedDate(new Date());
            customerService.saveCustomer(c);

            return "redirect:/web_shop/admin/products";
        } else {
            attributes.addFlashAttribute("message","Sai thông tin tài khoản hoặc mật khẩu");
            return "redirect:/sign_in_sign_up";
        }
    }

}
