package com.contact.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.aspectj.bridge.Message;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.contact.entity.Contact;
import com.contact.entity.MyOrder;
import com.contact.entity.User;
import com.contact.helpr.Messege;
import com.contact.repository.MyOrderRepository;
import com.contact.repository.UserRepository;
import com.contact.service.ContactService;
import com.contact.service.UserService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
@RequestMapping("/contact")
@MultipartConfig
public class ContactController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptpassword;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private MyOrderRepository myOrderRepository;
	@ModelAttribute
	public void addCommonData(Principal principal,Model model)
	{
		String username=principal.getName();
		User user=userRepository.getUserByEmail(username);
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/contacts")
	public String addContact(Model model)
	{
		model.addAttribute("contact", new Contact());
		return "user/addContact";
	}
	
	
	@RequestMapping(value = "/contactprocess",method = RequestMethod.POST)
	public String addContactProcess(@Valid @ModelAttribute Contact contact,BindingResult result, Principal principal,Model model,HttpSession session)
	{
		System.out.println("something wrong");
		
		try
		{
			if(result.hasErrors())
			{
				System.out.println("something wrong");	System.out.println("empty");
				return "user/addContact";
				
			}
			String username=principal.getName();
			User user=this.userRepository.getUserByEmail(username);
			contact.setUser(user);
			user.getContact().add(contact);
			Thread.sleep(1000);
			
			
			
//			if(image.isEmpty())
//			{
//				contact.setProfile("default.jpg");
//			}
//			else
//			{
//				String images=image.getOriginalFilename();
//			    contact.setProfile(images);
//				File saveFiles=new ClassPathResource("static/img").getFile();
//				Path path=Paths.get(saveFiles.getAbsolutePath()+File.separator+image.getOriginalFilename());
//			    Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//			    System.out.println("File uploaded successfully");
			   
				
				

			/* } */
			
			
		   
			 userRepository.save(user);
			 session.setAttribute("msg", new Messege("Contact Added Successful","success","alert-success"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "user/addContact"; 
	}
	
	@GetMapping("/allcontact/{page}")
	public String getAllContact(Principal principal,Model model,@PathVariable("page") int page)
	{
		Pageable pageable=PageRequest.of(page, 5);
		String username=principal.getName();
		User user=this.userRepository.getUserByEmail(username);
           Page<Contact> contact=this.userRepository.getAllContact(user.getUid(), pageable);
		model.addAttribute("contact", contact);
		model.addAttribute("currentpage", page);
		model.addAttribute("totalpage", contact.getTotalPages());
		return "user/allcontact";
	}

	@GetMapping("/singlecontact/{cid}")
	public String getSingleContact(@PathVariable("cid") Long cid,Model model,Principal principal)
	{
		String username=principal.getName();
		User user=this.userRepository.getUserByEmail(username);
	
		Contact contact=contactService.getConactById(cid);
		if(user.getUid()==contact.getUser().getUid())
		{
			model.addAttribute("contact", contact);
		}
		
		return "user/singleContact";
	}

	@RequestMapping("delete/{id}")
	
	public String deleteContact(@PathVariable("id") Long id,HttpSession session,Principal principal)
	{
		System.out.println("hello");
		
		String username=principal.getName();
		User user=this.userRepository.getUserByEmail(username);
	
		Contact contact=contactService.getConactById(id);
		if(user.getUid()==contact.getUser().getUid())
		{
			contactService.deleteContact(id);
		}
		
		session.setAttribute("msg", new Messege("Contact Deleted Successfuly","", "alert alert-success"));
	return "redirect:/contact/allcontact/0";
	}
	
	@RequestMapping("/updatecontact/{cid}")
	public String upateContact(@PathVariable("cid") Long cid,Model model)
	{
		Contact contact=this.contactService.getConactById(cid);
		model.addAttribute("contacts", contact);
		return "user/updatecontact";
	}
	
	@PostMapping("/contactupdateprocess")
	
	public String updateContactProcess(@ModelAttribute Contact contact,HttpSession session,Principal principal)
	{
		Contact oldcontact=contactService.getConactById(contact.getCid());
		String username=principal.getName();
		User user=userRepository.getUserByEmail(username);
//		if(files.isEmpty())
//		{
//			contact.setProfile(oldcontact.getProfile());
//		}
//		else
//		{
//			try
//			{
//				
//				//update photo
//				File savefile=new ClassPathResource("static/img").getFile();
//				Path path=Paths.get(savefile.getAbsolutePath()+File.separator+files.getOriginalFilename());
//				Files.copy(files.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING );
//				contact.setProfile(files.getOriginalFilename());
//				System.out.println("image upload successfully");
//				
//				//delete photo
//				
//				File deletefile=new ClassPathResource("static/img").getFile();
//				File file = new File(deletefile, oldcontact.getProfile());
//				boolean b=file.delete();
//				if(b==true)
//				{
//					System.out.println("file deleted");
//				}
//				
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		contact.setUser(user);
		contactService.updateContact(contact);
		session.setAttribute("msgc", new Messege("Contact Updated Successfuly !!!", "", "alert alert-success"));
		return "redirect:/contact/allcontact/0";
	}
	
	@RequestMapping("/setting")
	public String changePassword()
	{
		return "user/setting";
	}
	
	@PostMapping("/updatepassword")
	
	public String updatePassword(@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,Principal principal,HttpSession session)
	{
		
		String username=principal.getName();
		User user=userRepository.getUserByEmail(username);
		System.out.println(user.getPassword().toString());
		if(this.bcryptpassword.matches(oldpassword, user.getPassword()))
		{
			user.setPassword(this.bcryptpassword.encode(newpassword));
			this.userRepository.save(user);
			session.setAttribute("psms", new Messege("Password Change Successfuly","", "alert alert-success"));
		}else
		{
			System.out.println("password not change");
		}
		System.out.println(oldpassword);
		System.out.println(newpassword);
		return "redirect:/contact/setting";
	}
	
	@PostMapping("/createpayement")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data,Principal principal) throws RazorpayException
	{
		String username=principal.getName();
		User user=userRepository.getUserByEmail(username);
		
		int amt=Integer.parseInt(data.get("amount").toString());
		var client=new RazorpayClient("rzp_test_6yThDVUmEiuIA9", "fYYO8xFqzuutBxmMcTmMeeqL");
		JSONObject option=new JSONObject();
		option.put("amount", amt*100);
		option.put("currency", "INR");
		option.put("receipt", "txn_23456");
		
		
		Order order=client.Orders.create(option);
		System.out.println(order);
		
		MyOrder myOrder= new MyOrder();
		myOrder.setOrderId(order.get("id"));
		myOrder.setAmount(order.get("amount")+"");
		myOrder.setPayementId(null);
		myOrder.setStatus("created");
		myOrder.setUser(user);
		myOrder.setReceipt(order.get("receipt"));
		
		this.myOrderRepository.save(myOrder);
		return order.toString();
	}

	@PostMapping("/updatepayement")
public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data)
{
		MyOrder myOrder=this.myOrderRepository.findByOrderId(data.get("order_id").toString());
		myOrder.setPayementId(data.get("payment_id").toString());
		myOrder.setStatus(data.get("status").toString());
		this.myOrderRepository.save(myOrder);
	return ResponseEntity.ok(Map.of("msg","updated"));
}
}
