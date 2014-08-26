package rs.tfzr.FudbalT2.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.tfzr.FudbalT2.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController 
{
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/exhibition/{exhibitionId}", method = RequestMethod.GET)
	public String getExhibitionComments(@PathVariable Long exhibitionId, Model model)
	{
		model.addAttribute("commentsMap", commentService.getCommentsForExhibition(exhibitionId));
		return "comments";
	}
}
