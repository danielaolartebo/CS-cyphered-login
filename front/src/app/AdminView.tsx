import React, { useEffect, useState } from "react";
import axios from "axios";

type AdminViewProps = {
  navigateToLogin: () => void;
};

export default function AdminView({ navigateToLogin }: AdminViewProps) {
  const [usernames, setUsernames] = useState<string[]>([]);
  const [selectedUser, setSelectedUser] = useState<string | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("authToken"); // Assuming the token is stored in localStorage
  
    axios
      .get("http://localhost:8080/user/usernames", {
        headers: {
          Authorization: `Bearer ${token}`, // Attach token here
        },
      })
      .then((response) => setUsernames(response.data))
      .catch((error) => console.error("Error fetching usernames:", error));
  }, []);

  const deleteUser = () => {
    const token = localStorage.getItem("authToken"); // Assuming the token is stored in localStorage

    if (!selectedUser) {
      alert("Please select a user to delete.");
      return;
    }
    axios
      .delete(`http://localhost:8080/user/${selectedUser}`, {
        headers: {
          Authorization: `Bearer ${token}`, // Attach token here
        },
      })
      .then(() => {
        const username = localStorage.getItem("username");
        if(username===selectedUser){
          alert("Cannot delete admin user");
        }else{
          alert("User deleted successfully.");
          setUsernames(usernames.filter((username) => username !== selectedUser));
          setSelectedUser(null);
        }
      })
      .catch((error) => console.error("Error deleting user:", error));
  };

  const clearPassword = () => {
    const token = localStorage.getItem("authToken"); // Assuming the token is stored in localStorage
    if (!selectedUser) {
      alert("Please select a user to clear their password.");
      return;
    }
    axios
      .put(`http://localhost:8080/user/${selectedUser}/clear-password`,{}, {
        headers: {
          Authorization: `Bearer ${token}`, // Attach token here
        },
      })
      .then(() => {
        const username = localStorage.getItem("username");
        if(username===selectedUser){
          alert("Cannot clear admin user password");
        }else{
          alert("Password cleared succesfully");
        }
      })
      .catch((error) => console.error("Error clearing password:", error));
  };

  return (
    <div>
      <h1>Admin Dashboard</h1>
      <div>
        <h3 style={{
                fontWeight:"bold" ,
                color: "black",
                margin: "15px 0"
              }}>Usernames:</h3>
        <ul>
          {usernames.map((username) => (
            <li
              key={username}
              onClick={() => setSelectedUser(username)}
              style={{
                cursor: "pointer",
                fontWeight: selectedUser === username ? "bold" : "normal",
                color: "black",
                margin: "15px 0"
              }}
            >
              {username}
            </li>
          ))}
        </ul>
      </div>
      <button onClick={deleteUser}>Delete User</button>
      <button onClick={clearPassword}>Clear Password</button>
      <button onClick={navigateToLogin}>Logout</button>
    </div>
  );
}
