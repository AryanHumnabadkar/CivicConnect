/* eslint-disable react/prop-types */
import { useState } from "react";
import { Calendar, Clock, FileText, Tag } from "lucide-react";
import axios from "axios";
import Dialog from "./Dailog";

const RegisterEventForm = ({ onSuccess }) => {
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    eventType: "",
    date: "",
    time: "",
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [showSuccessDialog, setShowSuccessDialog] = useState();

  const eventTypes = ["FESTIVAL", "CONSERT", "SPORTS", "OTHER"];

  const initializeRazorpay = (orderData) => {
    const options = {
      key: "rzp_test_e4OMejQFQ7EH39",
      amount: orderData.amount,
      currency: orderData.currency,
      name: "CivicConnect",
      description: `Event Registration - ${formData.name}`,
      order_id: orderData.id,
      handler: async function (response) {
        try {
          const token = localStorage.getItem("token");
          // Send payment verification request to backend
          await axios.put(
            `http://localhost:8080/api/events/${localStorage.getItem(
              "eventId"
            )}`,
            {
              razorpay_payment_id: response.razorpay_payment_id,
              razorpay_order_id: response.razorpay_order_id,
            },
            {
              headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
              },
            }
          );

          localStorage.removeItem("eventId");
          // Show success dialog
          setShowSuccessDialog(true);

          // Reset form and call onSuccess callback
          setFormData({
            name: "",
            description: "",
            eventType: "",
            date: "",
            time: "",
          });

          if (onSuccess) onSuccess();
        } catch (error) {
          console.log(error);
          setError("Payment verification failed. Please contact support.");
        }
      },
      theme: {
        color: "#F96E2A",
      },
    };

    const razorpay = new window.Razorpay(options);
    razorpay.open();
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const token = localStorage.getItem("token");
      const response = await axios.post(
        `http://localhost:8080/api/events/register/${localStorage.getItem(
          "userId"
        )}`,
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      // Initialize Razorpay with the order details
      console.log(response.data.order);
      localStorage.setItem("eventId", response.data.eventId);
      initializeRazorpay(JSON.parse(response.data.order));
    } catch (error) {
      setError(
        error.response?.data?.message ||
          "Failed to register event. Please try again."
      );
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  return (
    <>
      <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
        <h2 className="text-2xl font-bold text-[#4A8DAB] mb-6">
          Register New Event
        </h2>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-[#F96E2A] mb-2">
              <div className="flex items-center gap-2">
                <Tag className="w-4 h-4" />
                Event Name
              </div>
            </label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-[#F96E2A] mb-2">
              <div className="flex items-center gap-2">
                <FileText className="w-4 h-4" />
                Description
              </div>
            </label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] min-h-[100px]"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-[#F96E2A] mb-2">
              Event Type
            </label>
            <select
              name="eventType"
              value={formData.eventType}
              onChange={handleChange}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
              required
            >
              <option value="">Select Event Type</option>
              {eventTypes.map((type) => (
                <option key={type} value={type}>
                  {type.charAt(0) + type.slice(1).toLowerCase()}
                </option>
              ))}
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                <div className="flex items-center gap-2">
                  <Calendar className="w-4 h-4" />
                  Date
                </div>
              </label>
              <input
                type="date"
                name="date"
                value={formData.date}
                onChange={handleChange}
                min={new Date().toISOString().split("T")[0]}
                className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                <div className="flex items-center gap-2">
                  <Clock className="w-4 h-4" />
                  Time
                </div>
              </label>
              <input
                type="time"
                name="time"
                value={formData.time}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                required
              />
            </div>
          </div>

          {error && <p className="text-red-500 text-sm">{error}</p>}

          <div className="flex justify-center">
            <button
              type="submit"
              disabled={loading}
              className="px-8 py-2 bg-[#F96E2A] text-[#FBF8EF] rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
            >
              {loading ? "Registering..." : "Register Event"}
            </button>
          </div>
        </form>
      </div>

      <Dialog
        isOpen={showSuccessDialog}
        onClose={() => {
          setShowSuccessDialog(false);
          setFormData({
            name: "",
            description: "",
            eventType: "",
            date: "",
            time: "",
          });
        }}
        title="Event Registered"
        message="Your event has been successfully registered and will be approved soon."
        type="success"
        confirmText="OK"
      />
    </>
  );
};

export default RegisterEventForm;
