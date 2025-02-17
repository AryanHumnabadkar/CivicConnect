# CivicConnect - E-Governance Platform

CivicConnect is a **web-based e-governance platform** designed to facilitate seamless interaction between **citizens and local government bodies**. It enables users to submit and track service requests, make secure payments, subscribe to government newsletters, and access real-time helpdesk support.

---

## 🚀 Features
- **Citizen Services**: Apply for permits, request trash pickup, and register for events.
- **Secure Payments**: Integrated **Razorpay API** for online transactions.
- **Real-Time Helpdesk**: Live chat support for citizen queries.
- **Newsletter Subscription**: Domain-based subscriptions via **Mailchimp API**.
- **Role-Based Access Control**: Secure authentication for Citizens, Admins, and Helpdesk users.
- **Scalable Deployment**: Hosted on **Vercel (Frontend)** and **AWS EC2 (Backend)**.

---

## 🛠️ Tech Stack
### **Frontend:**  
- React.js (UI Framework)  
- Tailwind CSS & Bootstrap (Styling)  
- Axios (API Calls)  

### **Backend:**  
- Spring Boot (REST APIs & Business Logic)  
- Hibernate & JPA (ORM)  
- JWT (Authentication & Authorization)  

### **Database:**  
- MySQL (Data Storage)  

### **Third-Party Integrations:**  
- **Razorpay API** (Payment Processing)  
- **Mailchimp API** (Newsletter Management)  
- **Live Chat API** (Helpdesk Support)  

---

## 📌 Installation & Setup
### **1. Clone the Repository**
```sh
git clone https://github.com/your-username/CivicConnect.git
cd CivicConnect
```

### **2.Backend Setup**
```sh
cd backend
mvn clean install
mvn spring-boot:run
```

### **3.Frontend Setup**
```sh
cd frontend
npm install
npm start
```

### **4. Database Configuration**
Configure MySQL in application.properties
Run database migrations (if applicable)

---

## 🔗 API Endpoints

### **Authentication**
- `POST /api/auth/register` - Register a new user  
- `POST /api/auth/login` - Authenticate user & return JWT  

### **Citizen Services**
- `POST /api/permits/apply` - Apply for a permit  
- `POST /api/trash/request` - Request trash pickup  
- `GET /api/events/list` - View available events  

### **Payments (Razorpay Integration)**
- `POST /payments/create-order` - Generate Razorpay order ID  
- `POST /payments/verify` - Verify successful payment  

### **Newsletter Subscription (Mailchimp)**
- `POST /api/newsletter/subscribe` - Subscribe to a domain-based newsletter  

---

## 📌 Deployment

### **Frontend:**  
- Deployed on **Vercel**  
- Configure environment variables in `.env`  

### **Backend:**  
- Deployed on **AWS EC2**  
- Exposed API endpoints with security  

---

## 📜 License
This project is licensed under the **MIT License**.

---

## 🤝 Contributing
We welcome contributions! To contribute:  
1. **Fork the repository**  
2. **Create a feature branch** (`git checkout -b feature-name`)  
3. **Commit changes** (`git commit -m "Added feature"`)  
4. **Push to branch** (`git push origin feature-name`)  
5. **Open a pull request**  

---

## 📞 Contact
For queries or collaboration, reach out via email: **humnabadkar.aryan@gmail.com**  
GitHub: **[AryanHumnabadkar](https://github.com/AryanHumnabadkar)**  

---


