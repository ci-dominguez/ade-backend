package com.ci_dominguez.ade_backend.model;

import com.ci_dominguez.ade_backend.model.enums.VisitStatus;
import com.ci_dominguez.ade_backend.model.enums.VisitType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name="visits")
public class Visits {

    /////////////////////Instance Variables/////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "homeId", referencedColumnName = "id")
    private Home home;

    @NotBlank(message = "Full name is required")
    @Size(max = 100, message = "Full name must be less than 100 characters")
    private String visitorName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String visitorEmail;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String visitorPhoneNumber;

    @NotBlank(message = "Message is required")
    @Size(max = 1000, message = "Message must be less than 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String visitorComment;

    private LocalDateTime requestedDate;

    @Column(nullable = true)
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    private VisitStatus status;

    //Change this to a relation to user entity once made instead of string
    @Column(nullable = true)
    private String agentId;

    @Enumerated(EnumType.STRING)
    private VisitType type;

    @Column(nullable = true)
    private String agentNote;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /////////////////////Methods/////////////////////
    /**
     * Invoked before persisting a new entity
     * Initializes entity metadata:
     *  - 'createdAt' to the current date and time
     *  - 'updatedAt' to the current date and time
     */
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * Invoked before updating existing entity
     * Updates entity metadata:
     *  - 'updatedAt' to the current date and time
     */
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    /////////////////////Getters & Setters/////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorEmail() {
        return visitorEmail;
    }

    public void setVisitorEmail(String visitorEmail) {
        this.visitorEmail = visitorEmail;
    }

    public String getVisitorPhoneNumber() {
        return visitorPhoneNumber;
    }

    public void setVisitorPhoneNumber(String visitorPhoneNumber) {
        this.visitorPhoneNumber = visitorPhoneNumber;
    }

    public String getVisitorComment() {
        return visitorComment;
    }

    public void setVisitorComment(String visitorComment) {
        this.visitorComment = visitorComment;
    }

    public LocalDateTime getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public VisitType getType() {
        return type;
    }

    public void setType(VisitType type) {
        this.type = type;
    }

    public String getAgentNote() {
        return agentNote;
    }

    public void setAgentNote(String agentNote) {
        this.agentNote = agentNote;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
