// src/components/admin/UserManagement.jsx
import { useState, useEffect } from "react";
import { Users, UserCog, MapPin } from "lucide-react";
import axios from "axios";
import Dialog from "./Dailog";

const UserManagement = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [deleteDialog, setDeleteDialog] = useState({
    isOpen: false,
    userId: null,
    userName: "",
  });

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    try {
      const token = localStorage.getItem("token");
      const response = await axios.get(
        "http://localhost:8080/api/admin/users",
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setUsers(response.data);
      setLoading(false);
    } catch (error) {
      console.log(error);
      setError("Failed to fetch users");
      setLoading(false);
    }
  };

  const initiateDelete = (userId, userName) => {
    setDeleteDialog({
      isOpen: true,
      userId,
      userName,
    });
  };

  const handleDeleteUser = async () => {
    try {
      const token = localStorage.getItem("token");
      await axios.delete(
        `http://localhost:8080/api/admin/users/${deleteDialog.userId}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );
      setDeleteDialog({ isOpen: false, userId: null, userName: "" });
      fetchUsers();
    } catch (error) {
      console.log(error);
      setError("Failed to delete user");
    }
  };

  const renderUserCard = (user) => (
    <div
      key={user.id}
      className="bg-white p-4 rounded-lg shadow flex justify-between items-start"
    >
      <div className="space-y-2">
        <h3 className="font-semibold text-[#4A8DAB]">{user.name}</h3>
        <p className="text-sm text-[#78B3CE]">{user.email}</p>
        <div className="flex items-center gap-2 text-sm">
          <MapPin className="w-4 h-4 text-[#F96E2A]" />
          {user.address ? (
            <div className="text-gray-600">
              <p>Sector: {user.address.sector.sectorName}</p>
              <p>
                {user.address.city}, {user.address.state}
              </p>
            </div>
          ) : (
            <span className="text-orange-500">Address not registered</span>
          )}
        </div>
      </div>
      <button
        onClick={() => initiateDelete(user.id, user.name)}
        className="px-4 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600"
      >
        Remove citizen
      </button>
    </div>
  );

  const adminUsers = users.filter((user) => user.role === "ROLE_ADMIN");
  const citizenUsers = users.filter((user) => user.role === "ROLE_CITIZEN");

  return (
    <div className="space-y-8">
      <h2 className="text-2xl font-bold text-[#4A8DAB]">User Management</h2>

      {loading ? (
        <div>Loading...</div>
      ) : error ? (
        <div className="text-red-500">{error}</div>
      ) : (
        <>
          {/* Admins Section */}
          <div className="space-y-4 mx-40">
            <div className="flex items-center gap-2 text-xl font-semibold text-[#F96E2A]">
              <UserCog className="w-6 h-6" />
              <h3>Administrators</h3>
            </div>
            <div className="grid gap-4">
              {adminUsers.length === 0 ? (
                <p className="text-gray-500">No administrators found</p>
              ) : (
                adminUsers.map(renderUserCard)
              )}
            </div>
          </div>

          {/* Citizens Section */}
          <div className="space-y-4 mx-40">
            <div className="flex items-center gap-2 text-xl font-semibold text-[#F96E2A]">
              <Users className="w-6 h-6" />
              <h3>Citizens</h3>
            </div>
            <div className="grid gap-4">
              {citizenUsers.length === 0 ? (
                <p className="text-gray-500">No citizens found</p>
              ) : (
                citizenUsers.map(renderUserCard)
              )}
            </div>
          </div>
        </>
      )}

      <Dialog
        isOpen={deleteDialog.isOpen}
        onClose={() =>
          setDeleteDialog({ isOpen: false, userId: null, userName: "" })
        }
        onConfirm={handleDeleteUser}
        title="Confirm Delete"
        message={`Are you sure you want to delete ${deleteDialog.userName}? This action cannot be undone.`}
        type="confirm"
        confirmText="Delete"
      />
    </div>
  );
};

export default UserManagement;
