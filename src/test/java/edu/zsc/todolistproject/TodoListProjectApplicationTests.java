package edu.zsc.todolistproject;


import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.Team;
import edu.zsc.todolistproject.mapper.BoardMapper2;
import edu.zsc.todolistproject.mapper.TeamMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest
class TodoListProjectApplicationTests {
    @Autowired
    BoardMapper2 boardMapper2;

    @Autowired
    TeamMapper teamMapper;

    @Test
    void contextLoads() {
//        Team team=boardMapper2.get(1L);
//        System.out.println(team.getTeamType().getName());
        Date date=new Date();
        Timestamp submitDate=new Timestamp(date.getTime());
        Board board=new Board();
        board.setTitle("测试动态查询");
        board.setVisibility(1);
        board.setUserId(1L);
        board.setSubmitDate(submitDate);
        board.setTeamId(4L);
        boardMapper2.insertB(board);
        System.out.println(board.getId());


    }


}
