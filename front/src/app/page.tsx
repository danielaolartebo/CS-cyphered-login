"use client";

import { useState, useEffect } from "react";
import axios from "axios";

export default function Home() {
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loginUsername, setLoginUsername] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [authToken, setAuthToken] = useState("");

  useEffect(() => {
    const signUpButton = document.getElementById("signUp");
    const signInButton = document.getElementById("signIn");
    const container = document.getElementById("container");

    if (signUpButton && signInButton && container) {
      signUpButton.addEventListener("click", () => {
        container.classList.add("right-panel-active");
      });

      signInButton.addEventListener("click", () => {
        container.classList.remove("right-panel-active");
      });
    }

    return () => {
      if (signUpButton) signUpButton.removeEventListener("click", () => {});
      if (signInButton) signInButton.removeEventListener("click", () => {});
    };
  }, []);

  // Handle user registration
  const handleRegistration = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/auth/register", {
        firstname,
        lastname,
        username,
        password,
      });
      alert("Registration successful!");
      console.log(response);
    } catch (error) {
      console.error("Registration failed:", error);
      alert("Registration failed!");
    }
  };

  // Handle user login
  const handleLogin = async (event: React.FormEvent) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/auth/login", {
        username: loginUsername,
        password: loginPassword,
      });
      const token = response.data.token;
      setAuthToken(token);
      localStorage.setItem("authToken", token); 
      alert("Login successful!");
      console.log(response);
    } catch (error) {
      console.error("Login failed:", error);
      alert("Login failed!");
    }
  };

  return (
    <>
      <div className="container" id="container">
        <div className="form-container sign-up-container">
          <form onSubmit={handleRegistration}>
            <h1>Create Account</h1>
            <input
              type="text"
              placeholder="First name"
              value={firstname}
              onChange={(e) => setFirstname(e.target.value)}
              style={{ color: "black" }}
            />
            <input
              type="text"
              placeholder="Last name"
              value={lastname}
              onChange={(e) => setLastname(e.target.value)}
              style={{ color: "black" }}
            />
            <input
              type="username"
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              style={{ color: "black" }}
            />
            <input
              type="password"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              style={{ color: "black" }}
            />
            <button type="submit">Sign Up</button>
          </form>
        </div>

        <div className="form-container sign-in-container">
          <form onSubmit={handleLogin}>
            <h1>Sign in</h1>
            <input
              type="username"
              placeholder="Username"
              value={loginUsername}
              onChange={(e) => setLoginUsername(e.target.value)} 
              style={{ color: "black" }}
            />
            <input
              type="password"
              placeholder="Password"
              value={loginPassword}
              onChange={(e) => setLoginPassword(e.target.value)}
              style={{ color: "black" }}
            />
            <button type="submit">Sign In</button>
          </form>
        </div>

        <div className="overlay-container">
          <div className="overlay">
            <div className="overlay-panel overlay-left">
              <h1>Welcome Back!</h1>
              <p>To keep connected with us, please login with your personal info</p>
              <button className="ghost" id="signIn">
                Sign In
              </button>
            </div>
            <div className="overlay-panel overlay-right">
              <h1>Hello, Friend!</h1>
              <p>Enter your personal details and start your journey with us</p>
              <button className="ghost" id="signUp">
                Sign Up
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

