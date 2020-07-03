package edu.zsc.todolistproject.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;


@Data
public class Team implements Serializable{
    private Long id;
    private String title;
    private int typeId;
<<<<<<< HEAD
=======
    private TeamType teamType;
>>>>>>> e578e1f7d0678136748e331266b970340f16757e
    private String description;
    private List<Board> boardList;

}
