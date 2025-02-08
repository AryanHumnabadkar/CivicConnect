import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [role, setRole] = useState("CITIZEN");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setIsLoading(true);
    setError("");

    try {
      const response = await axios.post(
        `http://localhost:8080/api/auth/register`,
        {
          name,
          email,
          password,
          confirmPassword,
          role,
        }
      );

      if (response.status === 200) {
        navigate("/login");
      }
    } catch (error) {
      setError(
        error.response?.data?.message ||
          "Registration failed. Please try again."
      );
      console.error("Registration failed:", error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-r from-[#4A8DAB] to-[#78B3CE]">
      <div className="bg-[#FBF8EF] p-8 rounded-lg shadow-2xl transform transition-all hover:scale-105 w-full max-w-md">
        <h1 className="text-3xl font-bold text-[#4A8DAB] text-center mb-6">
          Create Account
        </h1>

        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-[#F96E2A] mb-2">
              Role
            </label>
            <div className="flex justify-center space-x-6">
              <label className="inline-flex items-center">
                <input
                  type="radio"
                  className="form-radio text-[#4A8DAB]"
                  value="CITIZEN"
                  checked={role === "CITIZEN"}
                  onChange={(e) => setRole(e.target.value)}
                />
                <span className="ml-2 text-[#4A8DAB]">Citizen</span>
              </label>
              <label className="inline-flex items-center">
                <input
                  type="radio"
                  className="form-radio text-[#4A8DAB]"
                  value="ADMIN"
                  checked={role === "ADMIN"}
                  onChange={(e) => setRole(e.target.value)}
                />
                <span className="ml-2 text-[#4A8DAB]">Admin</span>
              </label>
            </div>
          </div>

          <div>
            <label
              htmlFor="name"
              className="block text-sm font-medium text-[#F96E2A] mb-2"
            >
              Name
            </label>
            <input
              type="text"
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
              placeholder="Enter your name"
              required
            />
          </div>

          <div>
            <label
              htmlFor="email"
              className="block text-sm font-medium text-[#F96E2A] mb-2"
            >
              Email
            </label>
            <input
              type="email"
              id="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
              placeholder="Enter your email"
              required
            />
          </div>

          <div>
            <label
              htmlFor="password"
              className="block text-sm font-medium text-[#F96E2A] mb-2"
            >
              Password
            </label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
              placeholder="Enter your password"
              required
            />
          </div>

          <div>
            <label
              htmlFor="confirmPassword"
              className="block text-sm font-medium text-[#F96E2A] mb-2"
            >
              Confirm Password
            </label>
            <input
              type="password"
              id="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              className="w-full px-4 py-2 border border-[#78B3CE] rounded-lg focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:border-transparent"
              placeholder="Confirm your password"
              required
            />
          </div>

          {error && <p className="text-red-500 text-sm text-center">{error}</p>}

          <div className="flex justify-center">
            <button
              type="submit"
              disabled={isLoading}
              className="px-8 bg-[#F96E2A] text-[#FBF8EF] py-2 rounded-lg hover:bg-opacity-90 transition-all duration-300 focus:outline-none focus:ring-2 focus:ring-[#4A8DAB] focus:ring-offset-2"
            >
              {isLoading ? "Creating Account..." : "Sign Up"}
            </button>
          </div>
        </form>

        <p className="text-center text-[#4A8DAB] mt-6">
          Already have an account?{" "}
          <a href="/signin" className="text-[#F96E2A] hover:underline">
            Sign in
          </a>
        </p>
      </div>
    </div>
  );
};

export default Signup;
