import { useState, useEffect } from "react";
import axios from "axios";

type UserViewProps = {
  navigateToLogin: () => void;
};

export default function UserView({ navigateToLogin }: UserViewProps) {
  const [lastLogin, setLastLogin] = useState<string | null>(null);
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  useEffect(() => {
    const fetchLastLogin = async () => {
      try {
        const response = await axios.get("http://localhost:8080/user/last-login", {
          headers: {
            "Authorization": `Bearer ${localStorage.getItem("authToken")}`,
          },
        });
        setLastLogin(response.data.lastLogin);
      } catch (error) {
        console.error("Error fetching last login:", error);
      }
    };
  
    fetchLastLogin();
  }, []);
  

  const handleChangePassword = async (e: React.FormEvent) => {
    e.preventDefault();
  
    if (newPassword !== confirmPassword) {
      alert("New password and confirmation do not match!");
      return;
    }
  
    try {
      const response = await axios.put(
        "http://localhost:8080/user/update-password",
        {
          username: "currentUsername", // This should be dynamic
          currentPassword,
          newPassword,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
  
      // Use the response here, for example, display a success message
      console.log("Password changed:", response);
      alert("Password changed successfully!");
    } catch (error) {
      console.error("Error updating password:", error);
      alert("Failed to change password. Please try again.");
    }
  };
  
  return (
    <div className="user-dashboard">
      <div className="top-bar">
        <h1 className="page-title">User Dashboard</h1>
        <button onClick={navigateToLogin} className="logout-button">Logout</button>
      </div>

      {/* Last login time */}
      <div className="last-login">
        <label className="last-login-label">Last Login:</label>
        <span className="last-login-time">{lastLogin ? lastLogin : "Loading..."}</span>
      </div>

      <hr className="separator" />

      {/* Change password form */}
      <div className="change-password-section">
        <h2 className="form-title">Change Password</h2>
        <form onSubmit={handleChangePassword} className="change-password-form">
          <div className="form-group">
            <label htmlFor="currentPassword" className="input-label">Current Password:</label>
            <input
              id="currentPassword"
              type="password"
              value={currentPassword}
              onChange={(e) => setCurrentPassword(e.target.value)}
              required
              className="input-field"
            />
          </div>
          <div className="form-group">
            <label htmlFor="newPassword" className="input-label">New Password:</label>
            <input
              id="newPassword"
              type="password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              required
              className="input-field"
            />
          </div>
          <div className="form-group">
            <label htmlFor="confirmPassword" className="input-label">Confirm New Password:</label>
            <input
              id="confirmPassword"
              type="password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
              className="input-field"
            />
          </div>
          <button type="submit" className="submit-button">Change Password</button>
        </form>
      </div>

      <hr className="separator" />
    </div>
  );
}

