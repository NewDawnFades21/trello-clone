package edu.zsc.todolistproject.vo;

import edu.zsc.todolistproject.domain.Board;
import edu.zsc.todolistproject.domain.TeamType;
import edu.zsc.todolistproject.domain.User;
import lombok.Data;

import java.util.List;

@Data
public class TeamDetail {
    private Long id;
    private String title;
    private TeamType type;
    private String description;
    private List<Board> boardList;
    private List<User> userList;
    private String typeName;
    private Long typeId;
}
