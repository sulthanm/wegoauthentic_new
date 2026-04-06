\# 📘 Project README – Travel Packages \& Booking System



\## 📌 Overview



This project is a \*\*Travel Packages \& Booking Platform\*\* that allows users to explore packages, view testimonials, browse galleries, and request/customize trips. It also provides admin capabilities to manage trips, gallery, and invoices.



\---



\# 🚀 Features



\## 🔍 Read Operations



\* Get all packages

\* Get all testimonials (mapped with selected package)

\* Get testimonials by package ID

\* Get gallery based on:



&#x20; \* Event type (nature, festivals, groups, adventures)

&#x20; \* Location (e.g., Bagdogra, Thimphu)

\* Search packages by location

\* Get trending destinations

\* Get FAQs

\* Get user trips (based on activation time if added by admin)



\---



\## ✍️ Write Operations



\### 👤 User Perspective



\* Submit feedback (login optional)



&#x20; \* Optional photo upload → stored in gallery

\* Contact form → sends email to owner

\* Customize trip request → stored in `trip\_bookings` with status



\---



\### 🛠️ Admin Perspective



\* Add trips

\* Update trip status

\* Update gallery

\* Generate invoices



\---



\## ❌ Out of Scope



\* Trending destination activities

\* Showing base package for searched location



\---



\# 🌐 REST API Design



\## 📖 Public APIs



```

GET    /api/v1/get-packages

GET    /api/v1/get-package/{packageId}

GET    /api/v1/get-itinerary/{itineraryId}

GET    /api/v1/get-inclusions

GET    /api/v1/get-exclusions

GET    /api/v1/get-testimonal

GET    /api/v1/get-gallery

GET    /api/v1/search/{search-name}

GET    /api/v1/get-faqs

POST   /api/v1/add-testimonal

POST   /api/v1/sign-up

POST   /api/v1/sign-in

```



\---



\## 🔐 Authenticated APIs



```

POST   /api/v1/add-package

POST   /api/v1/request-trip-book

GET    /api/v1/get-all-trips

PUT    /api/v1/cancel-trip/{tripId}

PUT    /api/v1/update-trip/{tripId}

POST   /api/v1/add-image

```



\---



\## ⚙️ Enhancements (Future Scope)



```

PUT    /api/v1/update-package/{packageId}

DELETE /api/v1/delete-package/{packageId}

PUT    /api/v1/update-itinerary/{itineraryId}

DELETE /api/v1/delete-itinerary/{itineraryId}

```



\---



\# 🗄️ Database Design



\## 👤 Users



\* id (PK)

\* name

\* phone

\* trips \[]

\* historyAcknowledgment

\* role

\* activatedAt

\* createdAt

\* lastUpdated



\---



\## 📦 Packages



\* id (PK)

\* name

\* type (group/private)

\* title

\* price

\* description

\* overview

\* itinerary\_id (FK)

\* inclusion\_set\_id (FK)

\* exclusion\_set\_id (FK)

\* created\_at

\* updated\_at



\---



\## 🗺️ Itineraries



\* id (PK)

\* name

\* days\_info\_json

\* created\_at

\* updated\_at



\---



\## ✅ Inclusion Sets



\* id

\* name

\* items\_json

\* created\_at

\* updated\_at



\---



\## ❌ Exclusion Sets



\* id

\* name

\* items\_json

\* created\_at

\* updated\_at



\---



\## ⭐ Testimonials



\* id

\* user\_id

\* name

\* rating

\* package\_id

\* phone (mandatory)

\* email (optional)

\* review\_comment

\* created\_at

\* lastUpdated



\---



\## ❓ FAQs



\* id

\* question

\* answer

\* is\_needed

\* created\_at

\* last\_updated



\---



\## 🧳 Trip Bookings



\* id (PK)

\* user\_id

\* package\_id

\* package\_name (snapshot)

\* price (snapshot)

\* start\_date

\* end\_date

\* status

\* payment\_status

\* created\_by

\* created\_by\_id

\* created\_at

\* updated\_at



\### 📊 Status Enums



\*\*TripStatus\*\*



\* Requested

\* Doubtful

\* Accepted

\* Completed

\* Cancelled



\*\*PaymentStatus\*\*



\* Pending

\* Completed

\* Failed

\* Refunded



\---



\## 🖼️ Gallery



\* id

\* s3\_url

\* location

\* tags \[]

\* created\_at

\* last\_updated



\---



\# 🧠 Key Design Decisions



\* \*\*Snapshot fields\*\* in bookings prevent data inconsistency

\* \*\*JSON-based itinerary \& inclusion/exclusion\*\* for flexibility

\* \*\*Optional authentication for feedback\*\* improves UX

\* \*\*Gallery tagging system\*\* enables flexible filtering



\---



\# 🎯 Future Improvements



\* Role-based access control (RBAC)

\* Advanced search \& filtering

\* Payment gateway integration

\* Notification system (email/WhatsApp automation)

\* Analytics dashboard



\---



\# 📌 Notes



\* Use \*\*branch protection + PR approvals\*\* for safe deployments

\* Prefer \*\*modular service design\*\* for scalability

\* Ensure \*\*validation on all APIs\*\*



\---



\# 👨‍💻 Contributors



\* Internal Team



\---



\*\*End of Document\*\*



