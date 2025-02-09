import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { User, Mail, MapPin } from "lucide-react";
import NavBar from "../components/NavBar";
import Dialog from "../components/Dailog";

export const UpdateProfile = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [includeAddress, setIncludeAddress] = useState(false);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    address: {
      street: "",
      city: "",
      state: "",
      sector: "",
    },
  });
  const [sectors, setSectors] = useState([]);
  const [showSuccessDialog, setShowSuccessDialog] = useState(false);

  useEffect(() => {
    const fetchInitialData = async () => {
      try {
        const token = localStorage.getItem("token");
        const userId = localStorage.getItem("userId");

        // Fetch user data
        const userResponse = await axios.get(
          `http://localhost:8080/api/citizen/profile/${userId}`,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        // Fetch sectors
        const sectorsResponse = await axios.get(
          "http://localhost:8080/api/citizen/sectors",
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );
        console.log(sectorsResponse);

        setFormData({
          name: userResponse.data.name || "",
          email: userResponse.data.email || "",
          address: userResponse.data.address || {
            street: "",
            city: "",
            state: "",
            sector: "",
          },
        });
        setIncludeAddress(!!userResponse.data.address);
        setSectors(sectorsResponse.data.map((sector) => sector.sectorName));
      } catch (error) {
        setError("Failed to fetch data");
        console.error("Error:", error);
      }
    };

    fetchInitialData();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const userId = localStorage.getItem("userId");
      const token = localStorage.getItem("token");

      const updateData = {
        name: formData.name,
        email: formData.email,
        address: includeAddress ? formData.address : null,
      };

      const response = await axios.put(
        `http://localhost:8080/api/citizen/profile/${userId}`,
        updateData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        setShowSuccessDialog(true); // Show dialog first
      }
    } catch (error) {
      setError(error.response?.data?.message || "Failed to update profile");
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.includes(".")) {
      const [parent, child] = name.split(".");
      setFormData((prev) => ({
        ...prev,
        [parent]: {
          ...prev[parent],
          [child]: value,
        },
      }));
    } else {
      setFormData((prev) => ({
        ...prev,
        [name]: value,
      }));
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <NavBar />
      <div className="max-w-2xl mx-auto px-4 py-8">
        <div className="bg-[#FBF8EF] rounded-lg shadow-lg p-6">
          <h2 className="text-2xl font-bold text-[#4A8DAB] mb-6">
            Update Profile
          </h2>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                <div className="flex items-center gap-2">
                  <User className="w-4 h-4" />
                  Name
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
                  <Mail className="w-4 h-4" />
                  Email
                </div>
              </label>
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                required
              />
            </div>

            <div className="border-t border-[#78B3CE] pt-4">
              <div className="flex items-center mb-4">
                <input
                  type="checkbox"
                  id="includeAddress"
                  checked={includeAddress}
                  onChange={(e) => setIncludeAddress(e.target.checked)}
                  className="mr-2"
                />
                <label
                  htmlFor="includeAddress"
                  className="text-[#4A8DAB] font-medium"
                >
                  Update Address Information
                </label>
              </div>

              {includeAddress && (
                <div className="space-y-4">
                  <div>
                    <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                      <div className="flex items-center gap-2">
                        <MapPin className="w-4 h-4" />
                        Street
                      </div>
                    </label>
                    <input
                      type="text"
                      name="address.street"
                      value={formData.address.street}
                      onChange={handleChange}
                      className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                      required={includeAddress}
                    />
                  </div>

                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                        City
                      </label>
                      <input
                        type="text"
                        name="address.city"
                        value={formData.address.city}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                        required={includeAddress}
                      />
                    </div>

                    <div>
                      <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                        State
                      </label>
                      <input
                        type="text"
                        name="address.state"
                        value={formData.address.state}
                        onChange={handleChange}
                        className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                        required={includeAddress}
                      />
                    </div>
                  </div>

                  <div>
                    <label className="block text-sm font-medium text-[#F96E2A] mb-2">
                      Sector
                    </label>
                    <select
                      name="address.sector"
                      value={formData.address.sector}
                      onChange={handleChange}
                      className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB]"
                      required={includeAddress}
                    >
                      <option value="">Select Sector</option>
                      {sectors.map((sectorName) => (
                        <option key={sectorName} value={sectorName}>
                          {sectorName.replace("SECTOR", "Sector ")}
                        </option>
                      ))}
                    </select>
                  </div>
                </div>
              )}
            </div>

            {error && <p className="text-red-500 text-sm">{error}</p>}

            <div className="flex justify-center pt-4">
              <button
                type="submit"
                disabled={loading}
                className="px-8 py-2 bg-[#F96E2A] text-[#FBF8EF] rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
              >
                {loading ? "Updating..." : "Update Profile"}
              </button>
            </div>
          </form>
        </div>
      </div>
      <Dialog
        isOpen={showSuccessDialog}
        onClose={() => {
          setShowSuccessDialog(false);
          navigate("/user");
        }}
        title="Profile Updated"
        message="Your profile has been successfully updated!"
        type="success"
        confirmText="OK"
      />
    </div>
  );
};

export default UpdateProfile;
