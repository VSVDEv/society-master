package com.example.society.controller;

import com.example.society.domain.Message;
import com.example.society.domain.Views;
import com.example.society.exception.NotFoundException;
import com.example.society.repository.MessageRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;
    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
    return messageRepository.findAll();
}


@GetMapping("{id}")
@JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
}

    

    @PostMapping
    public Message create(@RequestBody Message message){
message.setCreateDate(LocalDateTime.now());
        return messageRepository.save(message);
}
    @PutMapping("{id}")
    public Message updateOne(@PathVariable("id") Message messageFromDb, @RequestBody Message message){
        BeanUtils.copyProperties(message,messageFromDb,"id");

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteOne(@PathVariable("id") Message message ) {

        messageRepository.delete(message);
    }
    }
