/* eslint-disable no-unused-vars */
import { ScrollText } from "lucide-react";
import { useState } from "react";
import axios from "axios";
import ConfirmationDialog from "./ConfirmationDialog";

const TrashRequestForm = () => {
  const [description, setDescription] = useState("");
  const [isDialogOpen, setIsDialogOpen] = useState(false);
  const [error, setError] = useState("");

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
        console.log("Request submitted successfully:", response.data);
        setDescription("");
        setIsDialogOpen(false);
        setError("");
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
          <h2 className="text-xl text-[#4A8DAB] flex items-center gap-2">
            <ScrollText className="text-[#F96E2A]" />
            New Trash Request
          </h2>
        </div>
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-[#F96E2A] mb-2">
              Description
            </label>
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="w-full p-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent min-h-[120px]"
              placeholder="Please describe your trash pickup request..."
              required
            />
          </div>
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

      {isDialogOpen && (
        <ConfirmationDialog
          onClose={() => setIsDialogOpen(false)}
          onConfirm={confirmSubmit}
        />
      )}
    </>
  );
};

export default TrashRequestForm;
