package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.BoardMapper;
import edu.zsc.todolistproject.mapper.BoardMapper2;
import edu.zsc.todolistproject.mapper.TeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/notebook/user")
public class BoardController2 {

    private static Logger logger = LoggerFactory.getLogger(BoardController2.class);

    @Autowired
    BoardMapper2 boardMapper;

    @Autowired
    TeamMapper teamMapper;

    //检查title长度
    public List<Board> checkLength(List<Board> boardList){
        for(Board board:boardList){
            if(board.getTitle().length()>12){
                board.setTitle(board.getTitle().substring(0,12)+"...");
            }
        }
        return boardList;
    }

    @GetMapping({"/home",""})
    //改
//    public String home(Model model,@SessionAttribute User loginUser){
     public String home(Model model){
        //左侧团队菜单
//        List<Team>teamList=teamMapper.findUserTeam(loginUser.getId());
        List<Team>teamList=teamMapper.findUserTeam(1L);
        model.addAttribute("teamList",teamList);
        return "/notebook/home";
    }

    @GetMapping("/board")
    //改
//    public String boardList(Model model,@SessionAttribute User loginUser)
    public String boardList(Model model)
    {
        //我的面板
//        List<Board>boardList=boardMapper.findAllBoard(loginUser.getId());
        List<Board>boardList=boardMapper.findAllBoard(1L);
        model.addAttribute("boardList",checkLength(boardList));

        //最近查看
//        List<Board>boardDateList=boardMapper.findBoardSubmitDate(loginUser.getId());
        List<Board>boardDateList=boardMapper.findBoardSubmitDate(1L);
        model.addAttribute("boardDateList",checkLength(boardDateList));
        return "/notebook/board";
    }


    @GetMapping("/teamBoard")
    //改
//    public String showTeamBoard(@SessionAttribute User loginUser, @RequestParam("teamId") Long teamId ,Model model) {
    public String showTeamBoard(@RequestParam("teamId") Long teamId ,Model model) {
        System.out.println("团队号"+teamId);
       // 你的团队面板
//        List<Board> personalTeamBoardList=boardMapper.findPeraonalTeamBoard(loginUser.getId(),teamId);
        List<Board> personalTeamBoardList=boardMapper.findPeraonalTeamBoard(1L,teamId);
        model.addAttribute("personalTeamBoardList",checkLength(personalTeamBoardList));


        //这个团队所有面板除了你
//        List<Board>teamBoardList=boardMapper.findAllTeamBoard(loginUser.getId(),teamId);
        List<Board>teamBoardList=boardMapper.findAllTeamBoard(1L,teamId);
        model.addAttribute("teamBoardList",checkLength(teamBoardList));
        return "/notebook/teamBoard";
    }



    @GetMapping("/layout")
    public String view() {
        return "/fragments/layout";
    }
}
