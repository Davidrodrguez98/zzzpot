Zzzpot — Project Roadmap

Project Overview

Zzzpot is a backend-focused booking platform designed to demonstrate production-grade Java/Spring engineering concepts.

Main goals:

- Learn and demonstrate modern Java + Spring Boot development
- Build a realistic booking/reservation system
- Prevent overbooking through concurrency-safe reservation flows
- Showcase clean architecture, testing, persistence, security, and infrastructure
- Deploy a public demo suitable for CV and portfolio usage

⸻

Tech Stack

Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL
- Flyway
- Redis
- SpringDoc OpenAPI
- Maven
- Docker

Testing

- JUnit 5
- Mockito
- Testcontainers

Infrastructure

- Docker Compose
- GitHub Actions

⸻

Team Organization

Team size: 2 developers

Suggested Roles

Developer A

Focus:

- Authentication
- Security
- Infrastructure
- CI/CD
- Redis integration

Developer B

Focus:

- Booking domain
- Availability engine
- Persistence
- Concurrency logic
- Testing

Both developers should review each other’s PRs.

⸻

Development Rules

Git Workflow

Main branches

main
staging
develop

Feature branches

feature/auth-jwt
feature/property-crud
feature/booking-hold

⸻

Pull Request Rules

Every PR must:

- compile successfully
- pass tests
- include meaningful description
- include screenshots/examples if API changes
- be reviewed by the other developer

⸻

High-Level Architecture

Modular Monolith

com.dacar.zzzpot
auth/
property/
booking/
shared/
config/

Each module contains:

controller/
service/
repository/
domain/
dto/

⸻

Milestone Roadmap

⸻

Milestone 0 — Project Bootstrap

Goal:
Create stable local development environment.

Estimated time:
4–6 hours

Assigned:
Both

⸻

Features

Feature 0.1 — Initialize Spring Boot project

Tasks:

- Generate project from Spring Initializr
- Configure Maven wrapper
- Configure package structure
- Push initial repository

Deliverables:

- running application
- clean project structure

⸻

Feature 0.2 — Docker local environment

Tasks:

- Create docker-compose.yml
- Add PostgreSQL container
- Add Redis container
- Validate local startup

Deliverables:

- docker compose up works
- services accessible locally

⸻

Feature 0.3 — Environment configuration

Tasks:

- Configure application.yml
- Configure profiles
- Add actuator
- Validate DB connectivity

Deliverables:

- /actuator/health working

⸻

Milestone 1 — Database & Domain Modeling

Goal:
Create initial domain model and migrations.

Estimated time:
6–8 hours

Assigned:
Developer B lead

⸻

Features

Feature 1.1 — User entity

Fields:

- id
- email
- password
- role
- createdAt

Tasks:

- Create entity
- Create repository
- Create Flyway migration

⸻

Feature 1.2 — Property entity

Fields:

- id
- hostId
- title
- description
- maxGuests
- pricePerNight

Tasks:

- Create entity
- Create repository
- Create migration

⸻

Feature 1.3 — Booking entity

Fields:

- id
- propertyId
- guestId
- checkIn
- checkOut
- status
- expiresAt

Tasks:

- Create entity
- Create repository
- Create migration

⸻

Feature 1.4 — Flyway setup

Tasks:

- Configure migration execution
- Validate startup migrations
- Add baseline migration strategy

Deliverables:

- DB fully generated from migrations

⸻

Milestone 2 — Authentication & Security

Goal:
Implement production-style authentication flow.

Estimated time:
8–12 hours

Assigned:
Developer A lead

⸻

Features

Feature 2.1 — User registration

Endpoint:

POST /auth/register

Tasks:

- DTO validation
- password hashing
- duplicate email validation

⸻

Feature 2.2 — User login

Endpoint:

POST /auth/login

Tasks:

- authenticate credentials
- generate JWT
- return auth response

⸻

Feature 2.3 — JWT security filter

Tasks:

- implement OncePerRequestFilter
- configure SecurityContext
- secure endpoints

⸻

Feature 2.4 — Roles & authorization

Roles:

- HOST
- GUEST

Tasks:

- role-based access
- secure property creation
- secure booking actions

⸻

Feature 2.5 — Auth tests

Tasks:

- login success test
- invalid credential test
- forbidden access test

Deliverables:

- fully working JWT auth

⸻

Milestone 3 — Property Management

Goal:
Implement property CRUD and search.

Estimated time:
8–10 hours

Assigned:
Developer A + Developer B

⸻

Features

Feature 3.1 — Create property

Endpoint:

POST /properties

Requirements:

- authenticated host only
- request validation

⸻

Feature 3.2 — List properties

Endpoint:

GET /properties

Features:

- pagination
- sorting
- filtering

⸻

Feature 3.3 — Property details

Endpoint:

GET /properties/{id}

⸻

Feature 3.4 — Update property

Requirements:

- owner validation
- secure update flow

⸻

Feature 3.5 — Property search filters

Filters:

- minPrice
- maxPrice
- guests

Tasks:

- implement JPA Specifications
- optimize queries

Deliverables:

- production-style CRUD API

⸻

Milestone 4 — Availability Engine

Goal:
Determine booking availability correctly.

Estimated time:
6–8 hours

Assigned:
Developer B lead

⸻

Features

Feature 4.1 — Availability endpoint

Endpoint:

GET /availability

Parameters:

- propertyId
- checkIn
- checkOut

⸻

