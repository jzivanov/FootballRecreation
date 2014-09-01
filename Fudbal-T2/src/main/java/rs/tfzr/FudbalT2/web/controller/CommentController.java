package rs.tfzr.FudbalT2.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.model.Comment;
import rs.tfzr.FudbalT2.model.Exhibition;
import rs.tfzr.FudbalT2.model.User;
import rs.tfzr.FudbalT2.service.CommentService;
import rs.tfzr.FudbalT2.service.ExhibitionService;
import rs.tfzr.FudbalT2.web.dto.CommentDTO;
import rs.tfzr.FudbalT2.web.validator.CommentValidator;
import rs.tfzr.FudbalT2.web.validator.ExhibitionAvailableValidator;

@Controller
@RequestMapping("/comments")
public class CommentController 
{
	@Autowired
	private CommentValidator commentValidator;
	
	@Autowired
	private ExhibitionAvailableValidator exhibitionAvailableValidator;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ExhibitionService exhibitionService;

	@RequestMapping(value = "/exhibition/{exhibitionId}", method = RequestMethod.GET)
	public String getExhibitionComments(@PathVariable Long exhibitionId, Model model)
	{
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		DataBinder binder = new DataBinder(exhibition);
		binder.setValidator(exhibitionAvailableValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		
		if(!results.hasErrors())
		{
			CommentDTO comment = new CommentDTO();
			comment.setExhibitionId(exhibitionId);
			model.addAttribute("comment", comment);
			
			model.addAttribute("commentsMap", commentService.getCommentsForExhibition(exhibitionId));
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userId", user.getId());
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
		}
		
		return "comments";
	}
	
	@RequestMapping(value = "/exhibition/{exhibitionId}/add/{commentId}", method = RequestMethod.GET)
	public String setOnComment(@PathVariable Long exhibitionId, @PathVariable Long commentId, Model model)
	{
		//Proveri da li postoji komentar!
		Exhibition exhibition = exhibitionService.findOne(exhibitionId);
		DataBinder binder = new DataBinder(exhibition);
		binder.setValidator(exhibitionAvailableValidator);
		binder.validate();
		BindingResult results = binder.getBindingResult();
		
		if(!results.hasErrors())
		{
			CommentDTO comment = new CommentDTO();
			comment.setExhibitionId(exhibitionId);
			comment.setMainCommentId(commentId);
			model.addAttribute("comment", comment);
			
			model.addAttribute("mainComment", commentService.findOne(commentId));
			model.addAttribute("commentsMap", commentService.getCommentsForExhibition(exhibitionId));
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			model.addAttribute("userId", user.getId());
		}
		else
		{
			model.addAttribute("errors", results.getAllErrors());
		}
		return "comments";
	}
	
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String setComment(@Valid CommentDTO comment, BindingResult bindingResult, Model model)
	{
		if(!bindingResult.hasErrors())
		{
			Comment comm = new Comment();
			comm.setExhibition(exhibitionService.findOne(comment.getExhibitionId()));
			comm.setBody(comment.getBody());
			if(comment.getMainCommentId() != null)
				comm.setMainComment(commentService.findOne(comment.getMainCommentId()));
			comm.setTitle(comment.getTitle());
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			comm.setUser(user);
			
			commentService.save(comm);
		}
		else
		{
			model.addAttribute("comment", comment);
		}
		return "redirect:/comments/exhibition/" + comment.getExhibitionId();
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel(CommentDTO comment)
	{
		return "redirect:/comments/exhibition/" + comment.getExhibitionId();
	}
	
	@RequestMapping(value = "/remove/{commentId}", method = RequestMethod.GET)
	public String removeComment(@PathVariable Long commentId, Model model)
	{
		Comment comment = commentService.findOne(commentId);
		commentService.remove(commentId);
		return "redirect:/comments/exhibition/" + comment.getExhibition().getId();
	}
}
