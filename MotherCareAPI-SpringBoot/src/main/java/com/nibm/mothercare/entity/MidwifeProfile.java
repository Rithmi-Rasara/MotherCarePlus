package com.nibm.mothercare.entity;

import jakarta.persistence.*;

/**
 * JPA Entity mapping to the `midwife_profile` table.
 *
 * Used by get_midwife_dashboard.php to resolve user_id → midwife_id:
 *   SELECT midwife_id FROM midwife_profile WHERE user_id = ?
 */
@Entity
@Table(name = "midwife_profile")
public class MidwifeProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "midwife_id")
    private Integer midwifeId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    // ─── Getters ───────────────────────────────────────────────────────────────

    public Integer getMidwifeId() { return midwifeId; }
    public Integer getUserId()    { return userId; }
}
