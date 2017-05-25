package com.yd.gcj.router;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yd.gcj.entity.YdMangerFiles;
import com.yd.gcj.entity.YdMangerTaskCondition;
import com.yd.gcj.entity.vo.YdMangerTaskVo;
import com.yd.gcj.entity.vo.YdMangerUserVo;
import com.yd.gcj.service.YdMangerServiceFiles;
import com.yd.gcj.service.YdMangerServiceTask;

@Controller
@RequestMapping("/task")
public class YdMangerPageRouterTask {

	@Autowired
	private YdMangerServiceTask ydMangerServiceTask;

	@Autowired
	private YdMangerServiceFiles serviceFiles;

	private static final String pageFiles = "task/";

	@RequestMapping("/task-bianji")
	public String taskBianji() {
		return pageFiles + "task-bianji";
	}

	@RequestMapping("/task-chakan")
	public String taskChakan() {
		return pageFiles + "task-chakan";
	}

	@RequestMapping("/task-detail/{taskId}")
	public String taskDetail(@PathVariable Integer taskId, Model model, HttpServletRequest request) {
		YdMangerUserVo userVo = (YdMangerUserVo) request.getSession().getAttribute("user");
		if (userVo != null) {
			YdMangerTaskVo taskVo = ydMangerServiceTask.$queryById(taskId, userVo.getUser_id());
			model.addAttribute("taskDetail", taskVo);
		} else {
			YdMangerTaskVo taskVo = ydMangerServiceTask.$queryById(taskId, 0);
			model.addAttribute("taskDetail", taskVo);
		}
		List<YdMangerFiles> files = serviceFiles.$queryAllByTaskId(taskId);
		model.addAttribute("files", files);
		return pageFiles + "task-detail";
	}

	@RequestMapping("/task")
	public String task(HttpServletRequest request) {
		HttpSession session = request.getSession();
		YdMangerTaskCondition taskCondition = (YdMangerTaskCondition) session.getAttribute("taskCondition");
		if(taskCondition == null){
			taskCondition = new YdMangerTaskCondition();
			session.setAttribute("taskCondition", taskCondition);
		}
		return pageFiles + "task";
	}

}
