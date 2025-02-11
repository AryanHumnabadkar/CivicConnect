import { useState } from "react";
import { Mail } from "lucide-react";
import axios from "axios";

const Newsletter = () => {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  const subscribe = async () => {
    if (!email) {
      setMessage("Please enter an email.");
      return;
    }

    try {
      const response = await axios.post(
        "http://localhost:8080/api/newsletter/subscribe",
        { email }
      );
      console.log(response.data);
      setMessage("Subscribed Successfully!");
    } catch (error) {
      console.log(error);
      setMessage("Subscription Failed.");
    }
  };

  return (
    <div className="p-8 bg-[#FBF8EF] rounded-lg shadow-lg text-center">
      <div className="flex items-center justify-center mb-4">
        <Mail className="w-8 h-8 text-[#F96E2A]" />
      </div>
      <h3 className="text-2xl font-bold text-[#4A8DAB] mb-4">
        Stay Connected with Our Newsletter
      </h3>
      <p className="text-[#78B3CE] mb-6">
        Get updates about community events and services
      </p>
      <div className="flex flex-col sm:flex-row items-center justify-center gap-3">
        <input
          type="email"
          placeholder="Enter your email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full sm:w-auto px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
        />
        <button
          onClick={subscribe}
          className="w-full sm:w-auto px-6 py-2 bg-[#F96E2A] text-white rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
        >
          Subscribe
        </button>
      </div>
      {message && (
        <p
          className={`mt-4 ${
            message.includes("Successfully")
              ? "text-green-600"
              : "text-[#F96E2A]"
          }`}
        >
          {message}
        </p>
      )}
    </div>
  );
};

export default Newsletter;
