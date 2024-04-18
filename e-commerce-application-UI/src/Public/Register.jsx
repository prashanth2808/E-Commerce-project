import React, { useState } from "react";
import { Link } from "react-router-dom";

const Register = ( ) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(null); // State for error message
  const [submittedData, setSubmittedData] = useState(null); // State to hold submitted data

  const handleNameChange = (e) => {
    let inputName = e.target.value;

    // Remove non-alphabetical characters
    inputName = inputName.replace(/[^A-Za-z\s]/g, "");

    // Capitalize first letter of each word
    inputName = inputName.replace(/\b\w/g, (char) => char.toUpperCase());

    // Check if input is empty or contains invalid characters
    if (!inputName || inputName !== e.target.value) {
      setErrorMessage(
        "Please Enter A Valid Name With Only Alphabetical Characters."
      );
    } else {
      setErrorMessage("");
    }
    setName(inputName);
  };
  const handleEmailChange = (e) => {
    const enteredEmail = e.target.value;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Regex pattern for email validation

    if (!enteredEmail.match(emailPattern)) {
      setErrorMessage("Please Enter A Valid Email Address.");
    } else {
      setErrorMessage("");
    }
    setEmail(enteredEmail);
  };
  const handlePasswordChange = (e) => {
    const enteredPassword = e.target.value;
    const passwordPattern =
      /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+])(?=.*[a-zA-Z]).{8,}$/;
    if (!enteredPassword.match(passwordPattern)) {
      setErrorMessage(
        "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and be at least 8 characters long."
      );
    } else {
      setErrorMessage("");
    }

    setPassword(enteredPassword);
  };
  const handleSignup = () => {
    // Save form data to state
    const formData = { name, email, phoneNumber, password };
    setSubmittedData(formData);

    // Reset form fields
    setName("");
    setEmail("");
    setPhoneNumber("");
    setPassword("");
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div className="w-full max-w-xl p-8 bg-white shadow-md max-h-fit hover:shadow-2xl">
        <h2 className="mb-6 text-3xl font-bold text-center ">Registration Form</h2>
        <input
          type="text"
          placeholder="Full Name"
          value={name}
          onChange={handleNameChange}
          className="w-full px-3 py-2 mb-4 border border-gray-400 rounded-md focus:outline-none focus:border-blue-500"
        />
       
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={handleEmailChange}
          className="w-full px-3 py-2 mb-4 border border-gray-400 rounded-md focus:outline-none focus:border-blue-500"
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={handlePasswordChange}
          className="w-full px-3 py-2 mb-4 border border-gray-400 rounded-md focus:outline-none focus:border-blue-500"
        />
        {errorMessage && <div className="text-xs text-red-500">{errorMessage}</div>}
        <Link to= {'/VerifyOTP'} >
        <button 
          onClick={handleSignup}
          className="w-full px-4 py-2 font-bold text-white bg-orange-600 rounded-md hover:bg-orange-700"
        
        >
          Submit
        </button   >  </Link>

        <p className="mt-4 text-center text-gray-600">
          Already have an account?{" "}
          <Link to="/login" className="font-bold text-blue-500">
            Log In
          </Link>
        </p>
      </div>
    </div>
  );
};

export default Register;
