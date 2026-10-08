# Clinical Record Service API

## Project Overview

The **Clinical Record Service API** is a backend REST API developed using **Java and Spring Boot** for the **UK Healthcare Patient Management Platform**.

This service manages patient clinical records such as diagnoses, treatments, prescriptions, laboratory results, consultation details, and clinical notes.

The service provides REST APIs for creating, retrieving, updating, and archiving clinical records. It also includes request validation, role-based access checks, and audit logging for sensitive clinical record operations.

---

## Project Information

| Item | Details |
|---|---|
| Project | UK Healthcare Patient Management Platform |
| Task | T-003 – Clinical Record Service API |
| Module | Clinical Record Management |
| Role | Java Full Stack / Backend Development |
| Backend | Java + Spring Boot |
| Database | MySQL |
| API Type | REST API |
| Build Tool | Maven |
| Java Version | 17 |
| Server Port | 8081 |

---

# Features

## 1. Clinical Record Creation

The API allows authorized users to create clinical records.

A clinical record contains information such as:

- Patient ID
- Provider ID
- Record type
- Description
- Diagnosis
- Treatment
- Clinical notes

---

## 2. View Clinical Records

The service provides APIs to:

- Get all clinical records
- Get a clinical record by ID
- Get clinical records for a specific patient

---

## 3. Update Clinical Records

Authorized clinical users can update active clinical records.

Archived records cannot be modified.

---

## 4. Archive Clinical Records

Clinical records are not physically deleted from the database.

Instead, the DELETE operation changes the record status to:

```text
ARCHIVED

5. Request Validation

The API validates incoming requests using Spring Boot Bean Validation.

Examples:

Patient ID is required
Provider ID is required
Record type is required
Description is required

Invalid requests return:

400 BAD REQUEST
6. Role-Based Access Control

The development implementation supports basic role checks.

Read access

The following roles can access clinical records:

ADMIN
CLINICIAN
NURSE
Write access

The following roles can create, update, or archive records:

ADMIN
CLINICIAN

Unauthorized users receive:

403 FORBIDDEN

Note: The current implementation uses request headers for development/testing. In a production environment, these roles should come from an approved authentication mechanism such as JWT-based authentication.

7. Audit Logging

Sensitive operations are recorded in the audit_logs table.

The following operations are logged:

CREATE
UPDATE
ARCHIVE

Each audit record stores information such as:

Action
Entity name
Entity ID
User ID
Timestamp

Example:

CREATE | ClinicalRecord | 1 | 101 | 2026-10-08 10:00:00
UPDATE | ClinicalRecord | 1 | 101 | 2026-10-08 10:10:00
ARCHIVE | ClinicalRecord | 1 | 101 | 2026-10-08 10:20:00
