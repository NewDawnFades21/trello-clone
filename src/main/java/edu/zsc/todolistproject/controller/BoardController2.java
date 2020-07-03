package edu.zsc.todolistproject.controller;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.domain.User;
import edu.zsc.todolistproject.mapper.BoardMapper2;
import edu.zsc.todolistproject.mapper.TeamMapper;
import edu.zsc.todolistproject.service.BoardService2;
import edu.zsc.todolistproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notebook/user")
public class BoardController2 {

//    @Autowired
//    BoardMapper2 boardMapper;

//    @Autowired
//    TeamMapper teamMapper;

    @Autowired
    TeamService  teamService;

    @Autowired
    BoardService2 boardService;

    @GetMapping({"/home",""})
    //改
//    public String home(Model model,@SessionAttribute User loginUser){
     public String home(Model model){
        //左侧团队菜单
        leftMenu(model);

        selectT(model);
        selectTT(model);

//        List<Team>teamList=teamMapper.findUserTeam(loginUser.getId());
//          List<Team>teamList=teamMapper.findUserTeam(1L);
//        model.addAttribute("teamList",teamList);

        //我的面板
//        List<Board>boardList=boardMapper.findAllBoard(loginUser.getId());
        List<Board>boardList=boardService.findAllBoard(1L);
        model.addAttribute("boardList",checkLength(boardList));

        //最近查看
//        List<Board>boardDateList=boardMapper.findBoardSubmitDate(loginUser.getId());
        List<Board>boardDateList=boardService.findBoardSubmitDate(1L);
        model.addAttribute("boardDateList",checkLength(boardDateList));

        return "/notebook/home";
    }

//    改：跳转到彦绚写的那页
    @PostMapping("/team")
    public String team(@RequestParam("title2") String title,@RequestParam String teamMember,@RequestParam int typeId,@RequestParam String description){
        Team team=new Team();
        team.setTitle(title);
        team.setDescription(description);
        team.setTypeId(typeId);
        boardService.insertTeam(team);
        Long teamId=team.getId();
        String []s=teamMember.split(" ");
        Long userId;
        for(int i=0;i<s.length;i++){
            userId=boardService.findUserId(s[i]);
            System.out.println("找id"+userId);
            if(userId!=null) {
                System.out.println("测试后："+teamId+userId);
                boardService.insertTeamMember(teamId, userId);
            }
        }
        return "redirect:/notebook/user/team?teamId="+teamId;
    }

    @GetMapping("/team")
    public String t(){
        return "/notebook/team";
    }

//    @GetMapping("/board")
//    //改
////    public String boardList(Model model,@SessionAttribute User loginUser)
//    public String boardList(Model model)
//    {
//        //我的面板
////        List<Board>boardList=boardMapper.findAllBoard(loginUser.getId());
//        List<Board>boardList=boardMapper.findAllBoard(1L);
//        model.addAttribute("boardList",checkLength(boardList));
//
//        //最近查看
////        List<Board>boardDateList=boardMapper.findBoardSubmitDate(loginUser.getId());
//        List<Board>boardDateList=boardMapper.findBoardSubmitDate(1L);
//        model.addAttribute("boardDateList",checkLength(boardDateList));
//        return "/notebook/board";
//    }


    @GetMapping("/teamBoard")
    //改
//    public String showTeamBoard(@SessionAttribute User loginUser, @RequestParam("teamId") Long teamId ,Model model) {
    public String showTeamBoard(@RequestParam("teamId") Long teamId ,Model model) {
//        左侧团队菜单
//        List<Team>teamList=teamMapper.findUserTeam(loginUser.getId());
//        List<Team>teamList=teamMapper.findUserTeam(1L);
//        model.addAttribute("teamList",teamList);
        //改
        leftMenu(model);

        selectT(model);
        selectTT(model);

       // 你的团队面板
//        List<Board> personalTeamBoardList=boardMapper.findPeraonalTeamBoard(loginUser.getId(),teamId);
        List<Board> personalTeamBoardList=boardService.findPeraonalTeamBoard(1L,teamId);
        model.addAttribute("personalTeamBoardList",checkLength(personalTeamBoardList));


        //这个团队所有面板除了你
//        List<Board>teamBoardList=boardMapper.findAllTeamBoard(loginUser.getId(),teamId);
        List<Board>teamBoardList=boardService.findAllTeamBoard(1L,teamId);
        model.addAttribute("teamBoardList",checkLength(teamBoardList));



        return "/notebook/teamBoard";
    }


    @GetMapping("/deck")
    public String deckview(@RequestParam Long id, Model model) {
        Board board=boardService.findBoardById(id);
        model.addAttribute("board",board);
        return "/notebook/deck";
    }

    //创建一个新面板
    @PostMapping("/deck")
    public String addBoard(@RequestParam String title,@RequestParam int visibility,@RequestParam Long teamId, Model model){
        Date date=new Date();
        Timestamp submitDate=new Timestamp(date.getTime());
        //改：加上teamId
        System.out.println(teamId);
        Board board=new Board();
        board.setTitle(title);
        board.setVisibility(visibility);
        board.setUserId(1L);
        board.setSubmitDate(submitDate);
        Long id;
        if(teamId!=null) {
            board.setTeamId(teamId);
            boardService.insertBoard(board);
            id=board.getId();
        }
        else {
            boardService.insertBoardNoTeam(board);
            id=board.getId();
        }

//        model.addAttribute("id",id);
        return "redirect:/notebook/user/deck?id="+id;
    }



//-----------------------------------------------函数-----------------------
//    改
//    public String leftMenu(Model model,Long id){
public void leftMenu(Model model){
        //左侧团队菜单
//        List<Team>teamList=teamMapper.findUserTeam(1L);
//        model.addAttribute("teamList",teamList);

         List<Team>teamList=teamService.findUserTeam(1L);
         model.addAttribute("teamList",teamList);
    }

    //检查title长度
    public List<Board> checkLength(List<Board> boardList){
        for(Board board:boardList){
            if(board.getTitle().length()>12){
                board.setTitle(board.getTitle().substring(0,12)+"...");
            }
        }
        return boardList;
    }

//    团队select
    public void selectT(Model model){
        List<Team>teamSelects=teamService.findPersonalTeam(1L);
        model.addAttribute("teamSelects",teamSelects);
    }
    public void selectTT(Model model){
        List<TeamType>teamTSelects=teamService.findTeamType();
        model.addAttribute("teamTSelects",teamTSelects);
    }

//    public void insertTeamMember(String teamMember,Long teamId,Long userId){
//
//            String []s=teamMember.split(",");
//            for(int i=0;i<s.length;i++){
//                boardMapper.insertTeamMember(teamId,userId);
//        }
//    }




}
