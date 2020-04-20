package com.soft.srms.controller;

import com.soft.srms.model.*;
import com.soft.srms.service.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private AnnouncementService announcementService;

    private ApplicationService applicationService;

    private ComplaintService complaintService;

    private UserService userService;

    private RoomService roomService;

    public DashboardController(AnnouncementService announcementService, ApplicationService applicationService, ComplaintService complaintService, UserService userService, RoomService roomService) {
        this.announcementService = announcementService;
        this.applicationService = applicationService;
        this.complaintService = complaintService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @GetMapping("/admin")
    public String showAdminDashboard(Model model) {
        applicationData(model);
        roomData(model);
        complaintData(model);

        return "admin";
    }

    private void applicationData(Model model) {
        model.addAttribute("male", applicationService.getApplicationCountByApprovalStatusAndGender(true, Gender.MALE));
        model.addAttribute("female", applicationService.getApplicationCountByApprovalStatusAndGender(true, Gender.FEMALE));

        model.addAttribute("maleDenied", applicationService.getApplicationCountByApprovalStatusAndGender(false, Gender.MALE));
        model.addAttribute("femaleDenied", applicationService.getApplicationCountByApprovalStatusAndGender(false, Gender.FEMALE));
    }

    private void roomData(Model model) {
        model.addAttribute("emptyRooms", roomService.getEmptyRoomCount());
        model.addAttribute("halfRooms", roomService.getHalfEmptyRoomCount());
        model.addAttribute("fullRooms", roomService.getFullRoomCount());
    }

    private void complaintData(Model model) {
        model.addAttribute("today", complaintService.getComplaintCountByCreationDate(LocalDate.now()));
        model.addAttribute("dayago1", complaintService.getComplaintCountByCreationDate(LocalDate.now().minusDays(1)));
        model.addAttribute("dayago2", complaintService.getComplaintCountByCreationDate(LocalDate.now().minusDays(2)));
        model.addAttribute("dayago3", complaintService.getComplaintCountByCreationDate(LocalDate.now().minusDays(3)));

        LocalDate initial = LocalDate.now().withDayOfYear(1);
        for(Integer i = 1; i <= 12; i++) {
            LocalDate start = initial.with(firstDayOfMonth());
            LocalDate end = initial.with(lastDayOfMonth());
            model.addAttribute("m"+i.toString(), complaintService.getComplaintCountByCreationDateBetween(start,end));
            initial = end.plusDays(1);
        }
    }
    
    @GetMapping("/application")
    public String showApplicationForm(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        Application application = applicationService.findApplicationByUserId(userPrincipal.getUser().getId());
        if(application == null){
            model.addAttribute("application", new Application());
            model.addAttribute("readonly", false);
            model.addAttribute("gender", "MALE"); // thymeleaf bug workaround
            model.addAttribute("now", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        else {
            model.addAttribute("application", application);
            model.addAttribute("readonly", true);
            model.addAttribute("gender", application.getGender().name()); // thymeleaf bug workaround
            model.addAttribute("birthDate", application.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // thymeleaf bug workaround
        }

        return "application";
    }

    @PostMapping("/application")
    public String createApplication(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid Application application, Errors errors, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        boolean jmbagTaken = applicationService.isJmbagTaken(application.getJmbag());
        if(jmbagTaken) {
            FieldError jmbagTakenError = new FieldError("application", "jmbag", application.getJmbag(), false, null, null, "Application already exists with this JMBAG.");
            bindingResult.addError(jmbagTakenError);
        }

        if (errors.hasErrors() || jmbagTaken) {
            model.addAttribute("now", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            model.addAttribute("birthDate", application.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); // thymeleaf bug workaround
            model.addAttribute("gender", application.getGender().toString()); // thymeleaf bug workaround
            model.addAttribute("readonly", false);
            return "application";
        }
        else {
            applicationService.createApplication(userPrincipal.getUser(), application);
            redirectAttributes.addFlashAttribute("created", "Application successfully created.");
            if(application.getTenant()!=null) {
                redirectAttributes.addFlashAttribute("status", "Your application has been accepted. You were assigned to the room " + application.getTenant().getRoom().getName() + ".");
            } else {
                redirectAttributes.addFlashAttribute("status", "Your application is currently on the waiting list.");
            }
            return "redirect:/dashboard/application";
        }
    }

    @PostMapping("/application/delete")
    public String deleteApplication(@AuthenticationPrincipal UserPrincipal userPrincipal, RedirectAttributes redirectAttributes) {
        Application application = applicationService.findApplicationByUserId(userPrincipal.getUser().getId());
        if(application.getTenant()!=null) {
            redirectAttributes.addFlashAttribute("status", "You have moved out of the dorm.");
        }

        applicationService.deleteApplication(userPrincipal.getUser());
        redirectAttributes.addFlashAttribute("deleted", "Application successfully deleted.");
        return "redirect:/dashboard/application";
    }

    @GetMapping("/announcements")
    public String showAnnouncements(Model model) {
        model.addAttribute("announcementList", announcementService.getCurrentAnnouncements());
        return "current_announcements";
    }

    @GetMapping("/announcements/new")
    public String showAnnouncementForm(Model model) {
        if (!model.containsAttribute("announcement")) {
            model.addAttribute("announcement", new Announcement());
        }
        model.addAttribute("midnight", LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        model.addAttribute("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));

        return "announcement";
    }

    @PostMapping("/announcements/new")
    public String showAnnouncementForm(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid Announcement announcement, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("midnight", LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
            model.addAttribute("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
            return "announcement";
        } else {
            announcementService.createAnnouncement(userPrincipal.getUser(), announcement);
            redirectAttributes.addFlashAttribute("success", "Announcement successfully created.");
            return "redirect:/dashboard/announcements/new";
        }
    }

    @GetMapping("/complaints/new")
    public String showComplaintForm(Model model) {
        model.addAttribute("complaint", new Complaint());
        return "complaint";
    }

    @PostMapping("/complaints/new")
    public String showComplaintForm(@AuthenticationPrincipal UserPrincipal userPrincipal, @Valid Complaint complaint, Errors errors, RedirectAttributes redirectAttributes) {
        if(errors.hasErrors()) return "complaint";
        else {
            complaintService.createComplaint(userPrincipal.getUser(), complaint);
            redirectAttributes.addFlashAttribute("success", "Complaint successfully created.");
            return "redirect:/dashboard/complaints/new";
        }
    }

    @PostMapping("/complaints/solve")
    public String solveComplaint(@RequestParam("id_complaint") Long complaintId, Model model) {
        complaintService.solveComplaint(complaintId);
        return "redirect:/dashboard/complaints";
    }

    @GetMapping("/complaints")
    public String showUnsolvedComplaints(Model model) {
        model.addAttribute("complaintList", complaintService.getUnsolvedComplaints());
        return "current_complaints";
    }

    @GetMapping("/tenants")
    public String showTenants(Model model) {
        model.addAttribute("tenantList", applicationService.getTenantList());
        return "tenant_list";
    }

    @GetMapping("/waiting")
    public String showWaitingList(Model model) {
        model.addAttribute("waitingList", applicationService.getWaitingList());
        return "waiting_list";
    }

    private void profileData(User user, Model model) {
        Application application = applicationService.findApplicationByUserId(user.getId());
        model.addAttribute("appl",application);

        if(application!=null && application.isApproved()) {
            model.addAttribute("room", application.getTenant().getRoom());
        }
    }

    @GetMapping("/profile")
    public String showProfilePage(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        profileData(userPrincipal.getUser(), model);
        model.addAttribute("infoTab", true);
        return "profile";
    }

    @PostMapping("/profile")
    public String showProfilePage(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                  @RequestParam("password") String oldPassword,
                                  @RequestParam("new_password") String newPassword,
                                  Model model)
    {
        boolean validOldPassword = userService.confirmPassword(userPrincipal.getUser(), oldPassword);
        boolean validNewPassword = userService.validatePassword(newPassword);

        if(!validOldPassword) {
            model.addAttribute("wrongPassword", "Incorrect password.");
        }
        if(!validNewPassword) {
            model.addAttribute("shortPassword", "Password must contain at least 3 characters.");
        }
        if(validNewPassword && validOldPassword) {
            userService.changePassword(userPrincipal.getUser(), newPassword);
            model.addAttribute("success", "Password successfully changed.");
        }

        profileData(userPrincipal.getUser(), model);
        model.addAttribute("changeTab", true);
        return "profile";
    }
}
