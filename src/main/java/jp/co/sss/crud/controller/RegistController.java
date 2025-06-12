package jp.co.sss.crud.controller;



import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.RegistForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistController {
	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping(path = "/input", method = RequestMethod.GET)
	public String RegistInput() {
		return "/input";
	}
	
	@RequestMapping(path = "/check", method = RequestMethod.POST)
	public String RegistCheck(@ModelAttribute RegistForm registForm,Model model) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(registForm,employee,"id");
		employee = EmployeeRepository.save(employee);
		EmployeeBean employeeBean = new EmployeeBean();
		BeanUtils.copyProperties(employee,employeeBean);
		model.addAttribute("employee",employeeBean);
		
		String empPass = registForm.getEmpPass();
		String empName = registForm.getEmpName();
		Integer gender = registForm.getGender();
		String address = registForm.getAddress();
		Date birthday = registForm.getBirthday();
		Integer authority = registForm.getAuthority();
		String deptName = registForm.getDeptName();
		
		return "/check";
	}
	
}
