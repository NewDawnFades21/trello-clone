package edu.zsc.todolistproject.domain.dto;

import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

public class Mail extends SimpleMailMessage
{
    private Map<String, Object> props;

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
