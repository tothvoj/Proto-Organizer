package com.globallogic.protoorganizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.globallogic.protoorganizer.model.Helper;
import com.globallogic.protoorganizer.model.Device;
import com.globallogic.protoorganizer.model.Project;
import com.globallogic.protoorganizer.model.User;

@Controller
public class HomePageController {

	@Autowired
	DevicesDAO devicesDAO;
	@Autowired
	ProjectsDAO projectsDAO;
	@Autowired
	UsersDAO usersDAO;

	@RequestMapping("/getList")
	public ModelAndView getDevicesList(@RequestParam(value="q", required=false) String searchText, @RequestParam(value="sort", required=false) Integer sortingParam) {
		List<Device> devicesList = devicesDAO.getDevicesList(searchText);
		if (sortingParam != null){
			devicesDAO.sort(devicesList, sortingParam);
		}
		
		List<Device> removedDevicesList = devicesDAO.getRemovedDevicesList();
		
		ModelAndView mav  = new ModelAndView("getList");
		mav.addObject("devicesList", devicesList);
		mav.addObject("removedDevicesList", removedDevicesList);
		
		return mav;
	}

	@RequestMapping("/addDevice")
	public ModelAndView addDevice() {

		List<String> statusList = new ArrayList<String>();
		statusList.add("in");
		statusList.add("out");

		List<User> usersList = usersDAO.getUsersList();
		List<Project> projectsList = projectsDAO.getProjectsList();

		Map<String, List> map = new HashMap<String, List>();
		map.put("statusList", statusList);
		map.put("projectsList", projectsList);
		map.put("usersList", usersList);
		
		ModelAndView mav  = new ModelAndView("addDevice");
		mav.addObject("map", map);
		mav.addObject("device", new Device());
		return mav;
	}

	@RequestMapping("/addDeviceToDB")
	public String addDeviceToDB(@ModelAttribute Device device) {
		if (device != null) {
			devicesDAO.insertDevice(device);
		}
		return "redirect:/getList";
	}

	@RequestMapping("/deleteDevice")
	public ModelAndView deleteDevice() {

		List<Device> devicesList = devicesDAO.getDevicesList(null);
		ModelAndView mav = new ModelAndView("deleteDevice");
		mav.addObject("devicesList", devicesList);
		mav.addObject("helper", new Helper());
		return mav;
	}

	@RequestMapping("/deleteDevicesFromDB")
	public String deleteDevicesFromDB(@ModelAttribute Helper helper) {
		devicesDAO.deleteBatch(helper.getIds());
		return "redirect:/getList";

	}

	@RequestMapping("/addProject")
	public ModelAndView addProject() {

		ModelAndView mav = new ModelAndView("addProject");
		mav.addObject("project", new Project());
		return mav;
	}
	
	@RequestMapping("/deleteProject")
	public ModelAndView deleteProject() {

		List<Project> projectList = projectsDAO.getProjectsList();
		ModelAndView mav = new ModelAndView("deleteProject");
		mav.addObject("projectList", projectList);
		mav.addObject("helper", new Helper());
		return mav;
	}
	
	@RequestMapping("/deleteProjectsFromDB")
	public String deleteProjectsFromDB(@ModelAttribute Helper helper) {
		projectsDAO.deleteBatch(helper.getIds());
		return "redirect:/getList";
	}

	@RequestMapping("/addProjectToDB")
	public String addProjectToDB(@ModelAttribute Project project) {
		projectsDAO.insertProject(project.getProject_name());

		return "redirect:/getList";
	}
	
	@RequestMapping("/addUser")
	public ModelAndView addUser() {

		ModelAndView mav = new ModelAndView("addUser");
		mav.addObject("user", new User());
		return mav;
	}
	
	@RequestMapping("/addUserToDB")
	public String addUserToDB(@ModelAttribute User user) {
		usersDAO.insertUser(user);

		return "redirect:/getList";
	}
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser() {

		List<User> usersList = usersDAO.getUsersList();
		ModelAndView mav = new ModelAndView("deleteUser");
		mav.addObject("usersList", usersList);
		mav.addObject("helper", new Helper());
		return mav;
	}

	@RequestMapping("/deleteUsersFromDB")
	public String deleteUsersFromDB(@ModelAttribute Helper helper) {
		usersDAO.deleteBatch(helper.getIds());
		return "redirect:/getList";

	}
	
	@RequestMapping("/downloadExcel")
    public ModelAndView downloadExcel() {
         
		List<Device> devicesList = devicesDAO.getDevicesList(null);
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("excelView", "devicesList", devicesList);
    }
	
	@RequestMapping("/selectUser")  
	 public ModelAndView selecUser(@RequestParam Long id) {  
		List<User> usersList = usersDAO.getUsersList();
		ModelAndView mav = new ModelAndView("changeOwner");
		Helper helper = new Helper();
		helper.setSelectedDeviceID(id);
		mav.addObject("usersList", usersList);
		mav.addObject("helper", helper);
		return mav;
	}
	
	@RequestMapping("/moveTo")
	public String moveTo(@ModelAttribute Helper helper, @RequestParam Long deviceID) {
		devicesDAO.changeOwner(deviceID, helper.getUserID());
		return "redirect:/getList";

	}
}
