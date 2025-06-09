package com.technibook.technibook.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "friends",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "friendId"})
    }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "acceptedAt")
    private LocalDateTime acceptedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "friendId", nullable = false)
    private User friend;

    @Column(nullable = false)
    private boolean accepted;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.acceptedAt = null;
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.accepted) {
            this.acceptedAt = LocalDateTime.now();
        }
    }
}
