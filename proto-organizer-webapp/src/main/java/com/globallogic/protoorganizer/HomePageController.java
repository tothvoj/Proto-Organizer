package com.globallogic.protoorganizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.globallogic.protoorganizer.model.Device;
import com.globallogic.protoorganizer.model.DeviceUsageView;
import com.globallogic.protoorganizer.model.DeviceView;
import com.globallogic.protoorganizer.model.DevicesViewWrapper;
import com.globallogic.protoorganizer.model.Helper;
import com.globallogic.protoorganizer.model.Platform;
import com.globallogic.protoorganizer.model.Project;
import com.globallogic.protoorganizer.model.User;

@Controller
public class HomePageController {

	@Autowired
	DevicesDAO devicesDAO;
	@Autowired
	DevicesUsageLogDAO devicesUsageLogDao;
	@Autowired
	ProjectsDAO projectsDAO;
	@Autowired
	PlatformsDAO platformsDAO;
	@Autowired
	UsersDAO usersDAO;
	@Autowired
	MailSender mailSender;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(
			@RequestParam(value = "error", required = false) boolean error,
			ModelMap model) {

		if (error == true) {
			// Assign an error message
			model.put("error",
					"You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}

		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return "loginpage";
	}

	@RequestMapping("/getListAdmin")
	public ModelAndView getDevicesListAdmin(
			@RequestParam(value = "q", required = false) String searchText,
			@RequestParam(value = "sort", required = false) Integer sortingParam) {
		
		List<DeviceView> devicesList = devicesDAO.getDevicesViewList(searchText);
		
		if (sortingParam != null) {
			devicesDAO.sortInView(devicesList, sortingParam);
		}

		List<DeviceView> removedDevicesList = devicesDAO.getRemovedDevicesViewList();
		List<Project> projects = projectsDAO.getProjectsList();
		List<Platform> platforms = platformsDAO.getChildPlatforms();
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = usersDAO.getUserByEmail(user.getUsername());
		
		ModelAndView mav = new ModelAndView("getListAdmin");
		mav.addObject("devicesViewWrapper", 
				new DevicesViewWrapper(devicesList, removedDevicesList, projects, platforms));
		mav.addObject("username", dbUser.getFullName());
		mav.addObject("userId", dbUser.getId());
		mav.addObject("q", searchText);

		return mav;
	}

	@RequestMapping("/getList")
	public ModelAndView getDevicesList() {
		List<DeviceView> devicesList = devicesDAO.getDevicesViewList(null);
		List<Project> projects = projectsDAO.getProjectsList();
		List<Platform> platforms = platformsDAO.getChildPlatforms();
		
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User dbUser = usersDAO.getUserByEmail(user.getUsername());
		
		ModelAndView mav = new ModelAndView("getList");
		mav.addObject("devicesViewWrapper", 
				new DevicesViewWrapper(devicesList, null, projects, platforms));
		mav.addObject("username", dbUser.getFullName());
		mav.addObject("userId", dbUser.getId());

		return mav;
	}

	@RequestMapping("/addDevice")
	public ModelAndView addDevice() {

		List<User> usersList = usersDAO.getUsersList();
		List<Project> projectsList = projectsDAO.getProjectsList();
		List<Platform> platformsList = platformsDAO.getChildPlatforms();

		@SuppressWarnings("rawtypes")
		Map<String, List> map = new HashMap<String, List>();
		map.put("projectsList", projectsList);
		map.put("platformsList", platformsList);
		map.put("usersList", usersList);

		ModelAndView mav = new ModelAndView("addDevice");
		mav.addObject("map", map);
		mav.addObject("device", new Device());
		return mav;
	}

	@RequestMapping("/addDeviceToDB")
	public String addDeviceToDB(@ModelAttribute Device device) {
		if (device != null) {
			
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User dbUser = null;
			
			if(device.getOwnerId() < 1)
			{
				dbUser = usersDAO.getUserByEmail(user.getUsername());
				device.setOwnerId((int) dbUser.getId());
			}
			if(device.getLastModifiedBy() < 1)
			{
				if(dbUser == null) dbUser = usersDAO.getUserByEmail(user.getUsername());
				device.setLastModifiedBy((int) dbUser.getId());
			}
			
			devicesDAO.insertDevice(device);
			// TRIGGER IN DB AUTOMATICALLY CREATED ACTION IN DevicesUsageLog TABLE WITH ADDED ACTION
		}
		return "redirect:/getListAdmin";
	}
	
	@RequestMapping("/addPlatformToDB")
	public String addPlatformToDB(@ModelAttribute Platform platform) {
		if (platform != null) {
			platformsDAO.insertPlatform(platform);
		}
		return "redirect:/getListAdmin";
	}

	@RequestMapping("/deleteDevice")
	public ModelAndView deleteDevice() {

		List<DeviceView> devicesList = devicesDAO.getDevicesViewList(null);
		ModelAndView mav = new ModelAndView("deleteDevice");
		mav.addObject("devicesList", devicesList);
		mav.addObject("helper", new Helper());
		return mav;
	}

	@RequestMapping("/deleteDevicesFromDB")
	public String deleteDevicesFromDB(@ModelAttribute Helper helper) {
		devicesDAO.deleteBatch(helper.getIds());
		return "redirect:/getListAdmin";

	}
	
	@RequestMapping("/deleteSingleDevice")
	public ModelAndView deleteSingleDevice(@RequestParam("id") long id) {
		Device device = devicesDAO.getDevice((int) id);
		ModelAndView mav = new ModelAndView("deleteSingleDevice");
		
		mav.addObject("device", device);
		return mav;
	}
	
	@RequestMapping(value = "/deleteSingleDeviceFromDB", method = RequestMethod.POST)
	public String deleteSingleDeviceFromDB(@ModelAttribute Device device) {
		int rowsChanged = devicesDAO.deleteDevice(device.getId());
		
		return "redirect:/getListAdmin";
	}
	
	@RequestMapping(value = "/deleteSingleDeviceFromDBusingJS", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public String deleteSingleDeviceFromDBusingJS(@ModelAttribute Device device) {
		int rowsChanged = devicesDAO.deleteDevice(device.getId());
		
		return Integer.toString(rowsChanged);
	}

	@RequestMapping("/addProject")
	public ModelAndView addProject() {

		ModelAndView mav = new ModelAndView("addProject");
		mav.addObject("project", new Project());
		return mav;
	}
	
	@RequestMapping("/addPlatform")
	public ModelAndView addPlatform() {

		ModelAndView mav = new ModelAndView("addPlatform");
		mav.addObject("platform", new Platform());
		ArrayList<Platform> platforms = (ArrayList<Platform>) platformsDAO.getMasterPlatforms();
		mav.addObject("masterPlatforms", platforms);
		
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
		return "redirect:/getListAdmin";
	}
	
	@RequestMapping("/deletePlatform")
	public ModelAndView deletePlatform() {

		List<Platform> projectList = platformsDAO.getChildPlatforms();
		ModelAndView mav = new ModelAndView("deletePlatform");
		mav.addObject("platformsList", projectList);
		mav.addObject("helper", new Helper());
		return mav;
	}

	@RequestMapping("/deletePlatformsFromDB")
	public String deletePlatformsFromDB(@ModelAttribute Helper helper) {
		platformsDAO.deleteBatch(helper.getIds());
		return "redirect:/getListAdmin";
	}

	@RequestMapping("/addProjectToDB")
	public String addProjectToDB(@ModelAttribute Project project) {
		projectsDAO.insertProject(project.getProjectName());

		return "redirect:/getListAdmin";
	}

	@RequestMapping( "/addUser" )
	public ModelAndView addUser() {

		ModelAndView mav = new ModelAndView("addUser");
		User user = new User();
		user.setIsPerson(true);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("/addUserToDB")
	public String addUserToDB(@ModelAttribute User user) {
		boolean result = usersDAO.insertUser(user);

		if (result) {
			sendWelcomeMail(user.getEmail());
		}

		return "redirect:/getListAdmin";
	}
	
	@RequestMapping( "/addVault" )
	public ModelAndView addVault() {
		
		ModelAndView mav = new ModelAndView("addVault");
		User user = new User();
		user.setIsPerson(true);
		mav.addObject("user", user);
		return mav;
	}
	
	@RequestMapping("/addVaultToDB")
	public String addVaultToDB(@ModelAttribute User user) {
		//boolean result = 
			usersDAO.insertUser(user);

		return "redirect:/getListAdmin";
	}
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser() {

		List<User> usersList = usersDAO.getUsersList(true);
		ModelAndView mav = new ModelAndView("deleteUser");
		mav.addObject("usersList", usersList);
		mav.addObject("helper", new Helper());
		return mav;
	}

	@RequestMapping("/deleteUsersFromDB")
	public String deleteUsersFromDB(@ModelAttribute Helper helper) {
		usersDAO.deleteBatch(helper.getIds());
		return "redirect:/getListAdmin";

	}
	
	@RequestMapping("/deleteVault")
	public ModelAndView deleteVault() {

		List<User> usersList = usersDAO.getUsersList(false);
		ModelAndView mav = new ModelAndView("deleteVault");
		mav.addObject("usersList", usersList);
		mav.addObject("helper", new Helper());
		return mav;
	}

	@RequestMapping("/deleteVaultFromDB")
	public String deleteVaultFromDB(@ModelAttribute Helper helper) {
		usersDAO.deleteBatch(helper.getIds());
		return "redirect:/getListAdmin";

	}

	@RequestMapping("/downloadExcel")
	public ModelAndView downloadExcel() {

		List<Device> devicesList = devicesDAO.getDevicesList(null);
		// return a view which will be resolved by an excel view resolver
		return new ModelAndView("excelView", "devicesList", devicesList);
	}

	@RequestMapping("/selectUser")
	public ModelAndView selecUser(@RequestParam Long id) {
		List<User> usersList = usersDAO.getUsersList(true);
		ModelAndView mav = new ModelAndView("changeOwner");
		Helper helper = new Helper();
		helper.setSelectedDeviceID(id);
		mav.addObject("usersList", usersList);
		//mav.addObject("vaultsList", usersDAO.getUsersList(false));
		mav.addObject("helper", helper);
		return mav;
	}

	@RequestMapping("/moveTo")
	public String moveTo(@ModelAttribute Helper helper,
			@RequestParam Long deviceID) {
		devicesDAO.changeOwner(deviceID, helper.getUserID());
		
		return "redirect:/getListAdmin";

	}

	@RequestMapping("/changePassword")
	public ModelAndView changePassword(@RequestParam(value = "messagecode", required = false) Integer messageCode) {
		ModelAndView mav = new ModelAndView("changePassword");
		Helper helper = new Helper();
		String errorMsg = "";
		if (messageCode != null){
			if (messageCode == 1){
				errorMsg = "Entered current password is wrong.";
			}else if (messageCode == 2){
				errorMsg = "The passwords entered in the last two fields have to be the same.";
			}else{
				errorMsg = "Password succesfully changed.";
			}
		}
		mav.addObject("messagecode", errorMsg);
		mav.addObject("helper", helper);
		return mav;
	}

	@RequestMapping("/changePasswordInDB")
	public String changePasswordInDB(@ModelAttribute Helper helper) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		String currentPassword = helper.getCurrentPassword();
		String newPassword = helper.getNewPassword();
		String newPasswordRepeat = helper.getNewPasswordRepeat();
		
		if (!user.getPassword().contentEquals(encoder.encodePassword(currentPassword, null))){
			return "redirect:/changePassword?messagecode=1";
		}
		
		if (!newPassword.contentEquals(newPasswordRepeat)){
			return "redirect:/changePassword?messagecode=2";
		}
		
		usersDAO.changePassword(user.getUsername(), encoder.encodePassword(newPassword, null));
		
		return "redirect:/getListAdmin";
	}
	
	@RequestMapping(value = "/changeDeviceStatus", method = RequestMethod.POST)
	@ResponseBody
	public String changeDeviceStatus(int deviceId, int userId, String newStatus)
	{
		return devicesDAO.changeDeviceStatus(deviceId, userId, newStatus);
	}
	
	@RequestMapping(value = "/updateDevicesView", method = RequestMethod.POST)
	public String updateDevicesView(@ModelAttribute DevicesViewWrapper devicesViewWrapper)
	{
		devicesDAO.updateDevicePartially(devicesViewWrapper.getDevicesList());
		
		return "redirect:/getListAdmin";
	}
	 
	@RequestMapping("/deviceHistory")
	public ModelAndView deviceHistory(@RequestParam(value = "id", required = true) Integer deviceId) {
		ModelAndView mav = new ModelAndView("deviceHistory");
		
		List<DeviceUsageView> deviceUsage = devicesUsageLogDao.getDeviceUsageViewList(deviceId);
		Device device = devicesDAO.getDevice(deviceId);
		
		mav.addObject("deviceUsage", deviceUsage);
		mav.addObject("device", device);
		
		return mav;
	}
	
	private void sendWelcomeMail(String to) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		msg.setText("Hello");

		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}
}
