package edu.zsc.todolistproject.vo.requestVo;

import lombok.Data;

@Data
public class QueryUser {
    public String username;
    public String email;
    public Long authorityId;
    public String startDate;
    public String endDate;
}
