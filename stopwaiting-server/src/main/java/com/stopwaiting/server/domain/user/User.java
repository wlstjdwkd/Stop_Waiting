package com.stopwaiting.server.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.stopwaiting.server.domain.BaseTimeEntity;
import com.stopwaiting.server.domain.userqueue.UserQueue;
import com.stopwaiting.server.domain.waitingQueue.WaitingQueue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseTimeEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column
    private String token;

    @Column(nullable = false)
    private Integer reported;

//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;

    @JsonIgnore
    //@JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserQueue> userQueues = new ArrayList<>();

    @Builder
    public User(Long id, String name, String password, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void update(String phoneNumber, Integer reported) {
        this.phoneNumber = phoneNumber;
        this.reported = reported;
    }

    public void updateToken(String token) {
        this.token = token;
    }

    public void addReport() {
        this.reported += 1;
    }

    public void addUserQueue(UserQueue userQueue) {
        this.getUserQueues().add(userQueue);
        userQueue.updateUser(this);
    }

    @PrePersist
    public void prePersist() {
        this.reported = this.reported == null ? 0 : this.reported;
        //this.role = this.role == null ? Role.USER : this.role;
    }
}
