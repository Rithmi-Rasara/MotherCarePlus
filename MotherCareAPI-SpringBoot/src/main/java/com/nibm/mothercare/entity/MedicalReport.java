package com.nibm.mothercare.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "medical_report")
public class MedicalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportId;

    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    @Column(name = "mother_id", nullable = false)
    private Integer motherId;

    @Column(name = "report_title")
    private String reportTitle;

    @Column(name = "report_file")
    private String reportFile;

    @Column(name = "upload_date")
    private String uploadDate;

    public Integer getReportId() {
        return reportId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public Integer getMotherId() {
        return motherId;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public String getReportFile() {
        return reportFile;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public void setMotherId(Integer motherId) {
        this.motherId = motherId;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public void setReportFile(String reportFile) {
        this.reportFile = reportFile;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}