Feature 4.2 — Date overlap validation

Rules:

start < existingEnd
AND
end > existingStart

Tasks:

- overlap detection
- edge-case handling
- validation tests

⸻

Feature 4.3 — Availability tests

Scenarios:

- overlapping booking
- non-overlapping booking
- boundary conditions

Deliverables:

- reliable availability engine

⸻

Milestone 5 — Booking Hold System

Goal:
Implement temporary reservation holds.

Estimated time:
8–10 hours

Assigned:
Developer B lead

⸻

Features

Feature 5.1 — Create booking hold

Endpoint:

POST /bookings/hold

Flow:

1. validate availability
2. create hold
3. assign expiration timestamp

⸻

Feature 5.2 — Booking status model

Statuses:

PENDING
CONFIRMED
EXPIRED
CANCELLED

⸻

Feature 5.3 — Booking confirmation

Endpoint:

POST /bookings/{id}/confirm

⸻

Feature 5.4 — Booking tests

Scenarios:

- successful hold
- expired hold
- duplicate reservation attempt

Deliverables:

- reservation workflow operational

⸻

Milestone 6 — Anti-Overbooking Concurrency System

Goal:
Guarantee concurrency-safe reservations.

Estimated time:
10–14 hours

Assigned:
Developer B lead

⸻

Features

Feature 6.1 — Optimistic locking

Tasks:

- implement @Version
- handle concurrency exceptions
- return proper API errors

⸻

Feature 6.2 — Concurrent booking handling

Scenarios:

- multiple users booking same slot
- single winner confirmation

⸻

Feature 6.3 — Concurrency integration tests

Requirements:

- multi-thread simulation
- 50 concurrent requests
- validate single successful booking

Deliverables:

- overbooking prevention system

⸻

Milestone 7 — Expiration Scheduler

Goal:
Automatically release expired holds.

Estimated time:
4–5 hours

Assigned:
Developer A

⸻

Features

Feature 7.1 — Scheduled expiration job

Tasks:

- configure @Scheduled
- scan expired holds
- update booking status

⸻

Feature 7.2 — Expiration tests

Scenarios:

- expired bookings released
- valid bookings preserved

Deliverables:

- automated cleanup flow

⸻

Milestone 8 — Redis Reservation Locks

Goal:
Add temporary distributed locking support.

Estimated time:
6–8 hours

Assigned:
Developer A lead

⸻

Features

Feature 8.1 — Redis lock service

Tasks:

- configure RedisTemplate
- create reservation keys
- add TTL expiration

⸻

Feature 8.2 — Lock-aware booking flow

Tasks:

- prevent duplicate temporary holds
- integrate Redis with booking flow

⸻

Feature 8.3 — Redis integration tests

Deliverables:

- temporary distributed reservation lock system

⸻

Milestone 9 — Integration Testing

Goal:
Create production-like testing environment.

Estimated time:
8–10 hours

Assigned:
Both

⸻

Features

Feature 9.1 — PostgreSQL Testcontainers

Tasks:

- configure PostgreSQL container
- connect Spring test environment

⸻

Feature 9.2 — Redis Testcontainers

Tasks:

- configure Redis test container
- validate lock tests

⸻

Feature 9.3 — Full integration flows

Scenarios:

- auth + booking
- hold + confirmation
- concurrent reservations

Deliverables:

- reliable integration testing suite

⸻

Milestone 10 — Documentation & Developer Experience

Goal:
Prepare project for public portfolio usage.

Estimated time:
6–8 hours

Assigned:
Both

⸻

Features

Feature 10.1 — Swagger/OpenAPI

Tasks:

- document endpoints
- group APIs
- improve schema descriptions

⸻

Feature 10.2 — README

Sections:

- project overview
- architecture
- setup guide
- API examples
- concurrency explanation

⸻

Feature 10.3 — Architecture diagrams

Diagrams:

- request flow
- booking lifecycle
- concurrency workflow

⸻

Feature 10.4 — API examples

Provide:

- curl examples
- Postman collection

Deliverables:

- portfolio-ready documentation

⸻

Milestone 11 — Deployment

Goal:
Deploy public demo environment.

Estimated time:
4–6 hours

Assigned:
Developer A lead

⸻

Features

Feature 11.1 — Production environment

Tasks:

- deploy PostgreSQL
- deploy backend
- configure environment variables

⸻

Feature 11.2 — CI/CD

Tasks:

- configure GitHub Actions
- build pipeline
- test execution

⸻

Feature 11.3 — Public demo

Requirements:

- public API access
- Swagger UI available
- stable demo environment

Deliverables:

- deployable public backend

⸻

Definition of Done

A feature is complete only if:

- code compiles
- tests pass
- API documented
- PR reviewed
- no hardcoded secrets
- Flyway migrations included if DB changed

⸻

Suggested Timeline

Week 1

Milestones 0–1

Week 2

Milestone 2

Week 3

Milestone 3

Week 4

Milestone 4

Week 5

Milestone 5

Week 6

Milestone 6

Week 7

Milestones 7–8

Week 8

Milestones 9–11

⸻

Final Goal

By the end of the project, Zzzpot should demonstrate:

- secure Spring Boot backend development
- concurrency-safe reservation systems
- realistic database design
- Redis integration
- production-grade testing
- clean architecture
- deployable infrastructure
- professional API documentation

The project should be strong enough to be featured prominently in a professional backend engineering CV and portfolio.
