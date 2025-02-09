/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import { ScrollText } from "lucide-react";
import { useState, useEffect } from "react";
import axios from "axios";
import Dialog from "./Dailog";

const TrashRequestForm = ({ onSuccess }) => {
  const [description, setDescription] = useState("");
  const [isRegularRequest, setIsRegularRequest] = useState(false);
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [error, setError] = useState("");
  const [showSuccessDialog, setShowSuccessDialog] = useState(false);

  useEffect(() => {
    if (isRegularRequest) {
      setDescription("Regular pickup");
    } else {
      setDescription("");
    }
  }, [isRegularRequest]);

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsDialogOpen(true);
  };

  const confirmSubmit = async () => {
    try {
      const userId = localStorage.getItem("userId");
      const token = localStorage.getItem("token");

      const response = await axios.post(
        "http://localhost:8080/api/requests",
        {
          description,
          userId: parseInt(userId),
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200 || response.status === 201) {
        setDescription("");
        setIsDialogOpen(false);
        setShowSuccessDialog(true);
        setError("");
        onSuccess(); // Call the callback to refresh the list
      }
    } catch (error) {
      setError(error.response?.data?.message || "Error submitting request");
      console.error("Error submitting request:", error);
    } finally {
      setIsDialogOpen(false);
    }
  };

  return (
    <>
      <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
        <div className="mb-6">
          <h2 className="text-xl text-[#4A8DAB] flex items-center gap-2 font-bold">
            <ScrollText className="text-[#F96E2A]" />
            New Trash-Pickup Request
          </h2>
        </div>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="mb-4">
            <label className="block text-sm font-medium text-[#F96E2A] mb-2">
              Request Type
            </label>
            <div className="flex items-center gap-4">
              <label className="inline-flex items-center">
                <input
                  type="radio"
                  className="form-radio text-[#F96E2A] focus:ring-[#4A8DAB]"
                  name="requestType"
                  checked={isRegularRequest}
                  onChange={() => setIsRegularRequest(true)}
                />
                <span className="ml-2 text-[#4A8DAB]">Regular Pickup</span>
              </label>
              <label className="inline-flex items-center">
                <input
                  type="radio"
                  className="form-radio text-[#F96E2A] focus:ring-[#4A8DAB]"
                  name="requestType"
                  checked={!isRegularRequest}
                  onChange={() => setIsRegularRequest(false)}
                />
                <span className="ml-2 text-[#4A8DAB]">
                  Occasional or Extra Request
                </span>
              </label>
            </div>
          </div>

          {!isRegularRequest && (
            <div>
              <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                Description
              </label>
              <textarea
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                className="w-full p-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent min-h-[120px]"
                placeholder="Is this something unusual or occasional? Tell us more..."
                required
              />
            </div>
          )}

          <div className="flex justify-center">
            <button
              type="submit"
              className="px-8 bg-[#F96E2A] text-[#FBF8EF] py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
            >
              Submit Request
            </button>
          </div>
        </form>
      </div>

      <Dialog
        isOpen={isDialogOpen}
        onClose={() => setIsDialogOpen(false)}
        onConfirm={confirmSubmit}
        title="Confirm Request"
        message="Are you sure you want to submit this trash pickup request?"
        type="confirm"
      />

      <Dialog
        isOpen={showSuccessDialog}
        onClose={() => setShowSuccessDialog(false)}
        title="Request Submitted"
        message="Your trash pickup request has been successfully submitted!"
        type="success"
        confirmText="OK"
      />
    </>
  );
};

export default TrashRequestForm;
