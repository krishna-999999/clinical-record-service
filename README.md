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


